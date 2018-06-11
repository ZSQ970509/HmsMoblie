package com.hc.hmsmoblie.mvp.contact;

import com.hc.hmsmoblie.bean.json.LoginJs;
import com.yc.yclibrary.mvp.IView;

/**
 *
 */

public class LoginC {
    public interface V extends IView{
        void onLoginSuccess(LoginJs  loginJs);

        void onLoginFail(String msg);
    }
    public interface P {
        void login(String userName,String userPassword);
    }
}
