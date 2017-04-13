package com.dangjian.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.text.DecimalFormat;

/**
 * 转化保留小数点后几位小数
 */
public class NumUtil {
    /**
     * 保留小数点后两位 保持四舍五入
     */
    public static double parseNum(double num) {
        String number = num * 100 + "";
        if (number.length() > 5) {
            number = number.substring(0, 5);
        }
        return Double.parseDouble(number);
    }

    /**
     * 带逗号的double工具类
     */
    public static String fmtMicrometer(String text) {
        DecimalFormat df = null;
        if (text.indexOf(".") > 0) {
            if (text.length() - text.indexOf(".") - 1 == 0) {
                df = new DecimalFormat("###,##0.");
            } else if (text.length() - text.indexOf(".") - 1 == 1) {
                df = new DecimalFormat("###,##0.00");
            } else {
                df = new DecimalFormat("###,##0.00");
            }
        } else {
            df = new DecimalFormat("###,##0");
        }
        double number = 0.0;
        try {
            number = Double.parseDouble(text);
        } catch (Exception e) {
            number = 0.0;
        }
        return df.format(number);
    }

    /**
     * 任何利息进入 返回的格式为 0.00%
     */
    public static String parseRate(String str) {
        String result = "";
        if (str != null) {
            if (str.contains(".")) {
                String str1 = str.substring(str.indexOf(String.valueOf('.')),
                        str.length());
                if (str1.length() >= 3) {
                    result = str.substring(0,
                            str.indexOf(String.valueOf('.')) + 3);
                } else if (str1.length() == 2) {
                    result = str + "0";
                } else if (str1.length() < 2 && str1.length() >= 0) {
                    result = str;
                }
            } else {
                result = str + ".00";
            }
        }
        return result;
    }

    /**
     * 如：0.0
     */
    public static String parseRate1(String str) {
        String result = "";
        if (str.contains(".")) {
            String str1 = str.substring(str.indexOf(String.valueOf('.')),
                    str.length());
            if (str1.length() > 2) {
                result = str.substring(0, str.indexOf(String.valueOf('.')) + 2);
            } else if (str1.length() <= 2 && str1.length() >= 0) {
                result = str;
            }
        } else {
            result = str + ".0";
        }
        return result;
    }

    /**
     * 如：0，只保留整数
     */
    public static String parseRate2(String str) {
        String result = "";
        if (str.contains(".")) {
            result = str.substring(0, str.indexOf(String.valueOf('.')));
        }
        return result;
    }

    public static boolean isInteger(String input) {
        return input.matches("[1-9][0-9]*");
    }

    public static void setPricePoint(final EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + 3);
                        editText.setText(s);
                        editText.setSelection(s.length());
                    }
                }
                if (s.toString().trim().substring(0).equals(".")) {
                    s = "0" + s;
                    editText.setText(s);
                    editText.setSelection(2);
                }
                if (s.toString().startsWith("0")
                        && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        editText.setText(s.subSequence(0, 1));
                        editText.setSelection(1);
                        return;
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
}
