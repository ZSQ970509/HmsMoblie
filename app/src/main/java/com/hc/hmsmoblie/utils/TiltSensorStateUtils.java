package com.hc.hmsmoblie.utils;

import android.support.annotation.DrawableRes;

import com.hc.hmsmoblie.R;

/**
 * 倾角状态
 */

public class TiltSensorStateUtils {
    private static final String default_date = "";


    /**
     * X轴角度格式化（状态+绝对值）
     *
     * @param x
     * @return
     */
    public static String formatX(double x) {
        if (x > 0) {
            return "前";
        } else if (x < 0) {
            return "后";
        } else {
            return default_date;
        }
    }

    /**
     * Y轴角度格式化（状态+绝对值）
     *
     * @param y
     * @return
     */
    public static String formatY(double y) {
        if (y > 0) {
            return "右";
        } else if (y < 0) {
            return "左";
        } else {
            return default_date;
        }
    }

    /**
     * 空间X轴格式化（状态+绝对值）
     *
     * @param spaceX
     * @return
     */
    public static String formatSpaceX(double spaceX) {
        if (spaceX > 0) {
            return "前";
        } else if (spaceX < 0) {
            return "后";
        } else {
            return default_date;
        }
    }

    /**
     * 空间Y轴格式化（状态+绝对值）
     *
     * @param spaceY
     * @return
     */
    public static String formatSpaceY(double spaceY) {
        if (spaceY > 0) {
            return "右";
        } else if (spaceY < 0) {
            return "左";
        } else {
            return default_date;
        }
    }

    /**
     * 空间Z轴格式化（状态+绝对值）
     *
     * @param spaceZ
     * @return
     */
    public static String formatSpaceZ(double spaceZ) {
        if (spaceZ > 0) {
            return "上";
        } else if (spaceZ < 0) {
            return "下";
        } else {
            return default_date;
        }
    }

    /**
     * 沉降 格式化（状态+绝对值）
     */
    public static String formatSettlement(double settlement) {
        if (settlement > 0) {
            return "↑";
        } else if (settlement < 0) {
            return "↓";
        } else {
            return default_date;
        }
    }

    /**
     * 水平度浮动（左端和右端） 轴格式化（状态+绝对值）
     *
     * @param floating
     * @return
     */
    public static String formatFloating(double floating) {
        if (floating > 0) {
            return "↑";
        } else if (floating < 0) {
            return "↓";
        } else {
            return default_date;
        }
    }

    /**
     * 根据value返回显示趋势图标
     *
     * @param value
     * @return
     */
    @DrawableRes
    public static int getState(double value) {
        if (value > 0) {
            return R.drawable.ic_up;
        } else if (value == 0) {
            return R.drawable.ic_none;
        } else {
            return R.drawable.ic_down;
        }
    }

    public static String getFormData(double data, String unit) {
        if (data == 0) {
            return "-";
        } else {
            return FormatUtils.stripTrailingZeros(data) + unit;
        }
    }

    public static String getFormAdsData(double data, String unit) {
        if (data == 0) {
            return "-";
        } else {
            return FormatUtils.stripTrailingZeros(Math.abs(data)) + unit;
        }
    }
}
