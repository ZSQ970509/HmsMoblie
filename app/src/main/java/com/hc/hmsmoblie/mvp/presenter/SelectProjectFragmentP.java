package com.hc.hmsmoblie.mvp.presenter;

import com.hc.hmsmoblie.bean.json.GetProListByArea;
import com.hc.hmsmoblie.bean.json.ProjectJson;
import com.hc.hmsmoblie.mvp.contact.SelectProjectFragmentC;
import com.hc.hmsmoblie.mvp.contact.SelectProjectFragmentC;
import com.hc.hmsmoblie.mvp.model.SelectProjectFragmentM;
import com.hc.hmsmoblie.net.HttpResponse;
import com.hc.hmsmoblie.net.NetObserver;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.trello.rxlifecycle2.components.support.RxFragment;
import com.yc.yclibrary.exception.ApiException;
import com.yc.yclibrary.mvp.BasePresenter;

/**
 *
 */

public class SelectProjectFragmentP extends BasePresenter<SelectProjectFragmentC.V> implements SelectProjectFragmentC.P {
    @Override
    public void getProListByArea(final boolean isClearData, boolean isShowDialog, String folderId, int folderLevel, int pageIndex, int pageSize, String sysId) {
        if (isShowDialog) getIView().showLoading("加载中...");
        new SelectProjectFragmentM()
                .getProListByArea(folderId, folderLevel, pageIndex, pageSize, sysId)
                .compose(((RxFragment) getIView()).bindUntilEvent(FragmentEvent.DESTROY))
                .doFinally(() -> getIView().hideLoading())
                .subscribe(new NetObserver<HttpResponse<GetProListByArea>>() {
                    @Override
                    public void onSuccess(HttpResponse<GetProListByArea> loginBean) {
                        getIView().hideLoading();
                        getIView().onGetProjectSuccess(loginBean.getData(), isClearData);
                    }

                    @Override
                    public void onFail(ApiException msg) {
                        getIView().hideLoading();
                        getIView().onGetProjectFail(msg.getMessage());
                    }
                });
    }
}
