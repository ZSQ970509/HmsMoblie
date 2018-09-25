package com.hc.hmsmoblie.bean.json;

import java.util.List;

/**
 * Created by Administrator on 2018/9/20.
 */

public class SensorLogJson {

    /**
     * List : [{"RowNumber":1,"OxDiff":0.28,"OyDiff":1.008,"OxDir":0,"OyDir":270,"RecordID":161681,"CreateTime":"2018-09-14 14:40:13","Ox":1.103,"Oy":-1.691,"Oldx":-0.003,"Oldy":0.001,"ObdOldx":0,"ObdOldy":0,"CamID":1053613,"ParaID":86,"ParaName":"FF05","Obd":0},{"RowNumber":2,"OxDiff":0.277,"OyDiff":1.008,"OxDir":0,"OyDir":270,"RecordID":161680,"CreateTime":"2018-09-14 14:40:02","Ox":1.106,"Oy":-1.692,"Oldx":-0.001,"Oldy":0.001,"ObdOldx":0,"ObdOldy":0,"CamID":1053613,"ParaID":86,"ParaName":"FF05","Obd":0},{"RowNumber":3,"OxDiff":0.276,"OyDiff":1.009,"OxDir":0,"OyDir":270,"RecordID":161679,"CreateTime":"2018-09-14 14:39:51","Ox":1.107,"Oy":-1.693,"Oldx":0.003,"Oldy":-0.002,"ObdOldx":0,"ObdOldy":0,"CamID":1053613,"ParaID":86,"ParaName":"FF05","Obd":0},{"RowNumber":4,"OxDiff":0.28,"OyDiff":1.007,"OxDir":0,"OyDir":270,"RecordID":161678,"CreateTime":"2018-09-14 14:39:40","Ox":1.104,"Oy":-1.691,"Oldx":0.001,"Oldy":0,"ObdOldx":0,"ObdOldy":0,"CamID":1053613,"ParaID":86,"ParaName":"FF05","Obd":0},{"RowNumber":5,"OxDiff":0.28,"OyDiff":1.007,"OxDir":0,"OyDir":270,"RecordID":161677,"CreateTime":"2018-09-14 14:39:29","Ox":1.103,"Oy":-1.691,"Oldx":-0.001,"Oldy":-0.001,"ObdOldx":0,"ObdOldy":0,"CamID":1053613,"ParaID":86,"ParaName":"FF05","Obd":0},{"RowNumber":6,"OxDiff":0.279,"OyDiff":1.006,"OxDir":0,"OyDir":270,"RecordID":161676,"CreateTime":"2018-09-14 14:39:18","Ox":1.104,"Oy":-1.69,"Oldx":0.001,"Oldy":0.001,"ObdOldx":0,"ObdOldy":0,"CamID":1053613,"ParaID":86,"ParaName":"FF05","Obd":0},{"RowNumber":7,"OxDiff":0.28,"OyDiff":1.007,"OxDir":0,"OyDir":270,"RecordID":161675,"CreateTime":"2018-09-14 14:39:07","Ox":1.103,"Oy":-1.691,"Oldx":-0.001,"Oldy":0.002,"ObdOldx":0,"ObdOldy":0,"CamID":1053613,"ParaID":86,"ParaName":"FF05","Obd":0},{"RowNumber":8,"OxDiff":0.279,"OyDiff":1.009,"OxDir":0,"OyDir":270,"RecordID":161674,"CreateTime":"2018-09-14 14:38:56","Ox":1.104,"Oy":-1.693,"Oldx":-0.002,"Oldy":0.001,"ObdOldx":0,"ObdOldy":0,"CamID":1053613,"ParaID":86,"ParaName":"FF05","Obd":0},{"RowNumber":9,"OxDiff":0.277,"OyDiff":1.01,"OxDir":0,"OyDir":270,"RecordID":161673,"CreateTime":"2018-09-14 14:38:45","Ox":1.106,"Oy":-1.694,"Oldx":0.003,"Oldy":-0.002,"ObdOldx":0,"ObdOldy":0,"CamID":1053613,"ParaID":86,"ParaName":"FF05","Obd":0},{"RowNumber":10,"OxDiff":0.28,"OyDiff":1.007,"OxDir":0,"OyDir":270,"RecordID":161672,"CreateTime":"2018-09-14 14:38:35","Ox":1.103,"Oy":-1.692,"Oldx":0,"Oldy":0,"ObdOldx":0,"ObdOldy":0,"CamID":1053613,"ParaID":86,"ParaName":"FF05","Obd":0}]
     * total : 9107
     */

    private int total;
    private java.util.List<ListBean> List;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ListBean> getList() {
        return List;
    }

    public void setList(List<ListBean> List) {
        this.List = List;
    }

    public static class ListBean {
        /**
         * RowNumber : 1
         * OxDiff : 0.28
         * OyDiff : 1.008
         * OxDir : 0
         * OyDir : 270
         * RecordID : 161681
         * CreateTime : 2018-09-14 14:40:13
         * Ox : 1.103
         * Oy : -1.691
         * Oldx : -0.003
         * Oldy : 0.001
         * ObdOldx : 0.0
         * ObdOldy : 0.0
         * CamID : 1053613
         * ParaID : 86
         * ParaName : FF05
         * Obd : 0.0
         */

        private int RowNumber;
        private double OxDiff;
        private double OyDiff;
        private int OxDir;
        private int OyDir;
        private int RecordID;
        private String CreateTime;
        private double Ox;
        private double Oy;
        private double Oldx;
        private double Oldy;
        private double ObdOldx;
        private double ObdOldy;
        private int CamID;
        private int ParaID;
        private String ParaName;
        private double Obd;

        public int getRowNumber() {
            return RowNumber;
        }

        public void setRowNumber(int RowNumber) {
            this.RowNumber = RowNumber;
        }

        public double getOxDiff() {
            return OxDiff;
        }

        public void setOxDiff(double OxDiff) {
            this.OxDiff = OxDiff;
        }

        public double getOyDiff() {
            return OyDiff;
        }

        public void setOyDiff(double OyDiff) {
            this.OyDiff = OyDiff;
        }

        public int getOxDir() {
            return OxDir;
        }

        public void setOxDir(int OxDir) {
            this.OxDir = OxDir;
        }

        public int getOyDir() {
            return OyDir;
        }

        public void setOyDir(int OyDir) {
            this.OyDir = OyDir;
        }

        public int getRecordID() {
            return RecordID;
        }

        public void setRecordID(int RecordID) {
            this.RecordID = RecordID;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
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

        public double getObdOldx() {
            return ObdOldx;
        }

        public void setObdOldx(double ObdOldx) {
            this.ObdOldx = ObdOldx;
        }

        public double getObdOldy() {
            return ObdOldy;
        }

        public void setObdOldy(double ObdOldy) {
            this.ObdOldy = ObdOldy;
        }

        public int getCamID() {
            return CamID;
        }

        public void setCamID(int CamID) {
            this.CamID = CamID;
        }

        public int getParaID() {
            return ParaID;
        }

        public void setParaID(int ParaID) {
            this.ParaID = ParaID;
        }

        public String getParaName() {
            return ParaName;
        }

        public void setParaName(String ParaName) {
            this.ParaName = ParaName;
        }

        public double getObd() {
            return Obd;
        }

        public void setObd(double Obd) {
            this.Obd = Obd;
        }
    }
}
