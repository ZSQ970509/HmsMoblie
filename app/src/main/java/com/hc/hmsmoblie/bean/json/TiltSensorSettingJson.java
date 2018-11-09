package com.hc.hmsmoblie.bean.json;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/6/28.
 */

public class TiltSensorSettingJson implements Serializable{

    /**
     * commandId : 6777f7ebe19f4931a96422369dd123a8
     * appId : TrvudyubxJ8HsaeJX9aWoPzC7aka
     * deviceId : 87d152f1-3380-4a49-b258-0d8f63d74429
     * command : {"serviceId":"Setting","method":"SET_SLOPE_THRES","paras":{"Slope_Thres_x":1500,"Slope_Thres_y":1500}}
     * callbackUrl : http://120.35.11.49:28888/na/iocm/devNotify/v1.1.0/reportCmdExecResult
     * expireTime : 600
     * status : PENDING
     * result : null
     * creationTime : 20181109T023602Z
     * executeTime : null
     * platformIssuedTime : null
     * deliveredTime : null
     * issuedTimes : 0
     */

    private String commandId;
    private String appId;
    private String deviceId;
    private CommandBean command;
    private String callbackUrl;
    private int expireTime;
    private String status;
    private Object result;
    private String creationTime;
    private Object executeTime;
    private Object platformIssuedTime;
    private Object deliveredTime;
    private int issuedTimes;

    public String getCommandId() {
        return commandId;
    }

    public void setCommandId(String commandId) {
        this.commandId = commandId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public CommandBean getCommand() {
        return command;
    }

    public void setCommand(CommandBean command) {
        this.command = command;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    public int getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(int expireTime) {
        this.expireTime = expireTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public Object getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(Object executeTime) {
        this.executeTime = executeTime;
    }

    public Object getPlatformIssuedTime() {
        return platformIssuedTime;
    }

    public void setPlatformIssuedTime(Object platformIssuedTime) {
        this.platformIssuedTime = platformIssuedTime;
    }

    public Object getDeliveredTime() {
        return deliveredTime;
    }

    public void setDeliveredTime(Object deliveredTime) {
        this.deliveredTime = deliveredTime;
    }

    public int getIssuedTimes() {
        return issuedTimes;
    }

    public void setIssuedTimes(int issuedTimes) {
        this.issuedTimes = issuedTimes;
    }

    public static class CommandBean {
        /**
         * serviceId : Setting
         * method : SET_SLOPE_THRES
         * paras : {"Slope_Thres_x":1500,"Slope_Thres_y":1500}
         */

        private String serviceId;
        private String method;
        private ParasBean paras;

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

        public ParasBean getParas() {
            return paras;
        }

        public void setParas(ParasBean paras) {
            this.paras = paras;
        }

        public static class ParasBean {
            /**
             * Slope_Thres_x : 1500
             * Slope_Thres_y : 1500
             */

            private int Slope_Thres_x;
            private int Slope_Thres_y;

            public int getSlope_Thres_x() {
                return Slope_Thres_x;
            }

            public void setSlope_Thres_x(int Slope_Thres_x) {
                this.Slope_Thres_x = Slope_Thres_x;
            }

            public int getSlope_Thres_y() {
                return Slope_Thres_y;
            }

            public void setSlope_Thres_y(int Slope_Thres_y) {
                this.Slope_Thres_y = Slope_Thres_y;
            }
        }
    }
}
