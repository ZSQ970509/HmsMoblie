package com.hc.hmsmoblie.mvp.contact;

import com.hc.hmsmoblie.bean.json.ProjectJson;
import com.yc.yclibrary.exception.ApiException;
import com.yc.yclibrary.mvp.IView;

/**
 *
 */

public class LadderControlProjectListC {
    public interface V extends IView {
        void onGetProjectListSuccess(ProjectJson projectJson);

        void onGetProjectListFail(ApiException apiException);
    }

    public interface P {
        void getProjectList(String keyword, int pageIndex, int pageSize, String sysId);
    }
}
