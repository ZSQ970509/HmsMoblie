package com.hc.hmsmoblie.bean.json;

import com.hc.hmsmoblie.bean.type.TiltSensorParaState;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/6/28.
 */

public class TiltSensorAllJson implements Serializable {

    private List<DatTiltSensorBean> dat_TiltSensor;
    private List<DatTiltSensorListBean> dat_TiltSensorList;
    private List<DatTiltSensorListTimeBean> dat_TiltSensorList_time;
    private List<DatTiltSensorListStageBean> dat_TiltSensorList_Stage;

    public List<DatTiltSensorBean> getDat_TiltSensor() {
        return dat_TiltSensor;
    }

    public void setDat_TiltSensor(List<DatTiltSensorBean> dat_TiltSensor) {
        this.dat_TiltSensor = dat_TiltSensor;
    }

    public List<DatTiltSensorListBean> getDat_TiltSensorList() {
        return dat_TiltSensorList;
    }

    public void setDat_TiltSensorList(List<DatTiltSensorListBean> dat_TiltSensorList) {
        this.dat_TiltSensorList = dat_TiltSensorList;
    }

    public List<DatTiltSensorListTimeBean> getDat_TiltSensorList_time() {
        return dat_TiltSensorList_time;
    }

    public void setDat_TiltSensorList_time(List<DatTiltSensorListTimeBean> dat_TiltSensorList_time) {
        this.dat_TiltSensorList_time = dat_TiltSensorList_time;
    }

    public List<DatTiltSensorListStageBean> getDat_TiltSensorList_Stage() {
        return dat_TiltSensorList_Stage;
    }

    public void setDat_TiltSensorList_Stage(List<DatTiltSensorListStageBean> dat_TiltSensorList_Stage) {
        this.dat_TiltSensorList_Stage = dat_TiltSensorList_Stage;
    }

    public static class DatTiltSensorBean {
        /**
         * States : 1
         * Ox : 0.224
         * Oy : -1.334
         * Vo : 115
         * cam_singnal : 51
         * cam_state : 2
         * CreateTime : 2018-11-19 13:36:33
         * Obd : 2.394
         * FirstOldx : 0.147
         * FirstOldy : 0.0519
         * CdObdAdd : 0.2
         * HightObdAdd : 6.5
         * FloatObdLeft : 0
         * FloatObdRight : 0
         * Oldx : -0.003
         * Oldy : -0.001
         * CdObd : 1
         */

        private String States = TiltSensorParaState.UNKNOWN;
        private double Ox;
        private double Oy;
        private double Vo;
        private double cam_singnal;
        private int cam_state;
        private String CreateTime = "-";
        private double Obd;
        private double FirstOldx;
        private double FirstOldy;
        private double CdObdAdd;
        private double HightObdAdd;
        private double FloatObdLeft;
        private double FloatObdRight;
        private double Oldx;
        private double Oldy;
        private double CdObd;
        private double ObdFirstOldx;
        private double ObdFirstOldy;
        private double ObdFirstOldz;
        private double deflection;

        public double getDeflection() {
            return deflection;
        }

        public void setDeflection(double deflection) {
            this.deflection = deflection;
        }

        public String getStates() {
            return States;
        }

        public void setStates(String States) {
            this.States = States;
        }

        public double getOx() {
            return Ox;
        }

        public void setOx(double Ox) {
            this.Ox = Ox;
        }

        public double getOy() {
            return Oy;
        }

        public void setOy(double Oy) {
            this.Oy = Oy;
        }

        public double getVo() {
            return Vo;
        }

        public void setVo(double Vo) {
            this.Vo = Vo;
        }

        public double getCam_singnal() {
            return cam_singnal;
        }

        public void setCam_singnal(double cam_singnal) {
            this.cam_singnal = cam_singnal;
        }

        public int getCam_state() {
            return cam_state;
        }

        public void setCam_state(int cam_state) {
            this.cam_state = cam_state;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public double getObd() {
            return Obd;
        }

        public void setObd(double Obd) {
            this.Obd = Obd;
        }

        public double getFirstOldx() {
            return FirstOldx;
        }

        public void setFirstOldx(double FirstOldx) {
            this.FirstOldx = FirstOldx;
        }

        public double getFirstOldy() {
            return FirstOldy;
        }

        public void setFirstOldy(double FirstOldy) {
            this.FirstOldy = FirstOldy;
        }

        public double getCdObdAdd() {
            return CdObdAdd;
        }

        public void setCdObdAdd(double CdObdAdd) {
            this.CdObdAdd = CdObdAdd;
        }

        public double getHightObdAdd() {
            return HightObdAdd;
        }

        public void setHightObdAdd(double HightObdAdd) {
            this.HightObdAdd = HightObdAdd;
        }

        public double getFloatObdLeft() {
            return FloatObdLeft;
        }

        public void setFloatObdLeft(double FloatObdLeft) {
            this.FloatObdLeft = FloatObdLeft;
        }

        public double getFloatObdRight() {
            return FloatObdRight;
        }

        public void setFloatObdRight(double FloatObdRight) {
            this.FloatObdRight = FloatObdRight;
        }

        public double getOldx() {
            return Oldx;
        }

        public void setOldx(double Oldx) {
            this.Oldx = Oldx;
        }

        public double getOldy() {
            return Oldy;
        }

        public void setOldy(double Oldy) {
            this.Oldy = Oldy;
        }

        public double getCdObd() {
            return CdObd;
        }

        public void setCdObd(double CdObd) {
            this.CdObd = CdObd;
        }

        public double getObdFirstOldx() {
            return ObdFirstOldx;
        }

        public void setObdFirstOldx(double obdFirstOldx) {
            ObdFirstOldx = obdFirstOldx;
        }

        public double getObdFirstOldy() {
            return ObdFirstOldy;
        }

        public void setObdFirstOldy(double obdFirstOldy) {
            ObdFirstOldy = obdFirstOldy;
        }

        public double getObdFirstOldz() {
            return ObdFirstOldz;
        }

        public void setObdFirstOldz(double obdFirstOldz) {
            ObdFirstOldz = obdFirstOldz;
        }
    }

    public static class DatTiltSensorListBean {
        /**
         * FirstOldx : 0.033
         * FirstOldy : 0.0129
         * CreateTime : 2018-11-19 00:00:30
         * Px : 5
         * Py : 5
         */

        private double FirstOldx;
        private double FirstOldy;
        private String CreateTime;
        private double Px;
        private double Py;

        public double getFirstOldx() {
            return FirstOldx;
        }

        public void setFirstOldx(double FirstOldx) {
            this.FirstOldx = FirstOldx;
        }

        public double getFirstOldy() {
            return FirstOldy;
        }

        public void setFirstOldy(double FirstOldy) {
            this.FirstOldy = FirstOldy;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public double getPx() {
            return Px;
        }

        public void setPx(double Px) {
            this.Px = Px;
        }

        public double getPy() {
            return Py;
        }

        public void setPy(double Py) {
            this.Py = Py;
        }
    }

    public static class DatTiltSensorListTimeBean {
        /**
         * Obd : 2.395
         * CreateTime : 2018-11-19 00:00:30
         */

        private double Obd;
        private String CreateTime;

        public double getObd() {
            return Obd;
        }

        public void setObd(double Obd) {
            this.Obd = Obd;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }
    }


    public static class DatTiltSensorListStageBean {
        /**
         * ObdFirstOldx : 125.4
         * ObdFirstOldy : -307.9
         * ObdFirstOldz : 7409.2
         * CreateTime : 2018-11-06 06:40:24
         */

        private double ObdFirstOldx;
        private double ObdFirstOldy;
        private double ObdFirstOldz;
        private String CreateTime = "-";

        public double getObdFirstOldx() {
            return ObdFirstOldx;
        }

        public void setObdFirstOldx(double ObdFirstOldx) {
            this.ObdFirstOldx = ObdFirstOldx;
        }

        public double getObdFirstOldy() {
            return ObdFirstOldy;
        }

        public void setObdFirstOldy(double ObdFirstOldy) {
            this.ObdFirstOldy = ObdFirstOldy;
        }

        public double getObdFirstOldz() {
            return ObdFirstOldz;
        }

        public void setObdFirstOldz(double ObdFirstOldz) {
            this.ObdFirstOldz = ObdFirstOldz;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }
    }
}
