package com.dangjian.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dangjian.R;
import com.dangjian.utils.APIManager;
import com.dangjian.utils.Config;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 关于我们
 */

public class AboutUsAvtivity extends BaseActivity {

    @Bind(R.id.titleOneText_llBack)
    LinearLayout titleOneTextLlBack;
    @Bind(R.id.titleOneText_tvTitleName)
    TextView titleOneTextTvTitleName;
    @Bind(R.id.aboutUs_tvVersion)
    TextView aboutUsTvVersion;
    @Bind(R.id.aboutUs_ivDowloadCode)
    ImageView aboutUsIvDowloadCode;
    private static final String TAG = "AboutUsAvtivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us_avtivity);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleOneTextTvTitleName.setText("关于我们");
        Glide.with(mContext).load(APIManager.downloadewm).into(aboutUsIvDowloadCode);
        aboutUsTvVersion.setText(Config.vVersionCode);
    }

    @OnClick(R.id.titleOneText_llBack)
    public void onClick() {
        mContext.finish();
    }
}
