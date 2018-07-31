package com.hc.hmsmoblie.mvp.contact;

import com.hc.hmsmoblie.bean.json.LadderControlDeviceListJson;
import com.hc.hmsmoblie.bean.json.ProjectJson;
import com.yc.yclibrary.exception.ApiException;
import com.yc.yclibrary.mvp.IView;

/**
 *
 */

public class LadderControlDeviceListC {
    public interface V extends IView {
        void onGetDeviceListSuccess(LadderControlDeviceListJson projectJson);

        void onGetDeviceListFail(ApiException apiException);
    }

    public interface P {
        void getDeviceList(String keyword, int pageIndex, int pageSize,String proId,String camId);
    }
}
