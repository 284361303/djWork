package com.dangjian.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dangjian.R;
import com.dangjian.adapter.UnionWorkAdapter;
import com.dangjian.entity.LearningPomoteBean;
import com.dangjian.utils.Control;
import com.dangjian.utils.ToastUtil;
import com.dangjian.widgets.ItemDecoration;
import com.google.gson.Gson;

import org.json.JSONObject;
import org.xutils.common.Callback;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 工会工作
 */
public class UnionWorkActivity extends BaseActivity {

    @Bind(R.id.titleRightText_llBack)
    LinearLayout titleRightTextLlBack;
    @Bind(R.id.titleRightText_tvTitleName)
    TextView titleRightTextTvTitleName;
    @Bind(R.id.titleRightText_tvRightName)
    TextView titleRightTextTvRightName;
    @Bind(R.id.unionWork_recyclerView)
    RecyclerView unionWorkRecyclerView;
    @Bind(R.id.unionWork_ivPic)
    ImageView unionWorkIvPic;
    private UnionWorkAdapter adapter;
    private List<LearningPomoteBean.ListBean> listData = new ArrayList<LearningPomoteBean.ListBean>();
    private static final String TAG = "UnionWorkActivity";
    private GridLayoutManager gridLayoutManager;
    private LearningPomoteBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_union_work);
        ButterKnife.bind(this);
        initView();
        upDataView();
        getModulePic();
    }

    private void initView() {
        titleRightTextTvTitleName.setText("工会工作");
        titleRightTextTvRightName.setText("发布");
        gridLayoutManager = new GridLayoutManager(mContext, 3);
        unionWorkRecyclerView.setLayoutManager(gridLayoutManager);
        unionWorkRecyclerView.addItemDecoration(new ItemDecoration(mContext));
    }

    private void upDataView() {
        adapter = new UnionWorkAdapter(mContext, listData);
        initListener();
        unionWorkRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void initListener() {
        adapter.setOnItemClickListener(new UnionWorkAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                LearningPomoteBean.ListBean listBean = listData.get(position);
                Intent i = new Intent(mContext, YouthWorkTitleDetailActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable("LearningPomoteBean", listBean);
                i.putExtras(mBundle);
                i.putExtra("activity", "UnionWorkActivity");
                startActivity(i);
            }
        });
    }

    @OnClick({R.id.titleRightText_llBack, R.id.titleRightText_tvRightName})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titleRightText_llBack:
                mContext.finish();
                break;
        }
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
        if (listData != null && listData.size() > 0) {
            listData.clear();
        }
        adapter.notifyDataSetChanged();
        Control.getSecondMenu(mContext, "UNIONMENU", new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    Log.i(TAG, "工会工作菜单: " + result);
                    JSONObject jo = new JSONObject(result);
                    String status = jo.getString("status");
                    String msg = jo.getString("msg");
                    if (status.equals("1")) {
                        String showType = jo.getString("showType");     //二级菜单展现类型：TAB为tab页形式，ICON为图标形式
                        Gson gson = new Gson();
                        bean = gson.fromJson(result, LearningPomoteBean.class);
                        List<LearningPomoteBean.ListBean> list = bean.getList();
                        if (list != null && list.size() > 0) {
                            listData.addAll(list);
                        }
                        adapter.setShowType(showType);
                        adapter.notifyDataSetChanged();
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
     * （13023）获得模块首页图片
     */
    private void getModulePic() {
        Control.getModulePic(mContext, "UNIONMENU", new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    Log.i(TAG, "首页图片: " + result);
                    JSONObject jo = new JSONObject(result);
                    String status = jo.getString("status");
                    String msg = jo.getString("msg");
                    if (status != null && !status.equals("") && status.equals("1")) {
                        String iconPath = jo.getString("iconPath");
                        Glide.with(mContext).load(iconPath).error(R.drawable.union_work_bg).into(unionWorkIvPic);
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
