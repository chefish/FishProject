package com.fish.lib.iflytek.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.os.Process;

public class WorkThreadManager {

	/**
	 * 线程池中核心线程数
	 */
	private static WorkThreadHandler mWorkThreadHandler;
	private static ExecutorService mCachedThreadPool;
	private static ExecutorService mFixedThreadPool;

	/**
	 * 后台执行不长时间阻塞的任务
	 * 
	 * @param task
	 */
	public static void executeTaskInBackground(WorkTask task) {
		getOrCreateHandler().postTask(task);
	}

	public static void removeTaskInBackground(WorkTask task) {
		if (mWorkThreadHandler != null) {
			mWorkThreadHandler.removeTask(task);
		}
	}

	/**
	 * 立刻在后台执行内部不长时间阻塞的任务
	 * 
	 * @param task
	 */
	public static void executeTaskImmediatelyInBackground(WorkTask task) {
		getOrCreateHandler().postTaskAtFront(task);
	}

	/**
	 * 如果支持多个并发执行的话，则调用此类{@link newCachedThreadPool}
	 * 
	 * @param task
	 */
	public static void executeTaskInPool(WorkTask task) {
		if (mCachedThreadPool == null) {
			mCachedThreadPool = Executors.newCachedThreadPool();
		}

		mCachedThreadPool.execute(task);
	}
	
	/**
	 * 如果支持N个并发执行的话，则调用此类{@link newFixedThreadPool}
	 * 
	 * @param task
	 */
	public static void executeTaskInPool(WorkTask task, int nThreads) {
		if (mFixedThreadPool == null) {
			mFixedThreadPool = Executors.newFixedThreadPool(nThreads);
		}

		mFixedThreadPool.execute(task);
	}

	@Deprecated
	public static void release() {
		if (mWorkThreadHandler != null) {
			mWorkThreadHandler.release();
			mWorkThreadHandler = null;
		}

		if (mCachedThreadPool != null) {
			mCachedThreadPool.shutdownNow();
			mCachedThreadPool = null;
		}
		if (mFixedThreadPool != null) {
			mFixedThreadPool.shutdownNow();
			mFixedThreadPool = null;
		}
	}

	private static synchronized WorkThreadHandler getOrCreateHandler() {
		if (mWorkThreadHandler == null) {
			mWorkThreadHandler = new WorkThreadHandler(Process.THREAD_PRIORITY_BACKGROUND);
		}

		return mWorkThreadHandler;
	}

}
