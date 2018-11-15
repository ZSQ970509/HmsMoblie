package com.hc.hmsmoblie.mvp.contact;

import com.hc.hmsmoblie.bean.json.SensorLogJson;
import com.hc.hmsmoblie.bean.json.SetAllMessageJson;
import com.hc.hmsmoblie.bean.json.TiltSensorParaJson;
import com.hc.hmsmoblie.bean.json.TiltSensorSettingJson;
import com.hc.hmsmoblie.bean.json.TiltSensorStateJson;
import com.hc.hmsmoblie.net.HttpResponse;
import com.hc.hmsmoblie.net.NetObserver;
import com.yc.yclibrary.mvp.IView;

import retrofit2.http.Field;

/**
 *
 */

public class DipRealTimeDataC {
    public interface V extends IView{
        void onGetGetTiltSensorParaSuccess(TiltSensorParaJson dataBean);
        void onGetGetTiltSensorParaFail(String msg);
        void onGetTiltSensorLogSuccess(SensorLogJson dataBean);
        void onGetTiltSensorLogFail(String msg);
        void onSetAllMessageSuccess(HttpResponse<String> dataBean);
        void onSetAllMessageFail(String msg);
        void getTiltSensorStateSuccess(TiltSensorStateJson tiltSensorStateJson);
        void getTiltSensorStateSettingSuccess(TiltSensorStateJson tiltSensorStateJson);
        void getTiltSensorStateFail(String msg);
        void setIotDeviceInfoSuccess(boolean xy,boolean reportTime,boolean Switch);
        void setAllIotDeviceInfoSuccess(TiltSensorSettingJson tiltSensorSettingJson1, TiltSensorSettingJson tiltSensorSettingJson2
                , TiltSensorSettingJson tiltSensorSettingJson3, String seq);
    }
    public interface P {
        void getGetTiltSensorPara(String cmID);
        void getTiltSensorLog(boolean isShowLoad,String cmID, String paraID, int pageindex, int pagesize, String startTime
                , String endTime, NetObserver<HttpResponse<SensorLogJson>>responseNetObserver);
        void setAllMessage(String paraID,String seq, String type);
        void getTiltSensorState(String deviceId,boolean isSetting);
    }
}
