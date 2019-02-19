package com.fish.lib.fishlib.util.system.net;
/**
 * @author xmyu
 * @createTime 2015-3-9 下午4:43:04
 */
public class WifiStateUtil {
    /**
     * 将广播得到的int值转化为字符串
     * 
     * @param ws
     * @return
     */
    public static String wifistate2Str(int ws) {
        switch (ws) {
            case 0:
                return "WIFI_STATE_DISABLING";
            case 1:
                return "WIFI_STATE_DISABLED";
            case 2:
                return "WIFI_STATE_ENABLING";
            case 3:
                return "WIFI_STATE_ENABLED";
            case 4:
                return "WIFI_STATE_UNKNOWN";
            default:
                return " wifistate error";
        }
    }
}

