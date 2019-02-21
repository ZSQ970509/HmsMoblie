package com.zxl.library;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 地磅页面下拉刷选框类型(按布局从左到右)
 */
@IntDef({WeighingSelectorType.supplier, WeighingSelectorType.merchandise, WeighingSelectorType.weighing, WeighingSelectorType.weight})
@Retention(RetentionPolicy.SOURCE)
public @interface WeighingSelectorType {
    /**
     * 品名规格
     */
    int merchandise = 1;
    /**
     * 供应单位
     */
    int supplier = 2;
    /**
     * 司磅员
     */
    int weighing = 3;
    /**
     * 重量
     */
    int weight = 4;
}
