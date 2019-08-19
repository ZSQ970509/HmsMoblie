package com.hc.hmsmoblie.bean.json;

import java.util.List;

/**
 *
 */
public class GetProListByArea {

    /**
     * fileList : [{"proName":"工程视频监控模拟报建","proId":"10389","proStatusCurrent":"监控中(6/9)","proRegionCode":"350000","proAddress":"福州市鼓楼区"},{"proName":"林浦广场（一期）","proId":"40222","proStatusCurrent":"监控中(7/15)","proRegionCode":"350000","proAddress":"福州市仓山区城门镇福泉高速原秀宅收费站"},{"proName":"福州市传染病医院门诊综合楼、肝胆外科大楼、内科楼、制剂楼、营养食堂工程一期","proId":"40372","proStatusCurrent":"监控中(5/7)","proRegionCode":"350000","proAddress":"福州市鼓楼区西洪路312号，福州市传染病医院内"},{"proName":"闽投营运中心","proId":"43223","proStatusCurrent":"监控中(8/8)","proRegionCode":"350000","proAddress":"福建省福州市鼓楼区古田路南侧，鼓乐路西侧"},{"proName":"国网福建电力福州茶园路生产基地第1标段","proId":"45819","proStatusCurrent":"监控中(8/11)","proRegionCode":"350000","proAddress":"福州市晋安区茶园街道茶园路60号"},{"proName":"福建医科大学附属协和医院门诊楼、急诊楼及心血管病房楼建设项目","proId":"46166","proStatusCurrent":"监控中(7/11)","proRegionCode":"350000","proAddress":"福州市鼓楼区古田路2号"},{"proName":"海峡传媒港B地块（一期）","proId":"47595","proStatusCurrent":"监控中(8/14)","proRegionCode":"350000","proAddress":"福州市闽侯县甘蔗街道滨江大道49号"},{"proName":"福建省农业科学院科研综合实验中心","proId":"48045","proStatusCurrent":"监控中(5/7)","proRegionCode":"350000","proAddress":"福州市晋安区西凤路福建省农科院埔垱院区"},{"proName":"福建省儿童医院（区域儿童医学中心）","proId":"49579","proStatusCurrent":"监控中(13/29)","proRegionCode":"350000","proAddress":"福建省福州市晋安区"}]
     * fileTotal : 9
     * folderList : [{"folderId":"350100","folderName":"福州市","folderLevel":2},{"folderId":"350200","folderName":"厦门市","folderLevel":2},{"folderId":"350300","folderName":"莆田市","folderLevel":2},{"folderId":"350400","folderName":"三明市","folderLevel":2},{"folderId":"350500","folderName":"泉州市","folderLevel":2},{"folderId":"350600","folderName":"漳州市","folderLevel":2},{"folderId":"350700","folderName":"南平市","folderLevel":2},{"folderId":"350800","folderName":"龙岩市","folderLevel":2},{"folderId":"350900","folderName":"宁德市","folderLevel":2},{"folderId":"351000","folderName":"平潭综合实验区","folderLevel":2}]
     * folderTotal : 10
     */

    private int fileTotal;
    private int folderTotal;
    private List<FileListBean> fileList;
    private List<FolderListBean> folderList;

    public int getFileTotal() {
        return fileTotal;
    }

    public void setFileTotal(int fileTotal) {
        this.fileTotal = fileTotal;
    }

    public int getFolderTotal() {
        return folderTotal;
    }

    public void setFolderTotal(int folderTotal) {
        this.folderTotal = folderTotal;
    }

    public List<FileListBean> getFileList() {
        return fileList;
    }

    public void setFileList(List<FileListBean> fileList) {
        this.fileList = fileList;
    }

    public List<FolderListBean> getFolderList() {
        return folderList;
    }

    public void setFolderList(List<FolderListBean> folderList) {
        this.folderList = folderList;
    }

    public static class FileListBean {
        /**
         * proName : 工程视频监控模拟报建
         * proId : 10389
         * proStatusCurrent : 监控中(6/9)
         * proRegionCode : 350000
         * proAddress : 福州市鼓楼区
         */

        private String proName;
        private String proId;
        private String proStatusCurrent;
        private String proRegionCode;
        private String proAddress;

        public String getProName() {
            return proName;
        }

        public void setProName(String proName) {
            this.proName = proName;
        }

        public String getProId() {
            return proId;
        }

        public void setProId(String proId) {
            this.proId = proId;
        }

        public String getProStatusCurrent() {
            return proStatusCurrent;
        }

        public void setProStatusCurrent(String proStatusCurrent) {
            this.proStatusCurrent = proStatusCurrent;
        }

        public String getProRegionCode() {
            return proRegionCode;
        }

        public void setProRegionCode(String proRegionCode) {
            this.proRegionCode = proRegionCode;
        }

        public String getProAddress() {
            return proAddress;
        }

        public void setProAddress(String proAddress) {
            this.proAddress = proAddress;
        }
    }

    public static class FolderListBean {
        /**
         * folderId : 350100
         * folderName : 福州市
         * folderLevel : 2
         */

        private String folderId;
        private String folderName;
        private int folderLevel;

        public String getFolderId() {
            return folderId;
        }

        public void setFolderId(String folderId) {
            this.folderId = folderId;
        }

        public String getFolderName() {
            return folderName;
        }

        public void setFolderName(String folderName) {
            this.folderName = folderName;
        }

        public int getFolderLevel() {
            return folderLevel;
        }

        public void setFolderLevel(int folderLevel) {
            this.folderLevel = folderLevel;
        }
    }
}
