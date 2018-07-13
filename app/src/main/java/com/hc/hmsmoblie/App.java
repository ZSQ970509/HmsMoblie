package com.hc.hmsmoblie;

import android.app.Application;

import com.hc.hmsmoblie.net.UrlHelper;
import com.hik.mcrsdk.MCRSDK;
import com.hik.mcrsdk.rtsp.RtspClient;
import com.hikvision.sdk.VMSNetSDK;
import com.yc.yclibrary.EasyCode;

import org.xutils.x;

/**
 *
 */

public class App extends Application {
    private static App mApp;

    public static App getInstance() {
        return mApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        EasyCode.init(this, UrlHelper.BASE_URL);
        mApp = this;
        //海康8700初始化
        MCRSDK.init();
//        // 初始化RTSP
        RtspClient.initLib();
        MCRSDK.setPrint(1, null);
        // SDK初始化
        VMSNetSDK.init(this);
        x.Ext.init(this);//Xutils初始化
    }
}
