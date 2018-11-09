package com.hc.hmsmoblie.bean.domain;

/**
 * 倾角模块告警阈值
 */

public class TiltSensorSettingBean {
    private double axisX ;//X轴的倾角差阈值
    private double axisY;//Y轴的倾角差阈值
    private int rptPer;//数据上报时长
    private int rptPer_warn;//告警上报时长
    private int devState ;//传感器当前状态
    private int cacheTime;//缓存上报时间

    public TiltSensorSettingBean(double axisX, double axisY, int rptPer, int rptPer_warn, int devState, int cacheTime) {
        this.axisX = axisX;
        this.axisY = axisY;
        this.rptPer = rptPer;
        this.rptPer_warn = rptPer_warn;
        this.devState = devState;
        this.cacheTime = cacheTime;
    }

    public TiltSensorSettingBean(TiltSensorSettingBean alarmData) {
        this.axisX = alarmData.getAxisX();
        this.axisY = alarmData.getAxisY();
        this.rptPer = alarmData.getRptPer();
        this.rptPer_warn = alarmData.getRptPer_warn();
        this.devState = alarmData.getDevState();
        this.cacheTime = alarmData.getCacheTime();
    }

    public TiltSensorSettingBean() {

    }

    public double getAxisX() {
        return axisX;
    }

    public void setAxisX(double axisX) {
        this.axisX = axisX;
    }

    public double getAxisY() {
        return axisY;
    }

    public void setAxisY(double axisY) {
        this.axisY = axisY;
    }

    public int getRptPer() {
        return rptPer;
    }

    public void setRptPer(int rptPer) {
        this.rptPer = rptPer;
    }

    public int getRptPer_warn() {
        return rptPer_warn;
    }

    public void setRptPer_warn(int rptPer_warn) {
        this.rptPer_warn = rptPer_warn;
    }

    public int getDevState() {
        return devState;
    }

    public void setDevState(int devState) {
        this.devState = devState;
    }

    public int getCacheTime() {
        return cacheTime;
    }

    public void setCacheTime(int cacheTime) {
        this.cacheTime = cacheTime;
    }
}
