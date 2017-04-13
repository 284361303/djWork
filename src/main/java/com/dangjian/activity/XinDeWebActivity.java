package com.dangjian.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dangjian.R;
import com.dangjian.utils.APIManager;
import com.dangjian.utils.Config;
import com.dangjian.utils.NetWorkHelper;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 学习心得列表页面
 */
public class XinDeWebActivity extends BaseActivity {

    @Bind(R.id.titleRightText_llBack)
    LinearLayout titleRightTextLlBack;
    @Bind(R.id.titleRightText_tvTitleName)
    TextView titleRightTextTvTitleName;
    @Bind(R.id.titleRightText_tvRightName)
    TextView titleRightTextTvRightName;
    private static final String TAG = "XinDeWebActivity";
    @Bind(R.id.xinDeWebView)
    com.tencent.smtt.sdk.WebView xinDeWebView;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_xin_de_web);
            ButterKnife.bind(this);
            initView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initView() {
        try {
            titleRightTextTvTitleName.setText("学习心得");
            titleRightTextTvRightName.setText("发布");
            titleRightTextTvRightName.setVisibility(View.VISIBLE);
            if (NetWorkHelper.checkNetworkState(mContext) == true) {
                String wifiOrMobile = NetWorkHelper.isWifiOrMobile(mContext);
                Log.i(TAG, " 心得列表/wifiOrMobile: " + wifiOrMobile);
                url = APIManager.toXDList + ";jsessionid=" + Config.mCookie + "?netType=" + wifiOrMobile;
                Log.i(TAG, "getExtras/url: " + url);
                initWebView();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initWebView() {
        try {
            /*xinDeWebView.getSettings().setJavaScriptEnabled(true);
            Config.syncCookie(mContext, url);    //设置cookie
            xinDeWebView.getSettings().setAllowFileAccess(true);
            xinDeWebView.getSettings().setJavaScriptEnabled(true);
            xinDeWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
            xinDeWebView.getSettings().setAllowFileAccess(true);
            xinDeWebView.getSettings().setAppCacheEnabled(true);
            xinDeWebView.getSettings().setDomStorageEnabled(true);
            xinDeWebView.getSettings().setDatabaseEnabled(true);
            xinDeWebView.loadUrl(url);     //加载地址
            xinDeWebView.setHorizontalScrollBarEnabled(false);// 水平不显示
            xinDeWebView.setVerticalScrollBarEnabled(true); // 垂直不显示
            xinDeWebView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    return false;
                }
            });*/
            Config.syncCookie(mContext, url);    //设置cookie
            xinDeWebView.loadUrl(url);
            WebSettings webSettings = xinDeWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            xinDeWebView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return true;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.titleRightText_llBack, R.id.titleRightText_tvRightName})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titleRightText_llBack:
                mContext.finish();
                break;
            case R.id.titleRightText_tvRightName:       //发布
                Intent i = new Intent(mContext, ReleaseContainPicActivity.class);
                i.putExtra("activity", "XinDeWebActivity");
                startActivityForResult(i, 200);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 200) {
            initWebView();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && xinDeWebView.canGoBack()) {
            xinDeWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
