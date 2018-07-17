package com.hc.hmsmoblie.bean.type;

import com.hc.hmsmoblie.db.UserInfoPref;

/**
 * 登录服务器 的用户类型
 */

public enum UserTypeId {
    //汇川,市建委,省工程建设网
    HSM(), MCC(), PECN();

    public static UserTypeId getUserTypeId(int index) {
        switch (index) {
            case 2:
                return PECN;
            case 1:
                return MCC;
            default:
                return HSM;
        }
    }

    public int getIndex() {
        switch (this) {
            case PECN:
                return 2;
            case MCC:
                return 1;
            default:
                return 0;
        }
    }

    public String getTypeId() {
        switch (this) {
            case HSM:
                return "10";
            case MCC:
                return "20";
            default:
                return "30";
        }
    }

    public String getTypeName() {
        switch (this) {
            case HSM:
                return "HMS平台";
            case MCC:
                return "市建委";
            default:
                return "省工程建设网";
        }
    }
}
