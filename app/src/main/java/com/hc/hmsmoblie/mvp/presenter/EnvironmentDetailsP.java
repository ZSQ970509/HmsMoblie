package com.hc.hmsmoblie.mvp.presenter;

import com.hc.hmsmoblie.bean.json.EnvironmentDetailsJson;
import com.hc.hmsmoblie.bean.type.EnvironmentParaTypeEnum;
import com.hc.hmsmoblie.mvp.contact.EnvironmentDetailsC;
import com.hc.hmsmoblie.mvp.model.EnvironmentDetailsM;
import com.hc.hmsmoblie.net.HttpResponse;
import com.hc.hmsmoblie.net.NetObserver;
import com.yc.yclibrary.exception.ApiException;
import com.yc.yclibrary.mvp.BasePresenter;

/**
 *
 */

public class EnvironmentDetailsP extends BasePresenter<EnvironmentDetailsC.V> implements EnvironmentDetailsC.P {

    @Override
    public void getPara(String _camId, String seqId, String date, String projId, EnvironmentParaTypeEnum paraTypeEnum, String timeType) {
        getIView().showLoading("加载数据中...");
        new EnvironmentDetailsM()
                .getPara(_camId, seqId, date, projId, paraTypeEnum.getType(), timeType)
                .compose(getIView().bindLifecycle())
                .subscribe(new NetObserver<HttpResponse<EnvironmentDetailsJson>>() {
                    @Override
                    public void onSuccess(HttpResponse<EnvironmentDetailsJson> loginBean) {
                        getIView().hideLoading();
                        getIView().onGetParaSuccess(loginBean.getData(), paraTypeEnum);
                    }

                    @Override
                    public void onFail(ApiException msg) {
                        getIView().hideLoading();
                        getIView().onGetParaFail(msg);
                    }
                });
    }
}
