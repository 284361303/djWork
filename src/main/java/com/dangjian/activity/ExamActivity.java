package com.dangjian.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dangjian.R;
import com.dangjian.adapter.ExamAdapter;
import com.dangjian.entity.ExamBean;
import com.dangjian.utils.APIManager;
import com.dangjian.utils.Config;
import com.dangjian.utils.Control;
import com.dangjian.utils.NetWorkHelper;
import com.dangjian.utils.ToastUtil;
import com.google.gson.Gson;

import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * sg 2016年10月8日12:31:06
 * 考试题页面
 */
public class ExamActivity extends BaseActivity {

    @Bind(R.id.titleOneText_llBack)
    LinearLayout titleOneTextLlBack;
    @Bind(R.id.titleOneText_tvTitleName)
    TextView titleOneTextTvTitleName;
    @Bind(R.id.exam_tvSequence)
    TextView examTvSequence;
    @Bind(R.id.exam_tvTitleName)
    TextView examTvTitleName;
    @Bind(R.id.exam_recyclerView)
    RecyclerView examRecyclerView;
    @Bind(R.id.exam_tvHour)
    TextView examTvHour;
    @Bind(R.id.exam_tvMinute)
    TextView examTvMinute;
    @Bind(R.id.exam_tvSecond)
    TextView examTvSecond;
    @Bind(R.id.exam_btnUp)
    Button examBtnUp;
    @Bind(R.id.exam_btnDown)
    Button examBtnDown;
    @Bind(R.id.exam_tvTotle)
    TextView examTvTotle;
    @Bind(R.id.exam_llContent)
    LinearLayout examLlContent;
    /**
     * 考题题号
     */
    private int mSequences = 1;
    /**
     * 总题目数量
     */
    private int mTotalQuestions = 0;
    /**
     * 考试试卷id
     */
    private String paperId;
    private ExamAdapter adapter;
    private List<ExamBean.SelectItemsBean> listData = new ArrayList<ExamBean.SelectItemsBean>();
    private static final String TAG = "ExamActivity";
    private String mPaperItemId;
    private CountDownTimer tm;
    private String staticFlag = "LOCK";
    private String resultAnswers = "";
    private int COMPLETED = 0;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            try {
                if (msg.what == COMPLETED) {
                    Bundle data = msg.getData();
                    String sequencesFlag = data.getString("sequencesFlag");
                    String mType = data.getString("mType");
                    String title = data.getString("title");
                    if (sequencesFlag.equals("1")) {
                        examBtnUp.setVisibility(View.GONE);
                    } else {
                        examBtnUp.setVisibility(View.VISIBLE);
                    }
                    if (mType.equals("MULTI")) {
                        examTvTitleName.setText("（多选题）" + title);
                    } else {
                        examTvTitleName.setText(title);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        ButterKnife.bind(this);
        initView();
        upDataView();
        getExtras();
    }

    private void initView() {
        titleOneTextTvTitleName.setText("知识竞赛");
        setRandomColor();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        examRecyclerView.setLayoutManager(linearLayoutManager);
    }

    private void getExtras() {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            examBtnDown.setEnabled(false);
            String totalQuestions = extras.getString("totalQuestions");     //试卷总题数
            mTotalQuestions = Integer.parseInt(totalQuestions);
            String totalTimes = extras.getString("totalTimes");     //考试时间，单位为分，0为没有时间限制
            Integer aDouble = Integer.valueOf(totalTimes);
            paperId = extras.getString("paperId");       //考试试卷id
            String exaType = extras.getString("exaType");       //考试类型
//            getPaperQuestion(paperId);
            getPaperQuestionSync(paperId);
            examTvTotle.setText(totalQuestions);
            if (!totalTimes.equals("0")) {
                long v = aDouble * 60 * 1000;
                timeFormat(v);
            }
            Log.i(TAG, "getExtras(总题数--): " + totalQuestions + " ,总时间---" + totalTimes + " ,考试类型---" + exaType);
        }
    }

    private void upDataView() {
        adapter = new ExamAdapter(mContext, listData, this);
        initListener();
        examRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void initListener() {
        adapter.setOnItemClickListener(new ExamAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                try {
                    if (Config.type.equals("SINGLE") || Config.type.equals("JUDGE")) {       //单选或判断
                        adapter.flag = false;
                        for (int i = 0; i < adapter.holders.size(); i++) {
                            adapter.holders.get(i).itemExamIv.setChecked(false);
                        }
                        adapter.flag = true;
                        resultAnswers = listData.get(position).getNum();
                        adapter.holders.get(position).itemExamIv.setChecked(true);
                        Log.i(TAG, "onItemClickListener/num(单选): " + resultAnswers);
                    } else {
                        resultAnswers = "";
                        adapter.holders.get(position).itemExamIv.setChecked(!adapter.holders.get(position).itemExamIv.isChecked());
                        for (int i = 0; i < listData.size(); i++) {
                            boolean checked = adapter.holders.get(i).itemExamIv.isChecked();
                            if (checked) {
                                String num = listData.get(i).getNum();
                                if (!resultAnswers.equals("")) {
                                    resultAnswers += ",";
                                }
                                resultAnswers += num;
                            }
                        }
                        Log.i(TAG, "onItemClickListener/result(多选): " + resultAnswers);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @OnClick({R.id.titleOneText_llBack, R.id.exam_btnUp, R.id.exam_btnDown})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titleOneText_llBack:
                showFinishView();
                break;
            case R.id.exam_btnUp:       //上一题
                submitAnswer();
                examBtnUp.setEnabled(false);
                examBtnDown.setEnabled(false);
                examBtnDown.setText("下一题");
                if (mSequences >= 1) {
                    mSequences -= 1;
//                    getPaperQuestion(paperId);
                    if (mSequences == 0) {
                        mSequences = 1;
                    }
                    getPaperQuestionSync(paperId);
                }
                setRandomColor();
                break;
            case R.id.exam_btnDown:     //下一题
                submitAnswer();
                mSequences += 1;
                Log.i(TAG, "onClick(当前题数) : " + mSequences + " 总题数--" + mTotalQuestions);
                if (mSequences == mTotalQuestions) {
                    examBtnDown.setText("提交试卷");
                } else {
                    if (mSequences < mTotalQuestions + 1) {
                        resultAnswers = "";
                        examBtnDown.setText("下一题");
                    }
                }
                if (mSequences == mTotalQuestions + 1) {
                    mSequences -= 1;
                    showCancelPW();
                } else {
                    examBtnDown.setEnabled(false);
                    setRandomColor();
                    Log.i(TAG, "点击了---（下一题）: ");
//                    getPaperQuestion(paperId);
                    getPaperQuestionSync(paperId);
                }
                break;
        }
    }

    /**
     * （12002）获取考题
     */
    private void getPaperQuesti(String mPaperId) {
        if (listData != null && listData.size() > 0) {
            listData.clear();
        }
        adapter.notifyDataSetChanged();
        Control.getPaperQuestion(mContext, mPaperId, String.valueOf(mSequences), new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    if (mSequences >= Config.maxIndex) {
                        Config.maxIndex = mSequences;
                    }
                    Log.i(TAG, "获取考题: " + result);
                    JSONObject jo = new JSONObject(result);
                    examTvSequence.setText(String.valueOf(mSequences));
                    String mType = jo.getString("type");     //题目类型 SINGLE单选题，MULTI 多选题，JUDGE 判断题
                    mPaperItemId = jo.getString("paperItemId");
                    String sequences = jo.getString("sequence");
                    if (sequences.equals("1")) {
                        examBtnUp.setVisibility(View.GONE);
                    } else {
                        examBtnUp.setVisibility(View.VISIBLE);
                    }
                    if (mType.equals("MULTI")) {
                        examTvTitleName.setText("（多选题）" + jo.getString("title"));
                    } else {
                        examTvTitleName.setText(jo.getString("title"));
                    }
                    Config.type = mType;
                    Log.i(TAG, "题目类型: " + mType);
                    String userAnswer = jo.getString("userAnswer");
                    Config.userAnswer = userAnswer;
                    resultAnswers = userAnswer;
                    Gson gson = new Gson();
                    ExamBean examBean = gson.fromJson(result, ExamBean.class);
                    List<ExamBean.SelectItemsBean> selectItems = examBean.getSelectItems();
                    if (selectItems != null && selectItems.size() > 0) {
                        listData.addAll(selectItems);
                    }
                    adapter.cleanAnsItem();
                    adapter.notifyDataSetChanged();
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
     * （12002）获取考题（同步）
     *
     * @param mPaperId 考试试卷id
     */
    private void getPaperQuestionSync(final String mPaperId) {
        Log.i(TAG, "getPaperQuestionSync进来了: ");
        if (listData != null && listData.size() > 0) {
            listData.clear();
        }
        adapter.notifyDataSetChanged();
        examTvSequence.setText(String.valueOf(mSequences));
        new Thread() {
            @Override
            public void run() {
                try {
                    this.sleep(100);
                    if (NetWorkHelper.checkNetworkState(mContext) == true) {
                        Log.i(TAG, "同步里面-----: " + mPaperId + " ,sequence-----" + String.valueOf(mSequences));
                        RequestParams params = new RequestParams(APIManager.getPaperQuestion);
                        params.addParameter("paperId", mPaperId);
                        params.addParameter("sequence", String.valueOf(mSequences));
                        String postSync = x.http().postSync(params, String.class);
                        Log.i(TAG, "run/postSync: " + postSync);
                        JSONObject jo = new JSONObject(postSync);
                        String status = jo.getString("status");
                        String mMsg = jo.getString("msg");
                        if (status != null && !status.equals("") && status.equals("1")) {
                            if (mSequences >= Config.maxIndex) {
                                Config.maxIndex = mSequences;
                            }
                            Log.i(TAG, "获取考题: " + postSync);
                            String mType = jo.getString("type");     //题目类型 SINGLE单选题，MULTI 多选题，JUDGE 判断题
                            mPaperItemId = jo.getString("paperItemId");
                            String sequencesFlag = jo.getString("sequence");
                            String title = jo.getString("title");
                            Message msg = new Message();
                            msg.what = COMPLETED;
                            Bundle bundle = new Bundle();
                            bundle.putString("sequencesFlag", sequencesFlag);
                            bundle.putString("mType", mType);
                            bundle.putString("title", title);
                            msg.setData(bundle);
                            handler.sendMessage(msg);

                            Config.type = mType;
                            Log.i(TAG, "题目类型: " + mType);
                            String userAnswer = jo.getString("userAnswer");
                            Config.userAnswer = userAnswer;
                            resultAnswers = userAnswer;
                            Gson gson = new Gson();
                            ExamBean examBean = gson.fromJson(postSync, ExamBean.class);
                            List<ExamBean.SelectItemsBean> selectItems = examBean.getSelectItems();
                            if (selectItems != null && selectItems.size() > 0) {
                                listData.addAll(selectItems);
                            }
//                        adapter.cleanAnsItem();
                            ExamActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    adapter.cleanAnsItem();
                                    adapter.notifyDataSetChanged();
                                    examBtnUp.setEnabled(true);
                                    examBtnDown.setEnabled(true);
                                }
                            });
                        } else if (status.equals("9")) {
                            examBtnUp.setEnabled(true);  //12-13添加
                            examBtnDown.setEnabled(true);  //12-13添加
                            ToastUtil.showShort(mContext, mMsg);
                            Intent i = new Intent(mContext, LoginActivity.class);
                            startActivity(i);
                            mContext.finish();
                        } else {
                            ToastUtil.showShort(mContext, mMsg);
                            examBtnUp.setEnabled(true);  //12-13添加
                            examBtnDown.setEnabled(true);  //12-13添加
                        }
                    } else {
                        ToastUtil.showShort(mContext, "请检查网络");
                        examBtnUp.setEnabled(true);
                        examBtnDown.setEnabled(true);
                    }
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                    try {
                        examBtnUp.setEnabled(true);
                        examBtnDown.setEnabled(true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                super.run();
            }
        }.start();
    }

    /**
     * （12003）提交试卷
     */
    private void submitPaper(String sequence) {
        Control.submitPaper(mContext, paperId, sequence, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    Log.i(TAG, "提交试卷: " + result);
                    JSONObject jo = new JSONObject(result);
                    String status = jo.getString("status");
                    String msg = jo.getString("msg");
                    if (!status.equals("") && status.equals("1")) {
                        String rightQuestions = jo.getString("rightQuestions");     //答对题数
                        String points = jo.getString("points");     //获取积分
                        String score = jo.getString("score");       //考试得分
                        Intent i = new Intent(mContext, ExamResultActivity.class);      //跳转到考试结果页面
                        i.putExtra("activity", "ExamActivity");
                        i.putExtra("rightQuestions", rightQuestions);
                        i.putExtra("points", points);
                        i.putExtra("score", score);
                        i.putExtra("paperId", paperId);
                        startActivity(i);
                        mContext.finish();
                        if (tm != null) {
                            tm.cancel();
                        }
                    } else if (status.equals("9")) {
                        ToastUtil.showShort(mContext, msg);
                        Intent i = new Intent(mContext, LoginActivity.class);
                        startActivity(i);
                        mContext.finish();
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
     * 提交考题答案同步
     */
    public void submitQueAnswerSync(final String answerss, final String itemId) {
        Log.i(TAG, "submitQueAnswerSync/answer: " + answerss + " ,mPaperItemId----" + mPaperItemId);
        new Thread() {
            @Override
            public void run() {
                synchronized (staticFlag) {
                    try {
                        if (NetWorkHelper.checkNetworkState(mContext) == true) {
                            RequestParams params = new RequestParams(APIManager.submitQueAnswer);
                            params.addParameter("paperItemId", itemId);
                            params.addParameter("answer", answerss);
                            String s = x.http().postSync(params, String.class);
                            Log.i(TAG, "同步结果--: " + s);
                        } else {
                            ToastUtil.showShort(mContext, "请检查网络");
                        }
                    } catch (Throwable throwable) {
                        throwable.printStackTrace();
                    }
                    super.run();
                }
            }
        }.start();
    }

    /**
     * 提交考题答案
     *
     * @param answer
     */
    public void getSubmitQueAnswer(String answer) {
        Control.submitQueAnswer(mContext, mPaperItemId, answer, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    Log.i(TAG, "提交: " + result);
                    JSONObject jo = new JSONObject(result);
                    String status = jo.getString("status");
                    String msg = jo.getString("msg");
                    if (status.equals("0")) {
                        ToastUtil.showShort(mContext, "提交出错");
                    } else if (status.equals("9")) {
                        ToastUtil.showShort(mContext, msg);
                        Intent i = new Intent(mContext, LoginActivity.class);
                        startActivity(i);
                        mContext.finish();
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
     * 更换随机颜色
     */
    private void setRandomColor() {
        if (mSequences % 2 == 1) {
            examLlContent.setBackgroundResource(R.color.color_19f);
        } else {
            examLlContent.setBackgroundResource(R.color.color_246);
        }
    }

    /**
     * 时间转换显示上去
     *
     * @param start
     */
    private void timeFormat(final long start) {
        try {
            tm = new CountDownTimer(start, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    int ss = 1000;
                    int mi = ss * 60;
                    int hh = mi * 60;
                    int dd = hh * 24;
                    long day = millisUntilFinished / dd;
                    long hour = (millisUntilFinished - day * dd) / hh;
                    long minute = (millisUntilFinished - day * dd - hour * hh) / mi;
                    long second = (millisUntilFinished - day * dd - hour * hh - minute * mi) / ss;
                    long milliSecond = millisUntilFinished - day * dd - hour * hh - minute * mi - second * ss;
                    String strDay = day < 10 ? "0" + day : "" + day; //天
                    String strHour = hour < 10 ? "0" + hour : "" + hour;//小时
                    String strMinute = minute < 10 ? "0" + minute : "" + minute;//分钟
                    String strSecond = second < 10 ? "0" + second : "" + second;//秒
                    String strMilliSecond = milliSecond < 10 ? "0" + milliSecond : "" + milliSecond;//毫秒
                    strMilliSecond = milliSecond < 100 ? "0" + strMilliSecond : "" + strMilliSecond;
                    examTvHour.setText(strHour);
                    examTvMinute.setText(strMinute);
                    examTvSecond.setText(strSecond);
                }

                @Override
                public void onFinish() {
                    try {
                        submitAnswer();
                        submitPaper("");
                        if (tm != null) {
                            tm.cancel();
                        }
                        mContext.finish();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showCancelPW() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
        dialog.setMessage("确定提交 ？");
        dialog.setNegativeButton("取消", null);
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                submitPaper("");
            }
        }).show();
    }

    /**
     * 关闭当前页面提示
     */
    private void showFinishView() {
        try {
            AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
            dialog.setMessage("是否放弃考试 ？");
            dialog.setNegativeButton("取消", null);
            dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //                submitPaper(String.valueOf(Config.maxIndex));
                    submitAnswer();
                    if (tm != null) {
                        tm.cancel();
                    }
                    mContext.finish();
                }
            }).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 提交选中答案
     */
    private void submitAnswer() {
        String currAnswer = resultAnswers;
        String itemId = mPaperItemId;
        submitQueAnswerSync(currAnswer, itemId);
    }

    /**
     * 返回键确定情况下也提交试卷
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            showFinishView();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
