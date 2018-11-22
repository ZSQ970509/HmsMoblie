package com.hc.hmsmoblie.bean.type;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 等级（电量、信号强度）
 */
@IntDef({LevelType.Level0, LevelType.Level1, LevelType.Level2, LevelType.Level3, LevelType.Level4, LevelType.Level5, LevelType.Level6})
@Retention(RetentionPolicy.SOURCE)
public @interface LevelType {
    int Level0 = 0;
    int Level1 = 1;
    int Level2 = 2;
    int Level3 = 3;
    int Level4 = 4;
    int Level5 = 5;
    int Level6 = 6;
}
