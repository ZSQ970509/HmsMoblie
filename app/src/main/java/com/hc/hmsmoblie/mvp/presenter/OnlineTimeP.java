package com.hc.hmsmoblie.mvp.presenter;

import com.hc.hmsmoblie.bean.json.LoginJson;
import com.hc.hmsmoblie.bean.json.OnlineTimeJson;
import com.hc.hmsmoblie.db.UserInfoPref;
import com.hc.hmsmoblie.mvp.contact.LoginC;
import com.hc.hmsmoblie.mvp.contact.OnlineTimeC;
import com.hc.hmsmoblie.mvp.model.LoginM;
import com.hc.hmsmoblie.mvp.model.OnlineTimeM;
import com.hc.hmsmoblie.net.HttpResponse;
import com.hc.hmsmoblie.net.NetObserver;
import com.yc.yclibrary.exception.ApiException;
import com.yc.yclibrary.mvp.BasePresenter;

import java.util.ArrayList;

/**
 *
 */

public class OnlineTimeP extends BasePresenter<OnlineTimeC.V> implements OnlineTimeC.P {
    @Override
    public void GetOnlineRate(String projId, String startDate, String endDate) {
        getIView().showLoading("加载数据中...");
        new OnlineTimeM()
                .GetOnlineRate(projId, startDate, endDate)
                .compose(getIView().bindLifecycle())
                .subscribe(new NetObserver<HttpResponse<ArrayList<OnlineTimeJson>>>() {
                    @Override
                    public void onSuccess(HttpResponse<ArrayList<OnlineTimeJson>> onlineTimeBean) {

                        getIView().hideLoading();
                        getIView().onGetOnlineRateSuccess(onlineTimeBean.getData());
                    }

                    @Override
                    public void onFail(ApiException msg) {
                        getIView().hideLoading();
                        getIView().onGetOnlineRateFail(msg.getMessage());
                    }
                });
    }

}
