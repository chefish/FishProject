package com.fish.lib.fishlib.util.system.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;

/**
 * 网络连接管理工具类
 * 
 * @author xzwu
 * @date 2014-11-25
 */
public class ConnectionManager {

	static final String TAG = "ConnectionMgr";
	private ConnectivityManager mConnectivityManager;
	private NetworkInfo mNetworkInfo;
    private WifiManager mWifiManager;
	private volatile static ConnectionManager mInstance;

	public static ConnectionManager getInstance(Context context) {
		if (mInstance == null) {
			synchronized (ConnectionManager.class) {
				if (mInstance == null) {
					mInstance = new ConnectionManager(context.getApplicationContext());
				}
			}
		}
		return mInstance;
	}

	private ConnectionManager(Context context) {
		try {
			mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			mWifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
			refreshNetworkInfo();
		} catch (Exception e) {
		}
	}
	private void refreshNetworkInfo() {
		if (null != mConnectivityManager) {
			// 该方法在有的机型可能会出现异常
			mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
		}
	}

	/**
	 * 当前网络类型 TYPE_MOBILE, TYPE_WIFI, TYPE_WIMAX, TYPE_ETHERNET, TYPE_BLUETOOTH,
	 * 
	 * @return
	 */
	public int getCurrentNetworkType() {
		int networkType = -1;
		refreshNetworkInfo();
		if (null != mNetworkInfo) {
			networkType = mNetworkInfo.getType();
		}
		return networkType;
	}

	/**
	 * 当前网络是否连接
	 * 
	 * @return
	 */
	public boolean isNetworkConnected() {
		refreshNetworkInfo();
		if (mNetworkInfo != null) {
			return mNetworkInfo.isConnected();
		}
		return false;
	}

	/**
	 * 网络APN名称
	 * 
	 * @return
	 */
	public String getNetworkExtraInfo() {
		refreshNetworkInfo();
		if (mNetworkInfo != null) {
			return mNetworkInfo.getExtraInfo();
		}
		return null;
	}

	/**
	 * 网络子类型
	 * 
	 * @return
	 */
	public String getNetworkSubtypeName() {
		refreshNetworkInfo();
		if (mNetworkInfo != null) {
			return mNetworkInfo.getSubtypeName();
		}
		return null;
	}

    public void enableWifi() {
        if (!mWifiManager.isWifiEnabled()) {
            mWifiManager.setWifiEnabled(true);
        }
    }
    public boolean isWifiEnable()
    {
        return mWifiManager.isWifiEnabled();
    }
	//FIXME 需要优化，需要context吗
    /**
     * 判断数据网络是否连接
     * @param pContext
     * @return
     */
    public  boolean isMobileNetworkConnected() {
        boolean connected = false;
        NetworkInfo mobileNetworkInfo = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo.State networkState = mobileNetworkInfo.getState();
        if (networkState == NetworkInfo.State.CONNECTED) {
            connected = true;
        }
        return connected;
    }
}
