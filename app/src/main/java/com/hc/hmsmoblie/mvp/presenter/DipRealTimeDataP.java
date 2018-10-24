package com.hc.hmsmoblie.mvp.presenter;

import com.hc.hmsmoblie.bean.json.TiltSensorParaJson;
import com.hc.hmsmoblie.mvp.contact.DipRealTimeDataC;
import com.hc.hmsmoblie.mvp.contact.TiltSensorActivityC;
import com.hc.hmsmoblie.mvp.model.TiltSensorActivityM;
import com.hc.hmsmoblie.net.HttpResponse;
import com.hc.hmsmoblie.net.NetObserver;
import com.yc.yclibrary.exception.ApiException;
import com.yc.yclibrary.mvp.BasePresenter;

/**
 *
 */

public class DipRealTimeDataP extends BasePresenter<DipRealTimeDataC.V> implements DipRealTimeDataC.P{
    @Override
    public void getGetTiltSensorPara(String cmID) {

        new TiltSensorActivityM()
                .getGetTiltSensorPara(cmID)
                .compose(getIView().bindLifecycle())
                .doOnSubscribe(disposable -> getIView().showLoading("正在加载中~"))
                .doFinally(()->getIView().hideLoading())
                .subscribe(new NetObserver<HttpResponse<TiltSensorParaJson>>() {
                    @Override
                    public void onSuccess(HttpResponse<TiltSensorParaJson> tiltSensorParaJson) {
                        getIView().onGetGetTiltSensorParaSuccess(tiltSensorParaJson.getData());
                    }

                    @Override
                    public void onFail(ApiException msg) {
                        getIView().onGetGetTiltSensorParaFail(msg.getMessage());
                    }
                });
    }
}
