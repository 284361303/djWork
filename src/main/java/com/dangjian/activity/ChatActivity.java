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
import com.dangjian.fragments.ChatTabAFragment;
import com.dangjian.fragments.ChatTabBFragment;
import com.dangjian.fragments.ChatTabCFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 互动交流主容器
 */
public class ChatActivity extends FragmentActivity {

    @Bind(R.id.titleRightText_llBack)
    LinearLayout titleRightTextLlBack;
    @Bind(R.id.titleRightText_tvTitleName)
    TextView titleRightTextTvTitleName;
    @Bind(R.id.titleRightText_tvRightName)
    TextView titleRightTextTvRightName;
    @Bind(R.id.chat_tvA)
    TextView chatTvA;
    @Bind(R.id.chat_viewA)
    View chatViewA;
    @Bind(R.id.chat_llA)
    LinearLayout chatLlA;
    @Bind(R.id.chat_tvB)
    TextView chatTvB;
    @Bind(R.id.chat_viewB)
    View chatViewB;
    @Bind(R.id.chat_llB)
    LinearLayout chatLlB;
    @Bind(R.id.chat_tvC)
    TextView chatTvC;
    @Bind(R.id.chat_viewC)
    View chatViewC;
    @Bind(R.id.chat_llC)
    LinearLayout chatLlC;
    @Bind(R.id.chat_llAll)
    LinearLayout chatLlAll;
    private ChatActivity mContext;

    private Fragment[] fragments;
    private LinearLayout[] llButtons;
    private TextView[] tvButtons;
    private View[] ivButtons;
    private int currentTabIndex, index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);
        mContext = this;
        initView();
    }

    private void initView() {
        titleRightTextTvTitleName.setText("互动交流");
        titleRightTextTvRightName.setText("发帖");
        titleRightTextTvRightName.setVisibility(View.VISIBLE);
        currentTabIndex = 0;
        ChatTabAFragment Afragment = new ChatTabAFragment();
        ChatTabBFragment Bfragment = new ChatTabBFragment();
        ChatTabCFragment Cfragment = new ChatTabCFragment();
        fragments = new Fragment[]{Afragment, Bfragment, Cfragment};
        llButtons = new LinearLayout[3];

        llButtons[0] = (LinearLayout) findViewById(R.id.chat_llA);
        llButtons[1] = (LinearLayout) findViewById(R.id.chat_llB);
        llButtons[2] = (LinearLayout) findViewById(R.id.chat_llC);
        llButtons[0].setSelected(true);

        tvButtons = new TextView[3];
        tvButtons[0] = (TextView) findViewById(R.id.chat_tvA);
        tvButtons[1] = (TextView) findViewById(R.id.chat_tvB);
        tvButtons[2] = (TextView) findViewById(R.id.chat_tvC);
        ivButtons = new View[3];
        ivButtons[0] = (View) findViewById(R.id.chat_viewA);
        ivButtons[1] = (View) findViewById(R.id.chat_viewB);
        ivButtons[2] = (View) findViewById(R.id.chat_viewC);
        getSupportFragmentManager().beginTransaction().add(R.id.chat_llAll, Afragment)
                .add(R.id.chat_llAll, Bfragment)
                .add(R.id.chat_llAll, Cfragment)
                .hide(Bfragment).hide(Cfragment).show(Afragment).commit();
    }

    @OnClick({R.id.titleRightText_llBack, R.id.titleRightText_tvRightName, R.id.chat_llA, R.id.chat_llB, R.id.chat_llC})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titleRightText_llBack:
                mContext.finish();
                break;
            case R.id.titleRightText_tvRightName:   //发帖
                Intent ii=new Intent(mContext,ReleaseContainPicActivity.class);
                ii.putExtra("activity", "ChatActivity");
                startActivityForResult(ii, 200);
                break;
            case R.id.chat_llA:
                index = 0;
                break;
            case R.id.chat_llB:
                index = 1;
                break;
            case R.id.chat_llC:
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
