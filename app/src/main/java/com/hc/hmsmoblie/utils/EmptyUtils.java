package com.hc.hmsmoblie.utils;

import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.text.TextUtils;

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
    public static String isState(String original,String s1,String s2,String s3) {
        if(TextUtils.isEmpty(original)){
            return s1;
        }else if(original.equals("0")){
            return s2;
        }else
            return s3;
    }
    public static int isState(String original, @DrawableRes int s1, @DrawableRes int s2, @DrawableRes int s3) {
        if(TextUtils.isEmpty(original)){
            return s1;
        }else if(original.equals("0")){
            return s2;
        }else
            return s3;
    }
}
