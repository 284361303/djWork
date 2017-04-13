package com.dangjian.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dangjian.R;
import com.dangjian.entity.EmployeeRolesBean;
import com.dangjian.entity.OrganizationManagerBean;
import com.dangjian.utils.Config;
import com.dangjian.utils.Control;
import com.dangjian.utils.ToastUtil;
import com.google.gson.Gson;

import org.json.JSONObject;
import org.xutils.common.Callback;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 组织管理中的修改党员信息页面
 */
public class ChangeUserActivity extends BaseActivity {

    @Bind(R.id.titleOneText_llBack)
    LinearLayout titleOneTextLlBack;
    @Bind(R.id.titleOneText_tvTitleName)
    TextView titleOneTextTvTitleName;
    @Bind(R.id.changeUser_etUserName)
    EditText changeUserEtUserName;
    @Bind(R.id.changeUser_etPwd)
    EditText changeUserEtPwd;
    @Bind(R.id.changeUser_etZhiBu)
    EditText changeUserEtZhiBu;
    @Bind(R.id.changeUser_llSelectZhiBu)
    LinearLayout changeUserLlSelectZhiBu;
    @Bind(R.id.changeUser_etQuanXian)
    EditText changeUserEtQuanXian;
    @Bind(R.id.changeUser_llSelectQuanXian)
    LinearLayout changeUserLlSelectQuanXian;
    @Bind(R.id.changeUser_llQuanXian)
    LinearLayout changeUserLlQuanXian;
    @Bind(R.id.changeUser_btnCancel)
    Button changeUserBtnCancel;
    @Bind(R.id.changeUser_btnAdd)
    Button changeUserBtnAdd;
    private static final String TAG = "ChangeUserActivity";
    private String mDeptId = "", quanXianCode = "";
    private String mDeptName = "";
    private String mEmpId = "";
    private EmployeeRolesBean employeeRolesBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_user);
        ButterKnife.bind(this);
        initView();
        getExtras();
    }

    private void getExtras() {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            OrganizationManagerBean.ListBean listBean = (OrganizationManagerBean.ListBean) extras.getSerializable("OrganizationManagerBean");
            if (listBean != null && !listBean.equals("")) {
                getEmployeeRoles(listBean.getEmpId());
                mDeptId = listBean.getDeptId();
                mEmpId = listBean.getEmpId();
                changeUserEtUserName.setText(listBean.getLoginName());
                changeUserEtPwd.setText("********");
                changeUserEtZhiBu.setText(listBean.getDeptName());
                if (Config.DW_ADMIN_ROLE.equals("") && Config.ZB_ADMIN_ROLE.equals("ZB_ADMIN")) {
                    if (Config.mDeptId.equals(mDeptId)) {
                        changeUserLlSelectZhiBu.setClickable(false);
                        changeUserEtZhiBu.setText(Config.mDeptName);
                        mDeptId = Config.mDeptId;
                    }
                }
                //邵光 11-8日添加  党委和支部才能修改信息，其他权限的只能看
                if (Config.DW_ADMIN_ROLE.equals("DW_ADMIN") || (Config.ZB_ADMIN_ROLE.equals("ZB_ADMIN") && Config.mDeptId.equals(mDeptId))) {
                } else {
                    changeUserEtUserName.setKeyListener(null);
                    changeUserEtPwd.setKeyListener(null);
                    changeUserLlSelectZhiBu.setClickable(false);
                    changeUserBtnCancel.setVisibility(View.GONE);
                    changeUserBtnAdd.setVisibility(View.GONE);
                }
            }
        }
    }

    private void initView() {
        titleOneTextTvTitleName.setText("修改信息");
        changeUserEtZhiBu.setKeyListener(null);
        changeUserEtQuanXian.setKeyListener(null);
    }

    public void changeUser_QXClick(View view) {
        Intent i2 = new Intent(mContext, SelectAdministratorActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("employeeRolesBean", employeeRolesBean);
        i2.putExtras(bundle);
        i2.putExtra("mDeptId", mDeptId);
        startActivityForResult(i2, 102);
    }

    @OnClick({R.id.titleOneText_llBack, R.id.changeUser_llSelectZhiBu, R.id.changeUser_llQuanXian, R.id.changeUser_btnCancel, R.id.changeUser_btnAdd})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titleOneText_llBack:
                mContext.finish();
                break;
            case R.id.changeUser_llSelectZhiBu:   //选择支部  SelectZhiBuActivity
                Intent i1 = new Intent(mContext, SelectZhiBuActivity.class);
                startActivityForResult(i1, 101);
                break;
            case R.id.changeUser_btnCancel:
                showDeteleView();
                break;
            case R.id.changeUser_btnAdd:
                String userName = changeUserEtUserName.getText().toString().trim();
                String pwd = changeUserEtPwd.getText().toString().trim();
                String zhiBu = changeUserEtZhiBu.getText().toString().trim();
                String quanXian = changeUserEtQuanXian.getText().toString().trim();
                if (TextUtils.isEmpty(userName)) {
                    ToastUtil.showShort(mContext, "用户名不能为空");
                    return;
                }
                if (TextUtils.isEmpty(pwd)) {
                    ToastUtil.showShort(mContext, "密码不能为空");
                    return;
                }
                if (TextUtils.isEmpty(zhiBu) || mDeptId.equals("")) {
                    ToastUtil.showShort(mContext, "请选择所在支部");
                    return;
                }
                Save(userName, pwd, mDeptId, mEmpId, quanXianCode);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (resultCode == 101 && data != null) {
                String deptName = data.getStringExtra("deptName");
                mDeptId = data.getStringExtra("deptId");
                changeUserEtZhiBu.setText(deptName);
            }
            if (resultCode == 102 && data != null) {
                quanXianCode = data.getStringExtra("code");
                String quanXianName = data.getStringExtra("name");
                if (quanXianName != null && !quanXianName.equals("") && quanXianName.length() > 4) {
                    changeUserEtQuanXian.setText(quanXianName.substring(0, 5) + " ....");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭当前页面提示
     */
    private void showDeteleView() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
        dialog.setMessage("确认删除该党员？");
        dialog.setNegativeButton("取消", null);
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                deleteMember(mEmpId);
            }
        }).show();
    }

    /**
     * （11003）删除人员
     *
     * @param mEmpId 员工编号
     */
    private void deleteMember(String mEmpId) {
        Control.deleteMember(mContext, mEmpId, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    Log.i(TAG, "删除人员: " + result);
                    JSONObject jo = new JSONObject(result);
                    String status = jo.getString("status");
                    String msg = jo.getString("msg");
                    if (status != null && !status.equals("") && status.equals("1")) {
                        ToastUtil.showShort(mContext, msg);
                        setResult(103);
                        mContext.finish();
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
     * （14005）获得人员权限角色
     */
    private void getEmployeeRoles(String empId) {
        Control.getEmployeeRoles(mContext, empId, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    Log.i(TAG, "获得人员权限: " + result);
                    JSONObject jo = new JSONObject(result);
                    String status = jo.getString("status");
                    String msg = jo.getString("msg");
                    if (status != null && !status.equals("") && status.equals("1")) {
                        Gson gson = new Gson();
                        employeeRolesBean = gson.fromJson(result, EmployeeRolesBean.class);
                        List<EmployeeRolesBean.ListBean> list = employeeRolesBean.getList();
                        if (list != null && list.size() > 0) {
                            for (int i = 0; i < list.size(); i++) {
                                if (!mDeptName.equals("")) {
                                    mDeptName += ",";
                                    quanXianCode += ",";
                                }
                                quanXianCode += list.get(i).getCode();
                                mDeptName += list.get(i).getName();
                            }
                        }
                        if (mDeptName != null && !mDeptName.equals("") && mDeptName.length() > 4) {
                            changeUserEtQuanXian.setText(mDeptName.substring(0, 5) + " ....");
                        }
                        Log.i(TAG, "onSuccess/quanXianCode: " + quanXianCode + " ,---" + mDeptName);
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
     * （14001）增加/修改人员
     *
     * @param loginName loginName
     * @param password  密码
     * @param deptId    所属部门id
     * @param empId     员工id，新增员工该字段为空
     * @param roles     所选的管理员
     */
    private void Save(String loginName, String password, String deptId, String empId, String roles) {
        Control.saveMember(mContext, loginName, password, deptId, empId, roles, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    Log.i(TAG, "增加: " + result);
                    JSONObject jo = new JSONObject(result);
                    String status = jo.getString("status");
                    String msg = jo.getString("msg");
                    if (status != null && !status.equals("") && status.equals("1")) {
                        mContext.setResult(103);
                        mContext.finish();
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
