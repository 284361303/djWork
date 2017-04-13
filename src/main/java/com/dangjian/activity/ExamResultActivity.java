package com.dangjian.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dangjian.R;
import com.dangjian.utils.Config;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * sg  2016年10月8日10:31:07
 * 考试结果页面
 */
public class ExamResultActivity extends BaseActivity {

    @Bind(R.id.titleOneText_llBack)
    LinearLayout titleOneTextLlBack;
    @Bind(R.id.titleOneText_tvTitleName)
    TextView titleOneTextTvTitleName;
    @Bind(R.id.examResult_tvSum)
    TextView examResultTvSum;
    @Bind(R.id.examResult_tvTotal)
    TextView examResultTvTotal;
    @Bind(R.id.examResult_btnBack)
    Button examResultBtnBack;
    @Bind(R.id.examResult_btnRanking)
    Button examResultBtnRanking;
    @Bind(R.id.examResult_tvThanks)
    TextView examResultTvThanks;
    @Bind(R.id.examResult_llBottom)
    LinearLayout examResultLlBottom;
    @Bind(R.id.examResult_tvJiFen)
    TextView examResultTvJiFen;
    @Bind(R.id.examResult_tvAll)
    TextView examResultTvAll;
    @Bind(R.id.examResult_llAll)
    LinearLayout examResultLlAll;
    private String paperId;
    private static final String TAG = "ExamResultActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_result);
        ButterKnife.bind(this);
        initView();
        getExtras();
    }

    private void initView() {
        TextPaint tp = examResultTvThanks.getPaint();
        tp.setFakeBoldText(true);
    }

    private void getExtras() {
        try {
            Intent intent = getIntent();
            Bundle extras = intent.getExtras();
            if (extras != null) {
                String activity = extras.getString("activity");     //根据activity改变标题名字
                String rightQuestions = extras.getString("rightQuestions");      //答对题数
                String points = extras.getString("points");     //获取积分
                String score = extras.getString("score");     //考试得分
                Log.i(TAG, "getExtras/points: " + points);
                Log.i(TAG, "getExtras/score: " + score);
                paperId = extras.getString("paperId");     //考试试卷id
                if (activity.equals("WeekExamActivity")) {
                    titleOneTextTvTitleName.setText("每周一考");
                    examResultTvTotal.setText(points);
                    examResultBtnRanking.setVisibility(View.GONE);
                } else if (activity.equals("ExamActivity")) {
                    titleOneTextTvTitleName.setText("知识竞赛");
                    examResultTvJiFen.setText("  分");
                    examResultTvTotal.setText(score);
                } else if (activity.equals("TiKuLianXiExamActivity")) {
                    titleOneTextTvTitleName.setText("题库练习");
                    examResultLlAll.setVisibility(View.VISIBLE);
                    examResultTvAll.setText(String.valueOf(Config.maxIndex));
                    int iPoints = Integer.parseInt(points);
                    if (iPoints > 0) {
                        examResultLlBottom.setVisibility(View.VISIBLE);
                        examResultTvTotal.setText(points);
                    } else {
                        examResultLlBottom.setVisibility(View.GONE);
                    }
                    examResultBtnRanking.setVisibility(View.GONE);
                    examResultTvTotal.setText(points);
                } else if (activity.equals("ZhiShiJingSaiActivity")) {
                    titleOneTextTvTitleName.setText("知识竞赛");
                    examResultTvJiFen.setText("  分");
                    examResultTvTotal.setText(score);
                }
                examResultTvSum.setText(rightQuestions);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.titleOneText_llBack, R.id.examResult_btnBack, R.id.examResult_btnRanking})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titleOneText_llBack:
                mContext.finish();
                break;
            case R.id.examResult_btnBack:       //返回
                mContext.finish();
                break;
            case R.id.examResult_btnRanking:        //查看排名
                Intent iRanking = new Intent(mContext, RankingsActivity.class);
                iRanking.putExtra("paperId", paperId);
                startActivity(iRanking);
                break;
        }
    }
}
