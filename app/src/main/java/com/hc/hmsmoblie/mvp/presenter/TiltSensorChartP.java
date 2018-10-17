package com.hc.hmsmoblie.mvp.presenter;

import com.hc.hmsmoblie.bean.json.TiltSensorChartJson;
import com.hc.hmsmoblie.bean.json.TiltSensorChartJsonNew;
import com.hc.hmsmoblie.bean.json.UpdateVersionJson;
import com.hc.hmsmoblie.mvp.contact.TiltSensorChartC;
import com.hc.hmsmoblie.mvp.model.TiltSensorChartM;
import com.hc.hmsmoblie.net.HttpResponse;
import com.hc.hmsmoblie.net.NetObserver;
import com.hc.hmsmoblie.utils.PhoneSystemUtils;
import com.yc.yclibrary.exception.ApiException;
import com.yc.yclibrary.mvp.BasePresenter;
import com.yc.yclibrary.net.BaseObserver;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */

public class TiltSensorChartP extends BasePresenter<TiltSensorChartC.V> implements TiltSensorChartC.P {
    @SuppressWarnings("unchecked")
    @Override
    public void getTiltSensorChart(String camID, String paraID, String timeType, String selDate, int type) {
//        camID = "1053614";
//        paraID = "0"
        new TiltSensorChartM()
                .getTiltSensorChart(camID, paraID, timeType, selDate,type)
                .compose(getIView().bindLifecycle())
                .doOnSubscribe(d -> getIView().showLoading("正在搜索中..."))
                .doFinally(() -> getIView().hideLoading())
                .subscribe(new NetObserver<HttpResponse<List<TiltSensorChartJsonNew>>>() {
                    @Override
                    public void onSuccess(HttpResponse<List<TiltSensorChartJsonNew>> tiltSensorChartJson) {
                        getIView().getTiltSensorChartSuccess(tiltSensorChartJson.getData().get(0));
                    }

                    @Override
                    public void onFail(ApiException msg) {
                        getIView().getTiltSensorChartFail(msg.getMessage());
                    }
                });
    }
}
