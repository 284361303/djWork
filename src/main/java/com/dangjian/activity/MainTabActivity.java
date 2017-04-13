package com.dangjian.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dangjian.R;
import com.dangjian.entity.LearningPomoteBean;
import com.dangjian.utils.APIManager;
import com.dangjian.utils.AppManager;
import com.dangjian.utils.Config;
import com.dangjian.utils.Control;
import com.dangjian.utils.NetWorkHelper;
import com.dangjian.utils.ToastUtil;
import com.google.gson.Gson;

import org.json.JSONObject;
import org.xutils.common.Callback;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 项目首页展示页面
 */
public class MainTabActivity extends BaseActivity {

    @Bind(R.id.mainTab_userPic)
    CircleImageView mainTabUserPic;
    @Bind(R.id.mainTab_tvOne)
    TextView mainTabTvOne;
    @Bind(R.id.mainTab_tvTwo)
    TextView mainTabTvTwo;
    @Bind(R.id.mainTab_tvThree)
    TextView mainTabTvThree;
    @Bind(R.id.mainTab_tvA)
    TextView mainTabTvA;
    @Bind(R.id.mainTab_llA)
    LinearLayout mainTabLlA;
    @Bind(R.id.mainTab_tvB)
    TextView mainTabTvB;
    @Bind(R.id.mainTab_llB)
    LinearLayout mainTabLlB;
    @Bind(R.id.mainTab_tvC)
    TextView mainTabTvC;
    @Bind(R.id.mainTab_llC)
    LinearLayout mainTabLlC;
    @Bind(R.id.mainTab_tvD)
    TextView mainTabTvD;
    @Bind(R.id.mainTab_llD)
    LinearLayout mainTabLlD;
    @Bind(R.id.mainTab_tvE)
    TextView mainTabTvE;
    @Bind(R.id.mainTab_llE)
    LinearLayout mainTabLlE;
    @Bind(R.id.mainTab_tvF)
    TextView mainTabTvF;
    @Bind(R.id.mainTab_llF)
    LinearLayout mainTabLlF;
    @Bind(R.id.mainTab_tvH)
    TextView mainTabTvH;
    @Bind(R.id.mainTab_llH)
    LinearLayout mainTabLlH;
    @Bind(R.id.mainTab_tvI)
    TextView mainTabTvI;
    @Bind(R.id.mainTab_llI)
    LinearLayout mainTabLlI;
    @Bind(R.id.mainTab_tvJ)
    TextView mainTabTvJ;
    @Bind(R.id.mainTab_llJ)
    LinearLayout mainTabLlJ;
    @Bind(R.id.mainTab_tvK)
    TextView mainTabTvK;
    @Bind(R.id.mainTab_llK)
    LinearLayout mainTabLlK;
    @Bind(R.id.mainTab_llUser)
    LinearLayout mainTabLlUser;
    @Bind(R.id.mainTab_llBottom)
    LinearLayout mainTabLlBottom;
    private String iconPath, loginName, deptCode;
    private static boolean isExit = false;
    private String windowFlag = "0";
    private static final String TAG = "MainTabActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tab);
        ButterKnife.bind(this);
        initView();
        getExtras();
//        getBirthDayLan();
        getNotReadMessageIds();
    }

    private void getExtras() {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String lastLoginDate = extras.getString("lastLoginDate");       //上次登录时间
        iconPath = extras.getString("iconPath");     //个人图片路径
        loginName = extras.getString("loginName");       //登录名
        deptCode = extras.getString("deptCode");     //部门编码
        mainTabTvTwo.setText(deptCode);
        mainTabTvThree.setText(lastLoginDate);
    }

    private void initView() {
        // 党委管理员和支部管理员才能操作管理员功能
        if (!Config.DW_ADMIN_ROLE.equals("") || !Config.ZB_ADMIN_ROLE.equals("") || !Config.XC_ADMIN_ROLE.equals("") || !Config.ZZ_ADMIN_ROLE.equals("") || !Config.JJ_ADMIN_ROLE.equals("")
                || !Config.QN_AMIN_ROLE.equals("") || !Config.GH_ADMIN_ROLE.equals("")) {
            mainTabLlBottom.setVisibility(View.VISIBLE);
        } else {
            mainTabLlBottom.setVisibility(View.GONE);
        }
        //当前为非wifi状态下就提示
        if (NetWorkHelper.checkNetworkState(mContext) == true) {
            String wifiOrMobile = NetWorkHelper.isWifiOrMobile(mContext);
            if (wifiOrMobile.equals("MOBILE")) {
                String netWorkStatus = sharedPreferences.getString("NetWorkStatus", null);        //记住密码状态,true为不记住，false为记住
                if (netWorkStatus != null && !netWorkStatus.equals("")) {
                } else {
                    SharedPreferences.Editor edit = sharedPreferences.edit();
                    edit.putString("NetWorkStatus", "mobile");
                    edit.commit();
                    showPicStatePW();
                }
            }
        }
    }

    @OnClick({R.id.mainTab_llA, R.id.mainTab_llB, R.id.mainTab_llC, R.id.mainTab_llD, R.id.mainTab_llE,
            R.id.mainTab_llF, R.id.mainTab_llH, R.id.mainTab_llI, R.id.mainTab_llJ, R.id.mainTab_llK, R.id.mainTab_llUser})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mainTab_llA:      //通知公告
                Intent i = new Intent(mContext, InformMessageActivity.class);
                startActivity(i);
                break;
            case R.id.mainTab_llB:      //学习宣传
                Intent iLearning = new Intent(mContext, LearningPomoteActivity.class);
                startActivity(iLearning);
                break;
            case R.id.mainTab_llC:      //组织工作
                Intent izzWork = new Intent(mContext, ZZWorkActivity.class);
                startActivity(izzWork);
                break;
            case R.id.mainTab_llD:      //纪检工作
                Intent iJJWork = new Intent(mContext, JiJianWorkActivity.class);
                startActivity(iJJWork);
                break;
            case R.id.mainTab_llE:      //青年工作
                Intent iYouthWork = new Intent(mContext, YouthWorkActivity.class);
                startActivity(iYouthWork);
                break;
            case R.id.mainTab_llF:      //工会工作
                Intent iUnionWork = new Intent(mContext, UnionWorkActivity.class);
                startActivity(iUnionWork);
                break;
            case R.id.mainTab_llH:      //支部天地
                Intent iZBFamily = new Intent(mContext, ZhiBuFamilyActivity.class);
                startActivity(iZBFamily);
                break;
            case R.id.mainTab_llI:      //两学一做
                Intent iI = new Intent(mContext, XueAndZuoActivity.class);
                startActivity(iI);
                break;
            case R.id.mainTab_llJ:      //互动交流
                Intent iJ = new Intent(mContext, ChatActivity.class);
                startActivity(iJ);
                break;
            case R.id.mainTab_llK:      //管理员
                Intent ik = new Intent(mContext, AdministratorActivity.class);
                startActivity(ik);
                break;
            case R.id.mainTab_llUser:       //用户
                Intent iUser = new Intent(mContext, UserActivity.class);   //总排名
                iUser.putExtra("iconPath", iconPath);
                iUser.putExtra("loginName", loginName);
                iUser.putExtra("deptCode", deptCode);
                startActivity(iUser);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
        Glide.with(mContext).load(APIManager.ImagePath + Config.mIconPath).error(R.drawable.all_smallhead).into(mainTabUserPic);
        if (Config.mNickName != null && !Config.mNickName.equals("")) {
            mainTabTvOne.setText(Config.mNickName);
        } else {
            mainTabTvOne.setText(loginName);
        }
    }

    /**
     * 入党时间祝语
     *
     * @param partyMessage 祝语
     */
    private void showPartyWindow(String partyMessage) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View vv = inflater.inflate(R.layout.pop_birthday, null);
        final PopupWindow popupWindow = new PopupWindow(vv, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, false);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.showAsDropDown(vv);
        Button btnClose = (Button) vv.findViewById(R.id.popBirth_btnClose);
        TextView tvOne = (TextView) vv.findViewById(R.id.popBirth_tvOne);
        tvOne.setText(partyMessage);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }
            }
        });
    }

    /**
     * 生日祝语
     *
     * @param birthMessage
     */
    private void showBirthDayWindow(String birthMessage) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View vv = inflater.inflate(R.layout.pop_birthday, null);
        final PopupWindow popupWindow = new PopupWindow(vv, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, false);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.showAsDropDown(vv);
        Button btnClose = (Button) vv.findViewById(R.id.popBirth_btnClose);
        TextView tvOne = (TextView) vv.findViewById(R.id.popBirth_tvOne);
        tvOne.setText(birthMessage);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }
            }
        });
    }

    /**
     * 当前非wifi状态下弹窗提醒
     */
    private void showPicStatePW() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
        dialog.setMessage("当前为非WIFI状态，是否显示新闻信息类图片？");
        dialog.setNegativeButton("不显示", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                showPicType("WIFIPIC");
            }
        });
        dialog.setPositiveButton("显示", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                showPicType("ALLPIC");
            }
        }).show();
    }

    /**
     * （13001）获取未读消息数
     */
    private void getData() {
        Control.getSecondMenu(mContext, "DJPT", new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    Log.i(TAG, "未读消息数: " + result);
                    JSONObject jo = new JSONObject(result);
                    String status = jo.getString("status");
                    String msg = jo.getString("msg");
                    if (status != null && status.equals("1")) {
                        Gson gson = new Gson();
                        LearningPomoteBean bean = gson.fromJson(result, LearningPomoteBean.class);
                        List<LearningPomoteBean.ListBean> list = bean.getList();
                        if (list != null && list.size() > 0) {
                            for (int i = 0; i < list.size(); i++) {
                                String code = list.get(i).getCode();
                                if (code != null && !code.equals("") && code.equals("NOTICEMENU")) {  //通知公告
                                    String messCount = list.get(i).getMessCount();
                                    if (Integer.parseInt(messCount) > 0) {
                                        mainTabTvA.setVisibility(View.VISIBLE);
                                        mainTabTvA.setText(messCount);
                                    } else {
                                        mainTabTvA.setVisibility(View.INVISIBLE);
                                    }
                                } else if (code != null && !code.equals("") && code.equals("PARTMENU")) {   //支部工作
                                    String messCount = list.get(i).getMessCount();
                                    if (Integer.parseInt(messCount) > 0) {
                                        mainTabTvH.setVisibility(View.VISIBLE);
                                        mainTabTvH.setText(messCount);
                                    } else {
                                        mainTabTvH.setVisibility(View.INVISIBLE);
                                    }
                                } else if (code != null && !code.equals("") && code.equals("YOUNGMENU")) {   //青年工作
                                    String messCount = list.get(i).getMessCount();
                                    if (Integer.parseInt(messCount) > 0) {
                                        mainTabTvE.setVisibility(View.VISIBLE);
                                        mainTabTvE.setText(messCount);
                                    } else {
                                        mainTabTvE.setVisibility(View.INVISIBLE);
                                    }
                                } else if (code != null && !code.equals("") && code.equals("INSPECTIONMENU")) {   //纪检工作
                                    String messCount = list.get(i).getMessCount();
                                    if (Integer.parseInt(messCount) > 0) {
                                        mainTabTvD.setVisibility(View.VISIBLE);
                                        mainTabTvD.setText(messCount);
                                    } else {
                                        mainTabTvD.setVisibility(View.INVISIBLE);
                                    }
                                } else if (code != null && !code.equals("") && code.equals("ORGMENU")) {   //组织工作
                                    String messCount = list.get(i).getMessCount();
                                    if (Integer.parseInt(messCount) > 0) {
                                        mainTabTvC.setVisibility(View.VISIBLE);
                                        mainTabTvC.setText(messCount);
                                    } else {
                                        mainTabTvC.setVisibility(View.INVISIBLE);
                                    }
                                } else if (code != null && !code.equals("") && code.equals("STUDYMENU")) {   //学习宣传
                                    String messCount = list.get(i).getMessCount();
                                    if (Integer.parseInt(messCount) > 0) {
                                        mainTabTvB.setVisibility(View.VISIBLE);
                                        mainTabTvB.setText(messCount);
                                    } else {
                                        mainTabTvB.setVisibility(View.INVISIBLE);
                                    }
                                } else if (code != null && !code.equals("") && code.equals("TWOSTDONEDO")) {   //两学一做
                                    String messCount = list.get(i).getMessCount();
                                    if (Integer.parseInt(messCount) > 0) {
                                        mainTabTvI.setVisibility(View.VISIBLE);
                                        mainTabTvI.setText(messCount);
                                    } else {
                                        mainTabTvI.setVisibility(View.INVISIBLE);
                                    }
                                } else if (code != null && !code.equals("") && code.equals("UNIONMENU")) {   //工会工作
                                    String messCount = list.get(i).getMessCount();
                                    if (Integer.parseInt(messCount) > 0) {
                                        mainTabTvF.setVisibility(View.VISIBLE);
                                        mainTabTvF.setText(messCount);
                                    } else {
                                        mainTabTvF.setVisibility(View.INVISIBLE);
                                    }
                                } else if (code != null && !code.equals("") && code.equals("COMMENU")) {   //互动交流
                                    String messCount = list.get(i).getMessCount();
                                    if (Integer.parseInt(messCount) > 0) {
                                        mainTabTvJ.setVisibility(View.VISIBLE);
                                    } else {
                                        mainTabTvJ.setVisibility(View.INVISIBLE);
                                    }
                                }
                            }
                        }
                    } else if (status.equals("9")) {
                        ToastUtil.showShort(mContext, msg);
                        Intent i = new Intent(mContext, LoginActivity.class);
                        startActivity(i);
                        mContext.finish();
                    } else {
                        ToastUtil.showShort(mContext, msg);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                ex.printStackTrace();
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
     * （11010）获得生日祝语
     */
    private void getBirthDayLan() {
        Control.getBirthDayLan(mContext, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    Log.i(TAG, "生日祝语: " + result);
                    JSONObject jo = new JSONObject(result);
                    String status = jo.getString("status");
                    if (status != null && !status.equals("") && status.equals("1")) {
                        String partyLan = jo.getString("partyLan");     //入党生日祝语
                        String birthDayLan = jo.getString("birthDayLan");       //出生生日祝语
                        if ((partyLan != null && !partyLan.equals("")) && (birthDayLan != null && !birthDayLan.equals(""))) {
                            showPartyWindow(partyLan);
                            showBirthDayWindow(birthDayLan);
                            windowFlag = "1";
                        } else if (partyLan != null && !partyLan.equals("") && windowFlag.equals("0")) {
                            showPartyWindow(partyLan);
                        } else if (birthDayLan != null && !birthDayLan.equals("") && windowFlag.equals("0")) {
                            showBirthDayWindow(birthDayLan);
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
     * （11011）设置图片显示类型
     */
    private void showPicType(final String type) {
        Control.showPicType(mContext, type, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    Log.i(TAG, "设置图片显示: " + result);
                    JSONObject jo = new JSONObject(result);
                    String status = jo.getString("status");
                    String msg = jo.getString("msg");
                    if (status != null && !status.equals("") && status.equals("1")) {
                        Config.mLoginShowPicType = type;
                        ToastUtil.showShort(mContext, msg);
                    } else if (status.equals("9")) {
                        ToastUtil.showShort(mContext, msg);
                        Intent i = new Intent(mContext, LoginActivity.class);
                        startActivity(i);
                        mContext.finish();
                    } else {
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
     * （16004）获取未读登录弹出页面消息id列表
     */
    private void getNotReadMessageIds() {
        Control.getNotReadMessageIds(mContext, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    Log.i(TAG, "未读消息id列表: " + result);
                    JSONObject jo = new JSONObject(result);
                    String status = jo.getString("status");
                    String msg = jo.getString("msg");
                    if (!status.equals("") && status.equals("1")) {
                        String messageIds = jo.getString("messageIds");
                        if (messageIds != null && !messageIds.equals("")) {
                            String[] split = messageIds.split(",");
                            if (split != null && split.length > 0) {
                                for (int i = 0; i < split.length; i++) {
                                    String mesageId = split[i].toString();
                                    Log.i(TAG, "mesageId: " + mesageId);
                                    Intent notRead_Intent = new Intent(mContext, NotReadMessageActivity.class);
                                    notRead_Intent.putExtra("messageId", mesageId);
                                    startActivity(notRead_Intent);
                                }
                            }
                        }
                    } else if (status.equals("9")) {
                        ToastUtil.showShort(mContext, msg);
                        Intent i = new Intent(mContext, LoginActivity.class);
                        startActivity(i);
                        mContext.finish();
                    } else {
                        ToastUtil.showShort(mContext, msg);
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
     * 退出应用程序
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Timer t = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                isExit = false;
            }
        };
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isExit == false) {
                isExit = true;
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                tt = null;
                tt = new TimerTask() {
                    @Override
                    public void run() {
                        isExit = false;
                    }
                };
                t.schedule(tt, 2000);
            } else {
                mContext.finish();
                AppManager.getAppManager().AppExit(this);
            }
        }
        return true;
    }
}