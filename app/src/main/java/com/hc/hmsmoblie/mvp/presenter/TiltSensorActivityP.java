package com.hc.hmsmoblie.mvp.presenter;

import android.accounts.NetworkErrorException;

import com.hc.hmsmoblie.bean.json.TiltSensorParaJson;
import com.hc.hmsmoblie.mvp.contact.TiltSensorActivityC;
import com.hc.hmsmoblie.mvp.model.TiltSensorActivityM;
import com.hc.hmsmoblie.net.HttpResponse;
import com.hc.hmsmoblie.net.NetObserver;
import com.yc.yclibrary.exception.ApiException;
import com.yc.yclibrary.mvp.BasePresenter;

/**
 *
 */

public class TiltSensorActivityP extends BasePresenter<TiltSensorActivityC.V> implements TiltSensorActivityC.P{
    @Override
    public void getGetTiltSensorPara(String cmID) {
        new TiltSensorActivityM()
                .getGetTiltSensorPara(cmID)
                .compose(getIView().bindLifecycle())
                .subscribe(new NetObserver<HttpResponse<TiltSensorParaJson>>() {
                    @Override
                    public void onSuccess(HttpResponse<TiltSensorParaJson> tiltSensorParaJson) {
                        getIView().hideLoading();
                        getIView().onGetGetTiltSensorParaSuccess(tiltSensorParaJson.getData());
                    }

                    @Override
                    public void onFail(ApiException msg) {
                        getIView().hideLoading();
                        getIView().onGetGetTiltSensorParaFail(msg.getMessage());
                    }
                });
    }
}
