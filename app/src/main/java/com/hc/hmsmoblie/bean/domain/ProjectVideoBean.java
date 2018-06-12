package com.hc.hmsmoblie.bean.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/6/11.
 */

public class ProjectVideoBean implements Serializable{

    /**
     * result : 1
     * msg : 查询成功!
     * data : [{"row_number":1,"ProjName":"汇川技术部中心测试用","ProjAddress":"汇川","ProjStatusCurrent":"竣工验收","ProjStatusCurrId":115,"ProjID":239,"ProjLat":"26.07479","ProjLng":"119.283507","ProjRegionCode":350100,"ArpCheckStartDate":"2017-04-30 18:53:22","city":"福州市","area":null},{"row_number":2,"ProjName":"两岸物联网应用创新创业大赛","ProjAddress":null,"ProjStatusCurrent":"竣工验收","ProjStatusCurrId":115,"ProjID":274,"ProjLat":"25.092213","ProjLng":"117.037856","ProjRegionCode":350100,"ArpCheckStartDate":null,"city":"福州市","area":null},{"row_number":3,"ProjName":"永安洪田镇洪东新村滑坡监测","ProjAddress":null,"ProjStatusCurrent":"","ProjStatusCurrId":0,"ProjID":304,"ProjLat":"25.8530216217041","ProjLng":"117.26586151123","ProjRegionCode":350481,"ArpCheckStartDate":null,"city":"三明市","area":"永安市"},{"row_number":4,"ProjName":"禹洲国际大酒店","ProjAddress":"厦门市五缘湾","ProjStatusCurrent":"监控中(8/8)","ProjStatusCurrId":116,"ProjID":10001,"ProjLat":"24.543044","ProjLng":"118.175679","ProjRegionCode":350206,"ArpCheckStartDate":"2015-02-09 00:00:00","city":"厦门市","area":"湖里区"},{"row_number":5,"ProjName":"厦门东渡检验检疫局综合实验楼","ProjAddress":"厦门市湖里区东渡路259号","ProjStatusCurrent":"监控中(1/4)","ProjStatusCurrId":116,"ProjID":10011,"ProjLat":"24.506815","ProjLng":"118.093322","ProjRegionCode":350206,"ArpCheckStartDate":"2014-04-21 16:50:35","city":"厦门市","area":"湖里区"},{"row_number":6,"ProjName":"万科广场（2012JP05地块）06子地块","ProjAddress":"集美区11-07厦门（新）站片区圣果路与集美北大道交叉口东北侧","ProjStatusCurrent":"监控中(13/13)","ProjStatusCurrId":116,"ProjID":10012,"ProjLat":"24.640406","ProjLng":"118.084467","ProjRegionCode":350211,"ArpCheckStartDate":"2013-12-25 13:53:25","city":"厦门市","area":"集美区"},{"row_number":7,"ProjName":"龙岩市妇幼保健院、儿童医院、儿童康复医院","ProjAddress":"龙岩市新罗区曹溪镇石粉村","ProjStatusCurrent":"监控中(2/8)","ProjStatusCurrId":116,"ProjID":10041,"ProjLat":"25.075857","ProjLng":"117.031161","ProjRegionCode":350800,"ArpCheckStartDate":"2014-03-03 17:11:16","city":"龙岩市","area":null},{"row_number":8,"ProjName":"电信信息广场","ProjAddress":"东街电信信息广场2楼","ProjStatusCurrent":"监控中(1/1)","ProjStatusCurrId":116,"ProjID":10118,"ProjLat":"25.559475","ProjLng":"119.758929","ProjRegionCode":350102,"ArpCheckStartDate":null,"city":"福州市","area":"鼓楼区"},{"row_number":9,"ProjName":"新景地工程","ProjAddress":"仙岳路与环岛干道交叉口西南侧","ProjStatusCurrent":"监控中(4/4)","ProjStatusCurrId":116,"ProjID":10162,"ProjLat":"24.515528","ProjLng":"118.185471","ProjRegionCode":350206,"ArpCheckStartDate":"2015-01-17 00:00:00","city":"厦门市","area":"湖里区"},{"row_number":10,"ProjName":"晋安新城鹤林片区横屿组团安置房三期B区","ProjAddress":"晋安区铁路机务段西侧，祥屿佳园南侧","ProjStatusCurrent":"监控中(7/19)","ProjStatusCurrId":116,"ProjID":10163,"ProjLat":"26.109079","ProjLng":"119.368397","ProjRegionCode":350111,"ArpCheckStartDate":"2018-05-03 13:05:37","city":"福州市","area":"晋安区"}]
     */

    private int result;
    private String msg;
    private List<DataBean> data;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * row_number : 1
         * ProjName : 汇川技术部中心测试用
         * ProjAddress : 汇川
         * ProjStatusCurrent : 竣工验收
         * ProjStatusCurrId : 115
         * ProjID : 239
         * ProjLat : 26.07479
         * ProjLng : 119.283507
         * ProjRegionCode : 350100
         * ArpCheckStartDate : 2017-04-30 18:53:22
         * city : 福州市
         * area : null
         */

        private int row_number;
        private String ProjName;
        private String ProjAddress;
        private String ProjStatusCurrent;
        private int ProjStatusCurrId;
        private int ProjID;
        private String ProjLat;
        private String ProjLng;
        private int ProjRegionCode;
        private String ArpCheckStartDate;
        private String city;
        private Object area;

        public int getRow_number() {
            return row_number;
        }

        public void setRow_number(int row_number) {
            this.row_number = row_number;
        }

        public String getProjName() {
            return ProjName;
        }

        public void setProjName(String ProjName) {
            this.ProjName = ProjName;
        }

        public String getProjAddress() {
            return ProjAddress;
        }

        public void setProjAddress(String ProjAddress) {
            this.ProjAddress = ProjAddress;
        }

        public String getProjStatusCurrent() {
            return ProjStatusCurrent;
        }

        public void setProjStatusCurrent(String ProjStatusCurrent) {
            this.ProjStatusCurrent = ProjStatusCurrent;
        }

        public int getProjStatusCurrId() {
            return ProjStatusCurrId;
        }

        public void setProjStatusCurrId(int ProjStatusCurrId) {
            this.ProjStatusCurrId = ProjStatusCurrId;
        }

        public int getProjID() {
            return ProjID;
        }

        public void setProjID(int ProjID) {
            this.ProjID = ProjID;
        }

        public String getProjLat() {
            return ProjLat;
        }

        public void setProjLat(String ProjLat) {
            this.ProjLat = ProjLat;
        }

        public String getProjLng() {
            return ProjLng;
        }

        public void setProjLng(String ProjLng) {
            this.ProjLng = ProjLng;
        }

        public int getProjRegionCode() {
            return ProjRegionCode;
        }

        public void setProjRegionCode(int ProjRegionCode) {
            this.ProjRegionCode = ProjRegionCode;
        }

        public String getArpCheckStartDate() {
            return ArpCheckStartDate;
        }

        public void setArpCheckStartDate(String ArpCheckStartDate) {
            this.ArpCheckStartDate = ArpCheckStartDate;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public Object getArea() {
            return area;
        }

        public void setArea(Object area) {
            this.area = area;
        }
    }
}
