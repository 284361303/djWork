package com.dangjian.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dangjian.R;
import com.dangjian.entity.UserBean;
import com.dangjian.utils.APIManager;
import com.dangjian.utils.Config;
import com.dangjian.utils.Control;
import com.dangjian.utils.ToastUtil;
import com.google.gson.Gson;

import org.json.JSONObject;
import org.xutils.common.Callback;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class UserActivity extends BaseActivity {

    @Bind(R.id.user_llBack)
    LinearLayout userLlBack;
    @Bind(R.id.user_userPic)
    CircleImageView userUserPic;
    @Bind(R.id.user_tvCurrentSum)
    TextView userTvCurrentSum;
    @Bind(R.id.user_tvAllSum)
    TextView userTvAllSum;
    @Bind(R.id.user_tvLevel)
    TextView userTvLevel;
    @Bind(R.id.user_llMyCollect)
    LinearLayout userLlMyCollect;
    @Bind(R.id.user_llCounter)
    LinearLayout userLlCounter;
    @Bind(R.id.user_llList)
    LinearLayout userLlList;
    @Bind(R.id.user_llSet)
    LinearLayout userLlSet;
    private static final String TAG = "UserActivity";
    @Bind(R.id.user_tvUserName)
    TextView userTvUserName;
    @Bind(R.id.user_tvUserZhiBu)
    TextView userTvUserZhiBu;
    private UserBean userBean = null;
    private String loginName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ButterKnife.bind(this);
        initView();
        getExtras();
    }

    private void initView() {
        getMyInfo();
    }

    private void getExtras() {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String iconPath = extras.getString("iconPath");     //个人图片路径
        loginName = extras.getString("loginName");       //登录名
        String deptCode = extras.getString("deptCode");     //部门编码
        userTvUserZhiBu.setText(deptCode);
    }

    @OnClick({R.id.user_llBack, R.id.user_llMyCollect, R.id.user_llCounter, R.id.user_llList, R.id.user_llSet, R.id.user_llFunction, R.id.user_llAbout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_llBack:
                mContext.finish();
                break;
            case R.id.user_llMyCollect:     //我的收藏
                Intent iCollect = new Intent(mContext, MyCollectActivity.class);
                startActivity(iCollect);
                break;
            case R.id.user_llCounter:   //党费计算器
                Intent iDFCount = new Intent(mContext, DangFeiCountActivity.class);
                startActivity(iDFCount);
                break;
            case R.id.user_llList:  //积分排名
                Intent iList = new Intent(mContext, UserRankingActivity.class);
                startActivity(iList);
                break;
            case R.id.user_llSet:
                Intent i = new Intent(mContext, SettingsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("userBean", userBean);
                i.putExtras(bundle);
                startActivity(i);
                break;
            case R.id.user_llFunction:  //功能介绍
                Intent iFunction = new Intent(mContext, FunctionActivity.class);
                startActivity(iFunction);
                break;
            case R.id.user_llAbout:     //关于我们
                Intent iAbout = new Intent(mContext, AboutUsAvtivity.class);
                startActivity(iAbout);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Glide.with(mContext).load(APIManager.ImagePath + Config.mIconPath).error(R.drawable.all_smallhead).into(userUserPic);
        if (Config.mNickName != null && !Config.mNickName.equals("")) {
            userTvUserName.setText(Config.mNickName);
        } else {
            userTvUserName.setText(loginName);
        }
    }

    /**
     * （11002）获得我的个人信息数据
     */
    private void getMyInfo() {
        Control.getMyInfo(mContext, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    Log.i(TAG, "个人信息: " + result);
                    JSONObject jo = new JSONObject(result);
                    String status = jo.getString("status");
                    String msg = jo.getString("msg");
                    if (status != null && !status.equals("") && status.equals("1")) {
                        Gson gson = new Gson();
                        userBean = gson.fromJson(result, UserBean.class);
                        userTvCurrentSum.setText(userBean.getUsablePoint());
                        userTvAllSum.setText(userBean.getTotalPoints());
                        userTvLevel.setText(userBean.getRank());
                    } else if (status.equals("9")) {
                        ToastUtil.showShort(mContext, msg);
                        Intent i = new Intent(mContext, LoginActivity.class);
                        startActivity(i);
                        mContext.finish();
                    } else {
                        ToastUtil.showShort(mContext, msg);
                    }
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
