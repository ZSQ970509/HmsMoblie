package com.hc.hmsmoblie.mvp.contact;

import com.hc.hmsmoblie.bean.json.TiltSensorParaJson;
import com.hc.hmsmoblie.net.HttpResponse;
import com.hc.hmsmoblie.net.NetObserver;
import com.yc.yclibrary.mvp.IView;

/**
 *
 */

public class DipRealTimeDataC {
    public interface V extends IView{
        void onGetGetTiltSensorParaSuccess(TiltSensorParaJson dataBean);
        void onGetGetTiltSensorParaFail(String msg);
    }
    public interface P {
        void getGetTiltSensorPara(String cmID, NetObserver<HttpResponse<TiltSensorParaJson>>responseNetObserver);
    }
}
