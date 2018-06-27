package com.hc.hmsmoblie.mvp.presenter;

import com.hc.hmsmoblie.bean.json.LoginJson;
import com.hc.hmsmoblie.bean.json.MainJson;
import com.hc.hmsmoblie.db.UserInfoPref;
import com.hc.hmsmoblie.mvp.contact.LoginC;
import com.hc.hmsmoblie.mvp.contact.MainC;
import com.hc.hmsmoblie.mvp.model.LoginM;
import com.hc.hmsmoblie.mvp.model.MainM;
import com.hc.hmsmoblie.net.HttpResponse;
import com.hc.hmsmoblie.net.NetObserver;
import com.yc.yclibrary.exception.ApiException;
import com.yc.yclibrary.mvp.BasePresenter;

import java.util.ArrayList;

/**
 *
 */

public class MainP extends BasePresenter<MainC.V> implements MainC.P {
    @Override
    public void GetModulesList(String userAccount, String userID) {
        getIView().showLoading("初始化中，请稍后...");
        new MainM()
                .GetModulesList(userAccount, userID)
                .compose(getIView().bindLifecycle())
                .subscribe(new NetObserver<HttpResponse<ArrayList<MainJson>>>() {
                    @Override
                    public void onSuccess(HttpResponse<ArrayList<MainJson>> mainBean) {
                        getIView().hideLoading();
                        getIView().onGetModulesListSuccess(mainBean.getData());
                    }

                    @Override
                    public void onFail(ApiException msg) {
                        getIView().hideLoading();
                        getIView().onGetModulesListFail(msg.getMessage());
                    }
                });
    }

}
