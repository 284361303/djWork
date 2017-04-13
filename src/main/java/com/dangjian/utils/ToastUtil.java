package com.dangjian.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import com.dangjian.R;

//Toast统一管理类
public class ToastUtil {
    public static boolean isShow = true;
    private static ProgressDialog dialog;

    public static void showShort(Context context, CharSequence message) {
        try {
            if (isShow)
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 默认提示为“努力加载中……”
     **/
    public static void showProgressDialog(Context context) {
        try {
            if (context == null)
                MyApplication.getInstance();
            if (dialog == null) {
                dialog = new ProgressDialog(context);
                dialog.setMessage(context.getResources().getString(R.string.string_loading));
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void closeProgressDialog() {
        try {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
                dialog = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}