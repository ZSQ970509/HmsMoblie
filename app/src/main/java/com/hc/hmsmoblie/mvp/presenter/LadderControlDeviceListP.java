package com.hc.hmsmoblie.mvp.presenter;

import com.hc.hmsmoblie.bean.json.LadderControlDeviceListJson;
import com.hc.hmsmoblie.bean.json.ProjectJson;
import com.hc.hmsmoblie.db.UserInfoPref;
import com.hc.hmsmoblie.mvp.contact.LadderControlDeviceListC;
import com.hc.hmsmoblie.mvp.model.LadderControlDeviceListM;
import com.hc.hmsmoblie.net.HttpResponse;
import com.hc.hmsmoblie.net.NetObserver;
import com.yc.yclibrary.exception.ApiException;
import com.yc.yclibrary.mvp.BasePresenter;

/**
 *
 */

public class LadderControlDeviceListP extends BasePresenter<LadderControlDeviceListC.V> implements LadderControlDeviceListC.P {

    @Override
    public void getDeviceList(String keyword, int pageIndex, int pageSize,String proId) {
        new LadderControlDeviceListM()
                .getTowerCraneDevList(keyword, pageIndex, pageSize,proId)
                .compose(getIView().bindLifecycle())
                .subscribe(new NetObserver<HttpResponse<LadderControlDeviceListJson>>() {
                    @Override
                    public void onSuccess(HttpResponse<LadderControlDeviceListJson> loginBean) {
                        getIView().onGetDeviceListSuccess(loginBean.getData());
                    }

                    @Override
                    public void onFail(ApiException msg) {
                        getIView().onGetDeviceListFail(msg);
                    }
                });
    }
}
