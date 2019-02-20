package com.hc.hmsmoblie.mvp.contact;

import com.hc.hmsmoblie.bean.json.LadderControlDeviceListJson;
import com.hc.hmsmoblie.bean.json.WeighingMachineJson;
import com.hc.hmsmoblie.bean.json.WeighingMachineMsg;
import com.hc.hmsmoblie.net.HttpResponse;
import com.yc.yclibrary.exception.ApiException;
import com.yc.yclibrary.mvp.IView;

/**
 *
 */

public class WeighingMachineC {
    public interface V extends IView {
        void onGetWeighbridgeListSuccess(WeighingMachineJson projectJson);

        void onGetWeighbridgeListFail(ApiException apiException);

        void onGetWeighbridgeSuccess(WeighingMachineMsg weighingMachineMsg);

        void onGetWeighbridgeFail(ApiException apiException);
    }

    public interface P {
        void getWeighbridgeList(String  projId,int pageIndex, int pageSize, String openingTimeBegin, String openingTimeEnd);
        void getWeighbridge(String  recordId);
    }
}