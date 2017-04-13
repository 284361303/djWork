package com.dangjian.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dangjian.R;
import com.dangjian.activity.LoginActivity;
import com.dangjian.activity.TiKuLianXiExamActivity;
import com.dangjian.adapter.ZiLiaoTabCAdapter;
import com.dangjian.entity.TiKuLianXiBean;
import com.dangjian.utils.Control;
import com.dangjian.utils.ToastUtil;
import com.google.gson.Gson;

import org.json.JSONObject;
import org.xutils.common.Callback;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * sg  2016年10月4日11:05:24
 * 学习资料中的题库练习Fragmetn
 */
public class ZiLiaoTabCFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.emptyLoadingLayout_iv)
    ImageView emptyLoadingLayoutIv;
    @Bind(R.id.emptyLoadingLayout_tvName)
    TextView emptyLoadingLayoutTvName;
    @Bind(R.id.emptyLoadingLayout_ll)
    LinearLayout emptyLoadingLayoutLl;
    @Bind(R.id.ziLiaoTabC_recyclerView)
    RecyclerView ziLiaoTabCRecyclerView;
    @Bind(R.id.ziLiaoTabC_refresh)
    SwipeRefreshLayout ziLiaoTabCRefresh;
    private FragmentActivity mContext;
    private String pageSizes = "10";   //每页显示条数
    private int pageIndex = 1;    //当前页数
    private boolean isCanScroll = true;  //上拉加载更多的状态
    private String LoadingFlag = "0";    //判断是否有商品是否第一次
    private List<TiKuLianXiBean.ListBean> listData = new ArrayList<TiKuLianXiBean.ListBean>();
    private static final String TAG = "ZiLiaoCFragment";
    private ZiLiaoTabCAdapter adapter;
    private LinearLayoutManager linearLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zi_liao_tab_c, container, false);
        ButterKnife.bind(this, view);
        mContext = getActivity();
        initView();
        upDataView();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
    }

    private void initView() {
        linearLayoutManager = new LinearLayoutManager(mContext);
        ziLiaoTabCRecyclerView.setLayoutManager(linearLayoutManager);
        ziLiaoTabCRefresh.setOnRefreshListener(this);
        ziLiaoTabCRefresh.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);
        ziLiaoTabCRecyclerView.addOnScrollListener(new LoadMoreListener());
    }

    private void upDataView() {
        adapter = new ZiLiaoTabCAdapter(mContext, listData);
        initListener();
        ziLiaoTabCRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void initListener() {
        adapter.setOnItemClickListener(new ZiLiaoTabCAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                TiKuLianXiBean.ListBean listBean = listData.get(position);
                String excuteId = listBean.getExcuteId();
                Intent i = new Intent(mContext, TiKuLianXiExamActivity.class);
                i.putExtra("excuteId", excuteId);        //考试id
                startActivity(i);
            }
        });
    }

    /**
     * （12004）获取考试、练习列表
     */
    private void getData() {
        if (listData != null && listData.size() > 0) {
            listData.clear();
        }
        adapter.notifyDataSetChanged();
        Control.getExaExcuteList(mContext, "studyDB", String.valueOf(pageIndex), pageSizes, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    Log.i(TAG, "获取考试、练习列表: " + result);
                    JSONObject jo = new JSONObject(result);
                    String status = jo.getString("status");
                    String msg = jo.getString("msg");
                    if (!status.equals("") && status.equals("1")) {
                        Gson gson = new Gson();
                        TiKuLianXiBean tiKuLianXiBean = gson.fromJson(result, TiKuLianXiBean.class);
                        List<TiKuLianXiBean.ListBean> list = tiKuLianXiBean.getList();
                        if (list != null && list.size() > 0) {
                            LoadingFlag = "1";
                            isCanScroll = true;
                            listData.addAll(list);
                            emptyLoadingLayoutLl.setVisibility(View.GONE);
                            ziLiaoTabCRecyclerView.setVisibility(View.VISIBLE);
                        } else if (LoadingFlag.equals("1") && listData.size() >= 0) {
                            emptyLoadingLayoutLl.setVisibility(View.GONE);
                            ziLiaoTabCRecyclerView.setVisibility(View.VISIBLE);
                        } else {
                            emptyLoadingLayoutLl.setVisibility(View.VISIBLE);
                            ziLiaoTabCRecyclerView.setVisibility(View.GONE);
                        }
                        adapter.notifyDataSetChanged();
                    } else if (status.equals("9")) {
                        ToastUtil.showShort(mContext, msg);
                        Intent i = new Intent(mContext, LoginActivity.class);
                        startActivity(i);
                    } else {
                        emptyLoadingLayoutLl.setVisibility(View.VISIBLE);
                        ziLiaoTabCRecyclerView.setVisibility(View.GONE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                ziLiaoTabCRefresh.setRefreshing(false);
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
                ziLiaoTabCRefresh.setRefreshing(false);
            }
        });
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

    @Override
    public void onRefresh() {
        pageIndex = 1;
        getData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
