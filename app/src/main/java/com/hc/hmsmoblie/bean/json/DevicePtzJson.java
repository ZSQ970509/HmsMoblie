package com.hc.hmsmoblie.bean.json;

/**
 *
 */

public class DevicePtzJson {

    /**
     * msg : 调用成功
     * rtspUrl : rtsp://120.35.11.49:54000/32028200461327644681_01121_1
     * returnCode : 1
     * apiMethod : /device/getDevUrl
     * subCode : 10
     * subMsg : 成功
     */

    private String msg;
    private String rtspUrl;
    private String returnCode;
    private String apiMethod;
    private String subCode;
    private String subMsg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getRtspUrl() {
        return rtspUrl;
    }

    public void setRtspUrl(String rtspUrl) {
        this.rtspUrl = rtspUrl;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getApiMethod() {
        return apiMethod;
    }

    public void setApiMethod(String apiMethod) {
        this.apiMethod = apiMethod;
    }

    public String getSubCode() {
        return subCode;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }

    public String getSubMsg() {
        return subMsg;
    }

    public void setSubMsg(String subMsg) {
        this.subMsg = subMsg;
    }
}
