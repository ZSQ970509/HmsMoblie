package com.hc.hmsmoblie.bean.type;

import android.support.annotation.IntDef;
import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 倾角图表里数据类型
 */
@StringDef({TiltSensorParaState.OPEN, TiltSensorParaState.CLOSE, TiltSensorParaState.RESET,TiltSensorParaState.UNKNOWN})
@Retention(RetentionPolicy.SOURCE)
public @interface TiltSensorParaState {
    String OPEN = "1";//开启
    String CLOSE = "2";//关闭
    String RESET = "3";//重置（暂不用）
    String UNKNOWN = "4";//未知
}
