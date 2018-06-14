package com.hc.hmsmoblie.mvp.presenter;

import com.hc.hmsmoblie.bean.json.ProjectJson;
import com.hc.hmsmoblie.db.UserInfoPref;
import com.hc.hmsmoblie.mvp.contact.LadderControlProjectListC;
import com.hc.hmsmoblie.mvp.model.LadderControlProjectListM;
import com.hc.hmsmoblie.net.HttpResponse;
import com.hc.hmsmoblie.net.NetObserver;
import com.yc.yclibrary.exception.ApiException;
import com.yc.yclibrary.mvp.BasePresenter;

/**
 *
 */

public class LadderControlProjectListP extends BasePresenter<LadderControlProjectListC.V> implements LadderControlProjectListC.P {

    @Override
    public void getProjectList(String keyword, int pageIndex, int pageSize, String sysId) {
        new LadderControlProjectListM()
                .getCameraList(keyword, pageIndex, pageSize, sysId, UserInfoPref.getUserId())
                .compose(getIView().bindLifecycle())
                .subscribe(new NetObserver<HttpResponse<ProjectJson>>() {
                    @Override
                    public void onSuccess(HttpResponse<ProjectJson> loginBean) {
                        getIView().onGetProjectListSuccess(loginBean.getData());
                    }

                    @Override
                    public void onFail(ApiException msg) {
                        getIView().onGetProjectListFail(msg);
                    }
                });
    }
}
