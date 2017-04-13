package com.dangjian.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dangjian.R;
import com.dangjian.utils.Config;
import com.dangjian.utils.Control;
import com.dangjian.utils.ToastUtil;

import org.json.JSONObject;
import org.xutils.common.Callback;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * sg 2016年9月29日15:08:073
 * 添加成员
 */
public class AddUserActivity extends BaseActivity {

    @Bind(R.id.titleOneText_tvTitleName)
    TextView titleOneTextTvTitleName;
    @Bind(R.id.addUser_etUserName)
    EditText addUserEtUserName;
    @Bind(R.id.addUser_etPwd)
    EditText addUserEtPwd;
    @Bind(R.id.addUser_etZhiBu)
    EditText addUserEtZhiBu;
    @Bind(R.id.addUser_etQuanXian)
    EditText addUserEtQuanXian;
    @Bind(R.id.titleOneText_llBack)
    LinearLayout titleOneTextLlBack;
    @Bind(R.id.addUser_llSelectZhiBu)
    LinearLayout addUserLlSelectZhiBu;
    @Bind(R.id.addUser_llSelectQuanXian)
    LinearLayout addUserLlSelectQuanXian;
    @Bind(R.id.addUser_llQuanXian)
    LinearLayout addUserLlQuanXian;
    @Bind(R.id.addUser_btnAdd)
    Button addUserBtnAdd;
    private String mDeptId = "", quanXianCode = "";
    private static final String TAG = "AddUserActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleOneTextTvTitleName.setText("添加党员");
        addUserEtZhiBu.setKeyListener(null);
        addUserEtQuanXian.setKeyListener(null);
        addUserEtPwd.setText("dj@1234");
        if (Config.DW_ADMIN_ROLE.equals("DW_ADMIN") || Config.ZB_ADMIN_ROLE.equals("ZB_ADMIN")) {
            addUserLlQuanXian.setVisibility(View.VISIBLE);
        } else {
            addUserLlQuanXian.setVisibility(View.GONE);
        }
        if (Config.DW_ADMIN_ROLE.equals("") && Config.ZB_ADMIN_ROLE.equals("ZB_ADMIN")) {
            addUserLlSelectZhiBu.setClickable(false);
            addUserEtZhiBu.setText(Config.mDeptName);
            mDeptId = Config.mDeptId;
        }
    }

    @OnClick({R.id.titleOneText_llBack, R.id.addUser_llSelectZhiBu, R.id.addUser_llSelectQuanXian, R.id.addUser_btnAdd})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titleOneText_llBack:
                mContext.finish();
                break;
            case R.id.addUser_llSelectZhiBu:    //选择支部  SelectZhiBuActivity
                Intent i1 = new Intent(mContext, SelectZhiBuActivity.class);
                startActivityForResult(i1, 101);
                break;
            case R.id.addUser_llSelectQuanXian:     //选择权限
                Intent i2 = new Intent(mContext, SelectAdministratorActivity.class);
                startActivityForResult(i2, 102);
                break;
            case R.id.addUser_btnAdd:   //提交按妞
                String userName = addUserEtUserName.getText().toString().trim();
                String pwd = addUserEtPwd.getText().toString().trim();
                String zhiBu = addUserEtZhiBu.getText().toString().trim();
                String quanXian = addUserEtQuanXian.getText().toString().trim();
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
                Save(userName, pwd, mDeptId, quanXianCode);
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
                addUserEtZhiBu.setText(deptName);
            }
            if (resultCode == 102 && data != null) {
                quanXianCode = data.getStringExtra("code");
                String quanXianName = data.getStringExtra("name");
                if (quanXianName != null && !quanXianName.equals("") && quanXianName.length() > 4) {
                    addUserEtQuanXian.setText(quanXianName.substring(0, 5) + " ....");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * （14001）增加/修改人员
     *
     * @param loginName loginName
     * @param password  密码
     * @param deptId    所属部门id
     * @param roles     所选的管理员
     */
    private void Save(String loginName, String password, String deptId, String roles) {
        Control.saveMember(mContext, loginName, password, deptId, "", roles, new Callback.CommonCallback<String>() {
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
