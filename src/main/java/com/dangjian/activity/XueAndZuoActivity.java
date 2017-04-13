package com.dangjian.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dangjian.R;
import com.dangjian.entity.LearningPomoteBean;
import com.dangjian.utils.Control;
import com.dangjian.utils.ToastUtil;
import com.google.gson.Gson;

import org.json.JSONObject;
import org.xutils.common.Callback;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * sg  2016年9月30日10:11:18
 * 两学一做activity
 */
public class XueAndZuoActivity extends BaseActivity {

    @Bind(R.id.titleOneText_llBack)
    LinearLayout titleOneTextLlBack;
    @Bind(R.id.titleOneText_tvTitleName)
    TextView titleOneTextTvTitleName;
    @Bind(R.id.xueAndZuo_btnB)
    LinearLayout xueAndZuoBtnB;
    @Bind(R.id.xueAndZuo_btnA)
    LinearLayout xueAndZuoBtnA;
    @Bind(R.id.xueAndZuo_btnC)
    LinearLayout xueAndZuoBtnC;
    @Bind(R.id.xueAndZuo_btnD)
    LinearLayout xueAndZuoBtnD;
    @Bind(R.id.xueAndZuo_btnE)
    LinearLayout xueAndZuoBtnE;
    @Bind(R.id.xueAndZuo_tvAMessage)
    TextView xueAndZuoTvAMessage;
    @Bind(R.id.xueAndZuo_tvBMessage)
    TextView xueAndZuoTvBMessage;
    @Bind(R.id.xueAndZuo_tvCMessage)
    TextView xueAndZuoTvCMessage;
    @Bind(R.id.xueAndZuo_tvDMessage)
    TextView xueAndZuoTvDMessage;
    @Bind(R.id.xueAndZuo_tvEMessage)
    TextView xueAndZuoTvEMessage;
    private static final String TAG = "XAndZActivity";
    @Bind(R.id.xueAndZuo_ivPic)
    ImageView xueAndZuoIvPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xue_and_zuo);
        ButterKnife.bind(this);
        initView();
        getModulePic();
    }

    private void initView() {
        titleOneTextTvTitleName.setText("两学一做");
    }

    @OnClick({R.id.titleOneText_llBack, R.id.xueAndZuo_btnB, R.id.xueAndZuo_btnA, R.id.xueAndZuo_btnC, R.id.xueAndZuo_btnD, R.id.xueAndZuo_btnE})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titleOneText_llBack:
                mContext.finish();
                break;
            case R.id.xueAndZuo_btnB:   //每周一考
                getData();
                break;
            case R.id.xueAndZuo_btnA:   //学习资料
                Intent i = new Intent(mContext, XueXiZiLiaoActivity.class);
                startActivity(i);
                break;
            case R.id.xueAndZuo_btnC:      //知识竞赛
                Intent iC = new Intent(mContext, ZhiShiJingSaiActivity.class);
                startActivity(iC);
                break;
            case R.id.xueAndZuo_btnD:   //学习心得
                Intent iD = new Intent(mContext, XinDeWebActivity.class);
                startActivity(iD);
                break;
            case R.id.xueAndZuo_btnE:   //学习排名
                Intent iE = new Intent(mContext, LearningRankActivity.class);
                startActivity(iE);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getMessageCountData();
    }

    /**
     * （12008）获取每周一考考试Id
     */
    private void getData() {
        Control.getWeeklyExcuteId(mContext, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    Log.i(TAG, "每周一考: " + result);
                    JSONObject jo = new JSONObject(result);
                    String status = jo.getString("status");
                    String msg = jo.getString("msg");
                    if (status != null && !status.equals("") && status.equals("1")) {
                        String excuteId = jo.getString("excuteId");
                        getPaperResult(excuteId);
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
     * （12005）获得考试结果
     *
     * @param mExcuteId
     */
    private void getPaperResult(String mExcuteId) {
        Control.getPaperResult(mContext, mExcuteId, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    Log.i(TAG, "考试结果: " + result);
                    JSONObject jo = new JSONObject(result);
                    String paperId = jo.getString("paperId");       //试卷id，为-1时没有考试结果
                    String exaStatus = jo.getString("exaStatus");   //考试状态，ING 进行中,FINISHED已完成，NOTSTART没开始，DISABLED停用
                    String rightQuestions = jo.getString("rightQuestions");     //答对题数
                    String points = jo.getString("points");     //获取积分
                    String score = jo.getString("score");     //考试得分
                    if (paperId.equals("-1")) {
                        Intent i = new Intent(mContext, WeekExamActivity.class);
                        startActivity(i);
                    } else {
                        Intent i = new Intent(mContext, ExamResultActivity.class);      //跳转到考试结果页面
                        i.putExtra("activity", "WeekExamActivity");
                        i.putExtra("rightQuestions", rightQuestions);
                        i.putExtra("points", points);
                        i.putExtra("score", score);
                        i.putExtra("paperId", paperId);
                        startActivity(i);
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
     * （13001）获取未读消息数
     */
    private void getMessageCountData() {
        Control.getSecondMenu(mContext, "TWOSTDONEDO", new Callback.CommonCallback<String>() {
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
                                if (code != null && !code.equals("") && code.equals("competition")) {  //知识竞赛
                                    String messCount = list.get(i).getMessCount();
                                    if (Integer.parseInt(messCount) > 0) {
                                        xueAndZuoTvCMessage.setVisibility(View.VISIBLE);
                                        xueAndZuoTvCMessage.setText(messCount);
                                    } else {
                                        xueAndZuoTvCMessage.setVisibility(View.INVISIBLE);
                                    }
                                } else if (code != null && !code.equals("") && code.equals("weekly")) {   //每周一考
                                    String messCount = list.get(i).getMessCount();
                                    if (Integer.parseInt(messCount) > 0) {
                                        xueAndZuoTvBMessage.setVisibility(View.VISIBLE);
                                    } else {
                                        xueAndZuoTvBMessage.setVisibility(View.INVISIBLE);
                                    }
                                } else if (code != null && !code.equals("") && code.equals("STUDYMATERIAL")) {   //学习资料
                                    String messCount = list.get(i).getMessCount();
                                    if (Integer.parseInt(messCount) > 0) {
                                        xueAndZuoTvAMessage.setVisibility(View.VISIBLE);
                                        xueAndZuoTvAMessage.setText(messCount);
                                    } else {
                                        xueAndZuoTvAMessage.setVisibility(View.INVISIBLE);
                                    }
                                } else if (code != null && !code.equals("") && code.equals("STUDYNOTE")) {   //学习心得
                                    String messCount = list.get(i).getMessCount();
                                    if (Integer.parseInt(messCount) > 0) {
                                        xueAndZuoTvDMessage.setVisibility(View.VISIBLE);
                                    } else {
                                        xueAndZuoTvDMessage.setVisibility(View.INVISIBLE);
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
     * （13023）获得模块首页图片
     */
    private void getModulePic() {
        Control.getModulePic(mContext, "TWOSTDONEDO", new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    Log.i(TAG, "首页图片: " + result);
                    JSONObject jo = new JSONObject(result);
                    String status = jo.getString("status");
                    String msg = jo.getString("msg");
                    if (status != null && !status.equals("") && status.equals("1")) {
                        String iconPath = jo.getString("iconPath");
                        Glide.with(mContext).load(iconPath).error(R.drawable.twolearn_todo_bigbg).into(xueAndZuoIvPic);
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
}
