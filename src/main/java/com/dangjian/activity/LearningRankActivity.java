package com.dangjian.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dangjian.R;
import com.dangjian.adapter.UserRankingAdapter;
import com.dangjian.entity.UserRankingBean;
import com.dangjian.utils.APIManager;
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
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 学习排名
 */
public class LearningRankActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.learningRank_llBack)
    LinearLayout learningRankLlBack;
    @Bind(R.id.learningRank_tvTitleName)
    TextView learningRankTvTitleName;
    @Bind(R.id.learningRank_llTitleSelect)
    LinearLayout learningRankLlTitleSelect;
    @Bind(R.id.emptyLoadingLayout_iv)
    ImageView emptyLoadingLayoutIv;
    @Bind(R.id.emptyLoadingLayout_tvName)
    TextView emptyLoadingLayoutTvName;
    @Bind(R.id.emptyLoadingLayout_ll)
    LinearLayout emptyLoadingLayoutLl;
    @Bind(R.id.learningRank_recyclerView)
    RecyclerView learningRankRecyclerView;
    @Bind(R.id.learningRank_refresh)
    SwipeRefreshLayout learningRankRefresh;
    @Bind(R.id.llAll)
    LinearLayout llAll;
    @Bind(R.id.learningRank_tvSum)
    TextView learningRankTvSum;
    @Bind(R.id.learningRank_userPic)
    CircleImageView learningRankUserPic;
    @Bind(R.id.learningRank_tvName)
    TextView learningRankTvName;
    @Bind(R.id.learningRank_tvAll)
    TextView learningRankTvAll;
    private LinearLayoutManager linearLayoutManager;
    private UserRankingAdapter adapter;
    private String pageSizes = "10";   //每页显示条数
    private int pageIndex = 1;    //当前页数
    private boolean isCanScroll = true;  //上拉加载更多的状态
    private String LoadingFlag = "0";    //判断是否有商品是否第一次
    private List<UserRankingBean.ListBean> listData = new ArrayList<UserRankingBean.ListBean>();
    private static final String TAG = "LearningRankActivity";
    private String mType = "LX_TOTAL";
    private PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_rank);
        ButterKnife.bind(this);
        initView();
        upDataView();
        getData();
    }

    private void initView() {
        learningRankTvTitleName.setText("总排名");
        linearLayoutManager = new LinearLayoutManager(this);
        learningRankRecyclerView.setLayoutManager(linearLayoutManager);
        learningRankRefresh.setOnRefreshListener(this);
        learningRankRefresh.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);
        showTitleSelect();
    }

    private void upDataView() {
        adapter = new UserRankingAdapter(mContext, listData);
        learningRankRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @OnClick({R.id.learningRank_llBack, R.id.learningRank_llTitleSelect})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.learningRank_llBack:
                mContext.finish();
                break;
            case R.id.learningRank_llTitleSelect:   //头部筛选
                if (popupWindow.isShowing()) {
                    popupWindow.dismiss();
                } else {
                    popupWindow.showAsDropDown(learningRankLlTitleSelect);
                }
                break;
        }
    }

    @Override
    public void onRefresh() {
        getNewData();
    }

    private void getNewData() {
        if (listData != null && listData.size() > 0) {
            listData.clear();
        }
        LoadingFlag = "0";
        pageIndex = 1;
        adapter.notifyDataSetChanged();
        getData();
    }

    private void showTitleSelect() {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View vv = inflater.inflate(R.layout.pop_title_select4, null);
        popupWindow = new PopupWindow(vv, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, false);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        TextView tvOne = (TextView) vv.findViewById(R.id.titleSelect4_tvOne);        //总排名
        TextView tvTwo = (TextView) vv.findViewById(R.id.titleSelect4_tvTwo);      //月
        TextView tvThree = (TextView) vv.findViewById(R.id.titleSelect4_tvThree);      //周
        TextView tvFour = (TextView) vv.findViewById(R.id.titleSelect4_tvFour);      //支部内排名
        tvOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mType = "LX_TOTAL";
                learningRankTvTitleName.setText("总排名");
                getNewData();
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }
            }
        });
        tvTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mType = "MONTH";
                learningRankTvTitleName.setText("月排名");
                getNewData();
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }
            }
        });
        tvThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mType = "WEEK";
                getNewData();
                learningRankTvTitleName.setText("周排名");
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }
            }
        });
        tvFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mType = "LX_PERSONINORG";
                getNewData();
                learningRankTvTitleName.setText("支部内排名");
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }
            }
        });
    }

    /**
     * （15001）积分排名
     */
    private void getData() {
        Control.getRank(mContext, mType, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    Log.i(TAG, "积分排名: " + result);
                    JSONObject jo = new JSONObject(result);
                    String status = jo.getString("status");
                    String msg = jo.getString("msg");
                    if (status != null && !status.equals("") && status.equals("1")) {
                        Gson gson = new Gson();
                        UserRankingBean userRankingBean = gson.fromJson(result, UserRankingBean.class);
                        List<UserRankingBean.ListBean> list = userRankingBean.getList();
                        Glide.with(mContext).load(APIManager.ImagePath + Config.mIconPath).error(R.drawable.all_smallhead).into(learningRankUserPic);
                        if (Config.mNickName != null && !Config.mNickName.equals("")) {     //有昵称就显示昵称，没有则显示登录名
                            learningRankTvName.setText(Config.mNickName);
                        } else {
                            learningRankTvName.setText(Config.mLoginName);
                        }
                        learningRankTvSum.setText(userRankingBean.getMyRank());
                        learningRankTvAll.setText(userRankingBean.getMyPoints());
                        if (list != null && list.size() > 0) {
                            LoadingFlag = "1";
                            isCanScroll = true;
                            listData.addAll(list);
                            emptyLoadingLayoutLl.setVisibility(View.GONE);
                            learningRankRecyclerView.setVisibility(View.VISIBLE);
                        } else if (LoadingFlag.equals("1") && listData.size() >= 0) {
                            emptyLoadingLayoutLl.setVisibility(View.GONE);
                            learningRankRecyclerView.setVisibility(View.VISIBLE);
                        } else {
                            emptyLoadingLayoutLl.setVisibility(View.VISIBLE);
                            learningRankRecyclerView.setVisibility(View.GONE);
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
                        learningRankRecyclerView.setVisibility(View.GONE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                ToastUtil.closeProgressDialog();
                learningRankRefresh.setRefreshing(false);
            }

            @Override
            public void onCancelled(CancelledException cex) {
                ToastUtil.closeProgressDialog();
                learningRankRefresh.setRefreshing(false);
            }

            @Override
            public void onFinished() {
                ToastUtil.closeProgressDialog();
                learningRankRefresh.setRefreshing(false);
            }
        });
    }
}
