package com.hc.hmsmoblie.mvp.presenter;

import com.hc.hmsmoblie.bean.json.TiltSensorStateJson;
import com.hc.hmsmoblie.mvp.contact.AboutC;
import com.hc.hmsmoblie.mvp.contact.TiltSensorStateC;
import com.hc.hmsmoblie.mvp.model.AboutM;
import com.hc.hmsmoblie.mvp.model.TitleSensorStateM;
import com.hc.hmsmoblie.utils.PhoneSystemUtils;
import com.yc.yclibrary.exception.ApiException;
import com.yc.yclibrary.mvp.BasePresenter;
import com.yc.yclibrary.net.BaseObserver;

/**
 *
 */

public class TiltSensorStateP extends BasePresenter<TiltSensorStateC.V> implements TiltSensorStateC.P {

    @Override
    public void getTiltSensorState(String deviceId) {
        //getIView().showLoading("正在检测版本中...");
        new TitleSensorStateM()
                .getTiltSensorState(deviceId)
                .compose(getIView().bindLifecycle())
                .subscribe(new BaseObserver <TiltSensorStateJson>() {
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
