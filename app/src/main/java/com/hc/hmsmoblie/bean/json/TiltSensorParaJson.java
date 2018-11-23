package com.hc.hmsmoblie.bean.json;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/9/20.
 */

public class TiltSensorParaJson implements Serializable{


    /**
     * List : [{"ParaID":86,"Px":1,"Py":1,"PAo":null,"CamID":1053613,"Seq":"FF05","CreateTime":"2018-09-12 14:25:03","Ox":1.1030000448226929,"Oy":-1.690999984741211,"Av":0,"Ao":0,"UpdateTime":null,"Oldx":1.4359999895095825,"Oldy":-0.8450000286102295,"OldAv":null,"OldAo":null,"Photox":28.515625,"Photoy":63.1,"States":1,"ParaName":"FF05","Hight":0,"FirstOldx":1.4359999895095825,"FirstOldy":-0.8450000286102295,"Obd":0,"ObdDiff":0,"ObdThreshold":null}]
     * total : 1
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

    public static class ListBean implements Serializable{
        /**
         * ParaID : 86
         * Px : 1.0
         * Py : 1.0
         * PAo : null
         * CamID : 1053613
         * Seq : FF05
         * CreateTime : 2018-09-12 14:25:03
         * Ox : 1.1030000448226929
         * Oy : -1.690999984741211
         * Av : 0
         * Ao : 0.0
         * UpdateTime : null
         * Oldx : 1.4359999895095825
         * Oldy : -0.8450000286102295
         * OldAv : null
         * OldAo : null
         * Photox : 28.515625
         * Photoy : 63.1
         * States : 1
         * ParaName : FF05
         * Hight : 0.0
         * FirstOldx : 1.4359999895095825
         * FirstOldy : -0.8450000286102295
         * Obd : 0.0
         * ObdDiff : 0.0
         * ObdThreshold : null
         */

        private int ParaID;
        private double Px;
        private double Py;
        private String PAo;
        private int CamID;
        private String Seq;
        private String CreateTime;
        private double Ox;
        private double Oy;
        private int Av;
        private double Ao;
        private String UpdateTime;
        private double Oldx;
        private double Oldy;
        private String OldAv;
        private String OldAo;
        private double Photox;
        private double Photoy;
        private String States;
        private String ParaName;
        private double Hight;
        private double FirstOldx;
        private double FirstOldy;
        private double Obd;
        private double ObdDiff;
        private String ObdThreshold;

        public int getParaID() {
            return ParaID;
        }

        public void setParaID(int ParaID) {
            this.ParaID = ParaID;
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

        public String getPAo() {
            return PAo;
        }

        public void setPAo(String PAo) {
            this.PAo = PAo;
        }

        public int getCamID() {
            return CamID;
        }

        public void setCamID(int CamID) {
            this.CamID = CamID;
        }

        public String getSeq() {
            return Seq;
        }

        public void setSeq(String Seq) {
            this.Seq = Seq;
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

        public int getAv() {
            return Av;
        }

        public void setAv(int Av) {
            this.Av = Av;
        }

        public double getAo() {
            return Ao;
        }

        public void setAo(double Ao) {
            this.Ao = Ao;
        }

        public String getUpdateTime() {
            return UpdateTime;
        }

        public void setUpdateTime(String UpdateTime) {
            this.UpdateTime = UpdateTime;
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

        public String getOldAv() {
            return OldAv;
        }

        public void setOldAv(String OldAv) {
            this.OldAv = OldAv;
        }

        public String getOldAo() {
            return OldAo;
        }

        public void setOldAo(String OldAo) {
            this.OldAo = OldAo;
        }

        public double getPhotox() {
            return Photox;
        }

        public void setPhotox(double Photox) {
            this.Photox = Photox;
        }

        public double getPhotoy() {
            return Photoy;
        }

        public void setPhotoy(double Photoy) {
            this.Photoy = Photoy;
        }

        public String getStates() {
            return States;
        }

        public void setStates(String States) {
            this.States = States;
        }

        public String getParaName() {
            return ParaName;
        }

        public void setParaName(String ParaName) {
            this.ParaName = ParaName;
        }

        public double getHight() {
            return Hight;
        }

        public void setHight(double Hight) {
            this.Hight = Hight;
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

        public double getObd() {
            return Obd;
        }

        public void setObd(double Obd) {
            this.Obd = Obd;
        }

        public double getObdDiff() {
            return ObdDiff;
        }

        public void setObdDiff(double ObdDiff) {
            this.ObdDiff = ObdDiff;
        }

        public String getObdThreshold() {
            return ObdThreshold;
        }

        public void setObdThreshold(String ObdThreshold) {
            this.ObdThreshold = ObdThreshold;
        }
    }
}
