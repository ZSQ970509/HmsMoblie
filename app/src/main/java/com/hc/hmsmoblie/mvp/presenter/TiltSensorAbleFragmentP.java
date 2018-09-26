package com.hc.hmsmoblie.mvp.presenter;

import com.hc.hmsmoblie.bean.json.ProjectJson;
import com.hc.hmsmoblie.bean.json.SensorLogJson;
import com.hc.hmsmoblie.bean.json.TiltSensorParaJson;
import com.hc.hmsmoblie.mvp.contact.TiltSensorAbleFragmentC;
import com.hc.hmsmoblie.mvp.contact.VideoSelectProjectFragmentC;
import com.hc.hmsmoblie.mvp.model.TiltSensorAbleFragmentM;
import com.hc.hmsmoblie.mvp.model.VideoSelectProjectFragmentM;
import com.hc.hmsmoblie.net.HttpResponse;
import com.hc.hmsmoblie.net.NetObserver;
import com.yc.yclibrary.exception.ApiException;
import com.yc.yclibrary.mvp.BasePresenter;

import java.util.List;

/**
 *
 */

public class TiltSensorAbleFragmentP extends BasePresenter<TiltSensorAbleFragmentC.V> implements TiltSensorAbleFragmentC.P{
    @Override
    public void getTiltSensorLog(String cmID, String paraID, int pageindex, int pagesize, String startTime, String endTime) {

        new TiltSensorAbleFragmentM()
                .getTiltSensorLog(cmID, paraID,  pageindex, pagesize, startTime, endTime)
                .compose(getIView().bindLifecycle())
                .subscribe(new NetObserver<HttpResponse<SensorLogJson>>() {
                    @Override
                    public void onSuccess(HttpResponse<SensorLogJson> sensorLogJson) {
                        getIView().hideLoading();
                        getIView().onGetTiltSensorLogSuccess(sensorLogJson.getData());
                    }

                    @Override
                    public void onFail(ApiException msg) {
                        getIView().hideLoading();
                        getIView().onGetTiltSensorLogFail(msg.getMessage());
                    }
                });
    }
}
