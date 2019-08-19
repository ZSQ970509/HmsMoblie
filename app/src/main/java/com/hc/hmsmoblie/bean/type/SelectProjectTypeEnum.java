package com.hc.hmsmoblie.bean.type;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *
 */
@IntDef({SelectProjectTypeEnum.FILE, SelectProjectTypeEnum.FOLDER})
@Retention(RetentionPolicy.SOURCE)
public @interface SelectProjectTypeEnum {
    int FILE = 0;
    int FOLDER = 1;
}
