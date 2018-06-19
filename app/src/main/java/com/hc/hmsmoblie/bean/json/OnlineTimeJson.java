package com.hc.hmsmoblie.bean.json;

import java.util.List;

/**
 * Created by Administrator on 2018/6/19.
 */

public class OnlineTimeJson {


    private java.util.List<ListBean> List;

    public List<ListBean> getList() {
        return List;
    }

    public void setList(List<ListBean> List) {
        this.List = List;
    }

    public static class ListBean {
        /**
         * RowNum : 1
         * CamId : 1014461
         * CamName : 智能测距_海康
         * CamDateStartUse : 2018-03-30
         * DevTypeName : 全景成像测距摄像机
         * SumTime : 39090
         * TotalTime : 40320
         * CamOnlineDurationH : 651.5
         * CamOnlineMustTimeH : 672
         * CamOnlineRate : 0.969494047619
         */

        private int RowNum;
        private int CamId;
        private String CamName;
        private String CamDateStartUse;
        private String DevTypeName;
        private int SumTime;
        private int TotalTime;
        private double CamOnlineDurationH;
        private int CamOnlineMustTimeH;
        private double CamOnlineRate;

        public int getRowNum() {
            return RowNum;
        }

        public void setRowNum(int RowNum) {
            this.RowNum = RowNum;
        }

        public int getCamId() {
            return CamId;
        }

        public void setCamId(int CamId) {
            this.CamId = CamId;
        }

        public String getCamName() {
            return CamName;
        }

        public void setCamName(String CamName) {
            this.CamName = CamName;
        }

        public String getCamDateStartUse() {
            return CamDateStartUse;
        }

        public void setCamDateStartUse(String CamDateStartUse) {
            this.CamDateStartUse = CamDateStartUse;
        }

        public String getDevTypeName() {
            return DevTypeName;
        }

        public void setDevTypeName(String DevTypeName) {
            this.DevTypeName = DevTypeName;
        }

        public int getSumTime() {
            return SumTime;
        }

        public void setSumTime(int SumTime) {
            this.SumTime = SumTime;
        }

        public int getTotalTime() {
            return TotalTime;
        }

        public void setTotalTime(int TotalTime) {
            this.TotalTime = TotalTime;
        }

        public double getCamOnlineDurationH() {
            return CamOnlineDurationH;
        }

        public void setCamOnlineDurationH(double CamOnlineDurationH) {
            this.CamOnlineDurationH = CamOnlineDurationH;
        }

        public int getCamOnlineMustTimeH() {
            return CamOnlineMustTimeH;
        }

        public void setCamOnlineMustTimeH(int CamOnlineMustTimeH) {
            this.CamOnlineMustTimeH = CamOnlineMustTimeH;
        }

        public double getCamOnlineRate() {
            return CamOnlineRate;
        }

        public void setCamOnlineRate(double CamOnlineRate) {
            this.CamOnlineRate = CamOnlineRate;
        }
    }
}
