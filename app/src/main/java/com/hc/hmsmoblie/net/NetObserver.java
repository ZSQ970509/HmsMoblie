package com.hc.hmsmoblie.net;

import com.orhanobut.logger.Logger;
import com.yc.yclibrary.exception.ApiException;
import com.yc.yclibrary.exception.ErrorType;
import com.yc.yclibrary.net.BaseObserver;

import io.reactivex.annotations.NonNull;

/**
 *
 */

public abstract class NetObserver<T extends HttpResponse> extends BaseObserver<T> {
    @Override
    public void onNext(@NonNull T response) {
        //防止闪退问题
        try {
            if (response.getCode() == 0) {
                onFail(new ApiException(response.getMsg(), ErrorType.NETWORK_ERROR));
            } else {
                super.onNext(response);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            Logger.e("网络异常！错误码:" + ErrorType.DATE_NULL);
        } catch (ApiException e) {
            e.printStackTrace();
            Logger.e("网络异常！错误码:" + e.getCode() + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            Logger.e("网络异常！错误码:" + ErrorType.UN_KNOWN_ERROR);
        }
    }
}
