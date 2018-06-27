package com.hc.hmsmoblie.bean.json;

import java.util.List;

/**
 *
 */

public class ImageLogWideAngleJson {

    /**
     * List : [{"RecordId":292583,"CamSn":"155655654_001","CamId":1032871,"ProjId":38788,"StartTime":"2018-06-06 04:53:57","EndTime":"2018-06-06 04:53:57","PuzzleImg":"http://ftp.jsqqy.com:8122/upfile/Puzzle/ptimg/155655654_001/292583_201806060558275267.jpg","ImageTimes":213,"Pictrait":"8917,4429","CutImage":"http://ftp.jsqqy.com:8122/upfile/Puzzle/ptimg/155655654_001/292583_201806060558275267.jpg","PanoramicCount":8},{"RecordId":203336,"CamSn":"155655654_001","CamId":1032871,"ProjId":38788,"StartTime":"2018-04-16 14:17:45","EndTime":"2018-04-16 14:17:45","PuzzleImg":"http://ftp.jsqqy.com:8122/upfile/Puzzle/ptimg/155655654_001/203336_201804161511121156.jpg","ImageTimes":168,"Pictrait":"9027,3772","CutImage":"http://ftp.jsqqy.com:8122/upfile/Puzzle/ptimg/155655654_001/203336_201804161511121156.jpg","PanoramicCount":1454},{"RecordId":202949,"CamSn":"155655654_001","CamId":1032871,"ProjId":38788,"StartTime":"2018-04-16 07:52:52","EndTime":"2018-04-16 07:52:52","PuzzleImg":"http://ftp.jsqqy.com:8122/upfile/Puzzle/ptimg/155655654_001/202949_201804161251136468.jpg","ImageTimes":167,"Pictrait":"9027,3772","CutImage":"http://ftp.jsqqy.com:8122/upfile/Puzzle/ptimg/155655654_001/202949_201804161251136468.jpg","PanoramicCount":1434},{"RecordId":202160,"CamSn":"155655654_001","CamId":1032871,"ProjId":38788,"StartTime":"2018-04-15 19:51:37","EndTime":"2018-04-15 19:51:37","PuzzleImg":"http://ftp.jsqqy.com:8122/upfile/Puzzle/ptimg/155655654_001/202160_201804152323467700.jpg","ImageTimes":165,"Pictrait":"9027,3772","CutImage":"http://ftp.jsqqy.com:8122/upfile/Puzzle/ptimg/155655654_001/202160_201804152323467700.jpg","PanoramicCount":1456},{"RecordId":201768,"CamSn":"155655654_001","CamId":1032871,"ProjId":38788,"StartTime":"2018-04-15 14:23:31","EndTime":"2018-04-15 14:23:31","PuzzleImg":"http://ftp.jsqqy.com:8122/upfile/Puzzle/ptimg/155655654_001/201768_201804151626121606.jpg","ImageTimes":164,"Pictrait":"9027,3772","CutImage":"http://ftp.jsqqy.com:8122/upfile/Puzzle/ptimg/155655654_001/201768_201804151626121606.jpg","PanoramicCount":1461},{"RecordId":201367,"CamSn":"155655654_001","CamId":1032871,"ProjId":38788,"StartTime":"2018-04-15 07:51:39","EndTime":"2018-04-15 07:51:39","PuzzleImg":"http://ftp.jsqqy.com:8122/upfile/Puzzle/ptimg/155655654_001/201367_201804151100376762.jpg","ImageTimes":163,"Pictrait":"9027,3772","CutImage":"http://ftp.jsqqy.com:8122/upfile/Puzzle/ptimg/155655654_001/201367_201804151100376762.jpg","PanoramicCount":1447},{"RecordId":200554,"CamSn":"155655654_001","CamId":1032871,"ProjId":38788,"StartTime":"2018-04-14 19:36:29","EndTime":"2018-04-14 19:36:29","PuzzleImg":"http://ftp.jsqqy.com:8122/upfile/Puzzle/ptimg/155655654_001/200554_201804150324135200.jpg","ImageTimes":161,"Pictrait":"9027,3772","CutImage":"http://ftp.jsqqy.com:8122/upfile/Puzzle/ptimg/155655654_001/200554_201804150324135200.jpg","PanoramicCount":1451},{"RecordId":200260,"CamSn":"155655654_001","CamId":1032871,"ProjId":38788,"StartTime":"2018-04-14 14:24:05","EndTime":"2018-04-14 14:24:05","PuzzleImg":"http://ftp.jsqqy.com:8122/upfile/Puzzle/ptimg/155655654_001/200260_201804150319537856.jpg","ImageTimes":160,"Pictrait":"9027,3772","CutImage":"http://ftp.jsqqy.com:8122/upfile/Puzzle/ptimg/155655654_001/200260_201804150319537856.jpg","PanoramicCount":1345},{"RecordId":199856,"CamSn":"155655654_001","CamId":1032871,"ProjId":38788,"StartTime":"2018-04-14 07:51:53","EndTime":"2018-04-14 07:51:53","PuzzleImg":"http://ftp.jsqqy.com:8122/upfile/Puzzle/ptimg/155655654_001/199856_201804141047488793.jpg","ImageTimes":159,"Pictrait":"9027,3772","CutImage":"http://ftp.jsqqy.com:8122/upfile/Puzzle/ptimg/155655654_001/199856_201804141047488793.jpg","PanoramicCount":1333},{"RecordId":199055,"CamSn":"155655654_001","CamId":1032871,"ProjId":38788,"StartTime":"2018-04-13 19:54:07","EndTime":"2018-04-13 19:54:07","PuzzleImg":"http://ftp.jsqqy.com:8122/upfile/Puzzle/ptimg/155655654_001/199055_201804140606186293.jpg","ImageTimes":157,"Pictrait":"9027,3772","CutImage":"http://ftp.jsqqy.com:8122/upfile/Puzzle/ptimg/155655654_001/199055_201804140606186293.jpg","PanoramicCount":1373}]
     * total : 139
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
         * RecordId : 292583
         * CamSn : 155655654_001
         * CamId : 1032871
         * ProjId : 38788
         * StartTime : 2018-06-06 04:53:57
         * EndTime : 2018-06-06 04:53:57
         * PuzzleImg : http://ftp.jsqqy.com:8122/upfile/Puzzle/ptimg/155655654_001/292583_201806060558275267.jpg
         * ImageTimes : 213
         * Pictrait : 8917,4429
         * CutImage : http://ftp.jsqqy.com:8122/upfile/Puzzle/ptimg/155655654_001/292583_201806060558275267.jpg
         * PanoramicCount : 8
         */

        private int RecordId;
        private String CamSn;
        private int CamId;
        private int ProjId;
        private String StartTime;
        private String EndTime;
        private String PuzzleImg;
        private int ImageTimes;
        private String Pictrait;
        private String CutImage;
        private int PanoramicCount;

        public int getRecordId() {
            return RecordId;
        }

        public void setRecordId(int RecordId) {
            this.RecordId = RecordId;
        }

        public String getCamSn() {
            return CamSn;
        }

        public void setCamSn(String CamSn) {
            this.CamSn = CamSn;
        }

        public int getCamId() {
            return CamId;
        }

        public void setCamId(int CamId) {
            this.CamId = CamId;
        }

        public int getProjId() {
            return ProjId;
        }

        public void setProjId(int ProjId) {
            this.ProjId = ProjId;
        }

        public String getStartTime() {
            return StartTime;
        }

        public void setStartTime(String StartTime) {
            this.StartTime = StartTime;
        }

        public String getEndTime() {
            return EndTime;
        }

        public void setEndTime(String EndTime) {
            this.EndTime = EndTime;
        }

        public String getPuzzleImg() {
            return PuzzleImg;
        }

        public void setPuzzleImg(String PuzzleImg) {
            this.PuzzleImg = PuzzleImg;
        }

        public int getImageTimes() {
            return ImageTimes;
        }

        public void setImageTimes(int ImageTimes) {
            this.ImageTimes = ImageTimes;
        }

        public String getPictrait() {
            return Pictrait;
        }

        public void setPictrait(String Pictrait) {
            this.Pictrait = Pictrait;
        }

        public String getCutImage() {
            return CutImage;
        }

        public void setCutImage(String CutImage) {
            this.CutImage = CutImage;
        }

        public int getPanoramicCount() {
            return PanoramicCount;
        }

        public void setPanoramicCount(int PanoramicCount) {
            this.PanoramicCount = PanoramicCount;
        }
    }
}
