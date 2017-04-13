package com.dangjian.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 判断是否联网
 * Created by sg-pc on 2016/9/8.
 */
public class NetWorkHelper {
    /**
     * 检查网络是否连接
     */
    public static boolean checkNetworkState(Context context) {
        try {
            ConnectivityManager manager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = manager.getActiveNetworkInfo();
            return (info != null && info.isAvailable());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 判断当前是wifi还是手机网络
     */
    public static String isWifiOrMobile(Context context) {
        String type = "";
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobile = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (wifi != null && wifi.isConnected()) {
            type = "WIFI";
        } else if (mobile != null && mobile.isConnected()) {
            type = "MOBILE";
        } else {
            type = "";
        }
        return type;
    }
}
