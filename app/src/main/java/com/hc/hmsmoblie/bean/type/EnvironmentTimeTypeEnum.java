package com.hc.hmsmoblie.bean.type;

import java.util.List;

/**
 * 环境 时间的类型
 */

public enum EnvironmentTimeTypeEnum {
    Day("day"), Month("month"), Year("year");
    private String mType;

    EnvironmentTimeTypeEnum(String type) {
        mType = type;
    }

    public String getType() {
        return mType;
    }

}
