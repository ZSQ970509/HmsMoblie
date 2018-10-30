package com.hc.hmsmoblie.bean.domain;

/**
 * 倾角模块告警阈值
 */

public class TiltSensorAlarmBean {
    private double axisX = 1;//累计X轴阈值
    private double axisY = 1;//累计Y轴阈值
    private double settlement = 10;//累计沉降位移阈值
    private double space = 10;//累计空间位移阈值
    private double horizontalFloatingLeft = 10;//累计左端水平度浮动阈值
    private double horizontalFloatingRight = 10;//累计右端水平度浮动阈值
    private boolean isOpen = false;//是否开启
    private boolean isAlreadySet = false;//是否设置过

    public TiltSensorAlarmBean() {
    }

    public TiltSensorAlarmBean(TiltSensorAlarmBean bean) {
        this.axisX = bean.axisX;
        this.axisY = bean.axisY;
        this.settlement = bean.settlement;
        this.space = bean.space;
        this.horizontalFloatingLeft = bean.horizontalFloatingLeft;
        this.horizontalFloatingRight = bean.horizontalFloatingRight;
        this.isOpen = bean.isOpen;
    }

    public double getSpace() {
        return space;
    }

    public void setSpace(double space) {
        this.space = space;
    }

    public boolean isAlreadySet() {
        return isAlreadySet;
    }

    public void setAlreadySet(boolean alreadySet) {
        isAlreadySet = alreadySet;
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

    public double getSettlement() {
        return settlement;
    }

    public void setSettlement(double settlement) {
        this.settlement = settlement;
    }

    public double getHorizontalFloatingLeft() {
        return horizontalFloatingLeft;
    }

    public void setHorizontalFloatingLeft(double horizontalFloatingLeft) {
        this.horizontalFloatingLeft = horizontalFloatingLeft;
    }

    public double getHorizontalFloatingRight() {
        return horizontalFloatingRight;
    }

    public void setHorizontalFloatingRight(double horizontalFloatingRight) {
        this.horizontalFloatingRight = horizontalFloatingRight;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}
