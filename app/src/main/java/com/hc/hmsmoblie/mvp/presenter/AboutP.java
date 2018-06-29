package com.hc.hmsmoblie.mvp.presenter;

import com.hc.hmsmoblie.bean.json.LoginJson;
import com.hc.hmsmoblie.bean.json.UpdateVersionJson;
import com.hc.hmsmoblie.db.UserInfoPref;
import com.hc.hmsmoblie.mvp.contact.AboutC;
import com.hc.hmsmoblie.mvp.contact.LoginC;
import com.hc.hmsmoblie.mvp.model.AboutM;
import com.hc.hmsmoblie.mvp.model.LoginM;
import com.hc.hmsmoblie.net.HttpResponse;
import com.hc.hmsmoblie.net.NetObserver;
import com.hc.hmsmoblie.utils.PhoneSystemUtils;
import com.yc.yclibrary.exception.ApiException;
import com.yc.yclibrary.mvp.BasePresenter;
import com.yc.yclibrary.net.BaseObserver;

import java.util.ArrayList;

/**
 *
 */

public class AboutP extends BasePresenter<AboutC.V> implements AboutC.P {

    @Override
    public void updatedVersion() {
        getIView().showLoading("正在检测版本中...");
        new AboutM()
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
