package com.dangjian.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dangjian.R;
import com.dangjian.adapter.SelectAdministratorAdapter;
import com.dangjian.entity.AdministratorBean;
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
 * sg 2016年9月29日15:43:04
 * 选择权限页面
 */
public class SelectAdministratorActivity extends BaseActivity {

    @Bind(R.id.titleOneText_llBack)
    LinearLayout titleOneTextLlBack;
    @Bind(R.id.titleOneText_tvTitleName)
    TextView titleOneTextTvTitleName;
    @Bind(R.id.selectAdministrator_recyclerView)
    RecyclerView selectAdministratorRecyclerView;
    @Bind(R.id.selectAdministrator_btnTrue)
    Button selectAdministratorBtnTrue;
    private LinearLayoutManager linearLayoutManager;
    private SelectAdministratorAdapter adapter;
    private List<AdministratorBean.ListBean> listData = new ArrayList<AdministratorBean.ListBean>();
    private List<EmployeeRolesBean.ListBean> listSelect = new ArrayList<EmployeeRolesBean.ListBean>();
    private static final String TAG = "SelectAdminActivity";
    /**
     * 当前这个人所在的部门的deptid
     */
    private String mDeptId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_administrator);
        ButterKnife.bind(this);
        initView();
        upDataView();
        getData();
        getExtras();
    }

    private void getExtras() {
        try {
            Intent intent = getIntent();
            Bundle extras = intent.getExtras();
            if (extras != null) {
                mDeptId = extras.getString("mDeptId");
                EmployeeRolesBean bean = (EmployeeRolesBean) extras.getSerializable("employeeRolesBean");
                if (bean != null && !bean.equals("")) {
                    List<EmployeeRolesBean.ListBean> list = bean.getList();
                    if (list != null && list.size() > 0) {
                        listSelect.addAll(list);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initView() {
        titleOneTextTvTitleName.setText("选择权限");
        linearLayoutManager = new LinearLayoutManager(this);
        selectAdministratorRecyclerView.setLayoutManager(linearLayoutManager);
    }

    private void upDataView() {
        adapter = new SelectAdministratorAdapter(mContext, listData, listSelect);
        initListener();
        selectAdministratorRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void initListener() {
        adapter.setOnItemClickListener(new SelectAdministratorAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                String code = listData.get(position).getCode();
                if (Config.DW_ADMIN_ROLE.equals("DW_ADMIN")) {
                    adapter.items.get(position).itemQuanXianIvStates.setChecked(!adapter.items.get(position).itemQuanXianIvStates.isChecked());
                } else {
                    if (Config.ZB_ADMIN_ROLE.equals("ZB_ADMIN") && Config.ZB_ADMIN_ROLE.equals(code)) {       //支部只能选择自己
                        if (Config.mDeptId.equals(mDeptId)) {
                            adapter.items.get(position).itemQuanXianIvStates.setChecked(!adapter.items.get(position).itemQuanXianIvStates.isChecked());
                        }
                    }
                    if (Config.GH_ADMIN_ROLE.equals("GH_ADMIN") && Config.GH_ADMIN_ROLE.equals(code)) {    //工会
                        adapter.items.get(position).itemQuanXianIvStates.setChecked(!adapter.items.get(position).itemQuanXianIvStates.isChecked());
                    }
                    if (Config.XC_ADMIN_ROLE.equals("XC_ADMIN") && Config.XC_ADMIN_ROLE.equals(code)) {    //宣传
                        adapter.items.get(position).itemQuanXianIvStates.setChecked(!adapter.items.get(position).itemQuanXianIvStates.isChecked());
                    }
                    if (Config.ZZ_ADMIN_ROLE.equals("ZZ_ADMIN") && Config.ZZ_ADMIN_ROLE.equals(code)) {    //组织小组
                        adapter.items.get(position).itemQuanXianIvStates.setChecked(!adapter.items.get(position).itemQuanXianIvStates.isChecked());
                    }
                    if (Config.JJ_ADMIN_ROLE.equals("JJ_ADMIN") && Config.JJ_ADMIN_ROLE.equals(code)) {    //纪检小组
                        adapter.items.get(position).itemQuanXianIvStates.setChecked(!adapter.items.get(position).itemQuanXianIvStates.isChecked());
                    }
                    if (Config.QN_AMIN_ROLE.equals("QN_ADMIN") && Config.QN_AMIN_ROLE.equals(code)) {    //青年小组
                        adapter.items.get(position).itemQuanXianIvStates.setChecked(!adapter.items.get(position).itemQuanXianIvStates.isChecked());
                    }
                }
            }
        });
    }

    /**
     * （14006）获得系统角色列表
     */
    private void getData() {
        Control.getRolesList(mContext, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    Log.i(TAG, "系统角色: " + result);
                    JSONObject jo = new JSONObject(result);
                    String status = jo.getString("status");
                    String msg = jo.getString("msg");
                    if (status != null && !status.equals("") && status.equals("1")) {
                        Gson gson = new Gson();
                        AdministratorBean Bean = gson.fromJson(result, AdministratorBean.class);
                        List<AdministratorBean.ListBean> lists = Bean.getList();
                        if (lists != null && lists.size() > 0) {
                            listData.addAll(lists);
                        }
                        adapter.notifyDataSetChanged();
                        adapter.setmDeptId(mDeptId);
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

    @OnClick({R.id.titleOneText_llBack, R.id.selectAdministrator_btnTrue})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titleOneText_llBack:
                mContext.finish();
                break;
            case R.id.selectAdministrator_btnTrue:  //完成按钮
                String codes = "";
                String names = "";
                for (int i = 0; i < listData.size(); i++) {
                    boolean checked = adapter.items.get(i).itemQuanXianIvStates.isChecked();
                    if (checked) {
                        if (!codes.equals("")) {
                            codes += ",";
                            names += ",";
                        }
                        codes += listData.get(i).getCode();
                        names += listData.get(i).getName();
                    }
                }
                Intent i = new Intent();
                i.putExtra("code", codes);
                i.putExtra("name", names);
                mContext.setResult(102, i);
                mContext.finish();
                Log.i(TAG, "onItemClickListener--codes: " + codes + " ,names--" + names);
                break;
        }
    }
}
