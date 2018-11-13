package com.hc.hmsmoblie.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.functions.Consumer;

/**
 * 权限帮助类
 */

public class PermissionsUtils {
    /**
     * 蓝牙模块需要的权限
     */
    public static final String[] PERMISSION_BLUETOOTH = {
            Manifest.permission.READ_PHONE_STATE,//读写 sd card 权限非常重要
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.BLUETOOTH_ADMIN,//read phone state用于获取 imei 设备信息
            Manifest.permission.WAKE_LOCK,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.INTERNET,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.CHANGE_WIFI_STATE,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.RECEIVE_BOOT_COMPLETED,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.VIBRATE,
            Manifest.permission.GET_TASKS
    };
    /**
     * 申请权限(多个申请结果会合并成一个，即PermissionCall只会被调用一次)
     *
     * @param activity    activity
     * @param permissions 权限名
     */
    public static void requestPermissions(Activity activity, String[] permissions, PermissionCall call) {
        RxPermissions rxPermission = new RxPermissions(activity);
        rxPermission.request(permissions)
                .subscribe(aBoolean -> {
                    if (aBoolean) {
                        call.onSuccess();
                    } else {
                        call.onFail();
                    }
                });

    }

    /**
     * 权限回调，用于处理申请成功和失败的回调
     */
    public interface PermissionCall {
        /**
         * 请求权限成功
         */
        void onSuccess();

        /**
         * 请求权限失败
         */
        void onFail();
    }
}
