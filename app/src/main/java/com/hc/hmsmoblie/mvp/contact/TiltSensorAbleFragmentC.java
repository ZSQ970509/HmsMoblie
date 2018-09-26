package com.hc.hmsmoblie.mvp.contact;

import com.hc.hmsmoblie.bean.json.ProjectJson;
import com.hc.hmsmoblie.bean.json.SensorLogJson;
import com.hc.hmsmoblie.bean.json.TiltSensorParaJson;
import com.yc.yclibrary.mvp.IView;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */

public class TiltSensorAbleFragmentC {
    public interface V extends IView{
        void onGetTiltSensorLogSuccess(SensorLogJson sensorLogJson);
        void onGetTiltSensorLogFail(String msg);
    }
    public interface P {
        void getTiltSensorLog(String cmID, String paraID, int pageindex, int pagesize, String startTime, String endTime);
    }
}
