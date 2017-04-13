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
import com.dangjian.utils.Control;
import com.dangjian.utils.ToastUtil;

import org.json.JSONObject;
import org.xutils.common.Callback;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 修改密码页面
 */
public class RechangePWDActivity extends BaseActivity {

    @Bind(R.id.titleOneText_llBack)
    LinearLayout titleOneTextLlBack;
    @Bind(R.id.titleOneText_tvTitleName)
    TextView titleOneTextTvTitleName;
    @Bind(R.id.rechangePWD_etOld)
    EditText rechangePWDEtOld;
    @Bind(R.id.rechangePWD_etNew)
    EditText rechangePWDEtNew;
    @Bind(R.id.rechangePWD_etNew2)
    EditText rechangePWDEtNew2;
    @Bind(R.id.rechangePWD_btnSave)
    Button rechangePWDBtnSave;
    private static final String TAG = "RechangePWDActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rechange_pwd);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleOneTextTvTitleName.setText("设置");
    }

    @OnClick({R.id.titleOneText_llBack, R.id.rechangePWD_btnSave})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titleOneText_llBack:
                mContext.finish();
                break;
            case R.id.rechangePWD_btnSave:
                String old = rechangePWDEtOld.getText().toString().trim();
                String new1 = rechangePWDEtNew.getText().toString().trim();
                String new2 = rechangePWDEtNew2.getText().toString().trim();
                if (TextUtils.isEmpty(old)) {
                    ToastUtil.showShort(mContext, "原密码不能为空");
                    return;
                }
                if (TextUtils.isEmpty(new1)) {
                    ToastUtil.showShort(mContext, "新密码不能为空");
                    return;
                }
                if (TextUtils.isEmpty(new2)) {
                    ToastUtil.showShort(mContext, "确认密码不能为空");
                    return;
                }
                if (!new1.equals(new2)) {
                    ToastUtil.showShort(mContext, "两次密码不一致");
                    return;
                }
                getData(old, new2);
                break;
        }
    }

    /**
     * 修改密码
     */
    private void getData(String oldPass, String newPass) {
        Control.changePass(mContext, oldPass, newPass, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    Log.i(TAG, "修改密码: " + result);
                    JSONObject jo = new JSONObject(result);
                    String status = jo.getString("status");
                    String msg = jo.getString("msg");
                    if (!status.equals("") && status.equals("1")) {
                        mContext.finish();
                        ToastUtil.showShort(mContext, msg);
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
