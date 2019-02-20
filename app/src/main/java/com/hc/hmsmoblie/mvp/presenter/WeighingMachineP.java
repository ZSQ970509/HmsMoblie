package com.hc.hmsmoblie.mvp.presenter;

import com.hc.hmsmoblie.bean.json.LadderControlDeviceListJson;
import com.hc.hmsmoblie.bean.json.WeighingMachineJson;
import com.hc.hmsmoblie.bean.json.WeighingMachineMsg;
import com.hc.hmsmoblie.bean.json.WeightGroupJson;
import com.hc.hmsmoblie.mvp.contact.WeighingMachineC;
import com.hc.hmsmoblie.mvp.model.WeighingMachineM;
import com.hc.hmsmoblie.net.HttpResponse;
import com.hc.hmsmoblie.net.NetObserver;
import com.yc.yclibrary.exception.ApiException;
import com.yc.yclibrary.mvp.BasePresenter;

/**
 *
 */

public class WeighingMachineP extends BasePresenter<WeighingMachineC.V> implements WeighingMachineC.P {

    @Override
    public void getWeighbridgeList(String  projId,int pageIndex, int pageSize, String openingTimeBegin, String openingTimeEnd) {
        new WeighingMachineM()
                .getWeighbridgeList(projId, pageIndex, pageSize,openingTimeBegin,openingTimeEnd)
                .compose(getIView().bindLifecycle())
                .subscribe(new NetObserver<HttpResponse<WeighingMachineJson>>() {
                    @Override
                    public void onSuccess(HttpResponse<WeighingMachineJson> bean) {
                        getIView().onGetWeighbridgeListSuccess(bean.getData());
                    }

                    @Override
                    public void onFail(ApiException msg) {
                        getIView().onGetWeighbridgeListFail(msg);
                    }
                });
    }

    @Override
    public void getWeighbridge(String  recordId) {
        new WeighingMachineM()
                .getWeighbridge(recordId)
                .compose(getIView().bindLifecycle())
                .subscribe(new NetObserver<HttpResponse<WeighingMachineMsg>>() {
                    @Override
                    public void onSuccess(HttpResponse<WeighingMachineMsg> bean) {
                        getIView().onGetWeighbridgeSuccess(bean.getData());
                    }

                    @Override
                    public void onFail(ApiException msg) {
                        getIView().onGetWeighbridgeFail(msg);
                    }
                });
    }

    @Override
    public void getWeighGroupList(String proId) {
        new WeighingMachineM()
                .getWeighGroupList(proId)
                .compose(getIView().bindLifecycle())
                .subscribe(new NetObserver<HttpResponse<WeightGroupJson>>() {
                    @Override
                    public void onSuccess(HttpResponse<WeightGroupJson> bean) {
                        getIView().onGetWeighGroupListSuccess(bean.getData());
                    }

                    @Override
                    public void onFail(ApiException msg) {
                        getIView().onGetWeighGroupListFail(msg);
                    }
                });
    }
}
