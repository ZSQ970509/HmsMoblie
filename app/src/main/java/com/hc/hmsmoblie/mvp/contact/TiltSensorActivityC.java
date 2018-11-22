package com.hc.hmsmoblie.mvp.contact;

import com.hc.hmsmoblie.bean.json.TiltSensorAllJson;
import com.hc.hmsmoblie.bean.json.TiltSensorJson;
import com.hc.hmsmoblie.bean.json.TiltSensorParaJson;
import com.hc.hmsmoblie.bean.json.TiltSensorStateJson;
import com.hc.hmsmoblie.bean.type.TiltSensorParaState;
import com.yc.yclibrary.mvp.IView;

/**
 *
 */

public class TiltSensorActivityC {
    public interface V extends IView{
        void onGetTiltSensorParaSuccess(TiltSensorParaJson dataBean);
        void onGetTiltSensorParaFail(String msgFail);
        void onGetTitAllSuccess(TiltSensorAllJson tiltSensorJson);
        void onGetTitAllFail(String msgFail);
        void onSetAllMessage(@TiltSensorParaState String isOnOff);
        void onGetDeviceState(TiltSensorStateJson tiltSensorStateJson);
        void onGetDeviceSetting(TiltSensorStateJson tiltSensorStateJson);
    }
    public interface P {
        void getGetTiltSensorPara(String cmID);
    }
}
