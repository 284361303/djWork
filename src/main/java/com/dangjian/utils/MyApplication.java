package com.dangjian.utils;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.bumptech.glide.request.target.ViewTarget;
import com.dangjian.R;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.TbsListener;

import org.xutils.x;

import im.fir.sdk.FIR;

/**
 * Created by sg-pc on 2016/9/26.
 */
public class MyApplication extends Application {

    private static MyApplication instance;
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);   //注册Xutils3
        context = this.getApplicationContext();
        ViewTarget.setTagId(R.id.glide_tag);
        FIR.init(this);
        initWebView();
    }

    private void initWebView() {
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

            @Override
            public void onViewInitFinished(boolean arg0) {
                Log.e("apptbs", " onViewInitFinished is " + arg0);
            }

            @Override
            public void onCoreInitFinished() {
            }
        };
        QbSdk.setTbsListener(new TbsListener() {
            @Override
            public void onDownloadFinish(int i) {
                Log.d("apptbs", "onDownloadFinish");
            }

            @Override
            public void onInstallFinish(int i) {
                Log.d("apptbs", "onInstallFinish");
            }

            @Override
            public void onDownloadProgress(int i) {
                Log.d("apptbs", "onDownloadProgress:" + i);
            }
        });

        QbSdk.initX5Environment(getApplicationContext(), cb);
    }

    public static MyApplication getInstance() {
        return instance;
    }

    public static Context getContext() {
        return context;
    }
}
