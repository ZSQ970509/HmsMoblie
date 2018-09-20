package com.hc.hmsmoblie.widget.vh;

import android.support.annotation.IntDef;
import android.text.TextUtils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *
 */
@IntDef({ViewType.VIEW_EMPTY, ViewType.VIEW_ITEM, ViewType.VIEW_LOAD_MORE})
@Retention(RetentionPolicy.SOURCE)
public @interface ViewType {
    int VIEW_EMPTY = 0;
    int VIEW_ITEM = 1;
    int VIEW_LOAD_MORE = 2;
}
