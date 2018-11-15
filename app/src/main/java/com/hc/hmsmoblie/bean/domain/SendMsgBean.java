package com.hc.hmsmoblie.bean.domain;

/**
 *
 */

public class SendMsgBean {
    private boolean isXYSend ;
    private boolean isReportTimeSend ;
    private boolean isSwitchSend ;
    private String seq ;

    public SendMsgBean(boolean isXYSend, boolean isReportTimeSend, boolean isSwitchSend, String seq) {
        this.isXYSend = isXYSend;
        this.isReportTimeSend = isReportTimeSend;
        this.isSwitchSend = isSwitchSend;
        this.seq = seq;
    }

    public boolean isXYSend() {
        return isXYSend;
    }

    public void setXYSend(boolean XYSend) {
        isXYSend = XYSend;
    }

    public boolean isReportTimeSend() {
        return isReportTimeSend;
    }

    public void setReportTimeSend(boolean reportTimeSend) {
        isReportTimeSend = reportTimeSend;
    }

    public boolean isSwitchSend() {
        return isSwitchSend;
    }

    public void setSwitchSend(boolean switchSend) {
        isSwitchSend = switchSend;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }
}
