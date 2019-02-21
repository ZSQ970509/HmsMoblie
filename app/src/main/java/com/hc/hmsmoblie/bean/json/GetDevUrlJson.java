package com.hc.hmsmoblie.bean.json;

/**
 *
 */

public class GetDevUrlJson {

    /**
     * rtspUrl : aaa
     */

    private String rtspUrl;

    public String getRtspUrl() {
        return rtspUrl;
    }

    public void setRtspUrl(String rtspUrl) {
        this.rtspUrl = rtspUrl;
    }

    @Override
    public String toString() {
        return "GetDevUrlJson{" +
                "rtspUrl='" + rtspUrl + '\'' +
                '}';
    }
}
