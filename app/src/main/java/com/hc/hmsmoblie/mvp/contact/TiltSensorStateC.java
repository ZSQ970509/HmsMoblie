package com.hc.hmsmoblie.mvp.contact;

import com.hc.hmsmoblie.bean.json.LoginJson;
import com.hc.hmsmoblie.bean.json.TiltSensorStateJson;
import com.hc.hmsmoblie.bean.json.UpdateVersionJson;
import com.yc.yclibrary.mvp.IView;

/**
 *
 */

public class TiltSensorStateC {
    public interface V extends IView {

        void getTiltSensorStateSuccess(TiltSensorStateJson tiltSensorStateJson);

        void getTiltSensorStateFail(String msg);
    }

    public interface P {
        void getTiltSensorState(String deviceId);

    }
}
