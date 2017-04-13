package com.dangjian.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.dangjian.utils.AppManager;


/**
 * Created by sg-pc on 2016/9/13.
 */
public class BaseActivity extends Activity {

    protected BaseActivity mContext;
    protected SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getAppManager().addActivity(this);
        mContext = BaseActivity.this;
        sharedPreferences = getSharedPreferences("dangJian_Android", MODE_PRIVATE);
    }
}
