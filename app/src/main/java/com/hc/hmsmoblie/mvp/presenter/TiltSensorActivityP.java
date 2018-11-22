package com.hc.hmsmoblie.mvp.presenter;

import com.hc.hmsmoblie.bean.json.TiltSensorAllJson;
import com.hc.hmsmoblie.bean.json.TiltSensorParaJson;
import com.hc.hmsmoblie.bean.json.TiltSensorStateJson;
import com.hc.hmsmoblie.bean.type.TiltSensorParaState;
import com.hc.hmsmoblie.mvp.contact.TiltSensorActivityC;
import com.hc.hmsmoblie.mvp.model.TiltSensorActivityM;
import com.hc.hmsmoblie.mvp.model.TiltSensorActivityMOld;
import com.hc.hmsmoblie.net.HttpResponse;
import com.hc.hmsmoblie.net.NetObserver;
import com.yc.yclibrary.exception.ApiException;
import com.yc.yclibrary.mvp.BasePresenter;
import com.yc.yclibrary.net.BaseObserver;

/**
 *
 */

public class TiltSensorActivityP extends BasePresenter<TiltSensorActivityC.V> implements TiltSensorActivityC.P {
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
                        getIView().onGetTiltSensorParaSuccess(tiltSensorParaJson.getData());
                    }

                    @Override
                    public void onFail(ApiException msg) {
                        getIView().onGetTiltSensorParaFail(msg.getMessage());
                    }
                });
    }

    public NetObserver<HttpResponse<TiltSensorAllJson>> getTitAll(String paraID, final boolean isShow) {
        NetObserver<HttpResponse<TiltSensorAllJson>> netObserver = new NetObserver<HttpResponse<TiltSensorAllJson>>() {
            @Override
            public void onSuccess(HttpResponse<TiltSensorAllJson> tiltSensorParaJson) {
                getIView().onGetTitAllSuccess(tiltSensorParaJson.getData());
            }

            @Override
            public void onFail(ApiException msg) {
                getIView().onGetTitAllFail(msg.getMessage());
            }
        };
        new TiltSensorActivityM()
                .getTitAll(paraID)
                .compose(getIView().bindLifecycle())
                .doOnSubscribe(disposable -> {
                    if (isShow) getIView().showLoading("正在加载中~");
                })
                .doFinally(() -> getIView().hideLoading())
                .subscribe(netObserver);
        return netObserver;
    }
    public void setTiltSensorState(String deviceId) {
        new TiltSensorActivityMOld()
                .getTiltSensorState(deviceId)
                .compose(getIView().bindLifecycle())
                .doOnSubscribe(disposable -> getIView().showLoading("正在加载中~"))
                .doFinally(() -> getIView().hideLoading())
                .subscribe(new BaseObserver<TiltSensorStateJson>() {
                    @Override
                    public void onSuccess(TiltSensorStateJson tiltSensorStateJson) {
                        getIView().onGetDeviceSetting(tiltSensorStateJson);
                    }

                    @Override
                    public void onFail(ApiException msg) {
                        getIView().showMsg(msg.getMessage());
                    }
                });
    }
    public void getTiltSensorState(String deviceId) {
        new TiltSensorActivityMOld()
                .getTiltSensorState(deviceId)
                .compose(getIView().bindLifecycle())
                .doOnSubscribe(disposable -> getIView().showLoading("正在加载中~"))
                .doFinally(() -> getIView().hideLoading())
                .subscribe(new BaseObserver<TiltSensorStateJson>() {
                    @Override
                    public void onSuccess(TiltSensorStateJson tiltSensorStateJson) {
                        getIView().onGetDeviceState(tiltSensorStateJson);
                    }

                    @Override
                    public void onFail(ApiException msg) {
                        getIView().showMsg(msg.getMessage());
                    }
                });
    }
    public void setAllMessage(String paraID, String seq, String type) {
        new TiltSensorActivityMOld()
                .setAllMessage(paraID, seq, type)
                .compose(getIView().bindLifecycle())
                .doOnSubscribe(disposable -> getIView().showLoading("正在加载中~"))
                .doFinally(() -> getIView().hideLoading())
                .subscribe(new NetObserver<HttpResponse<String>>() {
                    @Override
                    public void onSuccess(HttpResponse<String> json) {
                        if (json == null) {
                            getIView().showMsg("没有数据返回");
                        } else if (json.getData() != null) {
                            if (json.getData().equals(TiltSensorParaState.OPEN)) {
                                getIView().onSetAllMessage(TiltSensorParaState.OPEN);
                            } else {
                                getIView().onSetAllMessage(TiltSensorParaState.CLOSE);
                            }
                        } else {
                            getIView().showMsg(json.getMsg());
                        }
                    }

                    @Override
                    public void onFail(ApiException msg) {
                        getIView().showMsg(msg.getMessage());
                    }
                });
    }
}
