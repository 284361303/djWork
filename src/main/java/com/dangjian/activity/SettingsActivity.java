package com.dangjian.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dangjian.R;
import com.dangjian.entity.UserBean;
import com.dangjian.utils.Control;

import org.xutils.common.Callback;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * sg 2016年10月9日00:02:43
 * 设置页面
 */
public class SettingsActivity extends BaseActivity {

    @Bind(R.id.titleOneText_llBack)
    LinearLayout titleOneTextLlBack;
    @Bind(R.id.titleOneText_tvTitleName)
    TextView titleOneTextTvTitleName;
    @Bind(R.id.settings_llUserDeatil)
    LinearLayout settingsLlUserDeatil;
    @Bind(R.id.settings_llRechangePWD)
    LinearLayout settingsLlRechangePWD;
    @Bind(R.id.settings_llIdea)
    LinearLayout settingsLlIdea;
    @Bind(R.id.settings_llExit)
    LinearLayout settingsLlExit;
    @Bind(R.id.settings_llRechangeShowPic)
    LinearLayout settingsLlRechangeShowPic;
    private UserBean user = null;
    private static final String TAG = "SettingsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        initView();
        getExtras();
    }

    private void initView() {
        titleOneTextTvTitleName.setText("设置");
    }

    private void getExtras() {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            user = (UserBean) extras.getSerializable("userBean");
        }
    }

    @OnClick({R.id.titleOneText_llBack, R.id.titleOneText_tvTitleName, R.id.settings_llUserDeatil, R.id.settings_llRechangePWD,
            R.id.settings_llIdea, R.id.settings_llExit, R.id.settings_llRechangeShowPic})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titleOneText_llBack:
                mContext.finish();
                break;
            case R.id.titleOneText_tvTitleName:
                break;
            case R.id.settings_llUserDeatil:    //个人信息
                Intent iUserDetail = new Intent(mContext, UserDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("userBean", user);
                iUserDetail.putExtras(bundle);
                startActivity(iUserDetail);
                break;
            case R.id.settings_llRechangePWD:
                Intent i = new Intent(mContext, RechangePWDActivity.class);
                startActivity(i);
                break;
            case R.id.settings_llIdea:  //意见
                Intent iIdea = new Intent(mContext, IdeaActivity.class);
                startActivity(iIdea);
                break;
            case R.id.settings_llExit:      //退出登录
                logOut();
                Intent iExit = new Intent(mContext, LoginActivity.class);
                startActivity(iExit);
                mContext.finish();
                break;
            case R.id.settings_llRechangeShowPic:   //设置新闻图片显示方式
                Intent iShowPic = new Intent(mContext, SettingShowPicActivity.class);
                startActivity(iShowPic);
                break;
        }
    }

    /**
     * 退出登录
     */
    private void logOut() {
        Control.logOut(mContext, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    Log.i(TAG, "退出: " + result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
            }
        });
    }
}
