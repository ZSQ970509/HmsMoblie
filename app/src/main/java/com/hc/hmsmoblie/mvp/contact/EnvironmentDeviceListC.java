package com.hc.hmsmoblie.mvp.contact;

import com.hc.hmsmoblie.bean.json.EnvironmentDetailsJson;
import com.hc.hmsmoblie.bean.json.EnvironmentDeviceListJson;
import com.yc.yclibrary.exception.ApiException;
import com.yc.yclibrary.mvp.IView;

import java.util.List;

/**
 *
 */

public class EnvironmentDeviceListC {
    public interface V extends IView {
        void onEnvironmentDeviceListSuccess(List<EnvironmentDeviceListJson> projectJson);

        void onEnvironmentDeviceListFail(ApiException apiException);
    }

    public interface P {
        void getEnvironmentDeviceList(String projId);
    }
}
