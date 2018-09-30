package com.hc.hmsmoblie.bean.json;

import java.util.List;

/**
 *
 */

public class TiltSensorChartJson {

    /**
     * ParaID : 89
     * ParaName : ff03
     * data : [{"timeName":[],"YuOxZ":[],"YuOyZ":[],"YuOxF":[],"YuOyF":[],"NewOx":[],"NewOy":[],"Obd":[],"Oldx":[],"Oldy":[],"Stagex":[],"Stagey":[],"FirstOldx":[],"FirstOldy":[],"ObdOldx":[],"ObdOldy":[],"ObdOldz":[],"ObdStagex":[],"ObdStagey":[],"ObdStagez":[],"ObdFirstOldx":[],"ObdFirstOldy":[],"ObdFirstOldz":[],"CdObd":[],"CdObdDiff":[],"CdObdAdd":[],"FloatObdRight":[],"FloatObdLeft":[]}]
     */

    private String ParaID;
    private String ParaName;
    private List<DataBean> data;

    public String getParaID() {
        return ParaID;
    }

    public void setParaID(String ParaID) {
        this.ParaID = ParaID;
    }

    public String getParaName() {
        return ParaName;
    }

    public void setParaName(String ParaName) {
        this.ParaName = ParaName;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private List<String> timeName;
        private List<Double> YuOxZ;
        private List<Double> YuOyZ;
        private List<Double> YuOxF;
        private List<Double> YuOyF;
        private List<Double> NewOx;
        private List<Double> NewOy;
        private List<Double> Obd;
        private List<Double> Oldx;
        private List<Double> Oldy;
        private List<Double> Stagex;
        private List<Double> Stagey;
        private List<Double> FirstOldx;
        private List<Double> FirstOldy;
        private List<Double> ObdOldx;
        private List<Double> ObdOldy;
        private List<Double> ObdOldz;
        private List<Double> ObdStagex;
        private List<Double> ObdStagey;
        private List<Double> ObdStagez;
        private List<Double> ObdFirstOldx;
        private List<Double> ObdFirstOldy;
        private List<Double> ObdFirstOldz;
        private List<Double> CdObd;
        private List<Double> CdObdDiff;
        private List<Double> CdObdAdd;
        private List<Double> FloatObdRight;
        private List<Double> FloatObdLeft;

        public List<String> getTimeName() {
            return timeName;
        }

        public void setTimeName(List<String> timeName) {
            this.timeName = timeName;
        }

        public List<Double> getYuOxZ() {
            return YuOxZ;
        }

        public void setYuOxZ(List<Double> YuOxZ) {
            this.YuOxZ = YuOxZ;
        }

        public List<Double> getYuOyZ() {
            return YuOyZ;
        }

        public void setYuOyZ(List<Double> YuOyZ) {
            this.YuOyZ = YuOyZ;
        }

        public List<Double> getYuOxF() {
            return YuOxF;
        }

        public void setYuOxF(List<Double> YuOxF) {
            this.YuOxF = YuOxF;
        }

        public List<Double> getYuOyF() {
            return YuOyF;
        }

        public void setYuOyF(List<Double> YuOyF) {
            this.YuOyF = YuOyF;
        }

        public List<Double> getNewOx() {
            return NewOx;
        }

        public void setNewOx(List<Double> NewOx) {
            this.NewOx = NewOx;
        }

        public List<Double> getNewOy() {
            return NewOy;
        }

        public void setNewOy(List<Double> NewOy) {
            this.NewOy = NewOy;
        }

        public List<Double> getObd() {
            return Obd;
        }

        public void setObd(List<Double> Obd) {
            this.Obd = Obd;
        }

        public List<Double> getOldx() {
            return Oldx;
        }

        public void setOldx(List<Double> Oldx) {
            this.Oldx = Oldx;
        }

        public List<Double> getOldy() {
            return Oldy;
        }

        public void setOldy(List<Double> Oldy) {
            this.Oldy = Oldy;
        }

        public List<Double> getStagex() {
            return Stagex;
        }

        public void setStagex(List<Double> Stagex) {
            this.Stagex = Stagex;
        }

        public List<Double> getStagey() {
            return Stagey;
        }

        public void setStagey(List<Double> Stagey) {
            this.Stagey = Stagey;
        }

        public List<Double> getFirstOldx() {
            return FirstOldx;
        }

        public void setFirstOldx(List<Double> FirstOldx) {
            this.FirstOldx = FirstOldx;
        }

        public List<Double> getFirstOldy() {
            return FirstOldy;
        }

        public void setFirstOldy(List<Double> FirstOldy) {
            this.FirstOldy = FirstOldy;
        }

        public List<Double> getObdOldx() {
            return ObdOldx;
        }

        public void setObdOldx(List<Double> ObdOldx) {
            this.ObdOldx = ObdOldx;
        }

        public List<Double> getObdOldy() {
            return ObdOldy;
        }

        public void setObdOldy(List<Double> ObdOldy) {
            this.ObdOldy = ObdOldy;
        }

        public List<Double> getObdOldz() {
            return ObdOldz;
        }

        public void setObdOldz(List<Double> ObdOldz) {
            this.ObdOldz = ObdOldz;
        }

        public List<Double> getObdStagex() {
            return ObdStagex;
        }

        public void setObdStagex(List<Double> ObdStagex) {
            this.ObdStagex = ObdStagex;
        }

        public List<Double> getObdStagey() {
            return ObdStagey;
        }

        public void setObdStagey(List<Double> ObdStagey) {
            this.ObdStagey = ObdStagey;
        }

        public List<Double> getObdStagez() {
            return ObdStagez;
        }

        public void setObdStagez(List<Double> ObdStagez) {
            this.ObdStagez = ObdStagez;
        }

        public List<Double> getObdFirstOldx() {
            return ObdFirstOldx;
        }

        public void setObdFirstOldx(List<Double> ObdFirstOldx) {
            this.ObdFirstOldx = ObdFirstOldx;
        }

        public List<Double> getObdFirstOldy() {
            return ObdFirstOldy;
        }

        public void setObdFirstOldy(List<Double> ObdFirstOldy) {
            this.ObdFirstOldy = ObdFirstOldy;
        }

        public List<Double> getObdFirstOldz() {
            return ObdFirstOldz;
        }

        public void setObdFirstOldz(List<Double> ObdFirstOldz) {
            this.ObdFirstOldz = ObdFirstOldz;
        }

        public List<Double> getCdObd() {
            return CdObd;
        }

        public void setCdObd(List<Double> CdObd) {
            this.CdObd = CdObd;
        }

        public List<Double> getCdObdDiff() {
            return CdObdDiff;
        }

        public void setCdObdDiff(List<Double> CdObdDiff) {
            this.CdObdDiff = CdObdDiff;
        }

        public List<Double> getCdObdAdd() {
            return CdObdAdd;
        }

        public void setCdObdAdd(List<Double> CdObdAdd) {
            this.CdObdAdd = CdObdAdd;
        }

        public List<Double> getFloatObdRight() {
            return FloatObdRight;
        }

        public void setFloatObdRight(List<Double> FloatObdRight) {
            this.FloatObdRight = FloatObdRight;
        }

        public List<Double> getFloatObdLeft() {
            return FloatObdLeft;
        }

        public void setFloatObdLeft(List<Double> FloatObdLeft) {
            this.FloatObdLeft = FloatObdLeft;
        }
    }
}
