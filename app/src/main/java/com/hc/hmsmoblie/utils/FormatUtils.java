package com.hc.hmsmoblie.utils;

import android.text.TextUtils;
import android.util.Log;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Administrator on 2018/6/19.
 */

public class FormatUtils {
    public static String round(double value) {
        return new BigDecimal(value).setScale(0, BigDecimal.ROUND_HALF_UP).toString();
    }

    public static String format2(double value) {
        return String.format("%.2f", value);
    }

    public static String format3(double value) {
        return String.format("%.3f", value);
    }

    public final static String FORMAT_TIME = "yyyy-MM-dd";
    public final static String FORMAT_TIME_MONTH = "yyyy-MM";
    public final static String FORMAT_TIME_YEAR = "yyyy";

    /**
     * 将Date类型格式化成String yyyy-MM-dd
     *
     * @param date 时间
     * @return
     */
    public static String dateToString(Date date) {
        if (date == null) {
            return "";
        } else {
            return new SimpleDateFormat(FORMAT_TIME).format(date);
        }
    }

    /**
     * 将Date类型格式化成String yyyy-MM-dd
     *
     * @param date 时间
     * @return
     */
    public static String dateToString(Date date, boolean month, boolean day) {
        if (date == null) {
            return "";
        } else {
            if (month == true && day == true) {
                return new SimpleDateFormat(FORMAT_TIME).format(date);
            } else if (month == true && day == false) {
                return new SimpleDateFormat(FORMAT_TIME_MONTH).format(date);
            }
            return new SimpleDateFormat(FORMAT_TIME_YEAR).format(date);

        }
    }

    public static String dateToString(Date date, String format) {
        if (date == null) {
            return "";
        } else {
            return new SimpleDateFormat(format).format(date);
        }
    }

    /**
     * 将Calendar类型格式化成String yyyy-MM-dd
     *
     * @param date 时间
     * @return
     */
    public static String calendarToString(Calendar date) {
        return calendarToString(date, FORMAT_TIME);
    }

    public static String calendarToString(Calendar date, String format) {
        if (date == null) {
            return "";
        } else {
            return new SimpleDateFormat(format, Locale.getDefault()).format(date.getTime());
        }
    }

    public static Date stringToDate(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_TIME);
        Date timeDate = null;
        try {
            timeDate = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timeDate;
    }

    public static Calendar stringToCalendar(String time) {
        if (TextUtils.isEmpty(time)) {
            Log.e("FormatUtils", "String转Calender失败，String为空");
            return Calendar.getInstance();
        }
        Date date = null;
        try {
            SimpleDateFormat df = new SimpleDateFormat(FORMAT_TIME);
            date = df.parse(time);
        } catch (ParseException e) {
            Log.e("FormatUtils", "String转Calender失败，String格式不是FORMAT_TIME");
            return Calendar.getInstance();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * 将Date类型格式化成Calendar
     *
     * @param date 时间
     * @return
     */
    public static Calendar dataToCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * 进行四舍五入
     *
     * @param values 要进行四舍五入的值
     * @param scale  表示表示需要精确到小数点以后几位。
     */
    public static List<Double> roundOff(List<Double> values, int scale) {
        int size = values.size();
        List<Double> newValues = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            newValues.set(i, roundOff(values.get(i), scale));
        }
        return newValues;
    }
    public static List<Double> roundOff(List<Double> values, int scale,double multiplier) {
        int size = values.size();
        List<Double> newValues = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            newValues.set(i, roundOff(values.get(i), scale)*multiplier);
        }
        return newValues;
    }
    /**
     * 进行四舍五入
     *
     * @param value 要进行四舍五入的值
     * @param scale 表示表示需要精确到小数点以后几位。
     */
    public static double roundOff(double value, int scale) {
        BigDecimal value1 = new BigDecimal(value + "");
        return value1.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
