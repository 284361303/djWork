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
import com.dangjian.adapter.MyCollectAdapter;
import com.dangjian.entity.MyCollectBean;
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
 * 我的收藏
 */
public class MyCollectActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.titleOneText_llBack)
    LinearLayout titleOneTextLlBack;
    @Bind(R.id.titleOneText_tvTitleName)
    TextView titleOneTextTvTitleName;
    @Bind(R.id.emptyLoadingLayout_iv)
    ImageView emptyLoadingLayoutIv;
    @Bind(R.id.emptyLoadingLayout_tvName)
    TextView emptyLoadingLayoutTvName;
    @Bind(R.id.emptyLoadingLayout_ll)
    LinearLayout emptyLoadingLayoutLl;
    @Bind(R.id.myCollect_recyclerView)
    RecyclerView myCollectRecyclerView;
    @Bind(R.id.myCollect_refresh)
    SwipeRefreshLayout myCollectRefresh;
    private MyCollectAdapter adapter;
    private String pageSizes = "10";   //每页显示条数
    private int pageIndex = 1;    //当前页数
    private boolean isCanScroll = true;  //上拉加载更多的状态
    private String LoadingFlag = "0";    //判断是否有商品是否第一次
    private List<MyCollectBean.ListBean> listData = new ArrayList<MyCollectBean.ListBean>();
    private static final String TAG = "MyCollectActivity";
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collect);
        ButterKnife.bind(this);
        initView();
        upDataContentView();
    }

    private void initView() {
        titleOneTextTvTitleName.setText("我的收藏");
        linearLayoutManager = new LinearLayoutManager(this);
        myCollectRecyclerView.setLayoutManager(linearLayoutManager);
        myCollectRefresh.setOnRefreshListener(this);
        myCollectRefresh.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);
        myCollectRecyclerView.addOnScrollListener(new LoadMoreListener());
    }

    private void upDataContentView() {
        adapter = new MyCollectAdapter(mContext, listData);
        initListener();
        myCollectRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void initListener() {
        adapter.setOnItemClickListener(new MyCollectAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                MyCollectBean.ListBean listBean = listData.get(position);
                String newsId = listBean.getNewsId();
                Intent i = new Intent(mContext, ZiLiaoWebActivity.class);
                i.putExtra("activity", "MyCollectActivity");
                i.putExtra("newsId", newsId);
                i.putExtra("isRead", "Y");
                startActivity(i);
            }
        });
    }

    @OnClick(R.id.titleOneText_llBack)
    public void onClick() {
        mContext.finish();
    }

    @Override
    public void onRefresh() {
        pageIndex = 1;
        getNewData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        onRefresh();
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

    private void getNewData() {
        if (listData != null && listData.size() > 0) {
            listData.clear();
        }
        adapter.notifyDataSetChanged();
        getData();
    }

    /**
     * （11005）获得我的收藏列表
     */
    private void getData() {
        Control.getMyFavorite(mContext, String.valueOf(pageIndex), pageSizes, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    Log.i(TAG, "我的收藏: " + result);
                    JSONObject jo = new JSONObject(result);
                    String status = jo.getString("status");
                    String msg = jo.getString("msg");
                    if (status != null && !status.equals("") && status.equals("1")) {
                        Gson gson = new Gson();
                        MyCollectBean myCollectBean = gson.fromJson(result, MyCollectBean.class);
                        List<MyCollectBean.ListBean> list = myCollectBean.getList();
                        if (list != null && list.size() > 0) {
                            LoadingFlag = "1";
                            isCanScroll = true;
                            listData.addAll(list);
                            emptyLoadingLayoutLl.setVisibility(View.GONE);
                            myCollectRecyclerView.setVisibility(View.VISIBLE);
                        } else if (LoadingFlag.equals("1") && listData.size() >= 0) {
                            emptyLoadingLayoutLl.setVisibility(View.GONE);
                            myCollectRecyclerView.setVisibility(View.VISIBLE);
                        } else {
                            emptyLoadingLayoutLl.setVisibility(View.VISIBLE);
                            myCollectRecyclerView.setVisibility(View.GONE);
                        }
                        adapter.notifyDataSetChanged();
                    } else if (status.equals("9")) {
                        ToastUtil.showShort(mContext, msg);
                        Intent i = new Intent(mContext, LoginActivity.class);
                        startActivity(i);
                        mContext.finish();
                    } else {
                        ToastUtil.showShort(mContext, msg);
                        emptyLoadingLayoutLl.setVisibility(View.VISIBLE);
                        myCollectRecyclerView.setVisibility(View.GONE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                myCollectRefresh.setRefreshing(false);
                ToastUtil.closeProgressDialog();
            }

            @Override
            public void onCancelled(CancelledException cex) {
                myCollectRefresh.setRefreshing(false);
                ToastUtil.closeProgressDialog();
            }

            @Override
            public void onFinished() {
                myCollectRefresh.setRefreshing(false);
                ToastUtil.closeProgressDialog();
            }
        });
    }
}
