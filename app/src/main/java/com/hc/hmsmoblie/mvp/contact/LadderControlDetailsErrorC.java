package com.hc.hmsmoblie.mvp.contact;

import com.hc.hmsmoblie.bean.json.LadderControlDetailsErrorJson;
import com.yc.yclibrary.exception.ApiException;
import com.yc.yclibrary.mvp.IView;

/**
 *
 */

public class LadderControlDetailsErrorC {
    public interface V extends IView {
        void onGetDetailsErrorSuccess(LadderControlDetailsErrorJson projectJson);

        void onGetDetailsErrorFail(ApiException apiException);
    }

    public interface P {
        void getDetailsError(String proId, int pageIndex, int pageSize);
    }
}
