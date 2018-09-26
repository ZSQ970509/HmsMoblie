package com.hc.hmsmoblie.bean.type;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *
 */
@IntDef({TimeType.DAY, TimeType.MONTH, TimeType.YEAR})
@Retention(RetentionPolicy.SOURCE)
public @interface TimeType {
    int DAY = 2;
    int MONTH = 1;
    int YEAR = 0;
}
