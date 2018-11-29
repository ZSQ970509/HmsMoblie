package com.hc.hmsmoblie.mvp.contact;

import com.hc.hmsmoblie.bean.json.TiltSensorAllJson;
import com.hc.hmsmoblie.bean.json.TiltSensorJson;
import com.hc.hmsmoblie.bean.json.TiltSensorParaJson;
import com.hc.hmsmoblie.bean.json.TiltSensorSettingJson;
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
        void onSetAllMessageSuccess(@TiltSensorParaState String isOnOff);
        void onGetDeviceStateSuccess(TiltSensorStateJson tiltSensorStateJson);
        void onGetDeviceSettingSuccess(TiltSensorStateJson tiltSensorStateJson);
        void setIotDeviceInfoSuccess(boolean xy,boolean reportTime,boolean Switch);
        void setAllIotDeviceInfoSuccess(TiltSensorSettingJson tiltSensorSettingJson1, TiltSensorSettingJson tiltSensorSettingJson2
                , TiltSensorSettingJson tiltSensorSettingJson3, String seq);
    }
    public interface P {
        void getGetTiltSensorPara(String cmID);
    }
}
