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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dangjian.R;
import com.dangjian.adapter.OrganizationMTitleAdapter;
import com.dangjian.adapter.OrganizationManagerAdapter;
import com.dangjian.entity.OrganizationManagerBean;
import com.dangjian.entity.ZhiBuListBean;
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
 * sg  2016年9月29日14:59:33
 * 组织管理
 */
public class OrganizationManagerActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.OrganizationM_tvTitleName)
    TextView OrganizationMTvTitleName;
    @Bind(R.id.OrganizationM_llBack)
    LinearLayout OrganizationMLlBack;
    @Bind(R.id.OrganizationM_llTitleName)
    LinearLayout OrganizationMLlTitleName;
    @Bind(R.id.OrganizationM_tvAdd)
    TextView OrganizationMTvAdd;
    @Bind(R.id.emptyLoadingLayout_iv)
    ImageView emptyLoadingLayoutIv;
    @Bind(R.id.emptyLoadingLayout_tvName)
    TextView emptyLoadingLayoutTvName;
    @Bind(R.id.emptyLoadingLayout_ll)
    LinearLayout emptyLoadingLayoutLl;
    @Bind(R.id.OrganizationM_recyclerView)
    RecyclerView OrganizationMRecyclerView;
    @Bind(R.id.OrganizationM_refresh)
    SwipeRefreshLayout OrganizationMRefresh;
    @Bind(R.id.OrganizationM_rlAll)
    RelativeLayout OrganizationMRlAll;
    @Bind(R.id.OrganizationM_ivDown)
    ImageView OrganizationMIvDown;
    private LinearLayoutManager linearLayoutManager;
    private OrganizationManagerAdapter adapter;
    private String pageSizes = "10";   //每页显示条数
    private int pageIndex = 1;    //当前页数
    private boolean isCanScroll = true;  //上拉加载更多的状态
    private String LoadingFlag = "0";    //判断是否有商品是否第一次
    private String mDeptId = "0";
    private List<OrganizationManagerBean.ListBean> listData = new ArrayList<OrganizationManagerBean.ListBean>();
    private List<ZhiBuListBean.ListBean> listTitleData = new ArrayList<ZhiBuListBean.ListBean>();
    private static final String TAG = "OMActivity";
    private PopupWindow popupWindow;
    private OrganizationMTitleAdapter titleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organization_manager);
        ButterKnife.bind(this);
        initView();
        upDataView();
        getData();
    }

    private void initView() {
        if ((Config.DW_ADMIN_ROLE != null && Config.DW_ADMIN_ROLE.equals("DW_ADMIN")) || (Config.ZB_ADMIN_ROLE != null && Config.ZB_ADMIN_ROLE.equals("ZB_ADMIN"))) {
            OrganizationMTvAdd.setVisibility(View.VISIBLE);
        } else {
            OrganizationMTvAdd.setVisibility(View.GONE);
        }

        if (Config.DW_ADMIN_ROLE.equals("") && Config.ZB_ADMIN_ROLE.equals("ZB_ADMIN") && Config.QN_AMIN_ROLE.equals("") && Config.GH_ADMIN_ROLE.equals("")
                && Config.XC_ADMIN_ROLE.equals("") && Config.ZZ_ADMIN_ROLE.equals("") && Config.JJ_ADMIN_ROLE.equals("")) {
            OrganizationMTvTitleName.setText(Config.mDeptName);
//            OrganizationMLlTitleName.setClickable(false);
//            OrganizationMIvDown.setVisibility(View.GONE);
        }
        OrganizationMIvDown.setVisibility(View.VISIBLE);
        getTitleData();
        linearLayoutManager = new LinearLayoutManager(mContext);
        OrganizationMRecyclerView.setLayoutManager(linearLayoutManager);
        OrganizationMRefresh.setOnRefreshListener(this);
        OrganizationMRefresh.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);
        OrganizationMRecyclerView.addOnScrollListener(new LoadMoreListener());
    }

    private void upDataView() {
        adapter = new OrganizationManagerAdapter(mContext, listData);
        initListener();
        OrganizationMRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void initListener() {
        adapter.setOnItemClickListener(new OrganizationManagerAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                OrganizationManagerBean.ListBean listBean = listData.get(position);
                Intent i = new Intent(mContext, ChangeUserActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable("OrganizationManagerBean", listBean);
                i.putExtras(mBundle);
                startActivityForResult(i, 203);
            }
        });
    }

    /**
     * （14004）获得人员列表
     */
    private void getData() {
        Control.getEmployeeList(mContext, mDeptId, String.valueOf(pageIndex), pageSizes, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    Log.i(TAG, "获得人员列表: " + result);
                    JSONObject jo = new JSONObject(result);
                    String status = jo.getString("status");
                    String msg = jo.getString("msg");
                    if (!status.equals("") && status.equals("1")) {
                        Gson gson = new Gson();
                        OrganizationManagerBean listBean = gson.fromJson(result, OrganizationManagerBean.class);
                        List<OrganizationManagerBean.ListBean> list = listBean.getList();
                        if (list != null && list.size() > 0) {
                            LoadingFlag = "1";
                            isCanScroll = true;
                            listData.addAll(list);
                            emptyLoadingLayoutLl.setVisibility(View.GONE);
                            OrganizationMRecyclerView.setVisibility(View.VISIBLE);
                        } else if (LoadingFlag.equals("1") && listData.size() >= 0) {
                            emptyLoadingLayoutLl.setVisibility(View.GONE);
                            OrganizationMRecyclerView.setVisibility(View.VISIBLE);
                        } else {
                            emptyLoadingLayoutLl.setVisibility(View.VISIBLE);
                            OrganizationMRecyclerView.setVisibility(View.GONE);
                        }
                        adapter.notifyDataSetChanged();
                    } else if (status.equals("9")) {
                        ToastUtil.showShort(mContext, msg);
                        Intent i = new Intent(mContext, LoginActivity.class);
                        startActivity(i);
                        mContext.finish();
                    } else {
                        emptyLoadingLayoutLl.setVisibility(View.VISIBLE);
                        OrganizationMRecyclerView.setVisibility(View.GONE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                OrganizationMRefresh.setRefreshing(false);
                ToastUtil.closeProgressDialog();
            }

            @Override
            public void onCancelled(CancelledException cex) {
                OrganizationMRefresh.setRefreshing(false);
                ToastUtil.closeProgressDialog();
            }

            @Override
            public void onFinished() {
                OrganizationMRefresh.setRefreshing(false);
                ToastUtil.closeProgressDialog();
            }
        });
    }

    private void getNewData() {
        pageIndex = 1;
        if (listData != null && !listData.equals("")) {
            listData.clear();
        }
        getData();
    }

    @OnClick({R.id.OrganizationM_llBack, R.id.OrganizationM_llTitleName, R.id.OrganizationM_tvAdd})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.OrganizationM_llBack:
                mContext.finish();
                break;
            case R.id.OrganizationM_llTitleName:    //头部帅选支部按钮
                showTitlePop();
                break;
            case R.id.OrganizationM_tvAdd:      //添加按钮
                if (Config.DW_ADMIN_ROLE.equals("DW_ADMIN") || Config.ZB_ADMIN_ROLE.equals("ZB_ADMIN")) {
                    Intent i = new Intent(mContext, AddUserActivity.class);
                    startActivityForResult(i, 103);
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 103) {
            getNewData();
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
                getData();
            }
        }
    }

    @Override
    public void onRefresh() {
        getNewData();
    }

    /**
     * （14002）获取部门信息
     */
    private void getTitleData() {
        Control.getDeptList(mContext, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    Log.i(TAG, "部门信息: " + result);
                    JSONObject jo = new JSONObject(result);
                    String status = jo.getString("status");
                    String msg = jo.getString("msg");
                    if (!status.equals("") && status.equals("1")) {
                        Gson gson = new Gson();
                        ZhiBuListBean zhiBuListBean = gson.fromJson(result, ZhiBuListBean.class);
                        List<ZhiBuListBean.ListBean> list = zhiBuListBean.getList();
                        if (list != null && list.size() > 0) {
                            listTitleData.addAll(list);
                        }
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

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 点击标题选择发布类型弹窗
     */
    private void showTitlePop() {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View vv = inflater.inflate(R.layout.pop_title_select_all, null);
        popupWindow = new PopupWindow(vv, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, false);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.showAsDropDown(OrganizationMRlAll);
        RecyclerView titleRecyclerView = (RecyclerView) vv.findViewById(R.id.popTitleSelectAll_recyclerView);
        titleRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        titleAdapter = new OrganizationMTitleAdapter(mContext, listTitleData);
        initTitleListener();
        titleRecyclerView.setAdapter(titleAdapter);
        titleAdapter.notifyDataSetChanged();
        TextView tvAll = (TextView) vv.findViewById(R.id.itemPopTitleSelect_tvAll);
        tvAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OrganizationMTvTitleName.setText("全部支部");
                mDeptId = "0";
                getNewData();
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }
            }
        });
    }

    /**
     * 弹窗中的item点击事件，也就是选择发布累类型item
     */
    private void initTitleListener() {
        titleAdapter.setOnItemClickListener(new OrganizationMTitleAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                ZhiBuListBean.ListBean listBean = listTitleData.get(position);
                String deptName = listBean.getDeptName();
                String deptId = listBean.getDeptId();
                mDeptId = deptId;
                OrganizationMTvTitleName.setText(deptName);
                getNewData();
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }
            }
        });
    }
}
