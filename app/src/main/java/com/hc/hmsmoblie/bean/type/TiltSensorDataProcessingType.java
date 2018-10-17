package com.hc.hmsmoblie.bean.type;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 倾角图表里数据处理的方式
 */
@IntDef({TiltSensorDataProcessingType.KEEP_DECIMAL_4, TiltSensorDataProcessingType.KEEP_DECIMAL_1_Multiply_1000})
@Retention(RetentionPolicy.SOURCE)
public @interface TiltSensorDataProcessingType {
    /**
     * 保留小数后4位
     */
    int KEEP_DECIMAL_4 = 0;
    /**
     * 保留小数后1位并乘以1000
     */
    int KEEP_DECIMAL_1_Multiply_1000 = 1;

}
