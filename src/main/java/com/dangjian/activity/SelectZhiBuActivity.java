package com.dangjian.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dangjian.R;
import com.dangjian.adapter.SelectZhiBuAdapter;
import com.dangjian.entity.ZhiBuListBean;
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
 * sg  2016年9月29日17:01:14
 * 选择支部页面
 */
public class SelectZhiBuActivity extends BaseActivity {

    @Bind(R.id.titleOneText_llBack)
    LinearLayout titleOneTextLlBack;
    @Bind(R.id.titleOneText_tvTitleName)
    TextView titleOneTextTvTitleName;
    @Bind(R.id.selectZhiBu_recyclerView)
    RecyclerView selectZhiBuRecyclerView;
    private LinearLayoutManager linearLayoutManager;
    private SelectZhiBuAdapter adapter;
    private List<ZhiBuListBean.ListBean> listData = new ArrayList<ZhiBuListBean.ListBean>();
    private static final String TAG = "ZhiBuActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_zhi_bu);
        ButterKnife.bind(this);
        initView();
        upDataView();
        getData();
    }

    private void initView() {
        titleOneTextTvTitleName.setText("选择所在支部");
        linearLayoutManager = new LinearLayoutManager(this);
        selectZhiBuRecyclerView.setLayoutManager(linearLayoutManager);
    }

    private void upDataView() {
        adapter = new SelectZhiBuAdapter(mContext, listData);
        initListener();
        selectZhiBuRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    /**
     * （14002）获取部门信息
     */
    private void getData() {
        if (listData != null && !listData.equals("")) {
            listData.clear();
        }
        adapter.notifyDataSetChanged();
        Control.getDeptList(mContext, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    Log.i(TAG, "获取部门: " + result);
                    JSONObject jo = new JSONObject(result);
                    String status = jo.getString("status");
                    String msg = jo.getString("msg");
                    if (!status.equals("") && status.equals("1")) {
                        Gson gson = new Gson();
                        ZhiBuListBean zhiBuListBean = gson.fromJson(result, ZhiBuListBean.class);
                        List<ZhiBuListBean.ListBean> list = zhiBuListBean.getList();
                        if (list != null && list.size() > 0) {
                            listData.addAll(list);
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

    private void initListener() {
        adapter.setOnItemClickListener(new SelectZhiBuAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                ZhiBuListBean.ListBean listBean = listData.get(position);
                String deptName = listBean.getDeptName();
                String deptId = listBean.getDeptId();
                Intent i = new Intent();
                i.putExtra("deptName", deptName);
                i.putExtra("deptId", deptId);
                mContext.setResult(101, i);
                mContext.finish();
            }
        });
    }

    @OnClick(R.id.titleOneText_llBack)
    public void onClick() {
        mContext.finish();
    }
}
