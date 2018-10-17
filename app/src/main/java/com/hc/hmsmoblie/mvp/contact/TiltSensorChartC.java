package com.hc.hmsmoblie.mvp.contact;

import com.hc.hmsmoblie.bean.json.TiltSensorChartJson;
import com.hc.hmsmoblie.bean.json.TiltSensorChartJsonNew;
import com.hc.hmsmoblie.bean.json.UpdateVersionJson;
import com.yc.yclibrary.mvp.IView;

import java.util.List;

/**
 *
 */

public class TiltSensorChartC {
    public interface V extends IView{

        void getTiltSensorChartSuccess(TiltSensorChartJsonNew httpResponse);

        void getTiltSensorChartFail(String msg);
    }
    public interface P {

        void getTiltSensorChart(String camID, String paraID, String timeType, String selDate,int type);
    }
}
