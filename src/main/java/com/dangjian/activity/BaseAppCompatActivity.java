package com.dangjian.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.dangjian.utils.AppManager;

import butterknife.ButterKnife;

/**
 * Created by shao_g on 2016/10/4.
 */
public class BaseAppCompatActivity extends AppCompatActivity {

    protected BaseAppCompatActivity mContext;
    protected SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getAppManager().addActivity(this);
        mContext = BaseAppCompatActivity.this;
        sharedPreferences = getSharedPreferences("dangJian_Android", MODE_PRIVATE);
    }
}
