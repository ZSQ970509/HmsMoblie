package com.hc.hmsmoblie.bean.json;

import java.util.List;

/**
 *
 */

public class ImageLogNodeJson {
    private double rowSumAll;
    private double colSumAll;
    private int rowSum30;
    private int colSum30;
    private List<DataAllBean> dataAll;
    private List<DataCenterBean> dataCenter;
    private List<Data30Bean> data30;

    public double getRowSumAll() {
        return rowSumAll;
    }

    public void setRowSumAll(double rowSumAll) {
        this.rowSumAll = rowSumAll;
    }

    public double getColSumAll() {
        return colSumAll;
    }

    public void setColSumAll(double colSumAll) {
        this.colSumAll = colSumAll;
    }

    public int getRowSum30() {
        return rowSum30;
    }

    public void setRowSum30(int rowSum30) {
        this.rowSum30 = rowSum30;
    }

    public int getColSum30() {
        return colSum30;
    }

    public void setColSum30(int colSum30) {
        this.colSum30 = colSum30;
    }

    public List<DataAllBean> getDataAll() {
        return dataAll;
    }

    public void setDataAll(List<DataAllBean> dataAll) {
        this.dataAll = dataAll;
    }

    public List<DataCenterBean> getDataCenter() {
        return dataCenter;
    }

    public void setDataCenter(List<DataCenterBean> dataCenter) {
        this.dataCenter = dataCenter;
    }

    public List<Data30Bean> getData30() {
        return data30;
    }

    public void setData30(List<Data30Bean> data30) {
        this.data30 = data30;
    }

    public static class DataAllBean {
        /**
         * RecordId : 472453767
         * Path : http://ftp.jsqqy.com:8123/upfile/Puzzle/155655669_001/2/1_14_5_11.jpg
         * CamSn : 155655669_001
         * FtpPath : null
         * ImageTimes : 14
         * ColNum : 11
         * RowNum : 5
         * Obd : 0
         * AddTime : 2018-06-24 14:22:20
         * adjust_across : null
         * adjust_vertical : null
         * adjust_inclined : null
         * Aha : 166.07400512695312
         * Ava : 76.0009994506836
         */

        private String RecordId;
        private String Path;
        private String CamSn;
        private String FtpPath;
        private String ImageTimes;
        private double ColNum;
        private double RowNum;
        private double Obd;
        private String AddTime;
        private String adjust_across;
        private String adjust_vertical;
        private String adjust_inclined;
        private double Aha;
        private double Ava;

        public String getRecordId() {
            return RecordId;
        }

        public void setRecordId(String RecordId) {
            this.RecordId = RecordId;
        }

        public String getPath() {
            return Path;
        }

        public void setPath(String Path) {
            this.Path = Path;
        }

        public String getCamSn() {
            return CamSn;
        }

        public void setCamSn(String CamSn) {
            this.CamSn = CamSn;
        }

        public String getFtpPath() {
            return FtpPath;
        }

        public void setFtpPath(String FtpPath) {
            this.FtpPath = FtpPath;
        }

        public String getImageTimes() {
            return ImageTimes;
        }

        public void setImageTimes(String ImageTimes) {
            this.ImageTimes = ImageTimes;
        }

        public double getColNum() {
            return ColNum;
        }

        public void setColNum(double ColNum) {
            this.ColNum = ColNum;
        }

        public double getRowNum() {
            return RowNum;
        }

        public void setRowNum(double RowNum) {
            this.RowNum = RowNum;
        }

        public double getObd() {
            return Obd;
        }

        public void setObd(double Obd) {
            this.Obd = Obd;
        }

        public String getAddTime() {
            return AddTime;
        }

        public void setAddTime(String AddTime) {
            this.AddTime = AddTime;
        }

        public String getAdjust_across() {
            return adjust_across;
        }

        public void setAdjust_across(String adjust_across) {
            this.adjust_across = adjust_across;
        }

        public String getAdjust_vertical() {
            return adjust_vertical;
        }

        public void setAdjust_vertical(String adjust_vertical) {
            this.adjust_vertical = adjust_vertical;
        }

        public String getAdjust_inclined() {
            return adjust_inclined;
        }

        public void setAdjust_inclined(String adjust_inclined) {
            this.adjust_inclined = adjust_inclined;
        }

        public double getAha() {
            return Aha;
        }

        public void setAha(double Aha) {
            this.Aha = Aha;
        }

        public double getAva() {
            return Ava;
        }

        public void setAva(double Ava) {
            this.Ava = Ava;
        }
    }

    public static class DataCenterBean {
        /**
         * RecordId : 472464044
         * Path : http://ftp.jsqqy.com:8123/upfile/Puzzle/155655669_001/2/1_13_21_30.jpg
         * CamSn : 155655669_001
         * FtpPath : null
         * ImageTimes : 14
         * ColNum : 30
         * RowNum : 21
         * Obd : 0
         * AddTime : 2018-06-24 14:22:49
         * adjust_across : null
         * adjust_vertical : null
         * adjust_inclined : null
         * Aha : 91.0530014038086
         * Ava : 64.00399780273438
         */

        private String RecordId;
        private String Path;
        private String CamSn;
        private String FtpPath;
        private String ImageTimes;
        private int ColNum;
        private int RowNum;
        private double Obd;
        private String AddTime;
        private String adjust_across;
        private String adjust_vertical;
        private String adjust_inclined;
        private double Aha;
        private double Ava;

        public String getRecordId() {
            return RecordId;
        }

        public void setRecordId(String RecordId) {
            this.RecordId = RecordId;
        }

        public String getPath() {
            return Path;
        }

        public void setPath(String Path) {
            this.Path = Path;
        }

        public String getCamSn() {
            return CamSn;
        }

        public void setCamSn(String CamSn) {
            this.CamSn = CamSn;
        }

        public String getFtpPath() {
            return FtpPath;
        }

        public void setFtpPath(String FtpPath) {
            this.FtpPath = FtpPath;
        }

        public String getImageTimes() {
            return ImageTimes;
        }

        public void setImageTimes(String ImageTimes) {
            this.ImageTimes = ImageTimes;
        }

        public int getColNum() {
            return ColNum;
        }

        public void setColNum(int ColNum) {
            this.ColNum = ColNum;
        }

        public int getRowNum() {
            return RowNum;
        }

        public void setRowNum(int RowNum) {
            this.RowNum = RowNum;
        }

        public double getObd() {
            return Obd;
        }

        public void setObd(double Obd) {
            this.Obd = Obd;
        }

        public String getAddTime() {
            return AddTime;
        }

        public void setAddTime(String AddTime) {
            this.AddTime = AddTime;
        }

        public String getAdjust_across() {
            return adjust_across;
        }

        public void setAdjust_across(String adjust_across) {
            this.adjust_across = adjust_across;
        }

        public String getAdjust_vertical() {
            return adjust_vertical;
        }

        public void setAdjust_vertical(String adjust_vertical) {
            this.adjust_vertical = adjust_vertical;
        }

        public String getAdjust_inclined() {
            return adjust_inclined;
        }

        public void setAdjust_inclined(String adjust_inclined) {
            this.adjust_inclined = adjust_inclined;
        }

        public double getAha() {
            return Aha;
        }

        public void setAha(double Aha) {
            this.Aha = Aha;
        }

        public double getAva() {
            return Ava;
        }

        public void setAva(double Ava) {
            this.Ava = Ava;
        }
    }

    public static class Data30Bean {
        /**
         * id : 1
         * panormamicId : 472463933
         * imgpath : http://ftp.jsqqy.com:8123/upfile/Puzzle/155655669_001/2/1_13_19_28.jpg
         * camsn : 155655669_001
         * imagetimes : 14
         * ftppath : null
         * colnum : 28
         * rownum : 19
         * Obd : 0
         * AddTime : 2018-06-24 14:22:49
         * adjust_across : null
         * adjust_vertical : null
         * adjust_inclined : null
         * Aha : 85.0459976196289
         * Ava : 58.005001068115234
         */

        private double id;
        private double panormamicId;
        private String imgpath;
        private String camsn;
        private double imagetimes;
        private String ftppath;
        private int colnum;
        private int rownum;
        private double Obd;
        private String AddTime;
        private String adjust_across;
        private String adjust_vertical;
        private String adjust_inclined;
        private double Aha;
        private double Ava;

        public double getId() {
            return id;
        }

        public void setId(double id) {
            this.id = id;
        }

        public double getPanormamicId() {
            return panormamicId;
        }

        public void setPanormamicId(double panormamicId) {
            this.panormamicId = panormamicId;
        }

        public String getImgpath() {
            return imgpath;
        }

        public void setImgpath(String imgpath) {
            this.imgpath = imgpath;
        }

        public String getCamsn() {
            return camsn;
        }

        public void setCamsn(String camsn) {
            this.camsn = camsn;
        }

        public double getImagetimes() {
            return imagetimes;
        }

        public void setImagetimes(double imagetimes) {
            this.imagetimes = imagetimes;
        }

        public String getFtppath() {
            return ftppath;
        }

        public void setFtppath(String ftppath) {
            this.ftppath = ftppath;
        }

        public int getColnum() {
            return colnum;
        }

        public void setColnum(int colnum) {
            this.colnum = colnum;
        }

        public int getRownum() {
            return rownum;
        }

        public void setRownum(int rownum) {
            this.rownum = rownum;
        }

        public double getObd() {
            return Obd;
        }

        public void setObd(double Obd) {
            this.Obd = Obd;
        }

        public String getAddTime() {
            return AddTime;
        }

        public void setAddTime(String AddTime) {
            this.AddTime = AddTime;
        }

        public String getAdjust_across() {
            return adjust_across;
        }

        public void setAdjust_across(String adjust_across) {
            this.adjust_across = adjust_across;
        }

        public String getAdjust_vertical() {
            return adjust_vertical;
        }

        public void setAdjust_vertical(String adjust_vertical) {
            this.adjust_vertical = adjust_vertical;
        }

        public String getAdjust_inclined() {
            return adjust_inclined;
        }

        public void setAdjust_inclined(String adjust_inclined) {
            this.adjust_inclined = adjust_inclined;
        }

        public double getAha() {
            return Aha;
        }

        public void setAha(double Aha) {
            this.Aha = Aha;
        }

        public double getAva() {
            return Ava;
        }

        public void setAva(double Ava) {
            this.Ava = Ava;
        }
    }
}
