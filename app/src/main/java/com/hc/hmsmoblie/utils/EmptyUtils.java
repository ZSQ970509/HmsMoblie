package com.hc.hmsmoblie.utils;

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
}
