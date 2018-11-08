package com.hc.hmsmoblie.mvp.presenter;

import com.hc.hmsmoblie.bean.json.SensorLogJson;
import com.hc.hmsmoblie.bean.json.SetAllMessageJson;
import com.hc.hmsmoblie.bean.json.TiltSensorParaJson;
import com.hc.hmsmoblie.bean.json.TiltSensorStateJson;
import com.hc.hmsmoblie.mvp.contact.DipRealTimeDataC;
import com.hc.hmsmoblie.mvp.contact.TiltSensorActivityC;
import com.hc.hmsmoblie.mvp.model.TiltSensorAbleFragmentM;
import com.hc.hmsmoblie.mvp.model.TiltSensorActivityM;
import com.hc.hmsmoblie.mvp.model.TitleSensorStateM;
import com.hc.hmsmoblie.net.HttpResponse;
import com.hc.hmsmoblie.net.NetObserver;
import com.yc.yclibrary.exception.ApiException;
import com.yc.yclibrary.mvp.BasePresenter;
import com.yc.yclibrary.net.BaseObserver;

/**
 *
 */

public class DipRealTimeDataP extends BasePresenter<DipRealTimeDataC.V> implements DipRealTimeDataC.P {
    @Override
    public void getGetTiltSensorPara(String cmID) {
        new TiltSensorActivityM()
                .getGetTiltSensorPara(cmID)
                .compose(getIView().bindLifecycle())
                .doOnSubscribe(disposable -> getIView().showLoading("正在加载中~"))
                .doFinally(() -> getIView().hideLoading())
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

    @Override
    public void getTiltSensorLog(boolean isShowLoad, String cmID, String paraID, int pageindex, int pagesize, String startTime, String endTime
            , NetObserver<HttpResponse<SensorLogJson>> responseNetObserver) {
        new TiltSensorActivityM()
                .getTiltSensorLog(cmID, paraID, pageindex, pagesize, startTime, endTime)
                .compose(getIView().bindLifecycle())
                .doOnSubscribe(disposable -> {
                    if (isShowLoad)
                        getIView().showLoading("正在加载中~");
                })
                .doFinally(() -> getIView().hideLoading())
                .subscribe(responseNetObserver);
    }

    @Override
    public void setAllMessage(String paraID, String seq, String type) {
        new TiltSensorActivityM()
                .setAllMessage(paraID, seq, type)
                .compose(getIView().bindLifecycle())
                .doOnSubscribe(disposable -> getIView().showLoading("正在加载中~"))
                .doFinally(() -> getIView().hideLoading())
                .subscribe(new NetObserver<HttpResponse<String>>() {
                    @Override
                    public void onSuccess(HttpResponse<String> tiltSensorParaJson) {
                        getIView().onSetAllMessageSuccess(tiltSensorParaJson);
                    }

                    @Override
                    public void onFail(ApiException msg) {
                        getIView().onSetAllMessageFail(msg.getMessage());
                    }
                });

    }

    @Override
    public void getTiltSensorState(String deviceId) {
        //getIView().showLoading("正在检测版本中...");
        new TiltSensorActivityM()
                .getTiltSensorState(deviceId)
                .compose(getIView().bindLifecycle())
                .subscribe(new BaseObserver<TiltSensorStateJson>() {
                    @Override
                    public void onSuccess(TiltSensorStateJson updateVersionJsons) {
                        getIView().hideLoading();
                        getIView().getTiltSensorStateSuccess(updateVersionJsons);
                    }

                    @Override
                    public void onFail(ApiException msg) {
                        getIView().hideLoading();
                        getIView().getTiltSensorStateFail(msg.getMessage());
                    }
                });
    }
}
