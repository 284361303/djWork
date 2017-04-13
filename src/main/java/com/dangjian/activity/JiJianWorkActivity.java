package com.dangjian.activity;

import android.content.Intent;
import android.graphics.Color;
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
import com.dangjian.adapter.LearingContentAdapter;
import com.dangjian.adapter.LearingTitleAdapter;
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

/**
 * sg  2016年10月12日13:59:44
 * 纪检工作
 */
public class JiJianWorkActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.titleRightText_llBack)
    LinearLayout titleRightTextLlBack;
    @Bind(R.id.titleRightText_tvTitleName)
    TextView titleRightTextTvTitleName;
    @Bind(R.id.titleRightText_tvRightName)
    TextView titleRightTextTvRightName;
    @Bind(R.id.JJWork_rvOne)
    RecyclerView JJWorkRvOne;
    @Bind(R.id.emptyLoadingLayout_iv)
    ImageView emptyLoadingLayoutIv;
    @Bind(R.id.emptyLoadingLayout_tvName)
    TextView emptyLoadingLayoutTvName;
    @Bind(R.id.emptyLoadingLayout_ll)
    LinearLayout emptyLoadingLayoutLl;
    @Bind(R.id.JJWork_rvTwo)
    RecyclerView JJWorkRvTwo;
    @Bind(R.id.JJWork_refresh)
    SwipeRefreshLayout JJWorkRefresh;
    private LinearLayoutManager linearLayoutManager, horizontalManager;
    private String pageSizes = "10";   //每页显示条数
    private int pageIndex = 1;    //当前页数
    private boolean isCanScroll = true;  //上拉加载更多的状态
    private String LoadingFlag = "0";    //判断是否有商品是否第一次
    private String mCodes = "";
    private List<LearningPomoteBean.ListBean> listTitleData = new ArrayList<LearningPomoteBean.ListBean>();
    private List<LearningMterialsBean.ListBean> listContentData = new ArrayList<LearningMterialsBean.ListBean>();
    private static final String TAG = "JiJianWorkActivity";
    private LearingTitleAdapter titleAdapter;
    private LearingContentAdapter contentAdapter;
    private LearningPomoteBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ji_jian_work);
        ButterKnife.bind(this);
        initView();
        upDataTitleView();
        upDataContentView();
        getData();
    }

    private void initView() {
        titleRightTextTvTitleName.setText("纪检工作");
        titleRightTextTvRightName.setText("发布");
        if ((Config.JJ_ADMIN_ROLE != null && Config.JJ_ADMIN_ROLE.equals("JJ_ADMIN")) || Config.DW_ADMIN_ROLE.equals("DW_ADMIN")) {
            titleRightTextTvRightName.setVisibility(View.VISIBLE);
        } else {
            titleRightTextTvRightName.setVisibility(View.GONE);
        }
        linearLayoutManager = new LinearLayoutManager(mContext);
        horizontalManager = new LinearLayoutManager(mContext);
        horizontalManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        JJWorkRvOne.setLayoutManager(horizontalManager);
        JJWorkRvTwo.setLayoutManager(linearLayoutManager);
        JJWorkRefresh.setOnRefreshListener(this);
        JJWorkRefresh.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);
        JJWorkRvTwo.addOnScrollListener(new LoadMoreListener());
    }

    private void upDataTitleView() {
        titleAdapter = new LearingTitleAdapter(mContext, listTitleData);
        initTitleListener();
        JJWorkRvOne.setAdapter(titleAdapter);
        titleAdapter.notifyDataSetChanged();
    }

    private void upDataContentView() {
        contentAdapter = new LearingContentAdapter(mContext, listContentData);
        initContentListener();
        JJWorkRvTwo.setAdapter(contentAdapter);
        contentAdapter.notifyDataSetChanged();
    }

    private void initContentListener() {
        contentAdapter.setOnItemClickListener(new LearingContentAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                LearningMterialsBean.ListBean listBean = listContentData.get(position);
                String newsId = listBean.getNewsId();
                String isRead = listBean.getIsRead();
                Intent i = new Intent(mContext, ZiLiaoWebActivity.class);
                i.putExtra("newsId", newsId);
                i.putExtra("isRead", isRead);
                i.putExtra("activity", "JiJianWorkActivity");
                startActivityForResult(i, 201);
            }
        });
    }

    private void initTitleListener() {
        titleAdapter.setOnItemClickListener(new LearingTitleAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                LearningPomoteBean.ListBean listBean = listTitleData.get(position);
                mCodes = listBean.getCode();
                getNewContentData();
                //重置点击显示效果
                int itemCount = titleAdapter.getItemCount();
                for (int i = 0; i < itemCount; i++) {
                    View childAt = JJWorkRvOne.getChildAt(i);
                    LearingTitleAdapter.TitleViewHolder titleViewHolder = (LearingTitleAdapter.TitleViewHolder) JJWorkRvOne.getChildViewHolder(childAt);
                    titleViewHolder.itemLearingTitleTv.setTextColor(Color.parseColor("#999999"));
                    titleViewHolder.itemLearingTitleView.setVisibility(View.INVISIBLE);
                }
                //设置点击选中效果
                View childAt = JJWorkRvOne.getChildAt(position);
                LearingTitleAdapter.TitleViewHolder titleViewHolder = (LearingTitleAdapter.TitleViewHolder) JJWorkRvOne.getChildViewHolder(childAt);
                titleViewHolder.itemLearingTitleTv.setTextColor(Color.parseColor("#333333"));
                titleViewHolder.itemLearingTitleView.setVisibility(View.VISIBLE);
                titleAdapter.listenerFlag(position);
                titleAdapter.notifyDataSetChanged();
            }
        });
    }

    @OnClick({R.id.titleRightText_llBack, R.id.titleRightText_tvRightName})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titleRightText_llBack:
                mContext.finish();
                break;
            case R.id.titleRightText_tvRightName:   //发布
                Intent iRelease = new Intent(mContext, ReleaseContainPicActivity.class);
                iRelease.putExtra("activity", "JiJianWorkActivity");
                Bundle bundle = new Bundle();
                bundle.putSerializable("LearningPomoteBean", bean);
                iRelease.putExtras(bundle);
                startActivityForResult(iRelease, 200);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 200 || resultCode == 201) {        //200发布成功返回刷新, 201浏览详情页返回的把未读标识符去掉刷新
            onRefresh();
        }
    }

    @Override
    public void onRefresh() {
        getNewContentData();
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
                getContentData(mCodes);
            }
        }
    }

    /**
     * （13001）获得栏目的二级菜单
     */
    private void getData() {
        if (listTitleData != null && listTitleData.size() > 0) {
            listTitleData.clear();
        }
        titleAdapter.notifyDataSetChanged();
        Control.getSecondMenu(mContext, "INSPECTIONMENU", new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    Log.i(TAG, "纪检工作菜单: " + result);
                    JSONObject jo = new JSONObject(result);
                    String status = jo.getString("status");
                    String msg = jo.getString("msg");
                    if (status.equals("1")) {
                        Gson gson = new Gson();
                        bean = gson.fromJson(result, LearningPomoteBean.class);
                        List<LearningPomoteBean.ListBean> list = bean.getList();
                        if (list != null && list.size() > 0) {
                            if (list.size() <= 1) {
                                JJWorkRvOne.setVisibility(View.GONE);
                            } else {
                                JJWorkRvOne.setVisibility(View.VISIBLE);
                            }
                            listTitleData.addAll(list);
                            mCodes = list.get(0).getCode();
                            getContentData(mCodes);
                        }
                        titleAdapter.notifyDataSetChanged();
                    } else if (status.equals("9")) {
                        ToastUtil.showShort(mContext, msg);
                        Intent i = new Intent(mContext, LoginActivity.class);
                        startActivity(i);
                        mContext.finish();
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

    private void getNewContentData() {
        pageIndex = 1;
        if (listContentData != null && listContentData.size() > 0) {
            listContentData.clear();
        }
        contentAdapter.notifyDataSetChanged();
        getContentData(mCodes);
    }

    /**
     * 获取学习宣传的内容
     */
    private void getContentData(String newsType) {
        Control.getNewsList(mContext, newsType, String.valueOf(pageIndex), pageSizes, new Callback.CommonCallback<String>() {
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
                            listContentData.addAll(list);
                            emptyLoadingLayoutLl.setVisibility(View.GONE);
                            JJWorkRvTwo.setVisibility(View.VISIBLE);
                        } else if (LoadingFlag.equals("1") && listContentData.size() >= 0) {
                            emptyLoadingLayoutLl.setVisibility(View.GONE);
                            JJWorkRvTwo.setVisibility(View.VISIBLE);
                        } else {
                            emptyLoadingLayoutLl.setVisibility(View.VISIBLE);
                            JJWorkRvTwo.setVisibility(View.GONE);
                        }
                        contentAdapter.notifyDataSetChanged();
                    } else if (status.equals("9")) {
                        ToastUtil.showShort(mContext, msg);
                        Intent i = new Intent(mContext, LoginActivity.class);
                        startActivity(i);
                        mContext.finish();
                    } else {
                        emptyLoadingLayoutLl.setVisibility(View.VISIBLE);
                        JJWorkRvTwo.setVisibility(View.GONE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                JJWorkRefresh.setRefreshing(false);
                ToastUtil.closeProgressDialog();
            }

            @Override
            public void onCancelled(CancelledException cex) {
                JJWorkRefresh.setRefreshing(false);
                ToastUtil.closeProgressDialog();
            }

            @Override
            public void onFinished() {
                JJWorkRefresh.setRefreshing(false);
                ToastUtil.closeProgressDialog();
            }
        });
    }
}
