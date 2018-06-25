package com.hc.hmsmoblie.bean.json;

import java.util.List;

/**
 *
 */

public class LadderControlDetailsOperationJson {

    /**
     * List : [{"id":87830,"deviceId":3502,"text":"开锁,成功","createTime":"2018-06-12 09:52:56","memberId":2322,"projectId":null,"projName":"汇川技术部中心测试用","userName":"18259158223","realName":"黄丹慧"},{"id":87831,"deviceId":3502,"text":"开锁,成功","createTime":"2018-06-12 09:52:56","memberId":2322,"projectId":null,"projName":"汇川技术部中心测试用","userName":"18259158223","realName":"黄丹慧"},{"id":87832,"deviceId":3502,"text":"开锁,成功","createTime":"2018-06-12 09:52:56","memberId":2322,"projectId":null,"projName":"汇川技术部中心测试用","userName":"18259158223","realName":"黄丹慧"},{"id":87833,"deviceId":3502,"text":"开锁,成功","createTime":"2018-06-12 09:52:56","memberId":2322,"projectId":null,"projName":"汇川技术部中心测试用","userName":"18259158223","realName":"黄丹慧"},{"id":87828,"deviceId":3502,"text":"开锁,成功","createTime":"2018-06-12 09:52:55","memberId":2322,"projectId":null,"projName":"汇川技术部中心测试用","userName":"18259158223","realName":"黄丹慧"},{"id":87829,"deviceId":3502,"text":"开锁,成功","createTime":"2018-06-12 09:52:55","memberId":2322,"projectId":null,"projName":"汇川技术部中心测试用","userName":"18259158223","realName":"黄丹慧"},{"id":87826,"deviceId":3502,"text":"开锁,成功","createTime":"2018-06-12 09:52:15","memberId":2322,"projectId":null,"projName":"汇川技术部中心测试用","userName":"18259158223","realName":"黄丹慧"},{"id":87827,"deviceId":3502,"text":"开锁,成功","createTime":"2018-06-12 09:52:15","memberId":2322,"projectId":null,"projName":"汇川技术部中心测试用","userName":"18259158223","realName":"黄丹慧"},{"id":87820,"deviceId":3502,"text":"开锁,成功","createTime":"2018-06-12 09:52:14","memberId":2322,"projectId":null,"projName":"汇川技术部中心测试用","userName":"18259158223","realName":"黄丹慧"},{"id":87821,"deviceId":3502,"text":"开锁,成功","createTime":"2018-06-12 09:52:14","memberId":2322,"projectId":null,"projName":"汇川技术部中心测试用","userName":"18259158223","realName":"黄丹慧"}]
     * total : 35
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
         * id : 87830
         * deviceId : 3502
         * text : 开锁,成功
         * createTime : 2018-06-12 09:52:56
         * memberId : 2322
         * projectId : null
         * projName : 汇川技术部中心测试用
         * userName : 18259158223
         * realName : 黄丹慧
         */

        private int id;
        private int deviceId;
        private String text;
        private String createTime;
        private int memberId;
        private String projectId;
        private String projName;
        private String userName;
        private String realName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(int deviceId) {
            this.deviceId = deviceId;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getMemberId() {
            return memberId;
        }

        public void setMemberId(int memberId) {
            this.memberId = memberId;
        }

        public String getProjectId() {
            return projectId;
        }

        public void setProjectId(String projectId) {
            this.projectId = projectId;
        }

        public String getProjName() {
            return projName;
        }

        public void setProjName(String projName) {
            this.projName = projName;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }
    }
}
