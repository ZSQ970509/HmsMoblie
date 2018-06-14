package com.hc.hmsmoblie.mvp.presenter;

import com.hc.hmsmoblie.bean.json.LoginJson;
import com.hc.hmsmoblie.db.UserInfoPref;
import com.hc.hmsmoblie.mvp.contact.LoginC;
import com.hc.hmsmoblie.mvp.model.LoginM;
import com.hc.hmsmoblie.net.HttpResponse;
import com.hc.hmsmoblie.net.NetObserver;
import com.yc.yclibrary.exception.ApiException;
import com.yc.yclibrary.mvp.BasePresenter;

/**
 *
 */

public class LoginP extends BasePresenter<LoginC.V> implements LoginC.P {
    @Override
    public void login(String userAccount, String userPassword) {
        getIView().showLoading("正在登陆中...");
        new LoginM()
                .login(userAccount, userPassword)
                .compose(getIView().bindLifecycle())
                .subscribe(new NetObserver<HttpResponse<LoginJson>>() {
                    @Override
                    public void onSuccess(HttpResponse<LoginJson> loginBean) {
                        UserInfoPref.setUserId(loginBean.getData().getUserID());
                        UserInfoPref.setUserAccount(userAccount);
                        UserInfoPref.setUserName(loginBean.getData().getUserName());
                        UserInfoPref.setUserPassword(userPassword);
                        getIView().hideLoading();
                        getIView().onLoginSuccess(loginBean.getData());
                    }

                    @Override
                    public void onFail(ApiException msg) {
                        getIView().hideLoading();
                        getIView().onLoginFail(msg.getMessage());
                    }
                });
    }

}
