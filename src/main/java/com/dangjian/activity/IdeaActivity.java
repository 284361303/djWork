package com.dangjian.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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
 * 意见反馈
 */
public class IdeaActivity extends BaseActivity {

    @Bind(R.id.titleOneText_llBack)
    LinearLayout titleOneTextLlBack;
    @Bind(R.id.titleOneText_tvTitleName)
    TextView titleOneTextTvTitleName;
    @Bind(R.id.idea_et)
    EditText ideaEt;
    @Bind(R.id.idea_tvSum)
    TextView ideaTvSum;
    @Bind(R.id.idea_btnSubmit)
    Button ideaBtnSubmit;
    private int maxSum = 201;
    private static final String TAG = "IdeaActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idea);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        try {
            titleOneTextTvTitleName.setText("意见反馈");
            ideaEt.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    String content = ideaEt.getText().toString().trim();
                    int length = content.length();
                    if (length > 0 && length < maxSum) {
                        length = maxSum - length - 1;
                        ideaTvSum.setText(String.valueOf(length));
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.titleOneText_llBack, R.id.idea_btnSubmit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titleOneText_llBack:
                mContext.finish();
                break;
            case R.id.idea_btnSubmit:
                String content = ideaEt.getText().toString().trim();
                if (TextUtils.isEmpty(content)) {
                    ToastUtil.showShort(mContext, "内容不能为空");
                    return;
                }
                submitFeedback(content);
                break;
        }
    }

    /**
     * （16003）提交意见反馈信息
     *
     * @param content 反馈内容
     */
    private void submitFeedback(String content) {
        Control.submitFeedback(mContext, "0", content, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    Log.i(TAG, "意见反馈: " + result);
                    JSONObject jo = new JSONObject(result);
                    String status = jo.getString("status");
                    String msg = jo.getString("msg");
                    if (status != null && !status.equals("") && status.equals("1")) {
                        ideaEt.setText("");
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
