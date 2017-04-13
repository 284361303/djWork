package com.dangjian.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dangjian.R;
import com.dangjian.utils.APIManager;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 功能介绍
 */
public class FunctionActivity extends BaseActivity {

    @Bind(R.id.titleOneText_llBack)
    LinearLayout titleOneTextLlBack;
    @Bind(R.id.titleOneText_tvTitleName)
    TextView titleOneTextTvTitleName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleOneTextTvTitleName.setText("党建工作平台功能介绍");
    }

    @OnClick({R.id.function_tvAPPNoteBook, R.id.function_tvAdminNoteBook, R.id.titleOneText_llBack})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.function_tvAPPNoteBook:   //app手册
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse(APIManager.app_manual);
                intent.setData(content_url);
                startActivity(intent);
                break;
            case R.id.function_tvAdminNoteBook:     //管理员使用手册
                Intent intent2 = new Intent();
                intent2.setAction("android.intent.action.VIEW");
                Uri url = Uri.parse(APIManager.manual);
                intent2.setData(url);
                startActivity(intent2);
                break;
            case R.id.titleOneText_llBack:
                mContext.finish();
                break;
        }
    }
}
