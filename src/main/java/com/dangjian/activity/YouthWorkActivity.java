package com.dangjian.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dangjian.R;
import com.dangjian.adapter.BannerViewPagerAdapter;
import com.dangjian.adapter.LearingContentAdapter;
import com.dangjian.adapter.YouThWorkTitleAdapter;
import com.dangjian.entity.LearningMterialsBean;
import com.dangjian.entity.LearningPomoteBean;
import com.dangjian.entity.YouthWorkBannerBean;
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
 * 青年工作
 */

public class YouthWorkActivity extends BaseActivity {

    @Bind(R.id.titleRightText_llBack)
    LinearLayout titleRightTextLlBack;
    @Bind(R.id.titleRightText_tvTitleName)
    TextView titleRightTextTvTitleName;
    @Bind(R.id.titleRightText_tvRightName)
    TextView titleRightTextTvRightName;
    @Bind(R.id.youthWork_rvOne)
    RecyclerView youthWorkRvOne;
    @Bind(R.id.emptyLoadingLayout_iv)
    ImageView emptyLoadingLayoutIv;
    @Bind(R.id.emptyLoadingLayout_tvName)
    TextView emptyLoadingLayoutTvName;
    @Bind(R.id.emptyLoadingLayout_ll)
    LinearLayout emptyLoadingLayoutLl;
    @Bind(R.id.youthWork_rvTwo)
    RecyclerView youthWorkRvTwo;
    @Bind(R.id.youthWork_viewPager)
    ViewPager youthWorkViewPager;
    @Bind(R.id.youthWork_llBanner)
    LinearLayout youthWorkLlBanner;
    private LinearLayoutManager linearLayoutManager, horizontalManager;
    private YouThWorkTitleAdapter titleAdapter;
    private LearingContentAdapter contentAdapter;
    private String pageSizes = "10";   //每页显示条数
    private int pageIndex = 1;    //当前页数
    private boolean isCanScroll = true;  //上拉加载更多的状态
    private String LoadingFlag = "0";    //判断是否有商品是否第一次
    private static final String TAG = "YouthWorkActivity";
    private List<LearningPomoteBean.ListBean> listTitleData = new ArrayList<LearningPomoteBean.ListBean>();
    private List<LearningMterialsBean.ListBean> listContentData = new ArrayList<LearningMterialsBean.ListBean>();
    private List<ImageView> piccLists = new ArrayList<ImageView>();
    private LearningPomoteBean bean;
    private int currentPosition;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            youthWorkViewPager.setCurrentItem((youthWorkViewPager.getCurrentItem() + 1) % piccLists.size());
            handler.sendEmptyMessageDelayed(1, 4000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youth_work);
        ButterKnife.bind(this);
        initView();
        upDataTitleView();
        upDataContentView();
        getBannerData();
        getContentData();
    }

    private void initView() {
        titleRightTextTvTitleName.setText("青年工作");
        linearLayoutManager = new LinearLayoutManager(mContext);
        horizontalManager = new LinearLayoutManager(mContext);
        horizontalManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        youthWorkRvOne.setLayoutManager(horizontalManager);
        youthWorkRvTwo.setLayoutManager(linearLayoutManager);
    }

    private void upDataTitleView() {
        titleAdapter = new YouThWorkTitleAdapter(mContext, listTitleData);
        initTitleListener();
        youthWorkRvOne.setAdapter(titleAdapter);
        titleAdapter.notifyDataSetChanged();
    }

    private void upDataContentView() {
        contentAdapter = new LearingContentAdapter(mContext, listContentData);
        initContentListener();
        youthWorkRvTwo.setAdapter(contentAdapter);
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
                i.putExtra("activity", "YouthWorkActivity");
                i.putExtra("newsId", newsId);
                i.putExtra("isRead", isRead);
                startActivityForResult(i, 201);
            }
        });
    }

    private void initTitleListener() {
        titleAdapter.setOnItemClickListener(new YouThWorkTitleAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                LearningPomoteBean.ListBean listBean = listTitleData.get(position);
                Intent i = new Intent(mContext, YouthWorkTitleDetailActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable("LearningPomoteBean", listBean);
                i.putExtras(mBundle);
                i.putExtra("activity", "YouthWorkActivity");
                startActivity(i);
            }
        });
    }

    @OnClick({R.id.titleRightText_llBack})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titleRightText_llBack:
                mContext.finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 200 || resultCode == 201) {        //200发布成功返回刷新, 201浏览详情页返回的把未读标识符去掉刷新
            pageIndex = 1;
            getNewContentData();
        }
    }

    private void getNewContentData() {
        if (listContentData != null && listContentData.size() > 0) {
            listContentData.clear();
        }
        contentAdapter.notifyDataSetChanged();
        getContentData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    /**
     * （13001）获得栏目的二级菜单
     */
    private void getData() {
        if (listTitleData != null && listTitleData.size() > 0) {
            listTitleData.clear();
        }
        titleAdapter.notifyDataSetChanged();
        Control.getSecondMenu(mContext, "YOUNGMENU", new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    Log.i(TAG, "青年工作标题: " + result);
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
                                youthWorkRvOne.setVisibility(View.GONE);
                            } else {
                                youthWorkRvOne.setVisibility(View.VISIBLE);
                            }
                            listTitleData.addAll(list);
                        }
                        titleAdapter.setShowType(showType);
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
     * （13014）获得一级菜单新闻TOP3列表(青年工作内容)
     */
    private void getContentData() {
        Control.getTopNewsByTopMenu(mContext, "YOUNGMENU", new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    Log.i(TAG, "青年工作内容: " + result);
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
                            youthWorkRvTwo.setVisibility(View.VISIBLE);
                        } else {
                            emptyLoadingLayoutLl.setVisibility(View.VISIBLE);
                            youthWorkRvTwo.setVisibility(View.GONE);
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
     * （13022）获得青年工作首页轮播图
     */
    private void getBannerData() {
        Control.getQNTop5Pic(mContext, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    Log.i(TAG, "青年工作轮播: " + result);
                    JSONObject jo = new JSONObject(result);
                    String status = jo.getString("status");
                    String msg = jo.getString("msg");
                    if (status != null && !status.equals("") && status.equals("1")) {
                        Gson gson = new Gson();
                        YouthWorkBannerBean youthWorkBannerBean = gson.fromJson(result, YouthWorkBannerBean.class);
                        final List<YouthWorkBannerBean.ListBean> list = youthWorkBannerBean.getList();
                        if (list != null && list.size() > 0) {
                            final ImageView[] icons = new ImageView[list.size()];
                            for (int i = 0; i < list.size(); i++) {
                                final String id = list.get(i).getId();    //新闻id
                                final String isRead = list.get(i).getIsRead();
                                icons[i] = new ImageView(mContext);
                                icons[i].setImageResource(R.drawable.icon_point);
                                // 设置小圆点的宽和高
                                LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                                layout.setMargins(3, 0, 3, 0);
                                icons[i].setLayoutParams(layout);
                                icons[i].setAdjustViewBounds(true);
                                youthWorkLlBanner.addView(icons[i]);
                                ImageView iv = new ImageView(mContext);
                                piccLists.add(iv);
                                Glide.with(mContext).load(list.get(i).getIconPath()).error(R.drawable.young_work_bg).centerCrop().into(iv);
                                if (!id.equals("-1")) {
                                    iv.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Intent i = new Intent(mContext, ZiLiaoWebActivity.class);
                                            i.putExtra("activity", "YouthWorkActivity");
                                            i.putExtra("newsId", id);
                                            i.putExtra("isRead", isRead);
                                            startActivityForResult(i, 201);
                                        }
                                    });
                                }
                            }
                            icons[0].setImageResource(R.drawable.icon_point_pre);
                            BannerViewPagerAdapter adapter = new BannerViewPagerAdapter(youthWorkViewPager, piccLists);
                            youthWorkViewPager.setAdapter(adapter);
                            handler.sendEmptyMessageDelayed(1, 4000);
                            youthWorkViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                                @Override
                                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                                }

                                @Override
                                public void onPageSelected(int position) {
                                    currentPosition = position % piccLists.size();
                                    for (int i = 0; i < piccLists.size(); i++) {
                                        icons[i].setImageResource(R.drawable.icon_point);
                                    }
                                    icons[position % piccLists.size()].setImageResource(R.drawable.icon_point_pre);
                                }

                                @Override
                                public void onPageScrollStateChanged(int state) {
                                }
                            });
                        }
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
}
