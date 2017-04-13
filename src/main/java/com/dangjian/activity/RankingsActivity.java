package com.dangjian.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
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

import com.dangjian.R;
import com.dangjian.adapter.RankingsAdapter;
import com.dangjian.entity.RankingsBean;
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

public class RankingsActivity extends BaseActivity {

    @Bind(R.id.rankings_llBack)
    LinearLayout rankingsLlBack;
    @Bind(R.id.rankings_tvTitleName)
    TextView rankingsTvTitleName;
    @Bind(R.id.rankings_llTitleName)
    LinearLayout rankingsLlTitleName;
    @Bind(R.id.emptyLoadingLayout_iv)
    ImageView emptyLoadingLayoutIv;
    @Bind(R.id.emptyLoadingLayout_tvName)
    TextView emptyLoadingLayoutTvName;
    @Bind(R.id.emptyLoadingLayout_ll)
    LinearLayout emptyLoadingLayoutLl;
    @Bind(R.id.rankings_recyclerView)
    RecyclerView rankingsRecyclerView;
    private List<RankingsBean.ListBean> listData = new ArrayList<RankingsBean.ListBean>();
    private static final String TAG = "RankingsActivity";
    private RankingsAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private String mPmType = "PERSON";
    private PopupWindow popupWindow;
    private String mPaperId, mExcuteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rankings);
        ButterKnife.bind(this);
        initView();
        upDataView();
        getExtras();
    }

    private void initView() {
        linearLayoutManager = new LinearLayoutManager(mContext);
        rankingsRecyclerView.setLayoutManager(linearLayoutManager);
        showTitleSelect();
    }

    private void getExtras() {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            mPaperId = extras.getString("paperId");       //试卷id    excuteId
            mExcuteId = extras.getString("excuteId");       //试卷id    excuteId
            if (mPaperId != null && !mPaperId.equals("")) {
                getData(mPaperId, "");
            } else {
                getData("", mExcuteId);
            }
        }
    }

    private void upDataView() {
        adapter = new RankingsAdapter(mContext, listData);
        rankingsRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @OnClick({R.id.rankings_llBack, R.id.rankings_llTitleName})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rankings_llBack:
                mContext.finish();
                break;
            case R.id.rankings_llTitleName:     //条件筛选
                if (popupWindow.isShowing()) {
                    popupWindow.dismiss();
                } else {
                    popupWindow.showAsDropDown(rankingsLlTitleName);
                }
                break;
        }
    }

    private void showTitleSelect() {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View vv = inflater.inflate(R.layout.item_title_select, null);
        popupWindow = new PopupWindow(vv, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, false);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        LinearLayout llZBIn = (LinearLayout) vv.findViewById(R.id.titleSelect_llZBIn);
        llZBIn.setVisibility(View.VISIBLE);
        TextView tv = (TextView) vv.findViewById(R.id.titleSelect_tvSelect);        //支部排名
        TextView tvUser = (TextView) vv.findViewById(R.id.titleSelect_tvUser);      //个人排名
        TextView tvIn = (TextView) vv.findViewById(R.id.titleSelect_tvZBIn);      //支部内排名
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPmType = "ORG";
                if (mPaperId != null && !mPaperId.equals("")) {
                    getData(mPaperId, "");
                } else {
                    getData("", mExcuteId);
                }
                rankingsTvTitleName.setText("支部排名");
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }
            }
        });
        tvUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPmType = "PERSON";
                if (mPaperId != null && !mPaperId.equals("")) {
                    getData(mPaperId, "");
                } else {
                    getData("", mExcuteId);
                }
                rankingsTvTitleName.setText("个人排名");
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }
            }
        });
        tvIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPmType = "PERSONINORG";
                if (mPaperId != null && !mPaperId.equals("")) {
                    getData(mPaperId, "");
                } else {
                    getData("", mExcuteId);
                }
                rankingsTvTitleName.setText("支部内排名");
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }
            }
        });
    }

    /**
     * （120049）获取考试排名
     *
     * @param mPaperid
     * @param excuteid
     */
    private void getData(String mPaperid, String excuteid) {
        if (listData != null && listData.size() > 0) {
            listData.clear();
        }
        adapter.notifyDataSetChanged();
        Log.i(TAG, "getData/mPmType: " + mPmType + " ,mPaperid---" + mPaperid + " ,excuteid--" + excuteid);
        Control.getKSPMList(mContext, mPmType, mPaperid, excuteid, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    Log.i(TAG, "考试排名: " + result);
                    JSONObject jo = new JSONObject(result);
                    String status = jo.getString("status");
                    String msg = jo.getString("msg");
                    if (status != null && !status.equals("") && status.equals("1")) {
                        Gson gson = new Gson();
                        RankingsBean rankingsBean = gson.fromJson(result, RankingsBean.class);
                        List<RankingsBean.ListBean> list = rankingsBean.getList();
                        if (list != null && list.size() > 0) {
                            listData.addAll(list);
                            emptyLoadingLayoutLl.setVisibility(View.GONE);
                            rankingsRecyclerView.setVisibility(View.VISIBLE);
                        } else {
                            emptyLoadingLayoutLl.setVisibility(View.VISIBLE);
                            rankingsRecyclerView.setVisibility(View.GONE);
                        }
                        adapter.setPmType(mPmType);
                        adapter.notifyDataSetChanged();
                    } else if (status.equals("9")) {
                        ToastUtil.showShort(mContext, msg);
                        Intent i = new Intent(mContext, LoginActivity.class);
                        startActivity(i);
                        mContext.finish();
                    } else {
                        ToastUtil.showShort(mContext, msg);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e(TAG, "onError: " + ex);
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e(TAG, "onCancelled: ");
            }

            @Override
            public void onFinished() {
                Log.e(TAG, "onFinished: ");
            }
        });
    }
}
