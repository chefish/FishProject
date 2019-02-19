package com.fish.lib.iflytek.thread;

import android.os.Bundle;
import android.os.Message;
import android.os.Process;

/**
 * 工作任务，可放入工作线程队列中执行，由于每次都要创建这样一个对象，可能消耗较大，后续考虑优化
 * 
 * @author kuncheng 2013-2-4
 */
public abstract class WorkTask implements Runnable, Comparable<WorkTask> {
	
	private static final int STATE_IDLE = 0;
	private static final int STATE_RUNNING = 1;
	private static final int STATE_DONE = 2;
	private static final int STATE_CANCELED = 3;

	private static final int DEFAULT_MSG_ID = 0;

	private int mState;

	private int mWhat;
	private Message mMsg;

	private int mPriority;

	protected abstract void execute();

	@Override
	public final void run() {
		if (!isIdle()) {
			return;
		}
		started();
		if (!isCancelled()) {
			execute();
		}
		done();
	}

	public WorkTask() {
		this(DEFAULT_MSG_ID);
	}

	public void relive() {
		init(mWhat);
	}

	public WorkTask(int msgId) {
		mWhat = msgId;
		mPriority = Process.THREAD_PRIORITY_DEFAULT;
		init(msgId);
	}

	private void init(int msgId) {
		setState(STATE_IDLE);
		mMsg = Message.obtain();
		mMsg.what = msgId;
	}

	public Message getMessage() {
		return mMsg;
	}

	public void setArg1(int arg1) {
		mMsg.arg1 = arg1;
	}

	public void setArg2(int arg2) {
		mMsg.arg2 = arg2;
	}

	public void setObj(Object obj) {
		mMsg.obj = obj;
	}

	public void setData(Bundle data) {
		mMsg.setData(data);
	}

	public void cancel() {
		setState(STATE_CANCELED);
	}

	public boolean isCancelled() {
		return (getState() == STATE_CANCELED);
	}

	private synchronized void setState(int state) {
		mState = state;
	}

	private synchronized int getState() {
		return mState;
	}

	private void done() {
		if (getState() != STATE_DONE) {
			setState(STATE_DONE);
			if (mMsg != null) {
				mMsg.recycle();
				mMsg = null;
			}
		}
	}

	private boolean isIdle() {
		return (getState() == STATE_IDLE);
	}

	private void started() {
		setState(STATE_RUNNING);
	}

	public boolean isRunning() {
		return (getState() == STATE_RUNNING);
	}

	public boolean isDone() {
		return (getState() == STATE_DONE);
	}
	
	public void setPriority(int priority) {
		mPriority = priority;
	}

	@Override
	public int compareTo(WorkTask another) {
		int anotherPriority = another.mPriority;
		if (mPriority > anotherPriority) {
			return 1;
		} else if (mPriority == anotherPriority) {
			return 0;
		} else {
			return -1;
		}
	}

}
