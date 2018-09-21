package com.hc.hmsmoblie.bean.json;

import java.util.List;

/**
 *
 */

public class TiltSensorChartJson {

    /**
     * ParaID : 89
     * ParaName : ff03
     * data : {"NewOx":[-27.193,27.34,0.659],"NewOy":[-2.202,5.767,-3.922],"timeName":["2018-09-13 08:41:12","2018-09-13 08:41:23","2018-09-13 08:41:33"],"YuOxZ":[1,1,1],"YuOyZ":[1,1,1],"YuOxF":[-1,-1,-1],"YuOyF":[-1,-1,-1],"ObdOldx":[-0.027,0.027,0.001],"ObdOldy":[0,0,0]}
     */

    private String ParaID;
    private String ParaName;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<Double> NewOx;
        private List<Double> NewOy;
        private List<String> timeName;
        private List<Double> YuOxZ;
        private List<Double> YuOyZ;
        private List<Double> YuOxF;
        private List<Double> YuOyF;
        private List<Double> ObdOldx;
        private List<Double> ObdOldy;

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
    }
}
