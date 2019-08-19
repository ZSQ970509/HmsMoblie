package com.hc.hmsmoblie.mvp.contact;

import com.hc.hmsmoblie.bean.json.ProjectJson;
import com.yc.yclibrary.mvp.IView;

/**
 *
 */

public class SearchProjectActivityC {
    public interface V extends IView{
        void onSearchProjectSuccess(final boolean isClear,ProjectJson dataBean);
        void onSearchProjectFail(String msg);
    }
    public interface P {
        void searchProject(final boolean isClear, final boolean isShowDialog, String keyword, int pageindex, int pagesize, String sysId);
    }
}
