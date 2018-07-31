package com.hc.hmsmoblie.utils;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Looper;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;

import com.hc.hmsmoblie.App;
import com.hc.hmsmoblie.base.BaseActivity;
import com.hc.hmsmoblie.base.BaseMvpActivity;
import com.hc.hmsmoblie.widget.CommonDialog;
import com.hikvision.sdk.VMSNetSDK;
import com.hikvision.sdk.consts.HttpConstants;
import com.hikvision.sdk.net.business.OnVMSNetSDKBusiness;
import com.yc.yclibrary.utils.ActivityUtils;

/**
 * Created by Administrator on 2018/6/21.
 */

public class HKUtil {
    /**
     * 获取登录设备mac地址
     *
     * @return Mac地址
     */
    public static String getMacAddress() {
        WifiManager wm = (WifiManager) App.getInstance().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo connectionInfo = wm.getConnectionInfo();
        String mac = connectionInfo.getMacAddress();
        return mac == null ? "" : mac;
    }

    public static void login(String ip, String port, String account, String pass, OnVMSNetSDKBusiness business) {
        String loginAddress = HttpConstants.HTTPS + ip + ":443";
       //String loginAddress = HttpConstants.HTTPS + ip + ":" + port;
        String userName = account;
        String password = pass;
        String macAddress = getMacAddress();
        VMSNetSDK.getInstance().Login(loginAddress, userName, password, macAddress, business);
    }

    public static void start(final String mSysCode, final SurfaceView mSurfaceView, final int mStreamType, final BaseMvpActivity context) {
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                VMSNetSDK.getInstance().startLiveOpt(mSysCode, mSurfaceView, mStreamType, new OnVMSNetSDKBusiness() {
                    @Override
                    public void onFailure() {
                        Log.e("8700", "播放失败");
                        if(context!= ActivityUtils.INSTANCE.getCurrentActivity())
                            return ;
                        CommonDialog.newInstanceSingle(context)
                                .setTitle("播放提示")
                                .setMsg("播放失败")
                                .setSingleBtnText("确定")
                                .setSingleClick(v -> context.finish())
                                .show();
//                            mHandler.sendEmptyMessage(GET_CAMERA_INFO_FAILURE);
                    }

                    @Override
                    public void onSuccess(Object obj) {

                        Log.e("8700", "播放成功");
//                            mHandler.sendEmptyMessage(GET_CAMERA_INFO_SUCCESS);
                    }
                });
                Looper.loop();
            }
        }.start();
    }
}
