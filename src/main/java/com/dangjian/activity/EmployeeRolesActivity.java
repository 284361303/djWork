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
import com.dangjian.adapter.EmployeeRolesAdapter;
import com.dangjian.entity.EmployeeRolesBean;
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
 * 获得人员权限角色
 */
public class EmployeeRolesActivity extends BaseActivity {

    @Bind(R.id.titleOneText_llBack)
    LinearLayout titleOneTextLlBack;
    @Bind(R.id.titleOneText_tvTitleName)
    TextView titleOneTextTvTitleName;
    @Bind(R.id.employeeRoles_recyclerView)
    RecyclerView employeeRolesRecyclerView;
    private LinearLayoutManager linearLayoutManager;
    private List<EmployeeRolesBean.ListBean> listData = new ArrayList<EmployeeRolesBean.ListBean>();
    private static final String TAG = "EmployeeRolesActivity";
    private EmployeeRolesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_roles);
        ButterKnife.bind(this);
        initView();
        upDataView();
        getData();
    }

    private void initView() {
        titleOneTextTvTitleName.setText("发布");
        linearLayoutManager = new LinearLayoutManager(this);
        employeeRolesRecyclerView.setLayoutManager(linearLayoutManager);
    }

    private void upDataView() {
        adapter = new EmployeeRolesAdapter(mContext, listData);
        initListener();
        employeeRolesRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void initListener() {
        adapter.setOnItemClickListener(new EmployeeRolesAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                EmployeeRolesBean.ListBean lists = listData.get(position);
                String name = lists.getName();
                String code = lists.getCode();
                Intent i = new Intent();
                i.putExtra("name", name);
                i.putExtra("code", code);
                mContext.setResult(105, i);
                mContext.finish();
            }
        });
    }

    @OnClick(R.id.titleOneText_llBack)
    public void onClick() {
        mContext.finish();
    }

    /**
     * （14005）获得人员权限角色
     */
    private void getData() {
        if (listData != null && listData.size() > 0) {
            listData.clear();
        }
        adapter.notifyDataSetChanged();
        Control.getEmployeeRoles(mContext, Config.mEmpId, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    Log.i(TAG, "人员权限: " + result);
                    JSONObject jo = new JSONObject(result);
                    String status = jo.getString("status");
                    String msg = jo.getString("msg");
                    if (status != null && !status.equals("") && status.equals("1")) {
                        Gson gson = new Gson();
                        EmployeeRolesBean employeeRolesBean = gson.fromJson(result, EmployeeRolesBean.class);
                        List<EmployeeRolesBean.ListBean> list = employeeRolesBean.getList();
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
}
