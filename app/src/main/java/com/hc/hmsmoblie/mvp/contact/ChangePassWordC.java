package com.hc.hmsmoblie.mvp.contact;

import com.hc.hmsmoblie.bean.json.LoginJson;
import com.hc.hmsmoblie.net.HttpResponse;
import com.yc.yclibrary.mvp.IView;

/**
 *
 */

public class ChangePassWordC {
    public interface V extends IView{
        void onChangeHmsPassWordSuccess(HttpResponse loginJs);

        void onChangeHmsPassWordFail(String msg);
    }
    public interface P {
        void changeHmsPassWord(String userAccount, String UserPwd, String newPwd);
    }
}
