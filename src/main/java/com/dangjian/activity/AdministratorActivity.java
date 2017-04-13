package com.dangjian.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dangjian.R;
import com.dangjian.utils.Config;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * sg  2016年9月29日13:58:40
 * 管理员
 */
public class AdministratorActivity extends BaseActivity {

    @Bind(R.id.titleOneText_tvTitleName)
    TextView titleOneTextTvTitleName;
    @Bind(R.id.titleOneText_llBack)
    LinearLayout titleOneTextLlBack;
    @Bind(R.id.administrator_llA)
    LinearLayout administratorLlA;
    @Bind(R.id.administrator_llB)
    LinearLayout administratorLlB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrator);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleOneTextTvTitleName.setText("管理员");
        //党委管理员才显示积分兑换
        if (!Config.DW_ADMIN_ROLE.equals("")) {
            administratorLlB.setVisibility(View.VISIBLE);
        } else {
            administratorLlB.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.titleOneText_llBack, R.id.administrator_llA, R.id.administrator_llB})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titleOneText_llBack:
                mContext.finish();
                break;
            case R.id.administrator_llA:    //组织管理
                Intent i1 = new Intent(mContext, OrganizationManagerActivity.class);
                startActivity(i1);
                break;
            case R.id.administrator_llB:    //积分兑换
                Intent i2 = new Intent(mContext, JiFenDuiHuanActivity.class);
                startActivity(i2);
                break;
        }
    }
}
