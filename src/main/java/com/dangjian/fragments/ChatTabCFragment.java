package com.dangjian.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dangjian.R;
import com.dangjian.utils.APIManager;
import com.dangjian.utils.Config;
import com.dangjian.utils.NetWorkHelper;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 互动交流中的我的评论
 */
public class ChatTabCFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.chatTabC_WebView)
    WebView chatTabCWebView;
    @Bind(R.id.chatTabC_refresh)
    SwipeRefreshLayout chatTabCRefresh;
    private FragmentActivity mContext;
    private static final String TAG = "ChatTabCFragment";
    private String url = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat_tab_c, container, false);
        ButterKnife.bind(this, view);
        mContext = getActivity();
        initView();
        getData();
        return view;
    }

    private void initView() {
        chatTabCRefresh.setOnRefreshListener(this);
        chatTabCRefresh.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);
    }

    private void getData() {
        String wifiOrMobile = NetWorkHelper.isWifiOrMobile(mContext);
        url = APIManager.myComments + ";jsessionid=" + Config.mCookie + "?netType=" + wifiOrMobile;
        Log.i(TAG, "getExtras/url: " + url);
        Config.syncCookie(mContext, url);    //设置cookie
        chatTabCWebView.loadUrl(url);
        WebSettings webSettings = chatTabCWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        chatTabCWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        chatTabCWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView webView, int i) {
                super.onProgressChanged(webView, i);
                try {
                    if (i == 100) {
                        chatTabCRefresh.setRefreshing(false);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onRefresh() {
        getData();
    }
}
