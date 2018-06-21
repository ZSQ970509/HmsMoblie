package com.hc.hmsmoblie.mvp.presenter;

import com.hc.hmsmoblie.bean.json.LadderControlDetailsOperationJson;
import com.hc.hmsmoblie.bean.json.LadderControlDetailsOperationJson;
import com.hc.hmsmoblie.mvp.contact.LadderControlDetailsOperationC;
import com.hc.hmsmoblie.mvp.model.LadderControlDetailsOperationM;
import com.hc.hmsmoblie.mvp.model.LadderControlDetailsOperationM;
import com.hc.hmsmoblie.net.HttpResponse;
import com.hc.hmsmoblie.net.NetObserver;
import com.yc.yclibrary.exception.ApiException;
import com.yc.yclibrary.mvp.BasePresenter;

/**
 *
 */

public class LadderControlDetailsOperationP extends BasePresenter<LadderControlDetailsOperationC.V> implements LadderControlDetailsOperationC.P {

    @Override
    public void getDetailsOperation(String devId, int pageIndex, int pageSize) {
        new LadderControlDetailsOperationM()
                .GetTowerCraneListByPaging(devId, pageIndex, pageSize)
                .compose(getIView().bindLifecycle())
                .subscribe(new NetObserver<HttpResponse<LadderControlDetailsOperationJson>>() {
                    @Override
                    public void onSuccess(HttpResponse<LadderControlDetailsOperationJson> loginBean) {
                        getIView().onGetDetailsOperationSuccess(loginBean.getData());
                    }

                    @Override
                    public void onFail(ApiException msg) {
                        getIView().onGetDetailsOperationFail(msg);
                    }
                });
    }
}
