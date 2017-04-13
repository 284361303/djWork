package com.dangjian.activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import com.dangjian.R;

import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 选择出生日期时间
 */
public class SelectDatePickerActivity extends BaseActivity {

    @Bind(R.id.dataPicker_tvCancel)
    TextView dataPickerTvCancel;
    @Bind(R.id.dataPicker_tvTrue)
    TextView dataPickerTvTrue;
    @Bind(R.id.dataPicker)
    DatePicker dataPicker;
    private static final String TAG = "SelectDateActivity";
    @Bind(R.id.selelctDate_tvOne)
    TextView selelctDateTvOne;
    @Bind(R.id.selelctDate_tvTwo)
    TextView selelctDateTvTwo;
    private String mMonth = "", mDay = "";
    private int year, month, day;
    private String activity = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_date_picker);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        try {
            String dateFormat = Settings.System.getString(getContentResolver(), Settings.System.DATE_FORMAT);
            Log.i(TAG, "initView/dateFormat: " + dateFormat);
            if (dateFormat != null && !dateFormat.equals("") && !dateFormat.equals("yyyy-MM-dd")) {
                Settings.System.putString(getContentResolver(), Settings.System.DATE_FORMAT, "yyyy-MM-dd");
            }
            Calendar calendar = Calendar.getInstance();
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH) + 1;
            day = calendar.get(Calendar.DAY_OF_MONTH);
            Intent intent = getIntent();
            Bundle extras = intent.getExtras();
            if (extras != null) {
                activity = extras.getString("activity");
                if (activity.equals("Birth")) {     //出生年月
                    selelctDateTvOne.setText("月");
                    selelctDateTvTwo.setText("日");
                    mMonth = String.valueOf(month + 1);
                    mDay = String.valueOf(day);
                    ((ViewGroup) ((ViewGroup) dataPicker.getChildAt(0)).getChildAt(0)).getChildAt(0).setVisibility(View.GONE);
                    /*final DatePicker datePicker = (DatePicker) findViewById(R.id.dataPicker);
                    if (datePicker != null) {
                        Field f[] = datePicker.getClass().getDeclaredFields();
                        for (Field field : f) {
                            String name = field.getName();
                            Log.i(TAG, "initView/name: " + name);
                            if (field.getName().equals("mYearPicker") || field.getName().equals("mYearSpinner")) {
                                field.setAccessible(true);
                                Object yearPicker = new Object();
                                yearPicker = field.get(datePicker);
                                ((View) yearPicker).setVisibility(View.GONE);
                            }
                        }
                    }*/
                    dataPicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
                        @Override
                        public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                            mMonth = String.valueOf(i1 + 1);
                            mDay = String.valueOf(i2);
                        }
                    });
                } else if (activity.equals("InTime")) {      //入党时间
                    selelctDateTvOne.setText("年");
                    selelctDateTvTwo.setText("月");
                    mMonth = String.valueOf(month + 1);
                    mDay = String.valueOf(year);
                    ((ViewGroup) ((ViewGroup) dataPicker.getChildAt(0)).getChildAt(0)).getChildAt(2).setVisibility(View.GONE);
                    /*final DatePicker datePicker = (DatePicker) findViewById(R.id.dataPicker);
                    if (datePicker != null) {
                        Field f[] = datePicker.getClass().getDeclaredFields();
                        for (Field field : f) {
                            String name = field.getName();
                            Log.i(TAG, "initView/name: " + name);
                            if (field.getName().equals("mDayPicker") || field.getName().equals("mDaySpinner")) {    //mDaySpinner
                                field.setAccessible(true);
                                Object monthPicker = new Object();
                                monthPicker = field.get(datePicker);
                                ((View) monthPicker).setVisibility(View.GONE);
                            }
                        }
                    }*/
                    dataPicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
                        @Override
                        public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                            mDay = String.valueOf(i);
                            mMonth = String.valueOf(i1 + 1);
                        }
                    });
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.dataPicker_tvCancel, R.id.dataPicker_tvTrue})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dataPicker_tvCancel:
//                intentClose();
                mContext.finish();
                break;
            case R.id.dataPicker_tvTrue:
                Intent ii = new Intent();
                ii.putExtra("mMonth", mMonth);
                ii.putExtra("mDay", mDay);
                mContext.setResult(202, ii);
                mContext.finish();
                break;
        }
    }

    private void intentClose() {
        if (activity.equals("Birth")) {     //出生年月
            mMonth = String.valueOf(month + 1);
            mDay = String.valueOf(day);
        } else {
            mMonth = String.valueOf(month + 1);
            mDay = String.valueOf(year);
        }
        Intent ii = new Intent();
        ii.putExtra("mMonth", mMonth);
        ii.putExtra("mDay", mDay);
        mContext.setResult(202, ii);
        mContext.finish();
    }

  /*  @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            if (activity.equals("Birth")) {     //出生年月
                mMonth = String.valueOf(month + 1);
                mDay = String.valueOf(day);
            } else {
                mMonth = String.valueOf(month + 1);
                mDay = String.valueOf(year);
            }
            Intent ii = new Intent();
            ii.putExtra("mMonth", mMonth);
            ii.putExtra("mDay", mDay);
            mContext.setResult(202, ii);
            mContext.finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }*/
}

