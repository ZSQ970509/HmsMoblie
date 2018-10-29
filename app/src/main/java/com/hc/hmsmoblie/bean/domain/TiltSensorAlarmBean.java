package com.hc.hmsmoblie.bean.domain;

/**
 * 倾角模块告警阈值
 */

public class TiltSensorAlarmBean {
    private double axisX = 1;//单次X轴阈值
    private double axisY = 1;//单次Y轴阈值
    private double settlement = 10;//单次沉降位移阈值
    private double horizontalFloatingLeft = 10;//单次左端水平度浮动阈值
    private double horizontalFloatingRight = 10;//单次右端水平度浮动阈值
    private boolean isOpen = false;
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
