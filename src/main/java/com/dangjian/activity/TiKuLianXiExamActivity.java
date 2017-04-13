package com.dangjian.activity;

import android.app.ActionBar;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dangjian.R;
import com.dangjian.adapter.TiKuLianXiAdapter;
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

import static com.dangjian.R.id.tklx_btnDown;

/**
 * 题库练习
 */
public class TiKuLianXiExamActivity extends BaseActivity {

    @Bind(R.id.titleOneText_llBack)
    LinearLayout titleOneTextLlBack;
    @Bind(R.id.titleOneText_tvTitleName)
    TextView titleOneTextTvTitleName;
    @Bind(R.id.tklx_recyclerView)
    RecyclerView tklxRecyclerView;
    @Bind(tklx_btnDown)
    Button tklxBtnDown;
    @Bind(R.id.tklx_tvLookAnswer)
    TextView tklxTvLookAnswer;
    @Bind(R.id.tklx_btnTest)
    Button tklxBtnTest;
    @Bind(R.id.tklx_llContent)
    LinearLayout tklxLlContent;
    @Bind(R.id.tklx_btnUp)
    Button tklxBtnUp;
    @Bind(R.id.tklx_tvSequence)
    TextView tklxTvSequence;
    @Bind(R.id.tklx_tvTitleName)
    TextView tklxTvTitleName;
    /**
     * 考题题号
     */
    private int sequences = 1;
    /**
     * 总题目数量
     */
    private int mTotalQuestions = 0;
    private List<ExamBean.SelectItemsBean> listData = new ArrayList<ExamBean.SelectItemsBean>();
    private static final String TAG = "LXEActivity";
    //    private TiKuLianXiAdapter adapter;
    private String mPaperItemId = "";
    /**
     * 考试试卷id
     */
    private String paperId;
    private String mRightAnswer, mAnswers = "";
    private TiKuLianXiAdapter adapter;
    private String staticFlag = "LOCK";
    private String resultAnswers = "";
    private int COMPLETED = 2;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            try {
                if (msg.what == COMPLETED) {
                    Bundle data = msg.getData();
                    String sequencesFlag = data.getString("sequencesFlag");
                    String mType = data.getString("mType");
                    String title = data.getString("title");  //btnType,userAnswer
                    String btnType = data.getString("btnType");
                    String userAnswer = data.getString("userAnswer");
                    if (sequencesFlag.equals("1")) {
                        tklxBtnUp.setVisibility(View.GONE);
                    } else {
                        tklxBtnUp.setVisibility(View.VISIBLE);
                    }
                    if (mType.equals("MULTI")) {
                        tklxTvTitleName.setText("（多选题）" + title);
                    } else {
                        tklxTvTitleName.setText(title);
                    }
                    if (btnType.equals("up")) {     //上一题就可以点击按钮
                        tklxBtnDown.setEnabled(true);
                        tklxBtnDown.setBackgroundResource(R.drawable.shi_btn_red);
                    } else if (btnType.equals("down")) {
                        if (userAnswer != null && !userAnswer.equals("")) {
                            tklxBtnDown.setEnabled(true);
                            tklxBtnDown.setBackgroundResource(R.drawable.shi_btn_red);
                        } else {
                            tklxBtnDown.setEnabled(false);
                            tklxBtnDown.setBackgroundResource(R.drawable.shi_btn_ecf);
                        }
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
        setContentView(R.layout.activity_ti_ku_lian_xi);
        ButterKnife.bind(this);
        initView();
        upDataView();
        getExtras();
    }

    private void initView() {
        tklxBtnDown.setEnabled(false);
        titleOneTextTvTitleName.setText("题库练习");
        setRandomColor();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        tklxRecyclerView.setLayoutManager(linearLayoutManager);
        tklxBtnDown.setEnabled(false);
    }

    private void getExtras() {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            String excuteId = extras.getString("excuteId");//考试id
            getData(excuteId);
        }
    }

    private void upDataView() {
        adapter = new TiKuLianXiAdapter(mContext, listData, this);
        initListener();
        tklxRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void initListener() {
        try {
            adapter.setOnItemClickListener(new TiKuLianXiAdapter.OnItemClickListener() {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.titleOneText_llBack, R.id.tklx_btnUp, tklx_btnDown, R.id.tklx_tvLookAnswer, R.id.tklx_btnTest})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titleOneText_llBack:
                showFinishView();
                break;
            case R.id.tklx_btnUp:      //上一题
                Log.i(TAG, "onClick/mPaperItemId: " + mPaperItemId);
                submitAnswer();
                tklxBtnDown.setEnabled(false);
                tklxBtnUp.setEnabled(false);
                tklxBtnDown.setText("下一题");
                if (sequences >= 1) {
                    sequences -= 1;
                    if (sequences == 0) {
                        sequences = 1;
                    }
//                    getPaperQuestion(paperId, "up");
                    getPaperQuestionSync(paperId, "up");
                }
                setRandomColor();
                break;
            case tklx_btnDown:    //下一题
                submitAnswer();
                resultAnswers = "";
                sequences += 1;
                Log.i(TAG, "onClick(当前题数) : " + sequences + " 总题数--" + mTotalQuestions);
                tklxBtnDown.setEnabled(false);
                tklxBtnDown.setText("下一题");
                setRandomColor();
//                getPaperQuestion(paperId, "down");
                getPaperQuestionSync(paperId, "down");
                break;
            case R.id.tklx_tvLookAnswer:        //查看答案
                if (mRightAnswer.equals("Y")) {
                    mRightAnswer = "正确";
                }
                if (mRightAnswer.equals("N")) {
                    mRightAnswer = "错误";
                }
                showPWD(mRightAnswer);
                if (mRightAnswer.equals("正确")) {
                    mRightAnswer = "Y";
                }
                if (mRightAnswer.equals("错误")) {
                    mRightAnswer = "N";
                }
                break;
            case R.id.tklx_btnTest:     //确定按钮验证答案是否正确
                submitAnswer();
                tklxBtnDown.setBackgroundResource(R.drawable.shi_btn_red);
                tklxBtnDown.setEnabled(true);
                Log.i(TAG, "验证答案: " + mRightAnswer + " ,mAnswers---" + mAnswers);
                Log.i(TAG, "onClick2: " + mAnswers.equals(mRightAnswer));
                if (mAnswers == null || mAnswers.equals("") || !mAnswers.equals(mRightAnswer)) {
                    showWarning("错误", "N");
                } else if (mAnswers.equals(mRightAnswer)) {
                    showWarning("正确", "Y");
                }
                break;
        }
    }

    /**
     * （12001）开始考试（只用于知识竞赛和题库练习）
     */
    private void getData(String excuteId) {
        Config.maxIndex = 0;
        Control.startExamination(mContext, excuteId, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    Log.i(TAG, "开始考试: " + result);
                    JSONObject jo = new JSONObject(result);
                    String status = jo.getString("status");
                    String msg = jo.getString("msg");
                    if (!status.equals("") && status.equals("1")) {
                        String totalQuestions = jo.getString("totalQuestions");     //试卷总题数
                        mTotalQuestions = Integer.parseInt(totalQuestions);
                        paperId = jo.getString("paperId");       //考试试卷id
                        String exaType = jo.getString("exaType");       //考试类型
                        sequences = 1;
//                        getPaperQuestion(paperId, "");
                        getPaperQuestionSync(paperId, "");
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
     * 12002）获取考题（同步）
     *
     * @param mPaperId 考试试卷id
     * @param btnType  区别上一题还是下一题
     */
    private void getPaperQuestionSync(final String mPaperId, final String btnType) {
        if (listData != null && listData.size() > 0) {
            listData.clear();
        }
        adapter.notifyDataSetChanged();
        tklxTvSequence.setText(String.valueOf(sequences));

        new Thread() {
            @Override
            public void run() {
                try {
                    this.sleep(100);
                    if (NetWorkHelper.checkNetworkState(mContext) == true) {
                        RequestParams params = new RequestParams(APIManager.getPaperQuestion);
                        params.addParameter("paperId", mPaperId);
                        params.addParameter("sequence", String.valueOf(sequences));
                        String postSync = x.http().postSync(params, String.class);
                        Log.i(TAG, "run/postSync: " + postSync);
                        JSONObject jo = new JSONObject(postSync);
                        String status = jo.getString("status");
                        String mMsg = jo.getString("msg");
                        Log.i(TAG, "run/status: " + status);
                        if (status != null && !status.equals("") && status.equals("1")) {
                            if (sequences >= Config.maxIndex) {
                                Config.maxIndex = sequences;
                            }
                            String mType = jo.getString("type");     //题目类型 SINGLE单选题，MULTI 多选题，JUDGE 判断题
                            mPaperItemId = jo.getString("paperItemId");
                            String sequencesFlag = jo.getString("sequence");
                            mRightAnswer = jo.getString("rightAnswer");   //正确答案
                            mAnswers = jo.getString("userAnswer");
                            resultAnswers = mAnswers;
                            String userAnswer = jo.getString("userAnswer");     //用户提交答案
                            String title = jo.getString("title");
                            Config.userAnswer = userAnswer;
                            Config.type = mType;

                            final Message msg = new Message();
                            msg.what = COMPLETED;
                            Bundle bundle = new Bundle();
                            bundle.putString("sequencesFlag", sequencesFlag);
                            bundle.putString("mType", mType);
                            bundle.putString("title", title);
                            bundle.putString("btnType", btnType);
                            bundle.putString("userAnswer", userAnswer);
                            msg.setData(bundle);
//                            handler.sendMessage(msg);

                            Gson gson = new Gson();
                            ExamBean examBean = gson.fromJson(postSync, ExamBean.class);
                            List<ExamBean.SelectItemsBean> selectItems = examBean.getSelectItems();
                            if (selectItems != null && selectItems.size() > 0) {
                                listData.addAll(selectItems);
                            }
                            mContext.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    adapter.cleanAnsItem();
                                    adapter.notifyDataSetChanged();
                                    handler.sendMessage(msg);
                                    tklxBtnDown.setEnabled(true);
                                    tklxBtnUp.setEnabled(true);
                                }
                            });
                        } else if (status.equals("9")) {
                            tklxBtnDown.setEnabled(true);
                            tklxBtnUp.setEnabled(true);
                            ToastUtil.showShort(mContext, mMsg);
                            Intent i = new Intent(mContext, LoginActivity.class);
                            startActivity(i);
                            mContext.finish();
                        } else {
                            tklxBtnDown.setEnabled(true);
                            tklxBtnUp.setEnabled(true);
                            ToastUtil.showShort(mContext, mMsg);
                        }
                    } else {
                        ToastUtil.showShort(mContext, "请检查网络");
                        tklxBtnDown.setEnabled(true);
                        tklxBtnUp.setEnabled(true);
                    }
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                    try {
                        tklxBtnDown.setEnabled(true);
                        tklxBtnUp.setEnabled(true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                super.run();
            }
        }.start();
    }

    /**
     * 12002）获取考题
     *
     * @param mPaperId 考试试卷id
     * @param btnType  区别上一题还是下一题
     */
    private void getPaperQuestion(String mPaperId, final String btnType) {
        if (listData != null && listData.size() > 0) {
            listData.clear();
        }
        adapter.notifyDataSetChanged();
        Log.i(TAG, "获取考题/mPaperId: " + mPaperId + " ,sequences--" + String.valueOf(sequences));
        Control.getPaperQuestion(mContext, mPaperId, String.valueOf(sequences), new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jo = new JSONObject(result);
                    if (sequences >= Config.maxIndex) {
                        Config.maxIndex = sequences;
                    }
                    Log.i(TAG, "获取考题: " + result);
                    tklxTvSequence.setText(String.valueOf(sequences));
                    String mType = jo.getString("type");     //题目类型 SINGLE单选题，MULTI 多选题，JUDGE 判断题
                    mPaperItemId = jo.getString("paperItemId");
                    String sequences = jo.getString("sequence");
                    mRightAnswer = jo.getString("rightAnswer");   //正确答案
                    mAnswers = jo.getString("userAnswer");
                    resultAnswers = mAnswers;
                    Config.type = mType;
                    if (mType.equals("MULTI")) {
                        tklxTvTitleName.setText("（多选题）" + jo.getString("title"));
                    } else {
                        tklxTvTitleName.setText(jo.getString("title"));
                    }
                    Log.i(TAG, "题目类型: " + mType);
                    String userAnswer = jo.getString("userAnswer");     //用户提交答案
                    Config.userAnswer = userAnswer;
                    if (sequences.equals("1")) {
                        tklxBtnUp.setVisibility(View.GONE);
                    } else {
                        tklxBtnUp.setVisibility(View.VISIBLE);
                    }
                    if (btnType.equals("up")) {     //上一题就可以点击按钮
                        tklxBtnDown.setEnabled(true);
                        tklxBtnDown.setBackgroundResource(R.drawable.shi_btn_red);
                    } else if (btnType.equals("down")) {
                        if (userAnswer != null && !userAnswer.equals("")) {
                            tklxBtnDown.setEnabled(true);
                            tklxBtnDown.setBackgroundResource(R.drawable.shi_btn_red);
                        } else {
                            tklxBtnDown.setEnabled(false);
                            tklxBtnDown.setBackgroundResource(R.drawable.shi_btn_ecf);
                        }
                    }
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
     * 提交考题答案同步
     */
    public void submitQueAnswerSync(final String answerss, final String itemId) {
        mAnswers = answerss;
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
        mAnswers = answer;
        Log.i(TAG, "getSubmitQueAnswer/answer: " + answer + " ,mPaperItemId----" + mPaperItemId);
        Control.submitQueAnswer(mContext, mPaperItemId, answer, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    Log.i(TAG, "提交题目答案: " + result);
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
     * （12003）提交试卷
     *
     * @param sequence 最大序号，题库练习有用，其它考试无效
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
                        i.putExtra("activity", "TiKuLianXiExamActivity");
                        i.putExtra("rightQuestions", rightQuestions);
                        i.putExtra("points", points);
                        i.putExtra("score", score);
                        i.putExtra("paperId", paperId);
                        startActivity(i);
                        mContext.finish();
                    } else if (status.equals("0")) {
                        ToastUtil.showShort(mContext, msg);
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
                ToastUtil.showShort(mContext, "网络异常");
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
        if (sequences % 2 == 1) {
            tklxLlContent.setBackgroundResource(R.color.color_19f);
        } else {
            tklxLlContent.setBackgroundResource(R.color.color_246);
        }
    }

    private void showCancelPW() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
        dialog.setMessage("确定提交？");
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
        AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
        dialog.setMessage("是否结束练习 ？");
        dialog.setNegativeButton("取消", null);
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                try {
                    submitAnswer();
                    SystemClock.sleep(100);
                    submitPaper(String.valueOf(Config.maxIndex));
                    Log.i(TAG, "结束练习: " + Config.maxIndex);
                    mContext.finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).show();
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
     * 显示查看答案框
     *
     * @param answer
     */
    private void showPWD(String answer) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.pop_pwd, null);
        final PopupWindow popupWindow = new PopupWindow(view, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        popupWindow.setFocusable(true);
        popupWindow.showAsDropDown(view);
        TextView tvAnswer = (TextView) view.findViewById(R.id.popPWD_tvAnswer);
        Button btnClose = (Button) view.findViewById(R.id.popPWD_btnClose);
        tvAnswer.setText(answer);
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
     * 答案是否正确提示框
     */
    private void showWarning(String answer, String type) {
        try {
            View view = LayoutInflater.from(mContext).inflate(R.layout.pop_warning, null);
            final PopupWindow warningPW = new PopupWindow(view, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
            warningPW.setFocusable(true);
            warningPW.showAsDropDown(view);
            ImageView ivStates = (ImageView) view.findViewById(R.id.popWarning_ivStates);
            TextView tvStates = (TextView) view.findViewById(R.id.popWarning_tvStates);
            if (type.equals("Y")) {
                Glide.with(mContext).load(R.drawable.mykeep_check).error(R.drawable.all_exit).into(ivStates);
            } else {
                Glide.with(mContext).load(R.drawable.all_exit).error(R.drawable.all_exit).into(ivStates);
            }
            tvStates.setText(answer);
            CountDownTimer tm = new CountDownTimer(500, 1000) {
                @Override
                public void onTick(long l) {
                }

                @Override
                public void onFinish() {
                    if (warningPW != null && warningPW.isShowing()) {
                        warningPW.dismiss();
                    }
                }
            }.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
