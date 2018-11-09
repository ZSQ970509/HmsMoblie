package com.hc.hmsmoblie.utils;

import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.text.TextUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 空处理
 */

public class EmptyUtils {
    public static String getString(String original) {
        return TextUtils.isEmpty(original) ? "" : original;
    }

    public static String getString(String original, String empty) {
        return TextUtils.isEmpty(original) ? empty : original;
    }

    public static String getString(String original, String empty, String suffix) {
        return TextUtils.isEmpty(original) ? empty : original + suffix;
    }

    public static String getStringNum(String original, String empty, String suffix) {
        return TextUtils.isEmpty(original) ? empty : Double.parseDouble(original) + suffix;
    }

    //时间戳处理
    public static String getDateString(String dateStr) {
        if (!TextUtils.isEmpty(dateStr)) {
            dateStr = dateStr.replace("/Date(", "").replace(")/", "");
            String time = dateStr.substring(0, dateStr.length() - 5);
            Date date = new Date(Long.parseLong(time));
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            return format.format(date);
        } else {
            return "";
        }

    }
    public static String  isEmptyStr(String s){
        if(s.equals("")){
            return "0";
        }
        return  s;
    }
    public static String isState(String original, String s1, String s2, String s3) {
        if (TextUtils.isEmpty(original)) {
            return s1;
        } else if (original.equals("0")) {
            return s2;
        } else
            return s3;
    }

    public static int isState(String original, @DrawableRes int s1, @DrawableRes int s2, @DrawableRes int s3) {
        if (TextUtils.isEmpty(original)) {
            return s3;
        } else if (original.equals("0")) {
            return s2;
        } else
            return s1;
    }
}
