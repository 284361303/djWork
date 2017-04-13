package com.dangjian.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dangjian.R;
import com.dangjian.adapter.ZZTitleDetailsAdapter;
import com.dangjian.entity.LearningMterialsBean;
import com.dangjian.entity.LearningPomoteBean;
import com.dangjian.utils.Config;
import com.dangjian.utils.Control;
import com.dangjian.utils.ToastUtil;
import com.google.gson.Gson;

import org.json.JSONObject;
import org.xutils.common.Callback;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ZZWorkTitleDetailsActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.titleRightText_llBack)
    LinearLayout titleRightTextLlBack;
    @Bind(R.id.titleRightText_tvTitleName)
    TextView titleRightTextTvTitleName;
    @Bind(R.id.titleRightText_tvRightName)
    TextView titleRightTextTvRightName;
    @Bind(R.id.emptyLoadingLayout_iv)
    ImageView emptyLoadingLayoutIv;
    @Bind(R.id.emptyLoadingLayout_tvName)
    TextView emptyLoadingLayoutTvName;
    @Bind(R.id.emptyLoadingLayout_ll)
    LinearLayout emptyLoadingLayoutLl;
    @Bind(R.id.zzTitleDetail_recyclerView)
    RecyclerView zzTitleDetailRecyclerView;
    @Bind(R.id.zzTitleDetail_refresh)
    SwipeRefreshLayout zzTitleDetailRefresh;
    private String pageSizes = "10";   //每页显示条数
    private int pageIndex = 1;    //当前页数
    private boolean isCanScroll = true;  //上拉加载更多的状态
    private String LoadingFlag = "0";    //判断是否有商品是否第一次
    private List<LearningMterialsBean.ListBean> listData = new ArrayList<LearningMterialsBean.ListBean>();
    private ZZTitleDetailsAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private static final String TAG = "ZZTDActivity";
    private String mCode = "", titleName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zzwork_title_details);
        ButterKnife.bind(this);
        initView();
        upDataContentView();
        getExtras();
    }

    private void getExtras() {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            LearningPomoteBean.ListBean bean = (LearningPomoteBean.ListBean) extras.getSerializable("LearningPomoteBean");
            mCode = bean.getCode();
            titleName = bean.getName();
            titleRightTextTvTitleName.setText(titleName);
            String activity = extras.getString("activity");
            if (activity.equals("ZZWorkActivity")) {       //组织工作
                titleRightTextTvRightName.setVisibility(View.VISIBLE);
                if ((Config.ZZ_ADMIN_ROLE != null && Config.ZZ_ADMIN_ROLE.equals("ZZ_ADMIN")) || Config.DW_ADMIN_ROLE.equals("DW_ADMIN")) {
                    titleRightTextTvRightName.setVisibility(View.VISIBLE);
                } else {
                    titleRightTextTvRightName.setVisibility(View.GONE);
                }
            }
        }
    }

    private void initView() {
        titleRightTextTvRightName.setText("发布");
        linearLayoutManager = new LinearLayoutManager(mContext);
        zzTitleDetailRecyclerView.setLayoutManager(linearLayoutManager);
        zzTitleDetailRefresh.setOnRefreshListener(this);
        zzTitleDetailRefresh.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);
        zzTitleDetailRecyclerView.addOnScrollListener(new LoadMoreListener());
    }

    private void upDataContentView() {
        adapter = new ZZTitleDetailsAdapter(mContext, listData);
        initListener();
        zzTitleDetailRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void initListener() {
        adapter.setOnItemClickListener(new ZZTitleDetailsAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                LearningMterialsBean.ListBean listBean = listData.get(position);
                String newsId = listBean.getNewsId();
                String isRead = listBean.getIsRead();
                Intent i = new Intent(mContext, ZiLiaoWebActivity.class);
                i.putExtra("activity", "YouthWorkTitleDetailActivity");
                i.putExtra("activityType", titleName);
                i.putExtra("newsId", newsId);
                i.putExtra("isRead", isRead);
                startActivity(i);
            }
        });
    }

    @OnClick({R.id.titleRightText_llBack, R.id.titleRightText_tvRightName})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titleRightText_llBack:
                mContext.finish();
                break;
            case R.id.titleRightText_tvRightName:      //发布
                Intent iRelease = new Intent(mContext, ReleaseContainPicActivity.class);
                iRelease.putExtra("activity", "ZZWorkTitleDetailsActivity");
                iRelease.putExtra("mCode", mCode);
                iRelease.putExtra("titleName", titleName);
                startActivityForResult(iRelease, 200);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getNewdata();
    }

    @Override
    public void onRefresh() {
        getNewdata();
    }

    /**
     * 上拉加载更多数据
     */
    private class LoadMoreListener extends RecyclerView.OnScrollListener {
        private int position;

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            position = linearLayoutManager.findLastCompletelyVisibleItemPosition();
            int count = recyclerView.getLayoutManager().getItemCount();
            if (count - 1 == position && isCanScroll == true && dy > 0) {
                isCanScroll = false;
                pageIndex += 1;
                getData();
            }
        }
    }

    private void getNewdata() {
        pageIndex = 1;
        if (listData != null && listData.size() > 0) {
            listData.clear();
        }
        adapter.notifyDataSetChanged();
        getData();
    }

    /**
     * 获取学习宣传的内容
     */
    private void getData() {
        Control.getNewsList(mContext, mCode, String.valueOf(pageIndex), pageSizes, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    Log.i(TAG, "13002: " + result);
                    JSONObject jo = new JSONObject(result);
                    String status = jo.getString("status");
                    String msg = jo.getString("msg");
                    if (status != null && status.equals("1")) {
                        Gson gson = new Gson();
                        LearningMterialsBean bean = gson.fromJson(result, LearningMterialsBean.class);
                        List<LearningMterialsBean.ListBean> list = bean.getList();
                        if (list != null && list.size() > 0) {
                            LoadingFlag = "1";
                            isCanScroll = true;
                            listData.addAll(list);
                            emptyLoadingLayoutLl.setVisibility(View.GONE);
                            zzTitleDetailRecyclerView.setVisibility(View.VISIBLE);
                        } else if (LoadingFlag.equals("1") && listData.size() >= 0) {
                            emptyLoadingLayoutLl.setVisibility(View.GONE);
                            zzTitleDetailRecyclerView.setVisibility(View.VISIBLE);
                        } else {
                            emptyLoadingLayoutLl.setVisibility(View.VISIBLE);
                            zzTitleDetailRecyclerView.setVisibility(View.GONE);
                        }
                        adapter.notifyDataSetChanged();
                    } else if (status.equals("9")) {
                        ToastUtil.showShort(mContext, msg);
                        Intent i = new Intent(mContext, LoginActivity.class);
                        startActivity(i);
                        mContext.finish();
                    } else {
                        emptyLoadingLayoutLl.setVisibility(View.VISIBLE);
                        zzTitleDetailRecyclerView.setVisibility(View.GONE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                zzTitleDetailRefresh.setRefreshing(false);
                ToastUtil.closeProgressDialog();
            }

            @Override
            public void onCancelled(CancelledException cex) {
                zzTitleDetailRefresh.setRefreshing(false);
                ToastUtil.closeProgressDialog();
            }

            @Override
            public void onFinished() {
                zzTitleDetailRefresh.setRefreshing(false);
                ToastUtil.closeProgressDialog();
            }
        });
    }
}
