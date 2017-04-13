package com.dangjian.utils;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;

import java.util.Stack;

/**
 * 项目工具类 邵光
 */
public class AppManager {

    private static AppManager instance = null;
    private static Stack<Activity> activitysStack = null;
    private static Stack<FragmentActivity> faStack = null;

    public static AppManager getAppManager() {
        if (null == instance) {
            instance = new AppManager();
        }
        return instance;
    }

    /**
     * 把所有的activity添加到集合中
     */
    public void addActivity(Activity activity) {
        if (null == activitysStack) {
            activitysStack = new Stack<Activity>();
        }
        activitysStack.add(activity);
    }

    /**
     * 把所有的FragmentActivity添加到集合中
     */
    public void addFragmentActivity(FragmentActivity fActivity) {
        if (null == faStack) {
            faStack = new Stack<FragmentActivity>();
        }
        faStack.add(fActivity);
    }

    /**
     * 销毁集合中的所有activity
     */
    public void finishAllActivity() {
        try {
            if (activitysStack != null && activitysStack.size() > 0) {
                for (int i = 0; i < activitysStack.size(); i++) {
                    if (activitysStack.get(i) != null) {
                        activitysStack.get(i).finish();
                    }
                }
                activitysStack.clear();
            }
        } catch (Exception e) {
        }
    }

    /**
     * 销毁集合中的所有FragmentActivity
     */
    public void finishAllfActivity() {
        try {
            if (faStack != null && faStack.size() > 0) {
                for (int i = 0; i < faStack.size(); i++) {
                    if (faStack.get(i) != null) {
                        faStack.get(i).finish();
                    }
                }
                faStack.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 返回到桌面时候退出并销毁应用以及内存
     */
    public void AppExit(Context context) {
        try {
            finishAllActivity();
            finishAllfActivity();
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
