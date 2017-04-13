package com.dangjian.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
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
 * 设置新闻图片显示方式
 */
public class SettingShowPicActivity extends BaseActivity {

    @Bind(R.id.titleOneText_llBack)
    LinearLayout titleOneTextLlBack;
    @Bind(R.id.titleOneText_tvTitleName)
    TextView titleOneTextTvTitleName;
    @Bind(R.id.showPic_ivAll)
    ImageView showPicIvAll;
    @Bind(R.id.showPic_llAll)
    LinearLayout showPicLlAll;
    @Bind(R.id.showPic_ivWIFI)
    ImageView showPicIvWIFI;
    @Bind(R.id.showPic_llWIFI)
    LinearLayout showPicLlWIFI;
    private static final String TAG = "SettingShowPicActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_show_pic);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleOneTextTvTitleName.setText("新闻图片显示方式");
        if (Config.mLoginShowPicType != null && !Config.mLoginShowPicType.equals("") && Config.mLoginShowPicType.equals("WIFIPIC")) {
            showPicIvAll.setVisibility(View.INVISIBLE);
            showPicIvWIFI.setVisibility(View.VISIBLE);
        } else if (Config.mLoginShowPicType != null && !Config.mLoginShowPicType.equals("") && Config.mLoginShowPicType.equals("ALLPIC")) {
            showPicIvAll.setVisibility(View.VISIBLE);
            showPicIvWIFI.setVisibility(View.INVISIBLE);
        }
    }

    @OnClick({R.id.titleOneText_llBack, R.id.showPic_llAll, R.id.showPic_llWIFI})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titleOneText_llBack:
                mContext.finish();
                break;
            case R.id.showPic_llAll:    //全都显示
                showPicIvAll.setVisibility(View.VISIBLE);
                showPicIvWIFI.setVisibility(View.INVISIBLE);
                showPicType("ALLPIC");
                break;
            case R.id.showPic_llWIFI:   //仅WIFI
                showPicIvAll.setVisibility(View.INVISIBLE);
                showPicIvWIFI.setVisibility(View.VISIBLE);
                showPicType("WIFIPIC");
                break;
        }
    }

    /**
     * （11011）设置图片显示类型
     */
    private void showPicType(final String type) {
        Control.showPicType(mContext, type, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    Log.i(TAG, "设置图片显示: " + result);
                    JSONObject jo = new JSONObject(result);
                    String status = jo.getString("status");
                    String msg = jo.getString("msg");
                    if (status != null && !status.equals("") && status.equals("1")) {
                        Config.mLoginShowPicType = type;
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
