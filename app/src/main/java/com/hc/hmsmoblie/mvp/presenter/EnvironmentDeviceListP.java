package com.hc.hmsmoblie.mvp.presenter;

import com.hc.hmsmoblie.bean.json.EnvironmentDeviceListJson;
import com.hc.hmsmoblie.mvp.contact.EnvironmentDeviceListC;
import com.hc.hmsmoblie.mvp.model.EnvironmentDeviceListM;
import com.hc.hmsmoblie.mvp.model.EnvironmentDeviceListM;
import com.hc.hmsmoblie.net.HttpResponse;
import com.hc.hmsmoblie.net.NetObserver;
import com.yc.yclibrary.exception.ApiException;
import com.yc.yclibrary.mvp.BasePresenter;

import java.util.List;

/**
 *
 */

public class EnvironmentDeviceListP extends BasePresenter<EnvironmentDeviceListC.V> implements EnvironmentDeviceListC.P {

    @Override
    public void getEnvironmentDeviceList(String projId) {
        new EnvironmentDeviceListM()
                .getEnvironmentDeviceList(projId)
                .compose(getIView().bindLifecycle())
                .subscribe(new NetObserver<HttpResponse<List<EnvironmentDeviceListJson>>>() {
                    @Override
                    public void onSuccess(HttpResponse<List<EnvironmentDeviceListJson>> loginBean) {
                        getIView().onEnvironmentDeviceListSuccess(loginBean.getData());
                    }

                    @Override
                    public void onFail(ApiException msg) {
                        getIView().onEnvironmentDeviceListFail(msg);
                    }
                });
    }
}
