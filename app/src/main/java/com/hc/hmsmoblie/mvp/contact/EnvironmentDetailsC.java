package com.hc.hmsmoblie.mvp.contact;

import com.hc.hmsmoblie.bean.json.EnvironmentDetailsJson;
import com.hc.hmsmoblie.bean.json.LadderControlDetailsErrorJson;
import com.hc.hmsmoblie.bean.type.EnvironmentParaTypeEnum;
import com.yc.yclibrary.exception.ApiException;
import com.yc.yclibrary.mvp.IView;

/**
 *
 */

public class EnvironmentDetailsC {
    public interface V extends IView {
        void onGetParaSuccess(EnvironmentDetailsJson projectJson,EnvironmentParaTypeEnum paraTypeEnum);

        void onGetParaFail(ApiException apiException);
    }

    public interface P {
        void getPara(String _camId, String seqId, String date, String projId, EnvironmentParaTypeEnum paraTypeEnum, String timeType);
    }
}
