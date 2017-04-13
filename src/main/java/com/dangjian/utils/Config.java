package com.dangjian.utils;

import android.content.Context;
//import android.webkit.CookieSyncManager;
import com.tencent.smtt.sdk.CookieSyncManager;
import com.tencent.smtt.sdk.CookieManager;

/**
 * Created by sg-pc on 2016/10/9.
 */
public class Config {

    /**
     * APP当前版本号
     */
    public static String vVersionCode = "1.2.1";
    /**
     * 登录名字
     */
    public static String mLoginName = "";
    /**
     * 登录接口上的昵称
     */
    public static String mNickName = "";
    /**
     * 部门编码
     */
    public static String mDeptCode = "";
    /**
     * 部门id
     */
    public static String mDeptId = "";
    /**
     * 个人图片路径
     */
    public static String mIconPath = "";
    /**
     * 题目类型 SINGLE单选题，MULTI 多选题，JUDGE 判断题
     */
    public static String type = "";
    /**
     * 用户提交答案
     */
    public static String userAnswer = "";
    /**
     * cookie
     */
    public static String mCookie = "";
    /**
     * DW_ADMIN党委管理员,为空是不是党委管理员
     */
    public static String DW_ADMIN_ROLE = "";
    /**
     * ZB_ADMIN支部管理员,为空是不是管理员
     */
    public static String ZB_ADMIN_ROLE = "";
    /**
     * XC_ADMIN宣传小组管理员,为空是不是管理员
     */
    public static String XC_ADMIN_ROLE = "";
    /**
     * ZZ_ADMIN组织小组管理员,为空是不是管理员
     */
    public static String ZZ_ADMIN_ROLE = "";
    /**
     * JJ_ADMIN纪检小组管理员,为空是不是管理员
     */
    public static String JJ_ADMIN_ROLE = "";
    /**
     * QN_AMIN青年小组管理员,为空是不是管理员
     */
    public static String QN_AMIN_ROLE = "";
    /**
     * GH_ADMIN工会管理员,为空是不是管理员
     */
    public static String GH_ADMIN_ROLE = "";
    /**
     * 员工编号
     */
    public static String mEmpId = "";
    /**
     * 部门名称
     */
    public static String mDeptName = "";
    /**
     * 题库练习中的做题的总数量
     */
    public static int maxIndex = 0;
    /**
     * WIFIPIC wifi下显示图片，ALLPIC所有网络情况下显示图片
     */
    public static String mLoginShowPicType = "";

    /**
     * 设置Cookie
     */
    public static void syncCookie(Context context, String url) {
        try {
            CookieSyncManager.createInstance(context);
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.setAcceptCookie(true);
            cookieManager.removeSessionCookie();// 移除
            cookieManager.removeAllCookie();
            String oldCookie = cookieManager.getCookie(url);
            StringBuilder sbCookie = new StringBuilder();
            sbCookie.append(String.format("JSESSIONID=%s", Config.mCookie));
            sbCookie.append(String.format(";domain=%s", APIManager.domain));
            sbCookie.append(String.format(";path=%s", APIManager.PATH));
            String cookieValue = sbCookie.toString();
            cookieManager.setCookie(url, cookieValue);
            CookieSyncManager.getInstance().sync();
            String newCookie = cookieManager.getCookie(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成随机数
     */
    public static String randomTime() {
        return java.util.UUID.randomUUID().toString();
    }
}
