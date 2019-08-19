package com.hc.hmsmoblie.mvp.presenter;

import com.hc.hmsmoblie.bean.json.ProjectJson;
import com.hc.hmsmoblie.mvp.contact.SearchProjectActivityC;
import com.hc.hmsmoblie.mvp.contact.VideoSelectProjectFragmentC;
import com.hc.hmsmoblie.mvp.model.SearchProjectActivityM;
import com.hc.hmsmoblie.mvp.model.VideoSelectProjectFragmentM;
import com.hc.hmsmoblie.net.HttpResponse;
import com.hc.hmsmoblie.net.NetObserver;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.trello.rxlifecycle2.components.RxActivity;
import com.trello.rxlifecycle2.components.support.RxFragment;
import com.yc.yclibrary.exception.ApiException;
import com.yc.yclibrary.mvp.BasePresenter;

/**
 *
 */

public class SearchProjectActivityP extends BasePresenter<SearchProjectActivityC.V> implements SearchProjectActivityC.P{
    @Override
    public void searchProject(final boolean isClear, final boolean isShowDialog, String keyword, int pageindex, int pagesize, String sysId) {
        if(isShowDialog)getIView().showLoading("加载中...");
        new SearchProjectActivityM()
                .getCameraList(keyword,pageindex,pagesize,sysId)
                .compose(getIView().bindLifecycle())
                .doFinally(()->getIView().hideLoading())
                .subscribe(new NetObserver<HttpResponse<ProjectJson>>() {
                    @Override
                    public void onSuccess(HttpResponse<ProjectJson> loginBean) {
                        getIView().onSearchProjectSuccess(isClear,loginBean.getData());
                    }

                    @Override
                    public void onFail(ApiException msg) {
                        getIView().onSearchProjectFail(msg.getMessage());
                    }
                });
    }
}
