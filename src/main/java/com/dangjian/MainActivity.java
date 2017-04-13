package com.dangjian;

import android.content.Intent;
import android.os.Bundle;

import com.dangjian.activity.BaseActivity;
import com.dangjian.activity.LoginActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        new Thread() {
            @Override
            public void run() {
                try {
                    this.sleep(1800);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            startLoginActivity();
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                super.run();
            }
        }.start();
    }

    private void startLoginActivity() {
        Intent intent = new Intent(mContext, LoginActivity.class);
        startActivity(intent);
        mContext.finish();
    }
}
