package com.dangjian.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Button;

import com.dangjian.R;
import com.dangjian.utils.APIManager;
import com.dangjian.utils.Config;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * sg 2017年2月23日10:44:50
 * 未读消息页面
 */
public class NotReadMessageActivity extends BaseActivity {

    @Bind(R.id.notReadMessage_webView)
    WebView notReadMessageWebView;
    @Bind(R.id.notReadMessage_btnClose)
    Button notReadMessageBtnClose;
    private static final String TAG = "NotReadMessageActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_read_message);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            String messageId = extras.getString("messageId");
            String url = APIManager.showMessage + ";jsessionid=" + Config.mCookie + "?messageId=" + messageId;
            Config.syncCookie(mContext, url);    //设置cookie
            notReadMessageWebView.loadUrl(url);
            WebSettings webSettings = notReadMessageWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            notReadMessageWebView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return true;
                }
            });
        }
    }

    @OnClick(R.id.notReadMessage_btnClose)
    public void onClick() {
        mContext.finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return false;
    }
}