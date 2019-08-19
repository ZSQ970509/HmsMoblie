package com.hc.hmsmoblie.mvp.contact;

import com.hc.hmsmoblie.bean.json.GetProListByArea;
import com.hc.hmsmoblie.bean.json.ProjectJson;
import com.yc.yclibrary.mvp.IView;

/**
 *
 */

public class SelectProjectFragmentC {
    public interface V extends IView {
        void onGetProjectSuccess(GetProListByArea dataBean, boolean isClearData);

        void onGetProjectFail(String msg);
    }

    public interface P {
        void getProListByArea(boolean isClearData, boolean isShowDialog, String folderId, int folderLevel, int pageindex, int pagesize, String sysId);
    }
}
