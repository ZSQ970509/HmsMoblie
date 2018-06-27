package com.hc.hmsmoblie.mvp.presenter;

import com.hc.hmsmoblie.bean.json.LoginJson;
import com.hc.hmsmoblie.db.UserInfoPref;
import com.hc.hmsmoblie.mvp.contact.ChangePassWordC;
import com.hc.hmsmoblie.mvp.contact.LoginC;
import com.hc.hmsmoblie.mvp.model.ChangePassWordM;
import com.hc.hmsmoblie.mvp.model.LoginM;
import com.hc.hmsmoblie.net.HttpResponse;
import com.hc.hmsmoblie.net.NetObserver;
import com.yc.yclibrary.exception.ApiException;
import com.yc.yclibrary.mvp.BasePresenter;

/**
 *
 */

public class ChangePassWordP extends BasePresenter<ChangePassWordC.V> implements ChangePassWordC.P {
    @Override
    public void changeHmsPassWord(String userAccount, String UserPwd, String newPwd) {
        getIView().showLoading("正在修改中...");
        new ChangePassWordM()
                .changeHmsPassWord(userAccount, UserPwd,newPwd)
                .compose(getIView().bindLifecycle())
                .subscribe(new NetObserver<HttpResponse>() {
                    @Override
                    public void onSuccess(HttpResponse loginBean) {

                        getIView().hideLoading();
                        getIView().onChangeHmsPassWordSuccess(loginBean);
                    }

                    @Override
                    public void onFail(ApiException msg) {
                        getIView().hideLoading();
                        getIView().onChangeHmsPassWordFail(msg.getMessage());
                    }
                });
    }


}
