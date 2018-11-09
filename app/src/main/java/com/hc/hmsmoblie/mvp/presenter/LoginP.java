package com.hc.hmsmoblie.mvp.presenter;

import com.hc.hmsmoblie.bean.json.LoginJson;
import com.hc.hmsmoblie.bean.json.UpdateVersionJson;
import com.hc.hmsmoblie.bean.type.UserTypeId;
import com.hc.hmsmoblie.db.UserInfoPref;
import com.hc.hmsmoblie.mvp.contact.LoginC;
import com.hc.hmsmoblie.mvp.model.LoginM;
import com.hc.hmsmoblie.net.HttpResponse;
import com.hc.hmsmoblie.net.NetObserver;
import com.hc.hmsmoblie.utils.PhoneSystemUtils;
import com.yc.yclibrary.exception.ApiException;
import com.yc.yclibrary.mvp.BasePresenter;
import com.yc.yclibrary.net.BaseObserver;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 *
 */

public class LoginP extends BasePresenter<LoginC.V> implements LoginC.P {
    @Override
    public void login(String userAccount, String userPassword,String userType) {
        getIView().showLoading("正在登陆中...");
        new LoginM()
                .login(userAccount, userPassword,userType)
                .compose(getIView().bindLifecycle())
                .subscribe(new NetObserver<HttpResponse<LoginJson>>() {
                    @Override
                    public void onSuccess(HttpResponse<LoginJson> loginBean) {
                        UserInfoPref.setUserId(loginBean.getData().getUserID());
                        UserInfoPref.setUserAccount(loginBean.getData().getUserAccount());
                        UserInfoPref.setUserName(loginBean.getData().getUserName());
                        UserInfoPref.setUserPassword(userPassword);
                        UserInfoPref.setUserToken(loginBean.getData().getUserHeadSculpture());
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
    @Override
    public void updatedVersion() {
        getIView().showLoading("正在检测版本中...");
        new LoginM()
                .updatedVersion(PhoneSystemUtils.getPackageName())
                .compose(getIView().bindLifecycle())
                .subscribe(new BaseObserver <ArrayList<UpdateVersionJson>>() {
                    @Override
                    public void onSuccess(ArrayList<UpdateVersionJson> updateVersionJsons) {
                        getIView().hideLoading();
                        getIView().onUpdatedVersionSuccess(updateVersionJsons.get(0));
                    }

                    @Override
                    public void onFail(ApiException msg) {
                        getIView().hideLoading();
                        getIView().onUpdatedVersionFail(msg.getMessage());
                    }
                });
    }
}
