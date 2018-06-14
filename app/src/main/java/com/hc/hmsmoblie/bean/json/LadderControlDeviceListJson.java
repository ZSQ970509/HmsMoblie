package com.hc.hmsmoblie.bean.json;

import java.util.List;

/**
 *
 */

public class LadderControlDeviceListJson {

    /**
     * List : [{"id":3629,"deviceName":"10A6B3E46","state":0,"deviceAddress":"7C:01:0A:6B:3E:46","company":null,"deptId":null,"operationTime":null,"createTime":"2018-06-12 09:48:35","isBind":0,"position":"吊塔1","projectId":239,"province":"350000","city":"350100","area":null,"stationId":null,"projName":"汇川技术部中心测试用","AreaName":null,"LastTime":"2018-06-12 15:21:16","Distance":"00018.86","BattleVolta":"4.10","Tamper":"未触发","Charging":"在充电"},{"id":3502,"deviceName":"3124A424B","state":1,"deviceAddress":"C4:F3:12:4A:42:4B","company":null,"deptId":null,"operationTime":null,"createTime":"2018-06-07 10:56:30","isBind":0,"position":"FFFF","projectId":239,"province":"350000","city":"350100","area":null,"stationId":null,"projName":"汇川技术部中心测试用","AreaName":null,"LastTime":"2018-06-12 09:52:56","Distance":"00017.21","BattleVolta":"4.10","Tamper":"已触发","Charging":"未充电"},{"id":3170,"deviceName":"308AB9841","state":0,"deviceAddress":"3C:A3:08:AB:98:41","company":null,"deptId":null,"operationTime":null,"createTime":"2018-05-21 09:46:33","isBind":0,"position":"大门","projectId":239,"province":"350000","city":"350100","area":null,"stationId":null,"projName":"汇川技术部中心测试用","AreaName":null,"LastTime":"2018-05-21 10:53:12","Distance":null,"BattleVolta":null,"Tamper":null,"Charging":null},{"id":976,"deviceName":"103481","state":1,"deviceAddress":"C8:FD:19:40:6C:B0","company":null,"deptId":null,"operationTime":null,"createTime":"2018-01-11 10:16:16","isBind":0,"position":"大门","projectId":239,"province":"350000","city":"350100","area":null,"stationId":null,"projName":"汇川技术部中心测试用","AreaName":null,"LastTime":null,"Distance":null,"BattleVolta":null,"Tamper":null,"Charging":null},{"id":402,"deviceName":"D588D96B5","state":1,"deviceAddress":"9C:1D:58:8D:96:B5","company":null,"deptId":null,"operationTime":null,"createTime":"2017-11-07 08:48:22","isBind":0,"position":"测试电梯右边","projectId":239,"province":"350000","city":"350100","area":null,"stationId":null,"projName":"汇川技术部中心测试用","AreaName":null,"LastTime":null,"Distance":null,"BattleVolta":null,"Tamper":null,"Charging":null},{"id":150,"deviceName":"D19406CB0","state":0,"deviceAddress":"C8:FD:19:40:6C:B0","company":null,"deptId":null,"operationTime":null,"createTime":"2017-09-13 14:47:19","isBind":0,"position":"大门","projectId":239,"province":"350000","city":"350100","area":null,"stationId":null,"projName":"汇川技术部中心测试用","AreaName":null,"LastTime":"2018-02-07 14:15:41","Distance":null,"BattleVolta":null,"Tamper":null,"Charging":null}]
     * total : 6
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
         * id : 3629
         * deviceName : 10A6B3E46
         * state : 0
         * deviceAddress : 7C:01:0A:6B:3E:46
         * company : null
         * deptId : null
         * operationTime : null
         * createTime : 2018-06-12 09:48:35
         * isBind : 0
         * position : 吊塔1
         * projectId : 239
         * province : 350000
         * city : 350100
         * area : null
         * stationId : null
         * projName : 汇川技术部中心测试用
         * AreaName : null
         * LastTime : 2018-06-12 15:21:16
         * Distance : 00018.86
         * BattleVolta : 4.10
         * Tamper : 未触发
         * Charging : 在充电
         */

        private String id;
        private String deviceName;
        private int state;
        private String deviceAddress;
        private String company;
        private String deptId;
        private String operationTime;
        private String createTime;
        private String isBind;
        private String position;
        private String projectId;
        private String province;
        private String city;
        private String area;
        private String stationId;
        private String projName;
        private String AreaName;
        private String LastTime;
        private double Distance;
        private String BattleVolta;
        private String Tamper;
        private String Charging;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDeviceName() {
            return deviceName;
        }

        public void setDeviceName(String deviceName) {
            this.deviceName = deviceName;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getDeviceAddress() {
            return deviceAddress;
        }

        public void setDeviceAddress(String deviceAddress) {
            this.deviceAddress = deviceAddress;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getDeptId() {
            return deptId;
        }

        public void setDeptId(String deptId) {
            this.deptId = deptId;
        }

        public String getOperationTime() {
            return operationTime;
        }

        public void setOperationTime(String operationTime) {
            this.operationTime = operationTime;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getIsBind() {
            return isBind;
        }

        public void setIsBind(String isBind) {
            this.isBind = isBind;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getProjectId() {
            return projectId;
        }

        public void setProjectId(String projectId) {
            this.projectId = projectId;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getStationId() {
            return stationId;
        }

        public void setStationId(String stationId) {
            this.stationId = stationId;
        }

        public String getProjName() {
            return projName;
        }

        public void setProjName(String projName) {
            this.projName = projName;
        }

        public String getAreaName() {
            return AreaName;
        }

        public void setAreaName(String AreaName) {
            this.AreaName = AreaName;
        }

        public String getLastTime() {
            return LastTime;
        }

        public void setLastTime(String LastTime) {
            this.LastTime = LastTime;
        }

        public double getDistance() {
            return Distance;
        }

        public void setDistance(double Distance) {
            this.Distance = Distance;
        }

        public String getBattleVolta() {
            return BattleVolta;
        }

        public void setBattleVolta(String BattleVolta) {
            this.BattleVolta = BattleVolta;
        }

        public String getTamper() {
            return Tamper;
        }

        public void setTamper(String Tamper) {
            this.Tamper = Tamper;
        }

        public String getCharging() {
            return Charging;
        }

        public void setCharging(String Charging) {
            this.Charging = Charging;
        }
    }
}
