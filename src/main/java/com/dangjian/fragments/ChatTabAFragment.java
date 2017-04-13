package com.dangjian.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.dangjian.activity.ZiLiaoWebActivity;
import com.dangjian.adapter.ChatAdapter;
import com.dangjian.entity.ChatBean;
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
 * 互动交流中的全部帖子
 */
public class ChatTabAFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.emptyLoadingLayout_iv)
    ImageView emptyLoadingLayoutIv;
    @Bind(R.id.emptyLoadingLayout_tvName)
    TextView emptyLoadingLayoutTvName;
    @Bind(R.id.emptyLoadingLayout_ll)
    LinearLayout emptyLoadingLayoutLl;
    @Bind(R.id.chatTabA_recyclerView)
    RecyclerView chatTabARecyclerView;
    @Bind(R.id.chatTabA_refresh)
    SwipeRefreshLayout chatTabARefresh;
    private FragmentActivity mContext;
    private LinearLayoutManager linearLayoutManager;
    private ChatAdapter adapter;
    private String mIsMyXD = "0";
    private String pageSizes = "10";   //每页显示条数
    private int pageIndex = 1;    //当前页数
    private boolean isCanScroll = true;  //上拉加载更多的状态
    private String LoadingFlag = "0";    //判断是否有商品是否第一次
    private List<ChatBean.ListBean> listData = new ArrayList<ChatBean.ListBean>();
    private static final String TAG = "ChatTabAFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat_tab_a, container, false);
        ButterKnife.bind(this, view);
        mContext = getActivity();
        initView();
        upDataView();
        return view;
    }

    private void initView() {
        linearLayoutManager = new LinearLayoutManager(mContext);
        chatTabARecyclerView.setLayoutManager(linearLayoutManager);
        chatTabARefresh.setOnRefreshListener(this);
        chatTabARefresh.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);
        chatTabARecyclerView.addOnScrollListener(new LoadMoreListener());
    }

    private void upDataView() {
        adapter = new ChatAdapter(mContext, listData);
        initListener();
        chatTabARecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void initListener() {
        adapter.setOnItemClickListener(new ChatAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                String xdId = listData.get(position).getXdId();
                Intent i = new Intent(mContext, ZiLiaoWebActivity.class);
                i.putExtra("activity", "ChatTabAFragment");
                i.putExtra("newsId", xdId);
                i.putExtra("isRead", "Y");
                startActivity(i);
            }
        });
    }

    @Override
    public void onRefresh() {
        pageIndex = 1;
        getNewData();
    }

    @Override
    public void onResume() {
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
     * （13016）获得发帖列表(全部帖子)
     */
    private void getData() {
        Control.getXDList(mContext, mIsMyXD, "COMUNICATION", String.valueOf(pageIndex), pageSizes, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    Log.i(TAG, "发帖列表: " + result);
                    JSONObject jo = new JSONObject(result);
                    String status = jo.getString("status");
                    String msg = jo.getString("msg");
                    if (status != null && !status.equals("") && status.equals("1")) {
                        Gson gson = new Gson();
                        ChatBean chatBean = gson.fromJson(result, ChatBean.class);
                        List<ChatBean.ListBean> list = chatBean.getList();
                        if (list != null && list.size() > 0) {
                            LoadingFlag = "1";
                            isCanScroll = true;
                            listData.addAll(list);
                            emptyLoadingLayoutLl.setVisibility(View.GONE);
                            chatTabARecyclerView.setVisibility(View.VISIBLE);
                        } else if (LoadingFlag.equals("1") && listData.size() >= 0) {
                            emptyLoadingLayoutLl.setVisibility(View.GONE);
                            chatTabARecyclerView.setVisibility(View.VISIBLE);
                        } else {
                            emptyLoadingLayoutLl.setVisibility(View.VISIBLE);
                            chatTabARecyclerView.setVisibility(View.GONE);
                        }
                        adapter.notifyDataSetChanged();
                    } else if (status.equals("9")) {
                        ToastUtil.showShort(mContext, msg);
                        Intent i = new Intent(mContext, LoginActivity.class);
                        startActivity(i);
                    } else {
                        emptyLoadingLayoutLl.setVisibility(View.VISIBLE);
                        chatTabARecyclerView.setVisibility(View.GONE);
                        ToastUtil.showShort(mContext, msg);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                ex.printStackTrace();
                ToastUtil.closeProgressDialog();
                chatTabARefresh.setRefreshing(false);
            }

            @Override
            public void onCancelled(CancelledException cex) {
                ToastUtil.closeProgressDialog();
                chatTabARefresh.setRefreshing(false);
            }

            @Override
            public void onFinished() {
                ToastUtil.closeProgressDialog();
                chatTabARefresh.setRefreshing(false);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
