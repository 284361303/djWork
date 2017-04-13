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
 * 总积分排名
 */
public class UserRankingActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.emptyLoadingLayout_iv)
    ImageView emptyLoadingLayoutIv;
    @Bind(R.id.emptyLoadingLayout_tvName)
    TextView emptyLoadingLayoutTvName;
    @Bind(R.id.emptyLoadingLayout_ll)
    LinearLayout emptyLoadingLayoutLl;
    @Bind(R.id.ranking_recyclerView)
    RecyclerView rankingRecyclerView;
    @Bind(R.id.ranking_refresh)
    SwipeRefreshLayout rankingRefresh;
    @Bind(R.id.ranking_ll)
    LinearLayout rankingLl;
    @Bind(R.id.ranking_tvSum)
    TextView rankingTvSum;
    @Bind(R.id.ranking_userPic)
    CircleImageView rankingUserPic;
    @Bind(R.id.ranking_tvName)
    TextView rankingTvName;
    @Bind(R.id.ranking_tvAll)
    TextView rankingTvAll;
    @Bind(R.id.ranking_tvSubmit)
    TextView rankingTvSubmit;
    @Bind(R.id.userRanking_llBack)
    LinearLayout userRankingLlBack;
    @Bind(R.id.userRanking_tvTitleName)
    TextView userRankingTvTitleName;
    @Bind(R.id.userRanking_llTitleName)
    LinearLayout userRankingLlTitleName;
    @Bind(R.id.userRanking_tvRight)
    TextView userRankingTvRight;
    private LinearLayoutManager linearLayoutManager;
    private UserRankingAdapter adapter;
    private String pageSizes = "10";   //每页显示条数
    private int pageIndex = 1;    //当前页数
    private boolean isCanScroll = true;  //上拉加载更多的状态
    private String LoadingFlag = "0";    //判断是否有商品是否第一次
    private List<UserRankingBean.ListBean> listData = new ArrayList<UserRankingBean.ListBean>();
    private static final String TAG = "UserRankingActivity";
    private String mType = "TOTAL";
    private PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_ranking);
        ButterKnife.bind(this);
        initView();
        upDataView();
        getData();
    }

    private void initView() {
        linearLayoutManager = new LinearLayoutManager(this);
        rankingRecyclerView.setLayoutManager(linearLayoutManager);
        rankingRefresh.setOnRefreshListener(this);
        rankingRefresh.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);
        showTitleSelect();
    }

    private void upDataView() {
        adapter = new UserRankingAdapter(mContext, listData);
        rankingRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @OnClick({R.id.userRanking_llBack, R.id.userRanking_tvRight, R.id.ranking_tvSubmit, R.id.userRanking_llTitleName})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.userRanking_llBack:
                mContext.finish();
                break;
            case R.id.userRanking_tvRight:   //明细
                Intent ii = new Intent(mContext, JiFenLogActivity.class);
                startActivity(ii);
                break;
            case R.id.ranking_tvSubmit:     //兑换
                submitPointApply();
                break;
            case R.id.userRanking_llTitleName:  //中间的条件筛选
                if (popupWindow.isShowing()) {
                    popupWindow.dismiss();
                } else {
                    popupWindow.showAsDropDown(userRankingLlTitleName);
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
        pageIndex = 1;
        LoadingFlag = "0";
        adapter.notifyDataSetChanged();
        getData();
    }

    /**
     * 头部筛选样式框
     */
    private void showTitleSelect() {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View vv = inflater.inflate(R.layout.item_title_select, null);
        popupWindow = new PopupWindow(vv, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, false);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        TextView tv = (TextView) vv.findViewById(R.id.titleSelect_tvSelect);        //总排名
        TextView tvUser = (TextView) vv.findViewById(R.id.titleSelect_tvUser);      //支部内排名
        tv.setText("总排名");
        tvUser.setText("支部内排名");
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mType = "TOTAL";
                getNewData();
                userRankingTvTitleName.setText("总排名");
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }
            }
        });
        tvUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mType = "TOTAL_PERSONINORG";
                getNewData();
                userRankingTvTitleName.setText("支部内排名");
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
                        Glide.with(mContext).load(APIManager.ImagePath + Config.mIconPath).error(R.drawable.all_smallhead).into(rankingUserPic);
                        if (Config.mNickName != null && !Config.mNickName.equals("")) {     //有昵称就显示昵称，没有则显示登录名
                            rankingTvName.setText(Config.mNickName);
                        } else {
                            rankingTvName.setText(Config.mLoginName);
                        }
                        rankingTvSum.setText(userRankingBean.getMyRank());
                        rankingTvAll.setText(userRankingBean.getMyPoints());
                        if (list != null && list.size() > 0) {
                            LoadingFlag = "1";
                            isCanScroll = true;
                            listData.addAll(list);
                            emptyLoadingLayoutLl.setVisibility(View.GONE);
                            rankingRecyclerView.setVisibility(View.VISIBLE);
                        } else if (LoadingFlag.equals("1") && listData.size() >= 0) {
                            emptyLoadingLayoutLl.setVisibility(View.GONE);
                            rankingRecyclerView.setVisibility(View.VISIBLE);
                        } else {
                            emptyLoadingLayoutLl.setVisibility(View.VISIBLE);
                            rankingRecyclerView.setVisibility(View.GONE);
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
                        rankingRecyclerView.setVisibility(View.GONE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                ToastUtil.closeProgressDialog();
                rankingRefresh.setRefreshing(false);
            }

            @Override
            public void onCancelled(CancelledException cex) {
                ToastUtil.closeProgressDialog();
                rankingRefresh.setRefreshing(false);
            }

            @Override
            public void onFinished() {
                ToastUtil.closeProgressDialog();
                rankingRefresh.setRefreshing(false);
            }
        });
    }

    /**
     * （11009）提交积分兑换申请
     */
    private void submitPointApply() {
        Control.submitPointApply(mContext, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    Log.i(TAG, "积分兑换: " + result);
                    JSONObject jo = new JSONObject(result);
                    String status = jo.getString("status");
                    String msg = jo.getString("msg");
                    ToastUtil.showShort(mContext, msg);
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
}
