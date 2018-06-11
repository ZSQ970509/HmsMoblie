package com.hc.hmsmoblie.mvp.presenter;

import com.hc.hmsmoblie.bean.json.LoginJs;
import com.hc.hmsmoblie.mvp.contact.LoginC;
import com.hc.hmsmoblie.mvp.model.LoginM;
import com.hc.hmsmoblie.net.HttpResponse;
import com.hc.hmsmoblie.net.NetObserver;
import com.yc.yclibrary.exception.ApiException;
import com.yc.yclibrary.mvp.BasePresenter;
import com.yc.yclibrary.net.BaseObserver;

/**
 *
 */

public class LoginP extends BasePresenter<LoginC.V> implements LoginC.P {
    @Override
    public void login(String userName, String userPassword) {
        getIView().showLoading("正在登陆中...");
        new LoginM()
                .login(userName, userPassword)
                .compose(getIView().bindLifecycle())
                .subscribe(new NetObserver<HttpResponse<LoginJs>>() {
                    @Override
                    public void onSuccess(HttpResponse<LoginJs> loginBean) {
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
