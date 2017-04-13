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
import com.dangjian.entity.TiKuLianXiBean;
import com.dangjian.utils.Config;
import com.dangjian.utils.Control;
import com.dangjian.utils.ToastUtil;

import org.json.JSONObject;
import org.xutils.common.Callback;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * sg  2016年9月30日14:01:29
 * 知识竞赛开始答题页面
 */
public class JingSaiStartActivity extends BaseActivity {

    @Bind(R.id.titleRightText_llBack)
    LinearLayout titleRightTextLlBack;
    @Bind(R.id.titleRightText_tvTitleName)
    TextView titleRightTextTvTitleName;
    @Bind(R.id.titleRightText_tvRightName)
    TextView titleRightTextTvRightName;
    @Bind(R.id.jingSaiStart_tvStartTime)
    TextView jingSaiStartTvStartTime;
    @Bind(R.id.jingSaiStart_tvEndTime)
    TextView jingSaiStartTvEndTime;
    @Bind(R.id.jingSaiStart_btnStart)
    Button jingSaiStartBtnStart;
    @Bind(R.id.jingSaiStart_tvTitle)
    TextView jingSaiStartTvTitle;
    private String excuteId = "";
    private static final String TAG = "StartActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jing_sai_start);
        ButterKnife.bind(this);
        initView();
        getExtras();
    }

    private void getExtras() {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            TiKuLianXiBean.ListBean list = (TiKuLianXiBean.ListBean) extras.getSerializable("TiKuLianXiBean");
            if (list != null) {
                String startDate = list.getStartDate();
                String endDate = list.getEndDate();
                jingSaiStartTvStartTime.setText(startDate);
                jingSaiStartTvEndTime.setText(endDate);
            }
            jingSaiStartTvTitle.setText(list.getTitle());
            excuteId = list.getExcuteId();
        }
    }

    private void initView() {
        titleRightTextTvTitleName.setText("知识竞赛");
        titleRightTextTvRightName.setText("排名");
        titleRightTextTvRightName.setVisibility(View.VISIBLE);
        TextPaint tp = jingSaiStartTvTitle.getPaint();
        tp.setFakeBoldText(true);
    }

    @OnClick({R.id.titleRightText_llBack, R.id.titleRightText_tvRightName, R.id.jingSaiStart_btnStart})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titleRightText_llBack:
                mContext.finish();
                break;
            case R.id.titleRightText_tvRightName:   //排名       //查看排名
                Intent iRanking = new Intent(mContext, RankingsActivity.class);
                iRanking.putExtra("excuteId", excuteId);
                startActivity(iRanking);
                break;
            case R.id.jingSaiStart_btnStart:        //开始答题
                getData();
                break;
        }
    }

    /**
     * （12001）开始考试（只用于知识竞赛和题库练习）
     */
    private void getData() {
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
                        String totalTimes = jo.getString("totalTimes");     //考试时间，单位为分，0为没有时间限制
                        String totalQuestions = jo.getString("totalQuestions");     //试卷总题数
                        String paperId = jo.getString("paperId");       //考试试卷id
                        String exaType = jo.getString("exaType");       //考试类型
                        Intent i = new Intent(mContext, ExamActivity.class);
                        i.putExtra("totalQuestions", totalQuestions);
                        i.putExtra("totalTimes", totalTimes);
                        i.putExtra("paperId", paperId);
                        i.putExtra("exaType", exaType);
                        startActivity(i);
                        mContext.finish();
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
}
