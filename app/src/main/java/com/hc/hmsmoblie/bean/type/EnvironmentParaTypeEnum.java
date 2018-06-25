package com.hc.hmsmoblie.bean.type;

/**
 * 环境 时间的类型
 */

public enum EnvironmentParaTypeEnum {
    PM10("0"), 湿度1("1"), 湿度2("2"), 噪音("3"), PM2点5("4");
    private String mType;

    EnvironmentParaTypeEnum(String type) {
        mType = type;
    }

    public String getType() {
        return mType;
    }
}
