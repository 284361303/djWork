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
import com.dangjian.utils.Control;
import com.dangjian.utils.NetWorkHelper;
import com.dangjian.utils.ToastUtil;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import org.json.JSONObject;
import org.xutils.common.Callback;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * sg
 * 学习资料中的详情页面web页面
 */
public class ZiLiaoWebActivity extends BaseActivity {

    private static final String TAG = "ZiLiaoWebActivity";
    @Bind(R.id.ziLiaoWebView)
    WebView ziLiaoWebView;
    @Bind(R.id.titleRightText_llBack)
    LinearLayout titleRightTextLlBack;
    @Bind(R.id.titleRightText_tvTitleName)
    TextView titleOneTextTvTitleName;
    @Bind(R.id.titleRightText_tvRightName)
    TextView titleRightTextTvRightName;
    private String url = "";
    private String newsId = "";
    private String activity = "";
    private String wifiOrMobile = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zi_liao_web);
        ButterKnife.bind(this);
        getExtras();
    }

    private void getExtras() {
        titleRightTextTvRightName.setText("编辑");
        try {
            Intent intent = getIntent();
            Bundle extras = intent.getExtras();
            if (extras != null) {
                newsId = extras.getString("newsId");
                String isRead = extras.getString("isRead");
                activity = extras.getString("activity");
                if (NetWorkHelper.checkNetworkState(mContext) == true) {
                    wifiOrMobile = NetWorkHelper.isWifiOrMobile(mContext);
                    Log.i(TAG, "显示新闻通知/wifiOrMobile: " + wifiOrMobile);
                    isNewsCanEditable(newsId, activity);
                    url = APIManager.showNews + ";jsessionid=" + Config.mCookie + "?newsId=" + newsId + "&isRead=" + isRead + "&netType=" + wifiOrMobile;
                    if (activity != null && !activity.equals("") && activity.equals("InformMessageActivity")) {
                        titleOneTextTvTitleName.setText("通知公告");
                    } else if (activity != null && !activity.equals("") && activity.equals("LearningPomoteActivity")) {
                        titleOneTextTvTitleName.setText("学习宣传");
                    } else if (activity != null && !activity.equals("") && activity.equals("JiJianWorkActivity")) {
                        titleOneTextTvTitleName.setText("纪检工作");
                    } else if (activity != null && !activity.equals("") && activity.equals("ZZWorkActivity")) {
                        titleOneTextTvTitleName.setText("组织工作");
                    } else if (activity != null && !activity.equals("") && activity.equals("ZiLiaoTabAFragment")) {
                        titleOneTextTvTitleName.setText("学习资料");
                    } else if (activity != null && !activity.equals("") && activity.equals("YouthWorkActivity")) {
                        titleOneTextTvTitleName.setText("青年工作");
                    } else if (activity != null && !activity.equals("") && activity.equals("YouthWorkTitleDetailActivity")) {
                        String activityType = extras.getString("activityType");
                        titleOneTextTvTitleName.setText(activityType);
                    } else if (activity != null && !activity.equals("") && activity.equals("ZhiBuFamilyActivity")) {
                        titleOneTextTvTitleName.setText("支部天地");
                    } else if (activity != null && !activity.equals("") && activity.equals("MyCollectActivity")) {  //我的收藏
                        titleOneTextTvTitleName.setText("我的收藏");
                    } else if (activity != null && !activity.equals("") && activity.equals("ChatTabAFragment")) {  //互动交流(全部发帖)
                        titleOneTextTvTitleName.setText("全部帖子");
                        url = APIManager.toTZDetail + ";jsessionid=" + Config.mCookie + "?id=" + newsId + "&isRead=" + isRead + "&netType=" + wifiOrMobile;
                    } else if (activity != null && !activity.equals("") && activity.equals("ChatTabBFragment")) {   //互动交流(我的帖子)
                        titleOneTextTvTitleName.setText("我的帖子");
                        url = APIManager.toTZDetail + ";jsessionid=" + Config.mCookie + "?id=" + newsId + "&isRead=" + isRead + "&netType=" + wifiOrMobile;
                    }
                    Log.i(TAG, "getExtras/url: " + url);
                } else {
                    ToastUtil.showShort(mContext, "请检查网络");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadUrls() {
        Config.syncCookie(mContext, url);    //设置cookie
        ziLiaoWebView.loadUrl(url);
        WebSettings webSettings = ziLiaoWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        ziLiaoWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    @OnClick({R.id.titleRightText_llBack, R.id.titleRightText_tvRightName})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titleRightText_llBack:
                setResult(201);
                mContext.finish();
                break;
            case R.id.titleRightText_tvRightName:   //编辑
                Intent i = new Intent(mContext, EditReleaseActivity.class);
                i.putExtra("activity", activity);
                i.putExtra("titleName", titleOneTextTvTitleName.getText().toString());
                i.putExtra("newsId", newsId);
                startActivityForResult(i, 201);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 201) {
            setResult(201);
            mContext.finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadUrls();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            setResult(201);
            mContext.finish();
            return true;
        }
        if (keyCode == KeyEvent.KEYCODE_BACK && ziLiaoWebView.canGoBack()) {
            ziLiaoWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * （13021）判断新闻是否可编辑和删除
     *
     * @param mId 新闻id
     */
    private void isNewsCanEditable(String mId, final String activity) {
        Control.isNewsCanEditable(mContext, mId, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    Log.i(TAG, "是否可编辑: " + result);
                    JSONObject jo = new JSONObject(result);
                    String status = jo.getString("status");
                    String msg = jo.getString("msg");
                    if (status != null && !status.equals("") && status.equals("1")) {
                        String isEditable = jo.getString("isEditable");
                        if (isEditable != null && !isEditable.equals("") && isEditable.equals("1")) {
                            titleRightTextTvRightName.setVisibility(View.VISIBLE);
                        }
                    } else {
                        ToastUtil.showShort(mContext, msg);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                ToastUtil.closeProgressDialog();
            }

            @Override
            public void onCancelled(CancelledException cex) {
                ToastUtil.closeProgressDialog();
            }

            @Override
            public void onFinished() {
                ToastUtil.closeProgressDialog();
            }
        });
    }
}
