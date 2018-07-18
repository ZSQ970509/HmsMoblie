package com.hc.hmsmoblie.bean.type;

/**
 * 登录服务器 的用户类型
 */

public enum UserTypeId {
    //汇川10,省工程建设网20,市建委30
    HSM(0), PECN(1), MCC(2);
    private String[] mName = {"HMS平台", "省工程建设网", "市建委"};
    private String[] mId = {"10", "20", "30"};
    private int mIndex;

    UserTypeId(int indext) {
        this.mIndex = indext;
    }

    public static UserTypeId getUserTypeId(int index) {
        switch (index) {
            case 2:
                return MCC;
            case 1:
                return PECN;
            default:
                return HSM;
        }
    }

    public int getIndex() {
        return mIndex;
    }

    public String getTypeId() {
        return mId[mIndex];
    }

    public String getTypeName() {
        return mName[mIndex];
    }
}
