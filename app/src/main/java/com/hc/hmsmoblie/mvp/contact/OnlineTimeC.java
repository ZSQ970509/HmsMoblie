package com.hc.hmsmoblie.mvp.contact;

import com.hc.hmsmoblie.bean.json.LoginJson;
import com.hc.hmsmoblie.bean.json.OnlineTimeJson;
import com.yc.yclibrary.mvp.IView;

import java.util.ArrayList;

/**
 *
 */

public class OnlineTimeC {
    public interface V extends IView{
        void onGetOnlineRateSuccess( ArrayList<OnlineTimeJson> onlineTimeJs);

        void onGetOnlineRateFail(String msg);
    }
    public interface P {
        void GetOnlineRate(String projId, String startDate, String endDate);
    }
}
