package com.fish.lib.iflytek.thread;

/**
 * 可延迟销毁的线程
 * 
 * @author kuncheng 2011-4-28
 * 
 */
public abstract class WaitableThread implements Runnable {

	private BaseThread mRunningThread;

	private long mWaitTime;

	/**
	 * 带等待时间的构造函数
	 * 
	 * @param waitMillisecond
	 */
	public WaitableThread(long waitMillisecond) {
		mWaitTime = waitMillisecond;
	}

	public WaitableThread() {
		mWaitTime = 0;
	}

	/**
	 * 启动线程
	 */
	public void start() {
		if (!isAlive()) {
			mRunningThread = new BaseThread();
			mRunningThread.startRunning();
		} else {
			mRunningThread.interrupt();
		}
	}

	/**
	 * 结束线程
	 */
	public void shutdown() {
		if (mRunningThread != null) {
			mRunningThread.shutdown();
		}
	}

	/**
	 * 线程是否在工作
	 * 
	 * @return
	 */
	public boolean isAlive() {
		if (mRunningThread != null) {
			return mRunningThread.isRunning();
		}

		return false;
	}

	/**
	 * 取得线程id
	 * 
	 * @return
	 */
	public long getId() {
		if (mRunningThread != null) {
			return mRunningThread.getId();
		}
		return 0;
	}

	/**
	 * 线程休眠
	 * 
	 * @param time
	 * @throws InterruptedException
	 */
	public static void sleep(long time) throws InterruptedException {
		Thread.sleep(time);
	}

	public abstract void run();

	/**
	 * 真实的线程类
	 * 
	 * @author kuncheng 2011-4-28
	 * 
	 */
	class BaseThread extends Thread {

		private boolean mRunning = false;

		public void startRunning() {
			mRunning = true;
			start();
		}

		public void shutdown() {
			mRunning = false;
			rouse();
		}

		public boolean isRunning() {
			return mRunning;
		}

		/**
		 * 唤醒线程
		 */
		private synchronized void rouse() {
			notify();
		}

		@Override
		public synchronized void run() {
			while (mRunning) {
				WaitableThread.this.run();
				if (mRunning && mWaitTime > 0) {
					try {
						wait(mWaitTime);
						break;
					} catch (InterruptedException e) {
					}
				} else {
					break;
				}
			}
		}
	}
}
