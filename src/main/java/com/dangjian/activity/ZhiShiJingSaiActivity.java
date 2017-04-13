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
import com.dangjian.adapter.ZhiShiJingSaiAdapter;
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
import butterknife.OnClick;

/**
 * sg 2016年9月30日10:26:51
 * 知识竞赛题库列表页面
 */
public class ZhiShiJingSaiActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

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
    @Bind(R.id.jingSai_recyclerView)
    RecyclerView jingSaiRecyclerView;
    @Bind(R.id.jingSai_refresh)
    SwipeRefreshLayout jingSaiRefresh;
    private LinearLayoutManager linearLayoutManager;
    private ZhiShiJingSaiAdapter adapter;
    private String pageSizes = "10";   //每页显示条数
    private int pageIndex = 1;    //当前页数
    private boolean isCanScroll = true;  //上拉加载更多的状态
    private String LoadingFlag = "0";    //判断是否有商品是否第一次
    private List<TiKuLianXiBean.ListBean> listData = new ArrayList<TiKuLianXiBean.ListBean>();
    private static final String TAG = "JingSaiActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhi_shi_jing_sai);
        ButterKnife.bind(this);
        initView();
        upDataView();
        getData();
    }

    private void initView() {
        titleOneTextTvTitleName.setText("知识竞赛");
        linearLayoutManager = new LinearLayoutManager(mContext);
        jingSaiRecyclerView.setLayoutManager(linearLayoutManager);
        jingSaiRefresh.setOnRefreshListener(this);
        jingSaiRefresh.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);
        jingSaiRecyclerView.addOnScrollListener(new LoadMoreListener());
    }

    private void upDataView() {
        adapter = new ZhiShiJingSaiAdapter(mContext, listData);
        initListener();
        jingSaiRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void initListener() {
        adapter.setOnItemClickListener(new ZhiShiJingSaiAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                TiKuLianXiBean.ListBean lists = listData.get(position);
                String excuteId = lists.getExcuteId();
                getPaperResult(excuteId, position, lists);
            }
        });
    }

    @OnClick(R.id.titleOneText_llBack)
    public void onClick() {
        mContext.finish();
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
        getNewData();
    }

    private void getNewData(){
        pageIndex=1;
        if (listData != null && listData.size() > 0) {
            listData.clear();
        }
        adapter.notifyDataSetChanged();
        getData();
    }

    /**
     * （12004）获取考试、练习列表
     */
    private void getData() {
        Control.getExaExcuteList(mContext, "competition", String.valueOf(pageIndex), pageSizes, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    Log.i(TAG, "竞赛: " + result);
                    JSONObject jo = new JSONObject(result);
                    String status = jo.getString("status");
                    String msg = jo.getString("msg");
                    if (!status.equals("") && status.equals("1")) {
                        Gson gson = new Gson();
                        TiKuLianXiBean tiKuLianXiBean = gson.fromJson(result, TiKuLianXiBean.class);
                        List<TiKuLianXiBean.ListBean> list = tiKuLianXiBean.getList();
                        Log.i(TAG, "onSuccess/list.size: " + list.size());
                        Log.i(TAG, "onSuccess/LoadingFlag: " + LoadingFlag);
                        if (list != null && list.size() > 0) {
                            LoadingFlag = "1";
                            isCanScroll = true;
                            listData.addAll(list);
                            emptyLoadingLayoutLl.setVisibility(View.GONE);
                            jingSaiRecyclerView.setVisibility(View.VISIBLE);
                        } else if (LoadingFlag.equals("1") && listData.size() >= 0) {
                            emptyLoadingLayoutLl.setVisibility(View.GONE);
                            jingSaiRecyclerView.setVisibility(View.VISIBLE);
                        } else {
                            emptyLoadingLayoutLl.setVisibility(View.VISIBLE);
                            jingSaiRecyclerView.setVisibility(View.GONE);
                        }
                        adapter.notifyDataSetChanged();
                    } else if (status.equals("9")) {
                        ToastUtil.showShort(mContext, msg);
                        Intent i = new Intent(mContext, LoginActivity.class);
                        startActivity(i);
                        mContext.finish();
                    } else {
                        emptyLoadingLayoutLl.setVisibility(View.VISIBLE);
                        jingSaiRecyclerView.setVisibility(View.GONE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                jingSaiRefresh.setRefreshing(false);
            }

            @Override
            public void onCancelled(CancelledException cex) {
                jingSaiRefresh.setRefreshing(false);
            }

            @Override
            public void onFinished() {
                jingSaiRefresh.setRefreshing(false);
            }
        });
    }

    /**
     * （12005）获得考试结果
     *
     * @param mExcuteId
     */
    private void getPaperResult(String mExcuteId, int mPosition, final TiKuLianXiBean.ListBean lists) {
        Control.getPaperResult(mContext, mExcuteId, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    Log.i(TAG, "考试结果: " + result);
                    JSONObject jo = new JSONObject(result);
                    String paperId = jo.getString("paperId");       //试卷id，为-1时没有考试结果
                    String exaStatus = jo.getString("exaStatus");   //考试状态，ING 进行中,FINISHED已完成，NOTSTART没开始，DISABLED停用
                    String rightQuestions = jo.getString("rightQuestions");     //答对题数
                    String points = jo.getString("points");     //获取积分
                    String score = jo.getString("score");     //考试得分
                    if (paperId.equals("-1")) {
                        if (exaStatus.equals("ING")) {
                            Intent i = new Intent(mContext, JingSaiStartActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("TiKuLianXiBean", lists);
                            i.putExtras(bundle);
                            startActivity(i);
                        } else {
                            ToastUtil.showShort(mContext, "该竞赛已结束");
                        }
                    } else {
                        Intent i = new Intent(mContext, ExamResultActivity.class);      //跳转到考试结果页面
                        i.putExtra("activity", "ZhiShiJingSaiActivity");
                        i.putExtra("rightQuestions", rightQuestions);
                        i.putExtra("points", points);
                        i.putExtra("score", score);
                        i.putExtra("paperId", paperId);
                        startActivity(i);
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
