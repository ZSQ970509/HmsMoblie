package com.hc.hmsmoblie.mvp.presenter;

import com.hc.hmsmoblie.bean.json.LadderControlDetailsErrorJson;
import com.hc.hmsmoblie.mvp.contact.LadderControlDetailsErrorC;
import com.hc.hmsmoblie.mvp.model.LadderControlDetailsErrorM;
import com.hc.hmsmoblie.net.HttpResponse;
import com.hc.hmsmoblie.net.NetObserver;
import com.yc.yclibrary.exception.ApiException;
import com.yc.yclibrary.mvp.BasePresenter;

/**
 *
 */

public class LadderControlDetailsErrorP extends BasePresenter<LadderControlDetailsErrorC.V> implements LadderControlDetailsErrorC.P {

    @Override
    public void getDetailsError(String proId, int pageIndex, int pageSize) {
        new LadderControlDetailsErrorM()
                .GetErrlog(proId, pageIndex, pageSize)
                .compose(getIView().bindLifecycle())
                .subscribe(new NetObserver<HttpResponse<LadderControlDetailsErrorJson>>() {
                    @Override
                    public void onSuccess(HttpResponse<LadderControlDetailsErrorJson> loginBean) {
                        getIView().onGetDetailsErrorSuccess(loginBean.getData());
                    }

                    @Override
                    public void onFail(ApiException msg) {
                        getIView().onGetDetailsErrorFail(msg);
                    }
                });
    }
}
