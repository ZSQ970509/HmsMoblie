package com.hc.hmsmoblie.bean.type;

/**
 *
 */

public enum ModuleTypeEnum {
    //视频监控  ,超视野移动监控,钢筋鉴证,影像日志,环境远程监测,移动考勤,大型设备操作员认证(梯控),断电断网
    video(1381),SUPER_VIDEO(1382);
    private int mId;

    ModuleTypeEnum(int id) {
        mId = id;
    }
}
