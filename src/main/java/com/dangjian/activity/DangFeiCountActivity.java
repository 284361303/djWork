package com.dangjian.activity;

import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.dangjian.R;
import com.dangjian.utils.NumUtil;
import com.dangjian.utils.ToastUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 党费计算页面
 */
public class DangFeiCountActivity extends BaseActivity {

    @Bind(R.id.dfCount_llBack)
    LinearLayout dfCountLlBack;
    @Bind(R.id.dfCount_tvTitleName)
    TextView dfCountTvTitleName;
    @Bind(R.id.dfCount_llTitleSelect)
    LinearLayout dfCountLlTitleSelect;
    @Bind(R.id.dfCount_rlTitle)
    RelativeLayout dfCountRlTitle;
    @Bind(R.id.dfCount_tv1)
    TextView dfCountTv1;
    @Bind(R.id.dfCount_et1)
    EditText dfCountEt1;
    @Bind(R.id.dfCount_ll1)
    LinearLayout dfCountLl1;
    @Bind(R.id.dfCount_tv2)
    TextView dfCountTv2;
    @Bind(R.id.dfCount_et2)
    EditText dfCountEt2;
    @Bind(R.id.dfCount_ll2)
    LinearLayout dfCountLl2;
    @Bind(R.id.dfCount_tv3)
    TextView dfCountTv3;
    @Bind(R.id.dfCount_et3)
    EditText dfCountEt3;
    @Bind(R.id.dfCount_ll3)
    LinearLayout dfCountLl3;
    @Bind(R.id.dfCount_tv4)
    TextView dfCountTv4;
    @Bind(R.id.dfCount_et4)
    EditText dfCountEt4;
    @Bind(R.id.dfCount_ll4)
    LinearLayout dfCountLl4;
    @Bind(R.id.dfCount_tv5)
    TextView dfCountTv5;
    @Bind(R.id.dfCount_et5)
    EditText dfCountEt5;
    @Bind(R.id.dfCount_ll5)
    LinearLayout dfCountLl5;
    @Bind(R.id.dfCount_tv6)
    TextView dfCountTv6;
    @Bind(R.id.dfCount_et6)
    EditText dfCountEt6;
    @Bind(R.id.dfCount_ll6)
    LinearLayout dfCountLl6;
    @Bind(R.id.dfCount_tv7)
    TextView dfCountTv7;
    @Bind(R.id.dfCount_et7)
    EditText dfCountEt7;
    @Bind(R.id.dfCount_ll7)
    LinearLayout dfCountLl7;
    @Bind(R.id.dfCount_tv8)
    TextView dfCountTv8;
    @Bind(R.id.dfCount_et8)
    EditText dfCountEt8;
    @Bind(R.id.dfCount_ll8)
    LinearLayout dfCountLl8;
    @Bind(R.id.dfCount_tv9)
    TextView dfCountTv9;
    @Bind(R.id.dfCount_et9)
    EditText dfCountEt9;
    @Bind(R.id.dfCount_ll9)
    LinearLayout dfCountLl9;
    @Bind(R.id.dfCount_scrollView)
    ScrollView dfCountScrollView;
    @Bind(R.id.dfCount_tvJiShu)
    TextView dfCountTvJiShu;
    @Bind(R.id.dfCount_tvYingJiao)
    TextView dfCountTvYingJiao;
    @Bind(R.id.dfCount_llJiShu)
    LinearLayout dfCountLlJiShu;
    @Bind(R.id.dfCount_tvYJiao_YingJiao)
    TextView dfCountTvYJiaoYingJiao;
    @Bind(R.id.dfCount_llYJiao)
    LinearLayout dfCountLlYJiao;
    @Bind(R.id.dfCount_btn)
    Button dfCountBtn;
    @Bind(R.id.dfCount_llBottom)
    LinearLayout dfCountLlBottom;
    private PopupWindow popupWindow;
    private String mType = "JiGuanRenYuan";
    private EditText[] editTexts;
    private LinearLayout[] linearLayouts;
    private TextView[] textViews;
    private String countFlag = "";      //区别是否进行过计算然后保存手机数据库中
    private TextView tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8, tv9;
    private static final String TAG = "DangFeiCountActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_fei_count);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        try {
            //  JiGuanRenYuan,ShiYe,JiGuanGongRen,QiYe,GeTi,TuiXiu,NongMin,XueSheng,KuNan
            showTitleSelect();
            editTexts = new EditText[9];
            linearLayouts = new LinearLayout[9];
            textViews = new TextView[9];
            editTexts[0] = (EditText) findViewById(R.id.dfCount_et1);
            editTexts[1] = (EditText) findViewById(R.id.dfCount_et2);
            editTexts[2] = (EditText) findViewById(R.id.dfCount_et3);
            editTexts[3] = (EditText) findViewById(R.id.dfCount_et4);
            editTexts[4] = (EditText) findViewById(R.id.dfCount_et5);
            editTexts[5] = (EditText) findViewById(R.id.dfCount_et6);
            editTexts[6] = (EditText) findViewById(R.id.dfCount_et7);
            editTexts[7] = (EditText) findViewById(R.id.dfCount_et8);
            editTexts[8] = (EditText) findViewById(R.id.dfCount_et9);

            linearLayouts[0] = (LinearLayout) findViewById(R.id.dfCount_ll1);
            linearLayouts[1] = (LinearLayout) findViewById(R.id.dfCount_ll2);
            linearLayouts[2] = (LinearLayout) findViewById(R.id.dfCount_ll3);
            linearLayouts[3] = (LinearLayout) findViewById(R.id.dfCount_ll4);
            linearLayouts[4] = (LinearLayout) findViewById(R.id.dfCount_ll5);
            linearLayouts[5] = (LinearLayout) findViewById(R.id.dfCount_ll6);
            linearLayouts[6] = (LinearLayout) findViewById(R.id.dfCount_ll7);
            linearLayouts[7] = (LinearLayout) findViewById(R.id.dfCount_ll8);
            linearLayouts[8] = (LinearLayout) findViewById(R.id.dfCount_ll9);

            textViews[0] = (TextView) findViewById(R.id.dfCount_tv1);
            textViews[1] = (TextView) findViewById(R.id.dfCount_tv2);
            textViews[2] = (TextView) findViewById(R.id.dfCount_tv3);
            textViews[3] = (TextView) findViewById(R.id.dfCount_tv4);
            textViews[4] = (TextView) findViewById(R.id.dfCount_tv5);
            textViews[5] = (TextView) findViewById(R.id.dfCount_tv6);
            textViews[6] = (TextView) findViewById(R.id.dfCount_tv7);
            textViews[7] = (TextView) findViewById(R.id.dfCount_tv8);
            textViews[8] = (TextView) findViewById(R.id.dfCount_tv9);

            String mType = sharedPreferences.getString("mType", null);      //计算的类型
            if (mType != null && !mType.equals("")) {
                if (mType.equals("JiGuanRenYuan")) {
                    tv1.performClick();
                } else if (mType.equals("ShiYe")) {
                    tv2.performClick();
                } else if (mType.equals("JiGuanGongRen")) {
                    tv3.performClick();
                } else if (mType.equals("QiYe")) {
                    tv4.performClick();
                } else if (mType.equals("GeTi")) {
                    tv5.performClick();
                } else if (mType.equals("TuiXiu")) {
                    tv6.performClick();
                } else if (mType.equals("NongMin")) {
                    tv7.performClick();
                } else if (mType.equals("XueSheng")) {
                    tv8.performClick();
                } else if (mType.equals("KuNan")) {
                    tv9.performClick();
                }
                if (mType.equals("NongMin") || mType.equals("XueSheng") || mType.equals("KuNan")) {
                    dfCountLlJiShu.setVisibility(View.GONE);
                    dfCountLlYJiao.setVisibility(View.VISIBLE);
                    String yyj = sharedPreferences.getString("yyj", null);      //应交总额
                    dfCountTvYJiaoYingJiao.setText(NumUtil.parseRate(yyj));
                } else {
                    dfCountLlJiShu.setVisibility(View.VISIBLE);
                    dfCountLlYJiao.setVisibility(View.GONE);
                    String jiShu = sharedPreferences.getString("jiShu", null);      //基数额度
                    String yingJiao = sharedPreferences.getString("yingJiao", null);      //应交总额
                    dfCountTvJiShu.setText(NumUtil.parseRate(jiShu));
                    dfCountTvYingJiao.setText(NumUtil.parseRate(yingJiao));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.dfCount_llBack, R.id.dfCount_llTitleSelect, R.id.dfCount_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dfCount_llBack:
                saveData();
                break;
            case R.id.dfCount_llTitleSelect:
                if (popupWindow.isShowing()) {
                    popupWindow.dismiss();
                } else {
                    popupWindow.showAsDropDown(dfCountRlTitle);
                }
                break;
            case R.id.dfCount_btn:  //计算按钮
                countFlag = "1";
                if (mType.equals("TuiXiu")) {
                    double yingJiao = 0;
                    String tuiXiuE = dfCountEt1.getText().toString().trim();    //退休金
                    if (TextUtils.isEmpty(tuiXiuE)) {
                        ToastUtil.showShort(mContext, "退休金总额不能为空");
                        return;
                    }
                    if (tuiXiuE != null && !tuiXiuE.equals("")) {
                        double iTuiXinE = Double.parseDouble(NumUtil.parseRate(tuiXiuE));
                        if (iTuiXinE > 0 && iTuiXinE <= 5000) {
                            yingJiao = (double) (iTuiXinE * 0.005);
                            Log.i(TAG, "onClick/sumTXE: " + yingJiao);
                        } else if (iTuiXinE > 0 && iTuiXinE > 5000) {
                            yingJiao = (double) (iTuiXinE * 0.01);
                            Log.i(TAG, "onClick/sumTXE: " + yingJiao);
                        }
                    }
                    dfCountTvJiShu.setText(NumUtil.parseRate(tuiXiuE));
                    dfCountTvYingJiao.setText(NumUtil.parseRate(String.valueOf(yingJiao)));
                } else if (mType.equals("GeTi")) {    //个体经营户
                    double jiShu = 0;
                    String sEt1 = dfCountEt1.getText().toString().trim();//纯收入
                    String sEt2 = dfCountEt2.getText().toString().trim();//个人所得税
                    String sEt3 = dfCountEt3.getText().toString().trim();//五险一金
                    String sEt4 = dfCountEt4.getText().toString().trim();//企业年金
                    if (TextUtils.isEmpty(sEt1)) {
                        ToastUtil.showShort(mContext, "平均纯收入不能为空");
                        return;
                    }
                    if (TextUtils.isEmpty(sEt2)) {
                        ToastUtil.showShort(mContext, "个人所得税不能为空");
                        return;
                    }
                    if (TextUtils.isEmpty(sEt3)) {
                        ToastUtil.showShort(mContext, "五险一金不能为空");
                        return;
                    }
                    if (TextUtils.isEmpty(sEt4)) {
                        ToastUtil.showShort(mContext, "企业年金不能为空");
                        return;
                    }
                    double iEt1 = 0, iEt2 = 0, iEt3 = 0, iEt4 = 0;
                    if (!sEt1.equals("")) {
                        iEt1 = Double.parseDouble(NumUtil.parseRate(sEt1));
                    } else {
                        iEt1 = 0;
                    }
                    if (!sEt2.equals("")) {
                        iEt2 = Double.parseDouble(NumUtil.parseRate(sEt2));
                    } else {
                        iEt2 = 0;
                    }
                    if (!sEt3.equals("")) {
                        iEt3 = Double.parseDouble(NumUtil.parseRate(sEt3));
                    } else {
                        iEt3 = 0;
                    }
                    if (!sEt4.equals("")) {
                        iEt4 = Double.parseDouble(NumUtil.parseRate(sEt4));
                    } else {
                        iEt4 = 0;
                    }
                    //（上季度平均纯收入基数）-（个人所得税+五险一金+企业年金）
                    jiShu = iEt1 - (iEt2 + iEt3 + iEt4);
                    double iYingJiao = getiYingJiao(jiShu);
                    dfCountTvJiShu.setText(NumUtil.parseRate(String.valueOf(jiShu)));
                    dfCountTvYingJiao.setText(NumUtil.parseRate(String.valueOf(iYingJiao)));
                } else if (mType.equals("QiYe")) {      //企业人员
                    double jiShu = 0;
                    String sEt1 = dfCountEt1.getText().toString().trim();//基本工资
                    String sEt2 = dfCountEt2.getText().toString().trim();//岗位工资
                    String sEt3 = dfCountEt3.getText().toString().trim();//奖金
                    String sEt4 = dfCountEt4.getText().toString().trim();//个人所得税
                    String sEt5 = dfCountEt5.getText().toString().trim();//五险一金
                    String sEt6 = dfCountEt6.getText().toString().trim();//企业年金
                    double iEt1 = 0, iEt2 = 0, iEt3 = 0, iEt4 = 0, iEt5 = 0, iEt6 = 0;
                    if (!sEt1.equals("")) {
                        iEt1 = Double.parseDouble(NumUtil.parseRate(sEt1));
                    } else {
                        iEt1 = 0;
                    }
                    if (!sEt2.equals("")) {
                        iEt2 = Double.parseDouble(NumUtil.parseRate(sEt2));
                    } else {
                        iEt2 = 0;
                    }
                    if (!sEt3.equals("")) {
                        iEt3 = Double.parseDouble(NumUtil.parseRate(sEt3));
                    } else {
                        iEt3 = 0;
                    }
                    if (!sEt4.equals("")) {
                        iEt4 = Double.parseDouble(NumUtil.parseRate(sEt4));
                    } else {
                        iEt4 = 0;
                    }
                    if (!sEt5.equals("")) {
                        iEt5 = Double.parseDouble(NumUtil.parseRate(sEt5));
                    } else {
                        iEt5 = 0;
                    }
                    if (!sEt6.equals("")) {
                        iEt6 = Double.parseDouble(NumUtil.parseRate(sEt6));
                    } else {
                        iEt6 = 0;
                    }
                    //（基本工资+岗位工资+奖金）-（个人所得税+五险一金+企业年金）
                    jiShu = (iEt1 + iEt2 + iEt3) - (iEt4 + iEt5 + iEt6);
                    double iQYYingJiao = getiYingJiao(jiShu);
                    dfCountTvJiShu.setText(NumUtil.parseRate(String.valueOf(jiShu)));
                    dfCountTvYingJiao.setText(NumUtil.parseRate(String.valueOf(iQYYingJiao)));
                } else if (mType.equals("JiGuanGongRen")) {     //机关工人
                    double jiShu = 0;
                    String sEt1 = dfCountEt1.getText().toString().trim();//岗位工资
                    String sEt2 = dfCountEt2.getText().toString().trim();//技术等级（职务）工资
                    String sEt3 = dfCountEt3.getText().toString().trim();//津贴补贴1
                    String sEt4 = dfCountEt4.getText().toString().trim();//津贴补贴2
                    String sEt5 = dfCountEt5.getText().toString().trim();//津贴补贴3
                    String sEt6 = dfCountEt6.getText().toString().trim();//个人所得税
                    String sEt7 = dfCountEt7.getText().toString().trim();//五险一金
                    String sEt8 = dfCountEt8.getText().toString().trim();//企业年金
                    /*if (TextUtils.isEmpty(sEt1)) {
                        ToastUtil.showShort(mContext, "岗位工资不能为空");
                        return;
                    }
                    if (TextUtils.isEmpty(sEt2)) {
                        ToastUtil.showShort(mContext, "技术等级（职务）工资不能为空");
                        return;
                    }
                    if (TextUtils.isEmpty(sEt4)) {
                        ToastUtil.showShort(mContext, "个人所得税不能为空");
                        return;
                    }
                    if (TextUtils.isEmpty(sEt5)) {
                        ToastUtil.showShort(mContext, "五险一金不能为空");
                        return;
                    }
                    if (TextUtils.isEmpty(sEt6)) {
                        ToastUtil.showShort(mContext, "企业年金不能为空");
                        return;
                    }*/
                    double iEt1 = 0, iEt2 = 0, iEt3 = 0, iEt4 = 0, iEt5 = 0, iEt6 = 0, iEt7 = 0, iEt8 = 0;
                    if (!sEt1.equals("")) {
                        iEt1 = Double.parseDouble(NumUtil.parseRate(sEt1));
                    } else {
                        iEt1 = 0;
                    }
                    if (!sEt2.equals("")) {
                        iEt2 = Double.parseDouble(NumUtil.parseRate(sEt2));
                    } else {
                        iEt2 = 0;
                    }
                    if (!sEt3.equals("")) {
                        iEt3 = Double.parseDouble(NumUtil.parseRate(sEt3));
                    } else {
                        iEt3 = 0;
                    }
                    if (!sEt4.equals("")) {
                        iEt4 = Double.parseDouble(NumUtil.parseRate(sEt4));
                    } else {
                        iEt4 = 0;
                    }
                    if (!sEt5.equals("")) {
                        iEt5 = Double.parseDouble(NumUtil.parseRate(sEt5));
                    } else {
                        iEt5 = 0;
                    }
                    if (!sEt6.equals("")) {
                        iEt6 = Double.parseDouble(NumUtil.parseRate(sEt6));
                    } else {
                        iEt6 = 0;
                    }
                    if (!sEt7.equals("")) {
                        iEt7 = Double.parseDouble(NumUtil.parseRate(sEt7));
                    } else {
                        iEt7 = 0;
                    }
                    if (!sEt8.equals("")) {
                        iEt8 = Double.parseDouble(NumUtil.parseRate(sEt8));
                    } else {
                        iEt8 = 0;
                    }
                    //（岗位工资+技术等级（职务）工资+津贴补贴1+津贴补贴2+津贴补贴3）-（个人所得税+五险一金+企业年金）
                    jiShu = (iEt1 + iEt2 + iEt3 + iEt4 + iEt5) - (iEt6 + iEt7 + iEt8);
                    double iQYYingJiao = getiYingJiao(jiShu);
                    dfCountTvJiShu.setText(NumUtil.parseRate(String.valueOf(jiShu)));
                    dfCountTvYingJiao.setText(NumUtil.parseRate(String.valueOf(iQYYingJiao)));
                } else if (mType.equals("ShiYe")) {     //事业单位
                    double jiShu = 0;
                    String sEt1 = dfCountEt1.getText().toString().trim();//岗位工资
                    String sEt2 = dfCountEt2.getText().toString().trim();//薪级工资
                    String sEt3 = dfCountEt3.getText().toString().trim();//绩效津贴
                    String sEt4 = dfCountEt4.getText().toString().trim();//津贴补贴1
                    String sEt5 = dfCountEt5.getText().toString().trim();//津贴补贴2
                    String sEt6 = dfCountEt6.getText().toString().trim();//津贴补贴3
                    String sEt7 = dfCountEt7.getText().toString().trim();//个人所得税
                    String sEt8 = dfCountEt8.getText().toString().trim();//五险一金
                    String sEt9 = dfCountEt9.getText().toString().trim();//企业年金
                    double iEt1 = 0, iEt2 = 0, iEt3 = 0, iEt4 = 0, iEt5 = 0, iEt6 = 0, iEt7 = 0, iEt8 = 0, iEt9 = 0;
                    if (!sEt1.equals("")) {
                        iEt1 = Double.parseDouble(NumUtil.parseRate(sEt1));
                    } else {
                        iEt1 = 0;
                    }
                    if (!sEt2.equals("")) {
                        iEt2 = Double.parseDouble(NumUtil.parseRate(sEt2));
                    } else {
                        iEt2 = 0;
                    }
                    if (!sEt3.equals("")) {
                        iEt3 = Double.parseDouble(NumUtil.parseRate(sEt3));
                    } else {
                        iEt3 = 0;
                    }
                    if (!sEt4.equals("")) {
                        iEt4 = Double.parseDouble(NumUtil.parseRate(sEt4));
                    } else {
                        iEt4 = 0;
                    }
                    if (!sEt5.equals("")) {
                        iEt5 = Double.parseDouble(NumUtil.parseRate(sEt5));
                    } else {
                        iEt5 = 0;
                    }
                    if (!sEt6.equals("")) {
                        iEt6 = Double.parseDouble(NumUtil.parseRate(sEt6));
                    } else {
                        iEt6 = 0;
                    }
                    if (!sEt7.equals("")) {
                        iEt7 = Double.parseDouble(NumUtil.parseRate(sEt7));
                    } else {
                        iEt7 = 0;
                    }
                    if (!sEt8.equals("")) {
                        iEt8 = Double.parseDouble(NumUtil.parseRate(sEt8));
                    } else {
                        iEt8 = 0;
                    }
                    if (!sEt9.equals("")) {
                        iEt9 = Double.parseDouble(NumUtil.parseRate(sEt9));
                    } else {
                        iEt9 = 0;
                    }
                    //（岗位工资+薪级工资+绩效津贴+津贴补贴1+津贴补贴2+津贴补贴3）-（个人所得税+五险一金+企业年金）
                    jiShu = (iEt1 + iEt2 + iEt3 + iEt4 + iEt5 + iEt6) - (iEt7 + iEt8 + iEt9);
                    double iQYYingJiao = getiYingJiao(jiShu);
                    dfCountTvJiShu.setText(NumUtil.parseRate(String.valueOf(jiShu)));
                    dfCountTvYingJiao.setText(NumUtil.parseRate(String.valueOf(iQYYingJiao)));
                } else if (mType.equals("JiGuanRenYuan")) {     //机关人员
                    double jiShu = 0;
                    String sEt1 = dfCountEt1.getText().toString().trim();   //岗位工资
                    String sEt2 = dfCountEt2.getText().toString().trim();   //薪级工资
                    String sEt3 = dfCountEt3.getText().toString().trim();   //绩效津贴
                    String sEt4 = dfCountEt4.getText().toString().trim();   //津贴补贴1
                    String sEt5 = dfCountEt5.getText().toString().trim();   //津贴补贴2
                    String sEt6 = dfCountEt6.getText().toString().trim();   //津贴补贴3
                    String sEt7 = dfCountEt7.getText().toString().trim();   //个人所得税
                    String sEt8 = dfCountEt8.getText().toString().trim();   //五险一金
                    String sEt9 = dfCountEt9.getText().toString().trim();   //企业年金
                    double iEt1 = 0, iEt2 = 0, iEt3 = 0, iEt4 = 0, iEt5 = 0, iEt6 = 0, iEt7 = 0, iEt8 = 0, iEt9 = 0;
                    if (!sEt1.equals("")) {
                        iEt1 = Double.parseDouble(NumUtil.parseRate(sEt1));
                    } else {
                        iEt1 = 0;
                    }
                    if (!sEt2.equals("")) {
                        iEt2 = Double.parseDouble(NumUtil.parseRate(sEt2));
                    } else {
                        iEt2 = 0;
                    }
                    if (!sEt3.equals("")) {
                        iEt3 = Double.parseDouble(NumUtil.parseRate(sEt3));
                    } else {
                        iEt3 = 0;
                    }
                    if (!sEt4.equals("")) {
                        iEt4 = Double.parseDouble(NumUtil.parseRate(sEt4));
                    } else {
                        iEt4 = 0;
                    }
                    if (!sEt5.equals("")) {
                        iEt5 = Double.parseDouble(NumUtil.parseRate(sEt5));
                    } else {
                        iEt5 = 0;
                    }
                    if (!sEt6.equals("")) {
                        iEt6 = Double.parseDouble(NumUtil.parseRate(sEt6));
                    } else {
                        iEt6 = 0;
                    }
                    if (!sEt7.equals("")) {
                        iEt7 = Double.parseDouble(NumUtil.parseRate(sEt7));
                    } else {
                        iEt7 = 0;
                    }
                    if (!sEt8.equals("")) {
                        iEt8 = Double.parseDouble(NumUtil.parseRate(sEt8));
                    } else {
                        iEt8 = 0;
                    }
                    if (!sEt9.equals("")) {
                        iEt9 = Double.parseDouble(NumUtil.parseRate(sEt9));
                    } else {
                        iEt9 = 0;
                    }
                    //（岗位工资+薪级工资+绩效津贴+津贴补贴1+津贴补贴2+津贴补贴3）-（个人所得税+五险一金+企业年金）
                    jiShu = (iEt1 + iEt2 + iEt3 + iEt4 + iEt5 + iEt6) - (iEt7 + iEt8 + iEt9);
                    double iQYYingJiao = getiYingJiao(jiShu);
                    dfCountTvJiShu.setText(NumUtil.parseRate(String.valueOf(jiShu)));
                    dfCountTvYingJiao.setText(NumUtil.parseRate(String.valueOf(iQYYingJiao)));
                }
                break;
        }
    }

    /**
     * 返回的时候保存计算的数据，下次进来直接显示
     */
    private void saveData() {
        //  JiGuanRenYuan,ShiYe,JiGuanGongRen,QiYe,GeTi,TuiXiu,NongMin,XueSheng,KuNan
        if (!countFlag.equals("") && countFlag.equals("1")) {
            String js = dfCountTvJiShu.getText().toString();
            String yj = dfCountTvYingJiao.getText().toString();
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putString("mType", mType);
            if (mType.equals("NongMin") || mType.equals("XueSheng") || mType.equals("KuNan")) {
                String yyj = dfCountTvYJiaoYingJiao.getText().toString();
                edit.putString("yyj", NumUtil.parseRate(yyj));
            } else {
                edit.putString("jiShu", NumUtil.parseRate(js));
                edit.putString("yingJiao", NumUtil.parseRate(yj));
            }
            edit.commit();
        }
        mContext.finish();
    }

    /**
     * 计算应交额度
     *
     * @param jiShu 基数总额
     * @return
     */
    private double getiYingJiao(double jiShu) {
        double iYingJiao = 0;
        if (jiShu > 0 && jiShu <= 3000) {
            iYingJiao = (double) (jiShu * 0.005);
        } else if (jiShu > 0 && jiShu > 3000 && jiShu <= 5000) {
            iYingJiao = (double) (jiShu * 0.01);
        } else if (jiShu > 0 && jiShu > 5000 && jiShu <= 10000) {
            iYingJiao = (double) (jiShu * 0.015);
        } else if (jiShu > 0 && jiShu > 10000) {
            iYingJiao = (double) (jiShu * 0.02);
        }
        return iYingJiao;
    }

    private void showTitleSelect() {
        try {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            View vv = inflater.inflate(R.layout.pop_title_select9, null);
            popupWindow = new PopupWindow(vv, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, false);
            popupWindow.setBackgroundDrawable(new BitmapDrawable());
            popupWindow.setOutsideTouchable(true);
            popupWindow.setFocusable(true);
            tv1 = (TextView) vv.findViewById(R.id.pwDF_tv1);        //机关工作人员
            tv2 = (TextView) vv.findViewById(R.id.pwDF_tv2);        //事业单位工作人员
            tv3 = (TextView) vv.findViewById(R.id.pwDF_tv3);        //机关工人
            tv4 = (TextView) vv.findViewById(R.id.pwDF_tv4);        //企业人员
            tv5 = (TextView) vv.findViewById(R.id.pwDF_tv5);        //个体经营者
            tv6 = (TextView) vv.findViewById(R.id.pwDF_tv6);        //离退休干部、职工
            tv7 = (TextView) vv.findViewById(R.id.pwDF_tv7);        //农民
            tv8 = (TextView) vv.findViewById(R.id.pwDF_tv8);        //学生
            tv9 = (TextView) vv.findViewById(R.id.pwDF_tv9);        //困难党员
            tv1.setOnClickListener(new View.OnClickListener() {        //机关工作人员
                @Override
                public void onClick(View view) {
                    mType = "JiGuanRenYuan";
                    cleraEditText();
                    dfCountTvTitleName.setText("机关工作人员");
                    dfCountTv1.setText("职务工资");
                    dfCountTv2.setText("级别工资");
                    dfCountTv3.setText("生活津贴");
                    dfCountTv4.setText("工作津贴1");
                    dfCountTv5.setText("工作津贴2");
                    dfCountTv6.setText("工作津贴3");
                    dfCountTv7.setText("个人所得税（元）");
                    dfCountTv8.setText("五险一金（元）");
                    dfCountTv9.setText("企业年金（元）");
                    dfCountTv7.setBackgroundResource(R.color.color_1ef);
                    dfCountTv8.setBackgroundResource(R.color.color_1ef);
                    dfCountTv9.setBackgroundResource(R.color.color_1ef);
                    linearLayouts[0].setVisibility(View.VISIBLE);
                    linearLayouts[1].setVisibility(View.VISIBLE);
                    linearLayouts[2].setVisibility(View.VISIBLE);
                    linearLayouts[3].setVisibility(View.VISIBLE);
                    linearLayouts[4].setVisibility(View.VISIBLE);
                    linearLayouts[5].setVisibility(View.VISIBLE);
                    linearLayouts[6].setVisibility(View.VISIBLE);
                    linearLayouts[7].setVisibility(View.VISIBLE);
                    linearLayouts[8].setVisibility(View.VISIBLE);
                    dfCountLlYJiao.setVisibility(View.GONE);
                    dfCountLlJiShu.setVisibility(View.VISIBLE);
                    dfCountTvYingJiao.setText("0");
                    dfCountTvJiShu.setText("0");
                    dfCountScrollView.setVisibility(View.VISIBLE);

                    if (popupWindow != null && popupWindow.isShowing()) {
                        popupWindow.dismiss();
                    }
                }
            });
            tv2.setOnClickListener(new View.OnClickListener() {        //事业单位工作人员
                @Override
                public void onClick(View view) {
                    mType = "ShiYe";
                    cleraEditText();
                    dfCountTvTitleName.setText("事业单位工作人员");
                    dfCountTv1.setText("岗位工资");
                    dfCountTv2.setText("薪级工资");
                    dfCountTv3.setText("绩效津贴");
                    dfCountTv4.setText("津贴补贴1");
                    dfCountTv5.setText("津贴补贴2");
                    dfCountTv6.setText("津贴补贴3");
                    dfCountTv7.setBackgroundResource(R.color.color_1ef);
                    dfCountTv8.setBackgroundResource(R.color.color_1ef);
                    dfCountTv9.setBackgroundResource(R.color.color_1ef);
                    linearLayouts[0].setVisibility(View.VISIBLE);
                    linearLayouts[1].setVisibility(View.VISIBLE);
                    linearLayouts[2].setVisibility(View.VISIBLE);
                    linearLayouts[3].setVisibility(View.VISIBLE);
                    linearLayouts[4].setVisibility(View.VISIBLE);
                    linearLayouts[5].setVisibility(View.VISIBLE);
                    linearLayouts[6].setVisibility(View.VISIBLE);
                    linearLayouts[7].setVisibility(View.VISIBLE);
                    linearLayouts[8].setVisibility(View.VISIBLE);
                    dfCountLlYJiao.setVisibility(View.GONE);
                    dfCountLlJiShu.setVisibility(View.VISIBLE);
                    dfCountTvYingJiao.setText("0");
                    dfCountTvJiShu.setText("0");
                    dfCountScrollView.setVisibility(View.VISIBLE);

                    if (popupWindow != null && popupWindow.isShowing()) {
                        popupWindow.dismiss();
                    }
                }
            });
            tv3.setOnClickListener(new View.OnClickListener() {        //机关工人
                @Override
                public void onClick(View view) {
                    mType = "JiGuanGongRen";
                    cleraEditText();
                    dfCountTvTitleName.setText("机关工人");
                    dfCountTv1.setText("岗位工资");
                    dfCountTv2.setText("技术等级（职务）工资");
                    dfCountTv3.setText("津贴补贴1");
                    dfCountTv4.setText("津贴补贴2");
                    dfCountTv5.setText("津贴补贴3");
                    dfCountTv6.setText("个人所得税（元）");
                    dfCountTv7.setText("五险一金（元）");
                    dfCountTv8.setText("企业年金（元）");
                    dfCountTv6.setBackgroundResource(R.color.color_1ef);
                    dfCountTv7.setBackgroundResource(R.color.color_1ef);
                    dfCountTv8.setBackgroundResource(R.color.color_1ef);
                    linearLayouts[0].setVisibility(View.VISIBLE);
                    linearLayouts[1].setVisibility(View.VISIBLE);
                    linearLayouts[2].setVisibility(View.VISIBLE);
                    linearLayouts[3].setVisibility(View.VISIBLE);
                    linearLayouts[4].setVisibility(View.VISIBLE);
                    linearLayouts[5].setVisibility(View.VISIBLE);
                    linearLayouts[6].setVisibility(View.VISIBLE);
                    linearLayouts[7].setVisibility(View.VISIBLE);
                    dfCountLlYJiao.setVisibility(View.GONE);
                    dfCountLlJiShu.setVisibility(View.VISIBLE);
                    dfCountTvYingJiao.setText("0");
                    dfCountTvJiShu.setText("0");
                    dfCountScrollView.setVisibility(View.VISIBLE);

                    if (popupWindow != null && popupWindow.isShowing()) {
                        popupWindow.dismiss();
                    }
                }
            });
            tv4.setOnClickListener(new View.OnClickListener() {       //企业人员
                @Override
                public void onClick(View view) {
                    mType = "QiYe";
                    cleraEditText();
                    dfCountTvTitleName.setText("企业人员");
                    dfCountTv1.setText("基本工资");
                    dfCountTv2.setText("岗位工资");
                    dfCountTv3.setText("奖金");
                    dfCountTv4.setText("个人所得税（元）");
                    dfCountTv5.setText("五险一金（元）");
                    dfCountTv6.setText("企业年金（元）");
                    dfCountTv4.setBackgroundResource(R.color.color_1ef);
                    dfCountTv5.setBackgroundResource(R.color.color_1ef);
                    dfCountTv6.setBackgroundResource(R.color.color_1ef);
                    linearLayouts[0].setVisibility(View.VISIBLE);
                    linearLayouts[1].setVisibility(View.VISIBLE);
                    linearLayouts[2].setVisibility(View.VISIBLE);
                    linearLayouts[3].setVisibility(View.VISIBLE);
                    linearLayouts[4].setVisibility(View.VISIBLE);
                    linearLayouts[5].setVisibility(View.VISIBLE);
                    dfCountLlYJiao.setVisibility(View.GONE);
                    dfCountLlJiShu.setVisibility(View.VISIBLE);
                    dfCountTvYingJiao.setText("0");
                    dfCountTvJiShu.setText("0");
                    dfCountScrollView.setVisibility(View.VISIBLE);

                    if (popupWindow != null && popupWindow.isShowing()) {
                        popupWindow.dismiss();
                    }
                }
            });
            tv5.setOnClickListener(new View.OnClickListener() {        //个体经营者
                @Override
                public void onClick(View view) {
                    mType = "GeTi";
                    cleraEditText();
                    dfCountTvTitleName.setText("个体经营者");
                    linearLayouts[0].setVisibility(View.VISIBLE);
                    linearLayouts[1].setVisibility(View.VISIBLE);
                    linearLayouts[2].setVisibility(View.VISIBLE);
                    linearLayouts[3].setVisibility(View.VISIBLE);
                    dfCountTv1.setText("上季度平均纯收入基数");
                    dfCountTv2.setText("个人所得税（元）");
                    dfCountTv3.setText("五险一金（元）");
                    dfCountTv4.setText("企业年金（元）");
                    dfCountTv2.setBackgroundResource(R.color.color_1ef);
                    dfCountTv3.setBackgroundResource(R.color.color_1ef);
                    dfCountTv4.setBackgroundResource(R.color.color_1ef);
                    dfCountLlYJiao.setVisibility(View.GONE);
                    dfCountLlJiShu.setVisibility(View.VISIBLE);
                    dfCountTvYingJiao.setText("0");
                    dfCountTvJiShu.setText("0");
                    dfCountScrollView.setVisibility(View.VISIBLE);

                    if (popupWindow != null && popupWindow.isShowing()) {
                        popupWindow.dismiss();
                    }
                }
            });
            tv6.setOnClickListener(new View.OnClickListener() {       //离退休干部、职工
                @Override
                public void onClick(View view) {
                    mType = "TuiXiu";
                    cleraEditText();
                    linearLayouts[0].setVisibility(View.VISIBLE);
                    dfCountTvTitleName.setText("离退休干部、职工");
                    dfCountTv1.setText("退休金总额");
                    dfCountLlYJiao.setVisibility(View.GONE);
                    dfCountLlJiShu.setVisibility(View.VISIBLE);
                    dfCountTvYingJiao.setText("0");
                    dfCountTvJiShu.setText("0");
                    dfCountScrollView.setVisibility(View.VISIBLE);

                    if (popupWindow != null && popupWindow.isShowing()) {
                        popupWindow.dismiss();
                    }
                }
            });
            tv7.setOnClickListener(new View.OnClickListener() {       //农民
                @Override
                public void onClick(View view) {
                    cleraEditText();
                    mType = "NongMin";
                    dfCountTvTitleName.setText("农民");
                    dfCountScrollView.setVisibility(View.GONE);
                    dfCountLlYJiao.setVisibility(View.VISIBLE);
                    dfCountLlJiShu.setVisibility(View.GONE);
                    dfCountTvYJiaoYingJiao.setText(getResources().getString(R.string.monery));
                    dfCountBtn.performClick();
                    if (popupWindow != null && popupWindow.isShowing()) {
                        popupWindow.dismiss();
                    }
                }
            });
            tv8.setOnClickListener(new View.OnClickListener() {     //学生
                @Override
                public void onClick(View view) {
                    mType = "XueSheng";
                    dfCountTvTitleName.setText("学生");
                    dfCountScrollView.setVisibility(View.GONE);
                    dfCountLlYJiao.setVisibility(View.VISIBLE);
                    dfCountLlJiShu.setVisibility(View.GONE);
                    dfCountTvYJiaoYingJiao.setText("0.20");
                    dfCountBtn.performClick();
                    if (popupWindow != null && popupWindow.isShowing()) {
                        popupWindow.dismiss();
                    }
                }
            });
            tv9.setOnClickListener(new View.OnClickListener() {      //困难党员
                @Override
                public void onClick(View view) {
                    mType = "KuNan";
                    dfCountTvTitleName.setText("困难党员");
                    dfCountScrollView.setVisibility(View.GONE);
                    dfCountLlYJiao.setVisibility(View.VISIBLE);
                    dfCountLlJiShu.setVisibility(View.GONE);
                    dfCountTvYJiaoYingJiao.setText("0.20");
                    dfCountBtn.performClick();
                    if (popupWindow != null && popupWindow.isShowing()) {
                        popupWindow.dismiss();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 切换类型时候把所有的输入框给置空
     */
    private void cleraEditText() {
        if (editTexts != null && editTexts.length > 0) {
            for (int i = 0; i < editTexts.length; i++) {
                editTexts[i].setText("");
            }
        }
        if (linearLayouts != null && linearLayouts.length > 0) {
            for (int i = 0; i < linearLayouts.length; i++) {
                linearLayouts[i].setVisibility(View.GONE);
            }
        }
        if (textViews != null && textViews.length > 0) {
            for (int i = 0; i < textViews.length; i++) {
                textViews[i].setBackgroundResource(R.color.white);
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            saveData();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
