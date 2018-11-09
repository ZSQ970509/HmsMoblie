package com.hc.hmsmoblie.bean.domain;

/**
 * 倾角模块告警阈值
 */

public class TiltSensorSettingPostBean {

    private String deviceId;
    private String serviceId;
    private String method;
    private String jsonCommand;
    private int expireTime;

    public TiltSensorSettingPostBean(String deviceId, String serviceId, String method, String jsonCommand, int expireTime) {
        this.deviceId = deviceId;
        this.serviceId = serviceId;
        this.method = method;
        this.jsonCommand = jsonCommand;
        this.expireTime = expireTime;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getJsonCommand() {
        return jsonCommand;
    }

    public void setJsonCommand(String jsonCommand) {
        this.jsonCommand = jsonCommand;
    }

    public int getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(int expireTime) {
        this.expireTime = expireTime;
    }
}
