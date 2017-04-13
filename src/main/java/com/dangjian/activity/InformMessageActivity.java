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
import com.dangjian.adapter.LearingTitleAdapter;
import com.dangjian.adapter.informMessageAdapter;
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
 * 通知公告页面
 */
public class InformMessageActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.titleRightText_llBack)
    LinearLayout titleRightTextLlBack;
    @Bind(R.id.titleRightText_tvTitleName)
    TextView titleRightTextTvTitleName;
    @Bind(R.id.titleRightText_tvRightName)
    TextView titleRightTextTvRightName;
    @Bind(R.id.informMessage_recyclerView)
    RecyclerView informMessageRecyclerView;
    @Bind(R.id.informMessage_refresh)
    SwipeRefreshLayout informMessageRefresh;
    @Bind(R.id.emptyLoadingLayout_iv)
    ImageView emptyLoadingLayoutIv;
    @Bind(R.id.emptyLoadingLayout_tvName)
    TextView emptyLoadingLayoutTvName;
    @Bind(R.id.emptyLoadingLayout_ll)
    LinearLayout emptyLoadingLayoutLl;
    @Bind(R.id.informMessage_rvOne)
    RecyclerView informMessageRvOne;
    private String pageSizes = "10";   //每页显示条数
    private int pageIndex = 1;    //当前页数
    private boolean isCanScroll = true;  //上拉加载更多的状态
    private String LoadingFlag = "0";    //判断是否有商品是否第一次
    private LinearLayoutManager linearLayoutManager, horizontalManager;
    private List<LearningMterialsBean.ListBean> listData = new ArrayList<LearningMterialsBean.ListBean>();
    private List<LearningPomoteBean.ListBean> listTitleData = new ArrayList<LearningPomoteBean.ListBean>();
    private static final String TAG = "InformMessageActivity";
    private informMessageAdapter adapter;
    private LearingTitleAdapter titleAdapter;
    private String mCodes = "";
    private LearningPomoteBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inform_message);
        ButterKnife.bind(this);
        initView();
        upDataTitleView();
        upDataView();
        getTitleData();
    }

    private void initView() {
        if (Config.DW_ADMIN_ROLE.equals("") && Config.ZB_ADMIN_ROLE.equals("") && Config.XC_ADMIN_ROLE.equals("") &&
                Config.ZZ_ADMIN_ROLE.equals("") && Config.JJ_ADMIN_ROLE.equals("") && Config.QN_AMIN_ROLE.equals("") && Config.GH_ADMIN_ROLE.equals("")) {
            titleRightTextTvRightName.setVisibility(View.GONE);
        } else {
            titleRightTextTvRightName.setVisibility(View.VISIBLE);
        }
        titleRightTextTvTitleName.setText("通知公告");
        titleRightTextTvRightName.setText("发布");
        linearLayoutManager = new LinearLayoutManager(mContext);
        horizontalManager = new LinearLayoutManager(mContext);
        horizontalManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        informMessageRvOne.setLayoutManager(horizontalManager);
        informMessageRecyclerView.setLayoutManager(linearLayoutManager);
        informMessageRefresh.setOnRefreshListener(this);
        informMessageRefresh.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);
        informMessageRecyclerView.addOnScrollListener(new LoadMoreListener());
    }

    private void upDataTitleView() {
        titleAdapter = new LearingTitleAdapter(mContext, listTitleData);
        initTitleListener();
        informMessageRvOne.setAdapter(titleAdapter);
        titleAdapter.notifyDataSetChanged();
    }

    private void upDataView() {
        adapter = new informMessageAdapter(mContext, listData);
        initListener();
        informMessageRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void initTitleListener() {
        titleAdapter.setOnItemClickListener(new LearingTitleAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                LearningPomoteBean.ListBean listBean = listTitleData.get(position);
                mCodes = listBean.getCode();
                getNewData();
                //重置点击显示效果
                int itemCount = titleAdapter.getItemCount();
                for (int i = 0; i < itemCount; i++) {
                    View childAt = informMessageRvOne.getChildAt(i);
                    LearingTitleAdapter.TitleViewHolder titleViewHolder = (LearingTitleAdapter.TitleViewHolder) informMessageRvOne.getChildViewHolder(childAt);
                    titleViewHolder.itemLearingTitleTv.setTextColor(Color.parseColor("#999999"));
                    titleViewHolder.itemLearingTitleView.setVisibility(View.INVISIBLE);
                }
                //设置点击选中效果
                View childAt = informMessageRvOne.getChildAt(position);
                LearingTitleAdapter.TitleViewHolder titleViewHolder = (LearingTitleAdapter.TitleViewHolder) informMessageRvOne.getChildViewHolder(childAt);
                titleViewHolder.itemLearingTitleTv.setTextColor(Color.parseColor("#333333"));
                titleViewHolder.itemLearingTitleView.setVisibility(View.VISIBLE);
                titleAdapter.listenerFlag(position);
                titleAdapter.notifyDataSetChanged();
            }
        });
    }

    private void initListener() {
        adapter.setOnItemClickListener(new informMessageAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                LearningMterialsBean.ListBean listBean = listData.get(position);
                String newsId = listBean.getNewsId();
                String isRead = listBean.getIsRead();
                Intent i = new Intent(mContext, ZiLiaoWebActivity.class);
                i.putExtra("activity", "InformMessageActivity");
                i.putExtra("newsId", newsId);
                i.putExtra("isRead", isRead);
                startActivityForResult(i, 201);
            }
        });
    }

    private void getNewData() {
        pageIndex = 1;
        if (listData != null && listData.size() > 0) {
            listData.clear();
        }
        adapter.notifyDataSetChanged();
        getData(mCodes);
    }

    /**
     * （13001）获得栏目的二级菜单(通知公告)
     */
    private void getTitleData() {
        if (listTitleData != null && listTitleData.size() > 0) {
            listTitleData.clear();
        }
        titleAdapter.notifyDataSetChanged();
        Control.getSecondMenu(mContext, "NOTICEMENU", new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    Log.i(TAG, "宣传菜单: " + result);
                    JSONObject jo = new JSONObject(result);
                    String status = jo.getString("status");
                    String msg = jo.getString("msg");
                    if (status.equals("1")) {
                        Gson gson = new Gson();
                        bean = gson.fromJson(result, LearningPomoteBean.class);
                        List<LearningPomoteBean.ListBean> list = bean.getList();
                        if (list != null && list.size() > 0) {
                            if (list.size() <= 1) {
                                informMessageRvOne.setVisibility(View.GONE);
                            } else {
                                informMessageRvOne.setVisibility(View.VISIBLE);
                            }
                            listTitleData.addAll(list);
                            mCodes = list.get(0).getCode();
                            getData(mCodes);
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

    /**
     * （13002）获得通知列表
     */
    private void getData(String mCodes) {
        Control.getNewsList(mContext, mCodes, String.valueOf(pageIndex), pageSizes, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    Log.i(TAG, "通知列表: " + result);
                    JSONObject jo = new JSONObject(result);
                    String status = jo.getString("status");
                    String msg = jo.getString("msg");
                    if (status != null && !status.equals("") && status.equals("1")) {
                        Gson gson = new Gson();
                        LearningMterialsBean bean = gson.fromJson(result, LearningMterialsBean.class);
                        List<LearningMterialsBean.ListBean> list = bean.getList();
                        if (list != null && list.size() > 0) {
                            LoadingFlag = "1";
                            isCanScroll = true;
                            listData.addAll(list);
                            emptyLoadingLayoutLl.setVisibility(View.GONE);
                            informMessageRecyclerView.setVisibility(View.VISIBLE);
                        } else if (LoadingFlag.equals("1") && listData.size() >= 0) {
                            emptyLoadingLayoutLl.setVisibility(View.GONE);
                            informMessageRecyclerView.setVisibility(View.VISIBLE);
                        } else {
                            emptyLoadingLayoutLl.setVisibility(View.VISIBLE);
                            informMessageRecyclerView.setVisibility(View.GONE);
                        }
                        adapter.notifyDataSetChanged();
                    } else if (status.equals("9")) {
                        ToastUtil.showShort(mContext, msg);
                        Intent i = new Intent(mContext, LoginActivity.class);
                        startActivity(i);
                        mContext.finish();
                    } else {
                        emptyLoadingLayoutLl.setVisibility(View.VISIBLE);
                        informMessageRecyclerView.setVisibility(View.GONE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                informMessageRefresh.setRefreshing(false);
                ToastUtil.closeProgressDialog();
            }

            @Override
            public void onCancelled(CancelledException cex) {
                informMessageRefresh.setRefreshing(false);
                ToastUtil.closeProgressDialog();
            }

            @Override
            public void onFinished() {
                informMessageRefresh.setRefreshing(false);
                ToastUtil.closeProgressDialog();
            }
        });
    }

    @OnClick({R.id.titleRightText_llBack, R.id.titleRightText_tvRightName})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titleRightText_llBack:
                mContext.finish();
                break;
            case R.id.titleRightText_tvRightName:       //发布按钮
                Intent i = new Intent(mContext, ReleaseActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("LearningPomoteBean", bean);
                i.putExtras(bundle);
                startActivityForResult(i, 200);
                break;
        }
    }

    @Override
    public void onRefresh() {
        getNewData();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 200 || resultCode == 201) {        //200发布成功返回刷新, 201浏览详情页返回的把未读标识符去掉刷新
            onRefresh();
        }
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
                getData(mCodes);
            }
        }
    }
}
