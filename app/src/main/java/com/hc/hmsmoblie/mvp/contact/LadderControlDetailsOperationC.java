package com.hc.hmsmoblie.mvp.contact;

import com.hc.hmsmoblie.bean.json.LadderControlDetailsOperationJson;
import com.yc.yclibrary.exception.ApiException;
import com.yc.yclibrary.mvp.IView;

/**
 *
 */

public class LadderControlDetailsOperationC {
    public interface V extends IView {
        void onGetDetailsOperationSuccess(LadderControlDetailsOperationJson projectJson);

        void onGetDetailsOperationFail(ApiException apiException);
    }

    public interface P {
        void getDetailsOperation(String proId, int pageIndex, int pageSize);
    }
}
