package com.hc.hmsmoblie.bean.json;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/6/28.
 */

public class TiltSensorStateJson implements Serializable{

    /**
     * totalCount : 1
     * pageNo : 0
     * pageSize : 1
     * devices : [{"deviceId":"87d152f1-3380-4a49-b258-0d8f63d74429","gatewayId":"87d152f1-3380-4a49-b258-0d8f63d74429","nodeType":"GATEWAY","createTime":"20181027T151614Z","lastModifiedTime":"20181107T070555Z","deviceInfo":{"nodeId":"869657038376197","name":"FF07-869657038376197","description":null,"manufacturerId":"HuiChuang","manufacturerName":"HuiChuang","mac":null,"location":"Shenzhen","deviceType":"QingJiaoV21","model":"QingJiaoV21","swVersion":null,"fwVersion":null,"hwVersion":null,"protocolType":"CoAP","bridgeId":null,"status":"ONLINE","statusDetail":"NONE","mute":"FALSE","supportedSecurity":null,"isSecurity":null,"signalStrength":null,"sigVersion":null,"serialNumber":null,"batteryLevel":null},"services":[{"serviceId":"DevInfo","serviceType":"DevInfo","data":{"IDPri":1,"IDSec":1,"IMEI":"869657038376197","HwVer":10,"SwVer":11},"eventTime":"20181107T052926Z","serviceInfo":null},{"serviceId":"InitVal","serviceType":"InitVal","data":{"SlopeInit_X":-12509,"SlopeInit_Y":18100,"DevDir":84},"eventTime":"20181107T052932Z","serviceInfo":null},{"serviceId":"CurVal","serviceType":"CurVal","data":{"SlopeCur_X":-12740,"SlopeCur_Y":18280,"SlopeDelta_X":230,"SlopeDelta_Y":180,"DistCur":4423,"DeltaDir_X":354,"DeltaDir_Y":264,"Bat":0,"Signal":70,"State":0,"Warn":0},"eventTime":"20181107T070555Z","serviceInfo":null},{"serviceId":"Setting","serviceType":"Setting","data":{"Slope_Thres_X":1800,"Slope_Thres_Y":1800,"RptPer":300,"RptPer_warn":10},"eventTime":"20181107T065502Z","serviceInfo":null}]}]
     */

    private int totalCount;
    private int pageNo;
    private int pageSize;
    private List<DevicesBean> devices;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<DevicesBean> getDevices() {
        return devices;
    }

    public void setDevices(List<DevicesBean> devices) {
        this.devices = devices;
    }

    public static class DevicesBean {
        /**
         * deviceId : 87d152f1-3380-4a49-b258-0d8f63d74429
         * gatewayId : 87d152f1-3380-4a49-b258-0d8f63d74429
         * nodeType : GATEWAY
         * createTime : 20181027T151614Z
         * lastModifiedTime : 20181107T070555Z
         * deviceInfo : {"nodeId":"869657038376197","name":"FF07-869657038376197","description":null,"manufacturerId":"HuiChuang","manufacturerName":"HuiChuang","mac":null,"location":"Shenzhen","deviceType":"QingJiaoV21","model":"QingJiaoV21","swVersion":null,"fwVersion":null,"hwVersion":null,"protocolType":"CoAP","bridgeId":null,"status":"ONLINE","statusDetail":"NONE","mute":"FALSE","supportedSecurity":null,"isSecurity":null,"signalStrength":null,"sigVersion":null,"serialNumber":null,"batteryLevel":null}
         * services : [{"serviceId":"DevInfo","serviceType":"DevInfo","data":{"IDPri":1,"IDSec":1,"IMEI":"869657038376197","HwVer":10,"SwVer":11},"eventTime":"20181107T052926Z","serviceInfo":null},{"serviceId":"InitVal","serviceType":"InitVal","data":{"SlopeInit_X":-12509,"SlopeInit_Y":18100,"DevDir":84},"eventTime":"20181107T052932Z","serviceInfo":null},{"serviceId":"CurVal","serviceType":"CurVal","data":{"SlopeCur_X":-12740,"SlopeCur_Y":18280,"SlopeDelta_X":230,"SlopeDelta_Y":180,"DistCur":4423,"DeltaDir_X":354,"DeltaDir_Y":264,"Bat":0,"Signal":70,"State":0,"Warn":0},"eventTime":"20181107T070555Z","serviceInfo":null},{"serviceId":"Setting","serviceType":"Setting","data":{"Slope_Thres_X":1800,"Slope_Thres_Y":1800,"RptPer":300,"RptPer_warn":10},"eventTime":"20181107T065502Z","serviceInfo":null}]
         */

        private String deviceId;
        private String gatewayId;
        private String nodeType;
        private String createTime;
        private String lastModifiedTime;
        private DeviceInfoBean deviceInfo;
        private List<ServicesBean> services;

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        public String getGatewayId() {
            return gatewayId;
        }

        public void setGatewayId(String gatewayId) {
            this.gatewayId = gatewayId;
        }

        public String getNodeType() {
            return nodeType;
        }

        public void setNodeType(String nodeType) {
            this.nodeType = nodeType;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getLastModifiedTime() {
            return lastModifiedTime;
        }

        public void setLastModifiedTime(String lastModifiedTime) {
            this.lastModifiedTime = lastModifiedTime;
        }

        public DeviceInfoBean getDeviceInfo() {
            return deviceInfo;
        }

        public void setDeviceInfo(DeviceInfoBean deviceInfo) {
            this.deviceInfo = deviceInfo;
        }

        public List<ServicesBean> getServices() {
            return services;
        }

        public void setServices(List<ServicesBean> services) {
            this.services = services;
        }

        public static class DeviceInfoBean {
            /**
             * nodeId : 869657038376197
             * name : FF07-869657038376197
             * description : null
             * manufacturerId : HuiChuang
             * manufacturerName : HuiChuang
             * mac : null
             * location : Shenzhen
             * deviceType : QingJiaoV21
             * model : QingJiaoV21
             * swVersion : null
             * fwVersion : null
             * hwVersion : null
             * protocolType : CoAP
             * bridgeId : null
             * status : ONLINE
             * statusDetail : NONE
             * mute : FALSE
             * supportedSecurity : null
             * isSecurity : null
             * signalStrength : null
             * sigVersion : null
             * serialNumber : null
             * batteryLevel : null
             */

            private String nodeId;
            private String name;
            private Object description;
            private String manufacturerId;
            private String manufacturerName;
            private Object mac;
            private String location;
            private String deviceType;
            private String model;
            private Object swVersion;
            private Object fwVersion;
            private Object hwVersion;
            private String protocolType;
            private Object bridgeId;
            private String status;
            private String statusDetail;
            private String mute;
            private Object supportedSecurity;
            private Object isSecurity;
            private Object signalStrength;
            private Object sigVersion;
            private Object serialNumber;
            private Object batteryLevel;

            public String getNodeId() {
                return nodeId;
            }

            public void setNodeId(String nodeId) {
                this.nodeId = nodeId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Object getDescription() {
                return description;
            }

            public void setDescription(Object description) {
                this.description = description;
            }

            public String getManufacturerId() {
                return manufacturerId;
            }

            public void setManufacturerId(String manufacturerId) {
                this.manufacturerId = manufacturerId;
            }

            public String getManufacturerName() {
                return manufacturerName;
            }

            public void setManufacturerName(String manufacturerName) {
                this.manufacturerName = manufacturerName;
            }

            public Object getMac() {
                return mac;
            }

            public void setMac(Object mac) {
                this.mac = mac;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getDeviceType() {
                return deviceType;
            }

            public void setDeviceType(String deviceType) {
                this.deviceType = deviceType;
            }

            public String getModel() {
                return model;
            }

            public void setModel(String model) {
                this.model = model;
            }

            public Object getSwVersion() {
                return swVersion;
            }

            public void setSwVersion(Object swVersion) {
                this.swVersion = swVersion;
            }

            public Object getFwVersion() {
                return fwVersion;
            }

            public void setFwVersion(Object fwVersion) {
                this.fwVersion = fwVersion;
            }

            public Object getHwVersion() {
                return hwVersion;
            }

            public void setHwVersion(Object hwVersion) {
                this.hwVersion = hwVersion;
            }

            public String getProtocolType() {
                return protocolType;
            }

            public void setProtocolType(String protocolType) {
                this.protocolType = protocolType;
            }

            public Object getBridgeId() {
                return bridgeId;
            }

            public void setBridgeId(Object bridgeId) {
                this.bridgeId = bridgeId;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getStatusDetail() {
                return statusDetail;
            }

            public void setStatusDetail(String statusDetail) {
                this.statusDetail = statusDetail;
            }

            public String getMute() {
                return mute;
            }

            public void setMute(String mute) {
                this.mute = mute;
            }

            public Object getSupportedSecurity() {
                return supportedSecurity;
            }

            public void setSupportedSecurity(Object supportedSecurity) {
                this.supportedSecurity = supportedSecurity;
            }

            public Object getIsSecurity() {
                return isSecurity;
            }

            public void setIsSecurity(Object isSecurity) {
                this.isSecurity = isSecurity;
            }

            public Object getSignalStrength() {
                return signalStrength;
            }

            public void setSignalStrength(Object signalStrength) {
                this.signalStrength = signalStrength;
            }

            public Object getSigVersion() {
                return sigVersion;
            }

            public void setSigVersion(Object sigVersion) {
                this.sigVersion = sigVersion;
            }

            public Object getSerialNumber() {
                return serialNumber;
            }

            public void setSerialNumber(Object serialNumber) {
                this.serialNumber = serialNumber;
            }

            public Object getBatteryLevel() {
                return batteryLevel;
            }

            public void setBatteryLevel(Object batteryLevel) {
                this.batteryLevel = batteryLevel;
            }
        }

        public static class ServicesBean {
            /**
             * serviceId : DevInfo
             * serviceType : DevInfo
             * data : {"IDPri":1,"IDSec":1,"IMEI":"869657038376197","HwVer":10,"SwVer":11}
             * eventTime : 20181107T052926Z
             * serviceInfo : null
             */

            private String serviceId;
            private String serviceType;
            private DataBean data;
            private String eventTime;
            private Object serviceInfo;

            public String getServiceId() {
                return serviceId;
            }

            public void setServiceId(String serviceId) {
                this.serviceId = serviceId;
            }

            public String getServiceType() {
                return serviceType;
            }

            public void setServiceType(String serviceType) {
                this.serviceType = serviceType;
            }

            public DataBean getData() {
                return data;
            }

            public void setData(DataBean data) {
                this.data = data;
            }

            public String getEventTime() {
                return eventTime;
            }

            public void setEventTime(String eventTime) {
                this.eventTime = eventTime;
            }

            public Object getServiceInfo() {
                return serviceInfo;
            }

            public void setServiceInfo(Object serviceInfo) {
                this.serviceInfo = serviceInfo;
            }

            public static class DataBean {
                /**
                 * IDPri : 1
                 * IDSec : 1
                 * IMEI : 869657038376197
                 * HwVer : 10
                 * SwVer : 11
                 */

                private int IDPri;
                private int IDSec;
                private String IMEI;
                private int HwVer;
                private int SwVer;
                private int Bat;
                private int Signal;
                private int State;
                private int Warn;
                private int Slope_Thres_X;
                private int Slope_Thres_Y;
                private int RptPer;
                private int RptPer_warn;
                public int getSlope_Thres_X() {
                    return Slope_Thres_X;
                }

                public void setSlope_Thres_X(int Slope_Thres_X) {
                    this.Slope_Thres_X = Slope_Thres_X;
                }

                public int getSlope_Thres_Y() {
                    return Slope_Thres_Y;
                }

                public void setSlope_Thres_Y(int slope_Thres_Y) {
                    Slope_Thres_Y = slope_Thres_Y;
                }

                public int getRptPer() {
                    return RptPer;
                }

                public void setRptPer(int rptPer) {
                    RptPer = rptPer;
                }

                public int getRptPer_warn() {
                    return RptPer_warn;
                }

                public void setRptPer_warn(int rptPer_warn) {
                    RptPer_warn = rptPer_warn;
                }

                public int getBat() {
                    return Bat;
                }

                public void setBat(int Bat) {
                    this.Bat = Bat;
                }

                public int getSignal() {
                    return Signal;
                }

                public void setSignal(int Signal) {
                    this.Signal = Signal;
                }

                public int getState() {
                    return State;
                }

                public void setState(int State) {
                    this.State = State;
                }

                public int getWarn() {
                    return Warn;
                }

                public void setWarn(int Warn) {
                    this.Warn = Warn;
                }

                public int getIDPri() {
                    return IDPri;
                }

                public void setIDPri(int IDPri) {
                    this.IDPri = IDPri;
                }

                public int getIDSec() {
                    return IDSec;
                }

                public void setIDSec(int IDSec) {
                    this.IDSec = IDSec;
                }

                public String getIMEI() {
                    return IMEI;
                }

                public void setIMEI(String IMEI) {
                    this.IMEI = IMEI;
                }

                public int getHwVer() {
                    return HwVer;
                }

                public void setHwVer(int HwVer) {
                    this.HwVer = HwVer;
                }

                public int getSwVer() {
                    return SwVer;
                }

                public void setSwVer(int SwVer) {
                    this.SwVer = SwVer;
                }
            }
        }
    }
}
