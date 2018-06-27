package com.hc.hmsmoblie.bean.type;

/**
 *  项目类型id
 */

public enum ProTypeEnum {
    //影像日志
    IMG_LOG("30");
    private String mTypeId;

    ProTypeEnum(String typeId) {
        mTypeId = typeId;
    }

    public String getTypeId() {
        return mTypeId;
    }
}
