package com.hc.hmsmoblie.bean.domain;

/**
 *
 */

public class DipRealBean {
    private Double data = 0.0;
    private boolean isEmpty = false;//是否为空的
    private boolean isAlarm = false;//是否预警
    private String suffix = "";//后缀

    public DipRealBean(boolean isEmpty) {
        this.isEmpty = isEmpty;
    }
    public DipRealBean(Double data) {
        this.data = data;
    }
    public DipRealBean(Double data, String suffix) {
        this.data = data;
        this.suffix = suffix;
    }
    public DipRealBean(Double data, boolean isAlarm) {
        this.data = data;
        this.isAlarm = isAlarm;
    }
    public DipRealBean(Double data, boolean isAlarm, String suffix) {
        this.data = data;
        this.isAlarm = isAlarm;
        this.suffix = suffix;
    }


    public Double getData() {
        return data;
    }

    public void setData(Double data) {
        this.data = data;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    public boolean isAlarm() {
        return isAlarm;
    }

    public void setAlarm(boolean alarm) {
        isAlarm = alarm;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
