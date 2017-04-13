package com.dangjian.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dangjian.R;
import com.dangjian.adapter.ZZWorkContentAdapter;
import com.dangjian.adapter.ZZWorkTitleAdapter;
import com.dangjian.entity.LearningMterialsBean;
import com.dangjian.entity.LearningPomoteBean;
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
 * 组织工作
 */
public class ZZWorkActivity extends BaseActivity {

    @Bind(R.id.titleRightText_llBack)
    LinearLayout titleRightTextLlBack;
    @Bind(R.id.titleRightText_tvTitleName)
    TextView titleRightTextTvTitleName;
    @Bind(R.id.zzWork_rvOne)
    RecyclerView zzWorkRvOne;
    @Bind(R.id.emptyLoadingLayout_iv)
    ImageView emptyLoadingLayoutIv;
    @Bind(R.id.emptyLoadingLayout_tvName)
    TextView emptyLoadingLayoutTvName;
    @Bind(R.id.emptyLoadingLayout_ll)
    LinearLayout emptyLoadingLayoutLl;
    @Bind(R.id.zzWork_rvTwo)
    RecyclerView zzWorkRvTwo;
    @Bind(R.id.zzWork_ivTitleView)
    ImageView zzWorkIvTitleView;
    private LinearLayoutManager linearLayoutManager, horizontalManager;
    private static final String TAG = "ZZWorkActivity";
    private List<LearningPomoteBean.ListBean> listTitleData = new ArrayList<LearningPomoteBean.ListBean>();
    private List<LearningMterialsBean.ListBean> listContentData = new ArrayList<LearningMterialsBean.ListBean>();
    private LearningPomoteBean bean;
    private ZZWorkTitleAdapter titleAdapter;
    private ZZWorkContentAdapter contentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zzwork);
        ButterKnife.bind(this);
        initView();
        upDataTitleView();
        upDataContentView();
        getModulePic(zzWorkRvTwo);  //给头部图片设置背景。接口上读取出来
        getContentData();
    }

    private void initView() {
        titleRightTextTvTitleName.setText("组织工作");
        /*titleRightTextTvRightName.setText("发布");
        if ((Config.ZZ_ADMIN_ROLE != null && Config.ZZ_ADMIN_ROLE.equals("ZZ_ADMIN")) || Config.DW_ADMIN_ROLE.equals("DW_ADMIN")) {
            titleRightTextTvRightName.setVisibility(View.VISIBLE);
        } else {
            titleRightTextTvRightName.setVisibility(View.GONE);
        }*/
        linearLayoutManager = new LinearLayoutManager(mContext);
        horizontalManager = new LinearLayoutManager(mContext);
        horizontalManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        zzWorkRvOne.setLayoutManager(horizontalManager);
        zzWorkRvTwo.setLayoutManager(linearLayoutManager);
    }

    private void upDataTitleView() {
        titleAdapter = new ZZWorkTitleAdapter(mContext, listTitleData);
        initTitleListener();
        zzWorkRvOne.setAdapter(titleAdapter);
        titleAdapter.notifyDataSetChanged();
    }

    private void upDataContentView() {
        contentAdapter = new ZZWorkContentAdapter(mContext, listContentData);
        initContentListener();
        zzWorkRvTwo.setAdapter(contentAdapter);
        contentAdapter.notifyDataSetChanged();
    }

    private void initTitleListener() {
        titleAdapter.setOnItemClickListener(new ZZWorkTitleAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                LearningPomoteBean.ListBean listBean = listTitleData.get(position);
                Intent i = new Intent(mContext, ZZWorkTitleDetailsActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable("LearningPomoteBean", listBean);
                i.putExtras(mBundle);
                i.putExtra("activity", "ZZWorkActivity");
                startActivity(i);
            }
        });
    }

    private void initContentListener() {
        contentAdapter.setOnItemClickListener(new ZZWorkContentAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                LearningMterialsBean.ListBean listBean = listContentData.get(position);
                String newsId = listBean.getNewsId();
                String isRead = listBean.getIsRead();
                Intent i = new Intent(mContext, ZiLiaoWebActivity.class);
                i.putExtra("newsId", newsId);
                i.putExtra("isRead", isRead);
                i.putExtra("activity", "ZZWorkActivity");
                startActivityForResult(i, 201);
            }
        });
    }

    @OnClick({R.id.titleRightText_llBack, R.id.titleRightText_tvRightName})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titleRightText_llBack:
                mContext.finish();
                break;
            case R.id.titleRightText_tvRightName:       //发布
                Intent iRelease = new Intent(mContext, ReleaseContainPicActivity.class);
                iRelease.putExtra("activity", "ZZWorkActivity");
                Bundle bundle = new Bundle();
                bundle.putSerializable("LearningPomoteBean", bean);
                iRelease.putExtras(bundle);
                startActivityForResult(iRelease, 200);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 200 || resultCode == 201) {        //200发布成功返回刷新, 201浏览详情页返回的把未读标识符去掉刷新
            getContentData();
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
        Control.getSecondMenu(mContext, "ORGMENU", new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    Log.i(TAG, "组织工作菜单: " + result);
                    JSONObject jo = new JSONObject(result);
                    String status = jo.getString("status");
                    String msg = jo.getString("msg");
                    if (status.equals("1")) {
                        String showType = jo.getString("showType");     //二级菜单展现类型：TAB为tab页形式，ICON为图标形式
                        Gson gson = new Gson();
                        bean = gson.fromJson(result, LearningPomoteBean.class);
                        List<LearningPomoteBean.ListBean> list = bean.getList();
                        if (list != null && list.size() > 0) {
                            if (list.size() <= 1) {
                                zzWorkRvOne.setVisibility(View.GONE);
                            } else {
                                zzWorkRvOne.setVisibility(View.VISIBLE);
                            }
                            listTitleData.addAll(list);
                        }
                        titleAdapter.notifyDataSetChanged();
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
     * （13014）获得一级菜单新闻TOP3列表(青年工作内容)
     */
    private void getContentData() {
        if (listContentData != null && listContentData.size() > 0) {
            listContentData.clear();
        }
        contentAdapter.notifyDataSetChanged();
        Control.getTopNewsByTopMenu(mContext, "ORGMENU", new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    Log.i(TAG, "组织工作内容: " + result);
                    JSONObject jo = new JSONObject(result);
                    String status = jo.getString("status");
                    String msg = jo.getString("msg");
                    if (status != null && !status.equals("") && status.equals("1")) {
                        Gson gson = new Gson();
                        LearningMterialsBean bean = gson.fromJson(result, LearningMterialsBean.class);
                        List<LearningMterialsBean.ListBean> list = bean.getList();
                        if (list != null && list.size() > 0) {
                            listContentData.addAll(list);
                            emptyLoadingLayoutLl.setVisibility(View.GONE);
                            zzWorkRvTwo.setVisibility(View.VISIBLE);
                        } else {
                            emptyLoadingLayoutLl.setVisibility(View.VISIBLE);
                            zzWorkRvTwo.setVisibility(View.GONE);
                        }
                        contentAdapter.notifyDataSetChanged();
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
     * （13023）获得模块首页图片
     */
    private void getModulePic(final RecyclerView zzWorkRecyclerView) {
        Control.getModulePic(mContext, "ORGMENU", new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    Log.i(TAG, "首页图片: " + result);
                    JSONObject jo = new JSONObject(result);
                    String status = jo.getString("status");
                    String msg = jo.getString("msg");
                    if (status != null && !status.equals("") && status.equals("1")) {
                        String iconPath = jo.getString("iconPath");
                        Glide.with(mContext).load(iconPath).error(R.drawable.organiza_bg).into(zzWorkIvTitleView);
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
