package com.fish.lib.iflytek.thread;

import java.util.PriorityQueue;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.SparseArray;

/**
 * 临时任务的执行器
 * 
 * @author kuncheng 2014-1-8
 */
final class TempTaskExecutor {

	/**
	 * 线程默认的空闲时间，超过这个时间则杀掉线程
	 */
	private static final int DEFAULT_IDLE_TIME = 5 * 60 * 1000;

	/**
	 * 默认最大的线程数
	 */
	private static final int DEFAULT_MAX_COUNT = 3;

	private long mIdleTime;
	private int mMaxCount;

	/**
	 * 创建过的线程总数
	 */
	private int mConstructThreadCount;

	private SparseArray<ExecuteHandler> mTempHandlers;

	/**
	 * 采用优先队列
	 */
	private PriorityQueue<WorkTask> mTempTasks;

	private static class ExecuteHandler extends Handler {

		private static final int HAS_TASK = 0;
		private static final int GOTO_DIE = 1;

		private TempTaskExecutor mExecutor;
		private long mIdleTime;
		private int mId;

		/**
		 * 是否正在运行任务
		 */
		private volatile boolean mRunTask;

		ExecuteHandler(Looper looper, TempTaskExecutor executor, int id) {
			super(looper);
			mExecutor = executor;
			mIdleTime = mExecutor.mIdleTime;
			mId = id;
			mRunTask = false;
		}

		public boolean isBusy() {
			if (hasMessages(HAS_TASK) || mRunTask) {
				// 队列中有任务或者正在执行任务，都是繁忙状态
				return true;
			}

			return false;
		}

		private void exit() {
			removeMessages(HAS_TASK);
			removeMessages(GOTO_DIE);
			Looper looper = getLooper();
			if (looper != null) {
				looper.quit();
			}

			mExecutor.removeThread(mId);
		}

		private void run() {
			WorkTask task = mExecutor.pollTopTask();
			if (task != null) {
				mRunTask = true;
				removeMessages(GOTO_DIE);
				task.run();
				mRunTask = false;

				if (mExecutor.getTaskQueueSize() == 0) {
					// 任务执行完成之后，则发送延迟退出的消息
					sendEmptyMessageDelayed(GOTO_DIE, mIdleTime);
				}
			} else {
				// 如果没有任何任务，则也发送延迟退出的消息
				sendEmptyMessageDelayed(GOTO_DIE, mIdleTime);
			}
		}

		public void handleMessage(Message msg) {
			switch (msg.what) {
				case HAS_TASK:
					run();
					break;
				case GOTO_DIE:
					exit();
					break;
			}
		}
	}

	public TempTaskExecutor() {
		mIdleTime = DEFAULT_IDLE_TIME;
		mMaxCount = DEFAULT_MAX_COUNT;
		mConstructThreadCount = 0;
	}

	public void setParams(long idleTime, int maxCount) {
		mIdleTime = idleTime;
		mMaxCount = maxCount;
	}

	public synchronized void execute(WorkTask task) {
		if (task == null) {
			return;
		}

		if (mTempTasks == null) {
			mTempTasks = new PriorityQueue<WorkTask>();
		}

		if (mTempHandlers == null) {
			mTempHandlers = new SparseArray<ExecuteHandler>();
		}

		int size = mTempHandlers.size();
		if (((!mTempTasks.isEmpty() || isExecutorBusy()) && size < mMaxCount) || size == 0) {
			// 如果队列中，还有待执行的任务，同时当前线程数小于最大线程数，或者没有处理线程，则创建新的处理线程
			int id = mConstructThreadCount++;
			String name = "TempThread" + id;
			HandlerThread thread = new HandlerThread(name);
			thread.start();
			ExecuteHandler handler = new ExecuteHandler(thread.getLooper(), this, id);
			mTempHandlers.put(id, handler);
		}

		mTempTasks.add(task);
		size = mTempHandlers.size();
		for (int i = 0; i < size; i++) {
			ExecuteHandler handler = mTempHandlers.valueAt(i);
			handler.sendEmptyMessage(ExecuteHandler.HAS_TASK);
		}
	}

	private boolean isExecutorBusy() {
		if (mTempHandlers == null) {
			return false;
		}

		int size = mTempHandlers.size();
		if (size == 0) {
			return false;
		}

		for (int i = 0; i < size; i++) {
			ExecuteHandler handler = mTempHandlers.valueAt(i);
			if (!handler.isBusy()) {
				return false;
			}
		}

		// 所有的执行器
		return true;
	}

	public synchronized void release() {
		mConstructThreadCount = 0;
		mTempTasks.clear();
		int size = mTempHandlers.size();
		for (int i = 0; i < size; i++) {
			ExecuteHandler handler = mTempHandlers.valueAt(i);
			handler.exit();
		}
	}

	private synchronized void removeThread(int id) {
		if (mTempHandlers != null) {
			mTempHandlers.remove(id);
		}
	}

	private synchronized WorkTask pollTopTask() {
		if (mTempTasks == null) {
			return null;
		}
		return mTempTasks.poll();
	}

	private synchronized int getTaskQueueSize() {
		if (mTempTasks == null) {
			return 0;
		}
		return mTempTasks.size();
	}

}
