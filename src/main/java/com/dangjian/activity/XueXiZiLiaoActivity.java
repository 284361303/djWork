package com.dangjian.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dangjian.R;
import com.dangjian.fragments.ZiLiaoTabAFragment;
import com.dangjian.fragments.ZiLiaoTabBFragment;
import com.dangjian.fragments.ZiLiaoTabCFragment;
import com.dangjian.utils.AppManager;
import com.dangjian.utils.Config;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 学习资料页面
 */
public class XueXiZiLiaoActivity extends FragmentActivity {

    @Bind(R.id.titleRightText_llBack)
    LinearLayout titleRightTextLlBack;
    @Bind(R.id.titleRightText_tvTitleName)
    TextView titleRightTextTvTitleName;
    @Bind(R.id.titleRightText_tvRightName)
    TextView titleRightTextTvRightName;
    @Bind(R.id.ziliao_tvA)
    TextView ziliaoTvA;
    @Bind(R.id.ziliao_viewA)
    View ziliaoViewA;
    @Bind(R.id.ziliao_llA)
    LinearLayout ziliaoLlA;
    @Bind(R.id.ziliao_tvB)
    TextView ziliaoTvB;
    @Bind(R.id.ziliao_viewB)
    View ziliaoViewB;
    @Bind(R.id.ziliao_llB)
    LinearLayout ziliaoLlB;
    @Bind(R.id.ziliao_tvC)
    TextView ziliaoTvC;
    @Bind(R.id.ziliao_viewC)
    View ziliaoViewC;
    @Bind(R.id.ziliao_llC)
    LinearLayout ziliaoLlC;
    @Bind(R.id.ziliao_llAll)
    LinearLayout ziliaoLlAll;

    private Fragment[] fragments;
    private LinearLayout[] llButtons;
    private TextView[] tvButtons;
    private View[] ivButtons;
    private int currentTabIndex, index;
    private XueXiZiLiaoActivity mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xue_xi_zi_liao);
        ButterKnife.bind(this);
        AppManager.getAppManager().addActivity(this);
        mContext = this;
        initView();
    }

    private void initView() {
        titleRightTextTvTitleName.setText("学习资料");
        titleRightTextTvRightName.setText("发布");
        if ((Config.DW_ADMIN_ROLE != null && Config.DW_ADMIN_ROLE.equals("DW_ADMIN")) || (Config.XC_ADMIN_ROLE != null && Config.XC_ADMIN_ROLE.equals("XC_ADMIN"))) {
            titleRightTextTvRightName.setVisibility(View.VISIBLE);
        } else {
            titleRightTextTvRightName.setVisibility(View.GONE);
        }
        currentTabIndex = 0;
        ZiLiaoTabAFragment Afragment = new ZiLiaoTabAFragment();
        ZiLiaoTabBFragment Bfragment = new ZiLiaoTabBFragment();
        ZiLiaoTabCFragment Cfragment = new ZiLiaoTabCFragment();
        fragments = new Fragment[]{Afragment, Bfragment, Cfragment};
        llButtons = new LinearLayout[3];

        llButtons[0] = (LinearLayout) findViewById(R.id.ziliao_llA);
        llButtons[1] = (LinearLayout) findViewById(R.id.ziliao_llB);
        llButtons[2] = (LinearLayout) findViewById(R.id.ziliao_llC);
        llButtons[0].setSelected(true);

        tvButtons = new TextView[3];
        tvButtons[0] = (TextView) findViewById(R.id.ziliao_tvA);
        tvButtons[1] = (TextView) findViewById(R.id.ziliao_tvB);
        tvButtons[2] = (TextView) findViewById(R.id.ziliao_tvC);
        ivButtons = new View[3];
        ivButtons[0] = (View) findViewById(R.id.ziliao_viewA);
        ivButtons[1] = (View) findViewById(R.id.ziliao_viewB);
        ivButtons[2] = (View) findViewById(R.id.ziliao_viewC);
        getSupportFragmentManager().beginTransaction().add(R.id.ziliao_llAll, Afragment)
                .add(R.id.ziliao_llAll, Bfragment)
                .add(R.id.ziliao_llAll, Cfragment)
                .hide(Bfragment).hide(Cfragment).show(Afragment).commit();
    }

    @OnClick({R.id.titleRightText_llBack, R.id.titleRightText_tvRightName, R.id.ziliao_llA, R.id.ziliao_llB, R.id.ziliao_llC})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titleRightText_llBack:
                mContext.finish();
                break;
            case R.id.titleRightText_tvRightName:   //发布按钮
                Intent iRelease = new Intent(mContext, ReleaseContainPicActivity.class);
                iRelease.putExtra("activity", "XueXiZiLiaoActivity");
                startActivityForResult(iRelease, 200);
                break;
            case R.id.ziliao_llA:
                index = 0;
                break;
            case R.id.ziliao_llB:
                index = 1;
                break;
            case R.id.ziliao_llC:
                index = 2;
                break;
        }
        if (currentTabIndex != index) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.hide(fragments[currentTabIndex]);
            if (!fragments[index].isAdded()) {
                ft.add(R.id.ziliao_llAll, fragments[index]);
            }
            ft.show(fragments[index]).commit();
        }
        llButtons[currentTabIndex].setSelected(false);
        llButtons[index].setSelected(true);
        ivButtons[currentTabIndex].setVisibility(View.GONE);
        ivButtons[index].setVisibility(View.VISIBLE);
        tvButtons[currentTabIndex].setTextColor(Color.parseColor("#999999"));
        tvButtons[index].setTextColor(Color.parseColor("#333333"));
        currentTabIndex = index;
    }
}
