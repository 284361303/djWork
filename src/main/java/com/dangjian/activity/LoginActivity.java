package com.dangjian.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.dangjian.R;
import com.dangjian.utils.AppManager;
import com.dangjian.utils.Config;
import com.dangjian.utils.Control;
import com.dangjian.utils.ToastUtil;

import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.cookie.DbCookieStore;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @Bind(R.id.login_etAccount)
    EditText loginEtAccount;
    @Bind(R.id.login_etPWD)
    EditText loginEtPWD;
    @Bind(R.id.login_btnLogin)
    Button loginBtnLogin;
    @Bind(R.id.login_ivType)
    ImageView loginIvType;
    @Bind(R.id.login_llType)
    LinearLayout loginLlType;
    private boolean states = true;

    private ProgressBar mProgress;
    private boolean cancelUpdate = false;
    private int progress;
    private String mSavePath, checkApkURL = "";
    private static final int DOWNLOAD = 1;
    private static final int DOWNLOAD_FINISH = 2;

    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        String loginSates = sharedPreferences.getString("loginSates", null);        //记住密码状态,true为不记住，false为记住
        String loginName = sharedPreferences.getString("loginName", null);      //登录账号
        String pwd = sharedPreferences.getString("pwd", null);      //密码
        if (loginSates == null || loginSates.equals("false")) {
            if (loginName != null && !loginName.equals("")) {
                loginEtAccount.setText(loginName);
            }
            if (pwd != null && !pwd.equals("")) {
                loginEtPWD.setText(pwd);
            }
        } else if (loginSates.equals("true")) {
            if (loginName != null && !loginName.equals("")) {
                loginEtAccount.setText(loginName);
            }
            Glide.with(mContext).load(R.drawable.all_choice_outo).into(loginIvType);
        }
    }

    @OnClick({R.id.login_btnLogin, R.id.login_ivType, R.id.login_llType})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_btnLogin:
                String account = loginEtAccount.getText().toString().trim();
                String pwd = loginEtPWD.getText().toString().trim();
                if (TextUtils.isEmpty(account)) {
                    ToastUtil.showShort(mContext, "账号不能为空");
                    return;
                }
                if (TextUtils.isEmpty(pwd)) {
                    ToastUtil.showShort(mContext, "密码不能为空");
                    return;
                }
                Login(account, pwd);
                break;
            case R.id.login_llType:
                SharedPreferences.Editor edit = sharedPreferences.edit();
                if (states) {
                    edit.putString("loginSates", "true");
                    edit.commit();
                    Glide.with(mContext).load(R.drawable.all_choice_outo).into(loginIvType);
                    states = false;
                } else {
                    edit.putString("loginSates", "false");
                    edit.commit();
                    Glide.with(mContext).load(R.drawable.signin_check).into(loginIvType);
                    states = true;
                }
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        CheckAppVersion();
    }

    /**
     * （11001）登录
     *
     * @param mLoginName
     * @param mPwd
     */
    private void Login(final String mLoginName, final String mPwd) {
        Control.Login(mContext, mLoginName, mPwd, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                DbCookieStore instance = DbCookieStore.INSTANCE;
                List cookies = instance.getCookies();
                for (int i = 0; i < cookies.size(); i++) {
                    if ((cookies.get(i) + "").contains("JSESSIONID")) {
                        Config.mCookie = cookies.get(i).toString().replace("JSESSIONID=", "");
                        break;
                    }
                }
                try {
                    Log.i(TAG, "登录: " + result);
                    JSONObject jo = new JSONObject(result);
                    String status = jo.getString("status");
                    String msg = jo.getString("msg");
                    if (status.equals("1")) {
                        String lastLoginDate = jo.getString("lastLoginDate");       //上次登录时间
                        String iconPath = jo.getString("iconPath");     //个人图片路径
                        String loginName = jo.getString("loginName");       //登录名
                        String deptCode = jo.getString("deptCode");     //部门编码
                        String deptId = jo.getString("deptId");     //部门id
                        Config.DW_ADMIN_ROLE = jo.getString("DW_ADMIN_ROLE");       //DW_ADMIN党委管理员,为空是不是党委管理员
                        Config.ZB_ADMIN_ROLE = jo.getString("ZB_ADMIN_ROLE");       //ZB_ADMIN支部管理员,为空是不是管理员
                        Config.XC_ADMIN_ROLE = jo.getString("XC_ADMIN_ROLE");       //XC_ADMIN宣传小组管理员,为空是不是管理员
                        Config.ZZ_ADMIN_ROLE = jo.getString("ZZ_ADMIN_ROLE");       //ZZ_ADMIN组织小组管理员,为空是不是管理员
                        Config.JJ_ADMIN_ROLE = jo.getString("JJ_ADMIN_ROLE");       //JJ_ADMIN纪检小组管理员,为空是不是管理员
                        Config.QN_AMIN_ROLE = jo.getString("QN_ADMIN_ROLE");         //QN_AMIN青年小组管理员,为空是不是管理员
                        Config.GH_ADMIN_ROLE = jo.getString("GH_ADMIN_ROLE");       //GH_ADMIN工会管理员,为空是不是管理员
                        Config.mDeptName = jo.getString("deptName");  //  部门名称
                        Config.mEmpId = jo.getString("empId");       // 员工编号
                        Config.mLoginShowPicType = jo.getString("showPicType");    //WIFIPIC wifi下显示图片，ALLPIC所有网络情况下显示图片
                        Config.mNickName = jo.getString("nickName");  //昵称
                        Config.mDeptCode = deptCode;
                        Config.mDeptId = deptId;
                        Config.mIconPath = iconPath;
                        Config.mLoginName = loginName;
                        SharedPreferences.Editor edit = sharedPreferences.edit();
                        edit.putString("loginName", mLoginName);
                        edit.putString("pwd", mPwd);
                        edit.commit();
                        Intent i = new Intent(mContext, MainTabActivity.class);
                        i.putExtra("lastLoginDate", lastLoginDate);
                        i.putExtra("iconPath", iconPath);
                        i.putExtra("loginName", loginName);
                        i.putExtra("deptCode", deptCode);
                        startActivity(i);
                        mContext.finish();
                    } else if (status.equals("0")) {
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

    /**
     * （16001）检查APP版本更新
     */
    private void CheckAppVersion() {
        int versionCode = getVersionCode();
        Control.checkAppUpdate(mContext, String.valueOf(versionCode), "2", new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    Log.i(TAG, "检查APP更新: " + result);
                    JSONObject jo = new JSONObject(result);
                    String status = jo.getString("status");     //  1 有更新  0 没有更新
                    if (status.equals("1")) {
                        String level = jo.getString("level");   //  0表示应该更新，1表示必须更新
                        checkApkURL = jo.getString("url");
                        if (level != null && !level.equals("") && level.equals("1")) {
                            showUpdataApk2();
                        } else {
                            showUpdataApk();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
            }
        });
    }

    /**
     * 获取当前版本号
     *
     * @return
     */
    private int getVersionCode() {
        int versionCode = 0;
        try {
            PackageManager packageManager = this.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(this.getPackageName(), 0);
            versionCode = packageInfo.versionCode;
            Log.i(TAG, "getVersionCode: " + versionCode);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * 检查更新弹出框
     */
    private void showUpdataApk() {
        try {
            final AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
            dialog.setTitle("软件更新");
            dialog.setMessage("检测到新版本，立即更新吗 ？");
            dialog.setCancelable(false);
            dialog.setNegativeButton("稍后更新", null);
            dialog.setPositiveButton("更新", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    showUpDataing();
                }
            }).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 检查强制更新弹出框
     */
    private void showUpdataApk2() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
        dialog.setTitle("软件更新");
        dialog.setMessage("检测到新版本，立即更新吗 ？");
        dialog.setCancelable(false);
        dialog.setNegativeButton("退出", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                exitApplication();
            }
        });
        dialog.setPositiveButton("更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                showUpDataing();
            }
        }).show();
    }

    /**
     * 下载中弹窗
     */
    private void showUpDataing() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
        dialog.setTitle("正在更新...");
        dialog.setCancelable(false);
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.item_progressbar, null, false);
        mProgress = (ProgressBar) view.findViewById(R.id.update_progress);
        dialog.setView(view);

        dialog.setPositiveButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                cancelUpdate = true;
                exitApplication();
            }
        }).show();
        new downloadApkThread().start();
    }

    public class downloadApkThread extends Thread {
        @Override
        public void run() {
            try {
                if (Environment.getExternalStorageState().equals(
                        Environment.MEDIA_MOUNTED)) {
                    String sdpath = Environment.getExternalStorageDirectory() + "/";
                    mSavePath = sdpath + "do";
                    File file = new File(mSavePath);
                    if (!file.exists()) {
                        file.mkdir();
                    }
                    File apkFile = new File(mSavePath + "dangjian.apk");
                    URL url1 = new URL(checkApkURL);
                    HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
                    conn.connect();
                    int length = conn.getContentLength();
                    InputStream is = conn.getInputStream();
                    FileOutputStream fos = new FileOutputStream(apkFile);
                    int count = 0;
                    byte buf[] = new byte[1024];
                    do {
                        int numread = is.read(buf);
                        count += numread;
                        progress = (int) (((float) count / length) * 100);
                        mHandler1.sendEmptyMessage(DOWNLOAD);
                        if (numread <= 0) {
                            mHandler1.sendEmptyMessage(DOWNLOAD_FINISH);
                            break;
                        }
                        fos.write(buf, 0, numread);
                    } while (!cancelUpdate);
                    fos.close();
                    is.close();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private Handler mHandler1 = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case DOWNLOAD:
                    mProgress.setProgress(progress);
                    break;
                case DOWNLOAD_FINISH:
                    installApk();
                    openFile();
                    break;
                default:
                    break;
            }
        }
    };

    public void installApk() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "dodangjian.apk")), "application/vnd.android.package-archive");
        startActivity(intent);
    }

    private void openFile() {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(android.content.Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "dodangjian.apk")), "application/vnd.android.package-archive");
        startActivity(intent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        try {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                exitApplication();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exitApplication() {
        try {
            finish();
            AppManager.getAppManager().finishAllActivity();
            AppManager.getAppManager().finishAllfActivity();
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
            finish();
            System.exit(0);
        }
    }
}