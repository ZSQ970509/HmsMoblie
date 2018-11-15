package com.hc.hmsmoblie.mvp.presenter;

import android.util.Log;

import com.hc.hmsmoblie.bean.domain.TiltSensorSettingPostBean;
import com.hc.hmsmoblie.bean.json.SensorLogJson;
import com.hc.hmsmoblie.bean.json.SetAllMessageJson;
import com.hc.hmsmoblie.bean.json.TiltSensorParaJson;
import com.hc.hmsmoblie.bean.json.TiltSensorSettingJson;
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

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Function3;

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
    public void getTiltSensorState(String deviceId, boolean isSetting) {
        getIView().showLoading("正在加载数据中...");
        new TiltSensorActivityM()
                .getTiltSensorState(deviceId)
                .compose(getIView().bindLifecycle())
                .subscribe(new BaseObserver<TiltSensorStateJson>() {
                    @Override
                    public void onSuccess(TiltSensorStateJson updateVersionJsons) {
                        getIView().hideLoading();
                        if (!isSetting) {
                            getIView().getTiltSensorStateSuccess(updateVersionJsons);
                        } else {
                            getIView().getTiltSensorStateSettingSuccess(updateVersionJsons);
                        }

                    }

                    @Override
                    public void onFail(ApiException msg) {
                        getIView().hideLoading();
                        getIView().getTiltSensorStateFail(msg.getMessage());
                    }
                });
    }

    public void setIotDeviceInfo(TiltSensorSettingPostBean tiltSensorSettingPostBean_1, TiltSensorSettingPostBean tiltSensorSettingPostBean_2
            , TiltSensorSettingPostBean tiltSensorSettingPostBean_3) {
        Observable<TiltSensorSettingJson> setting_XY = new TiltSensorActivityM().setIotDeviceInfo(tiltSensorSettingPostBean_1.getDeviceId(),tiltSensorSettingPostBean_1.getServiceId(),
                tiltSensorSettingPostBean_1.getMethod(),tiltSensorSettingPostBean_1.getJsonCommand(),tiltSensorSettingPostBean_1.getExpireTime());
        Observable<TiltSensorSettingJson> setting_ReportTime = new TiltSensorActivityM().setIotDeviceInfo(tiltSensorSettingPostBean_2.getDeviceId(),tiltSensorSettingPostBean_2.getServiceId(),
                tiltSensorSettingPostBean_2.getMethod(),tiltSensorSettingPostBean_2.getJsonCommand(),tiltSensorSettingPostBean_2.getExpireTime());
        Observable<TiltSensorSettingJson> setting_Switch = new TiltSensorActivityM().setIotDeviceInfo(tiltSensorSettingPostBean_3.getDeviceId(),tiltSensorSettingPostBean_3.getServiceId(),
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
        Observable<TiltSensorSettingJson> setting_XY = new TiltSensorActivityM().setIotDeviceInfo(tiltSensorSettingPostBean_1.getDeviceId(),tiltSensorSettingPostBean_1.getServiceId(),
                tiltSensorSettingPostBean_1.getMethod(),tiltSensorSettingPostBean_1.getJsonCommand(),tiltSensorSettingPostBean_1.getExpireTime());
        Observable<TiltSensorSettingJson> setting_ReportTime = new TiltSensorActivityM().setIotDeviceInfo(tiltSensorSettingPostBean_2.getDeviceId(),tiltSensorSettingPostBean_2.getServiceId(),
                tiltSensorSettingPostBean_2.getMethod(),tiltSensorSettingPostBean_2.getJsonCommand(),tiltSensorSettingPostBean_2.getExpireTime());
        Observable<TiltSensorSettingJson> setting_Switch = new TiltSensorActivityM().setIotDeviceInfo(tiltSensorSettingPostBean_3.getDeviceId(),tiltSensorSettingPostBean_3.getServiceId(),
                tiltSensorSettingPostBean_3.getMethod(),tiltSensorSettingPostBean_3.getJsonCommand(),tiltSensorSettingPostBean_3.getExpireTime());

        Observable.zip(setting_XY, setting_ReportTime, setting_Switch, (tiltSensorSettingJson, tiltSensorSettingJson2, tiltSensorSettingJson3) -> {
            //服务端返回的数据分别是 tiltSensorStateJson, tiltSensorStateJson2, tiltSensorStateJson3
            getIView().setAllIotDeviceInfoSuccess(tiltSensorSettingJson,tiltSensorSettingJson2,tiltSensorSettingJson3,seq);
            getIView().hideLoading();
            return "";
        }).subscribe();
    }
}
