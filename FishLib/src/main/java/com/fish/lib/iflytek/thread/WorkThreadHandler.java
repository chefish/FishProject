package com.fish.lib.iflytek.thread;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

class WorkThreadHandler {

	private static final String TAG = "WorkThreadHandler";

	private HandlerThread mWorkThread;
	private Handler mWorkHandler;

	public WorkThreadHandler(int priority) {
		mWorkThread = new HandlerThread(TAG, priority);
		mWorkThread.start();
		mWorkHandler = new Handler(mWorkThread.getLooper());
	}

	public boolean isRelease() {
		if (mWorkThread == null || mWorkHandler == null) {
			return true;
		}

		return false;
	}

	public void release() {
		if (mWorkThread != null) {
			mWorkHandler.removeCallbacksAndMessages(null);

			Looper looper = mWorkThread.getLooper();
			if (looper != null) {
				looper.quit();
			}
		}

		mWorkThread = null;
		mWorkHandler = null;
	}

	public void postTask(WorkTask task) {
		if (mWorkHandler != null) {
			mWorkHandler.post(task);
		}
	}

	public void postTaskDelayed(WorkTask task, long delay) {
		if (mWorkHandler != null) {
			mWorkHandler.postDelayed(task, delay);
		}
	}

	public void postTaskAtTime(WorkTask task, long when) {
		if (mWorkHandler != null) {
			mWorkHandler.postAtTime(task, when);
		}
	}

	public void postTaskAtFront(WorkTask task) {
		if (mWorkHandler != null) {
			mWorkHandler.postAtFrontOfQueue(task);
		}
	}

	public void removeTask(WorkTask task) {
		if (mWorkHandler != null) {
			mWorkHandler.removeCallbacks(task);
		}
	}
}
