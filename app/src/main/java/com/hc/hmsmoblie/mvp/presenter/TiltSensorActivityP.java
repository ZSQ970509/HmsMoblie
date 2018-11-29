package com.hc.hmsmoblie.mvp.presenter;

import com.hc.hmsmoblie.bean.domain.TiltSensorSettingPostBean;
import com.hc.hmsmoblie.bean.json.TiltSensorAllJson;
import com.hc.hmsmoblie.bean.json.TiltSensorParaJson;
import com.hc.hmsmoblie.bean.json.TiltSensorSettingJson;
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

import io.reactivex.Observable;

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
//        Log.e("TAG","getTitAll");
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
                        getIView().onGetDeviceSettingSuccess(tiltSensorStateJson);
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
                        getIView().onGetDeviceStateSuccess(tiltSensorStateJson);
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
                                getIView().onSetAllMessageSuccess(TiltSensorParaState.OPEN);
                            } else {
                                getIView().onSetAllMessageSuccess(TiltSensorParaState.CLOSE);
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

    public void setIotDeviceInfo(TiltSensorSettingPostBean tiltSensorSettingPostBean_1, TiltSensorSettingPostBean tiltSensorSettingPostBean_2
            , TiltSensorSettingPostBean tiltSensorSettingPostBean_3) {
        Observable<TiltSensorSettingJson> setting_XY = new TiltSensorActivityMOld().setIotDeviceInfo(tiltSensorSettingPostBean_1.getDeviceId(),tiltSensorSettingPostBean_1.getServiceId(),
                tiltSensorSettingPostBean_1.getMethod(),tiltSensorSettingPostBean_1.getJsonCommand(),tiltSensorSettingPostBean_1.getExpireTime());
        Observable<TiltSensorSettingJson> setting_ReportTime = new TiltSensorActivityMOld().setIotDeviceInfo(tiltSensorSettingPostBean_2.getDeviceId(),tiltSensorSettingPostBean_2.getServiceId(),
                tiltSensorSettingPostBean_2.getMethod(),tiltSensorSettingPostBean_2.getJsonCommand(),tiltSensorSettingPostBean_2.getExpireTime());
        Observable<TiltSensorSettingJson> setting_Switch = new TiltSensorActivityMOld().setIotDeviceInfo(tiltSensorSettingPostBean_3.getDeviceId(),tiltSensorSettingPostBean_3.getServiceId(),
                tiltSensorSettingPostBean_3.getMethod(),tiltSensorSettingPostBean_3.getJsonCommand(),tiltSensorSettingPostBean_3.getExpireTime());

        Observable.zip(setting_XY, setting_ReportTime, setting_Switch, (tiltSensorSettingJson, tiltSensorSettingJson2, tiltSensorSettingJson3) -> {
            //服务端返回的数据分别是 tiltSensorStateJson, tiltSensorStateJson2, tiltSensorStateJson3
            boolean isXYSend = tiltSensorSettingJson.getStatus() != null && tiltSensorSettingJson.getStatus().equals("PENDING");
            boolean isReportTimeSend = tiltSensorSettingJson2.getStatus() !=null &&tiltSensorSettingJson2.getStatus().equals("PENDING");
            boolean isSwitchSend =  tiltSensorSettingJson3.getStatus() !=null && tiltSensorSettingJson3.getStatus().equals("PENDING");
            getIView().setIotDeviceInfoSuccess(isXYSend,isReportTimeSend,isSwitchSend);
            getIView().hideLoading();
            return "";
        }).subscribe();
    }
    public void setAllIotDeviceInfo(TiltSensorSettingPostBean tiltSensorSettingPostBean_1, TiltSensorSettingPostBean tiltSensorSettingPostBean_2
            , TiltSensorSettingPostBean tiltSensorSettingPostBean_3, String seq) {
        Observable<TiltSensorSettingJson> setting_XY = new TiltSensorActivityMOld().setIotDeviceInfo(tiltSensorSettingPostBean_1.getDeviceId(),tiltSensorSettingPostBean_1.getServiceId(),
                tiltSensorSettingPostBean_1.getMethod(),tiltSensorSettingPostBean_1.getJsonCommand(),tiltSensorSettingPostBean_1.getExpireTime());
        Observable<TiltSensorSettingJson> setting_ReportTime = new TiltSensorActivityMOld().setIotDeviceInfo(tiltSensorSettingPostBean_2.getDeviceId(),tiltSensorSettingPostBean_2.getServiceId(),
                tiltSensorSettingPostBean_2.getMethod(),tiltSensorSettingPostBean_2.getJsonCommand(),tiltSensorSettingPostBean_2.getExpireTime());
        Observable<TiltSensorSettingJson> setting_Switch = new TiltSensorActivityMOld().setIotDeviceInfo(tiltSensorSettingPostBean_3.getDeviceId(),tiltSensorSettingPostBean_3.getServiceId(),
                tiltSensorSettingPostBean_3.getMethod(),tiltSensorSettingPostBean_3.getJsonCommand(),tiltSensorSettingPostBean_3.getExpireTime());

        Observable.zip(setting_XY, setting_ReportTime, setting_Switch, (tiltSensorSettingJson, tiltSensorSettingJson2, tiltSensorSettingJson3) -> {
            //服务端返回的数据分别是 tiltSensorStateJson, tiltSensorStateJson2, tiltSensorStateJson3
            getIView().setAllIotDeviceInfoSuccess(tiltSensorSettingJson,tiltSensorSettingJson2,tiltSensorSettingJson3,seq);
            getIView().hideLoading();
            return "";
        }).subscribe();
    }
}
