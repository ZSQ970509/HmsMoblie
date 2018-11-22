package com.hc.hmsmoblie.mvp.contact;

import com.hc.hmsmoblie.bean.json.SensorLogJson;
import com.hc.hmsmoblie.bean.json.TiltSensorParaJson;
import com.yc.yclibrary.mvp.IView;

/**
 *
 */

public class TiltSensorActivityCOld {
    public interface V extends IView{
        void onGetGetTiltSensorParaSuccess(TiltSensorParaJson dataBean);
        void onGetGetTiltSensorParaFail(String msg);
    }
    public interface P {
        void getGetTiltSensorPara(String cmID);
    }
}
