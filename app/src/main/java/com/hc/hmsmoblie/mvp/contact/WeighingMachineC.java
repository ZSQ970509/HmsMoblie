package com.hc.hmsmoblie.mvp.contact;

import com.hc.hmsmoblie.bean.json.LadderControlDeviceListJson;
import com.hc.hmsmoblie.bean.json.WeighingMachineJson;
import com.hc.hmsmoblie.bean.json.WeighingMachineMsg;
import com.hc.hmsmoblie.bean.json.WeightGroupJson;
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

        void onGetWeighGroupListSuccess(WeightGroupJson weightGroupJson);

        void onGetWeighGroupListFail(ApiException apiException);
    }

    public interface P {
        void getWeighbridgeList(String  projId,int pageIndex, int pageSize, String openingTimeBegin, String openingTimeEnd,
                                String supplier, String merchandise, String weighing, double weigh);
        void getWeighbridge(String  recordId);
        void getWeighGroupList(String proId);
    }
}
