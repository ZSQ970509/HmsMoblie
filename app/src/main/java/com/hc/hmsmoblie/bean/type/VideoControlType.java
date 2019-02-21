package com.hc.hmsmoblie.bean.type;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 慧眼设备控制
 */
@StringDef({VideoControlType.top, VideoControlType.bottom, VideoControlType.left, VideoControlType.right,
        VideoControlType.stop, VideoControlType.enlarge, VideoControlType.narrow})
@Retention(RetentionPolicy.SOURCE)
public @interface VideoControlType {
    String top = "3";//向上
    String bottom = "4";//向下
    String left = "5";//向左
    String right = "6";//向右
    String stop = "0";//停止
    //    String start = 5;//开始
    String enlarge = "2";//放大
    String narrow = "1"; //缩小
}
