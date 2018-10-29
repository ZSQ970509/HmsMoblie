package com.hc.hmsmoblie.bean.type;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 倾角图表里数据类型
 */
@IntDef({TiltSensorType.A_ANGLE_VALUE, TiltSensorType.B_SINGLE_ANGLE_DIFFERENCE, TiltSensorType.C_STAGE_ANGLE_DIFFERENCE
        , TiltSensorType.D_CUMULATIVE_ANGLE_DIFFERENCE, TiltSensorType.E_SINGLE_SETTLEMENT, TiltSensorType.F_STAGE_SETTLEMENT
        , TiltSensorType.G_ACCUMULATIVE_SETTLEMENT, TiltSensorType.H_PARALLELISM})
@Retention(RetentionPolicy.SOURCE)
public @interface TiltSensorType {
    /**
     * 角度值
     */
    int A_ANGLE_VALUE = 0;
    /**
     * 单次角度差
     */
    int B_SINGLE_ANGLE_DIFFERENCE = 1;
    /**
     * 阶段角度差
     */
    int C_STAGE_ANGLE_DIFFERENCE = 2;
    /**
     * 累计角度差
     */
    int D_CUMULATIVE_ANGLE_DIFFERENCE = 3;
    /**
     * 单次沉降
     */
    int E_SINGLE_SETTLEMENT = 4;
    /**
     * 阶段沉降
     */
    int F_STAGE_SETTLEMENT = 5;
    /**
     * 累计沉降
     */
    int G_ACCUMULATIVE_SETTLEMENT = 6;
    /**
     * 水平度
     */
    int H_PARALLELISM = 7;
}
