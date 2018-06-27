package com.hc.hmsmoblie.bean.json;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/6/26.
 */

public class MainJson implements Serializable{

    /**
     * MdID : 1381
     * MdSysID : 111
     * MdName : 视频监控
     * MdCode : null
     * MdParentID : 0
     * MdPageUrl : project-list.aspx?systemId=11
     * MdIcon : iconfont icon-jiankong
     * MdNotes : null
     * MdOrder : 10100
     * MdReMark : null
     * MdIsShow : true
     * MdIsEnable : true
     * ActionKey : null
     * InstanceKey : null
     * MdUpdateTime : /Date(1528257085000+0800)/
     */

    private int MdID;
    private int MdSysID;
    private String MdName;
    private Object MdCode;
    private int MdParentID;
    private String MdPageUrl;
    private String MdIcon;
    private Object MdNotes;
    private int MdOrder;
    private Object MdReMark;
    private boolean MdIsShow;
    private boolean MdIsEnable;
    private Object ActionKey;
    private Object InstanceKey;
    private String MdUpdateTime;

    public int getMdID() {
        return MdID;
    }

    public void setMdID(int MdID) {
        this.MdID = MdID;
    }

    public int getMdSysID() {
        return MdSysID;
    }

    public void setMdSysID(int MdSysID) {
        this.MdSysID = MdSysID;
    }

    public String getMdName() {
        return MdName;
    }

    public void setMdName(String MdName) {
        this.MdName = MdName;
    }

    public Object getMdCode() {
        return MdCode;
    }

    public void setMdCode(Object MdCode) {
        this.MdCode = MdCode;
    }

    public int getMdParentID() {
        return MdParentID;
    }

    public void setMdParentID(int MdParentID) {
        this.MdParentID = MdParentID;
    }

    public String getMdPageUrl() {
        return MdPageUrl;
    }

    public void setMdPageUrl(String MdPageUrl) {
        this.MdPageUrl = MdPageUrl;
    }

    public String getMdIcon() {
        return MdIcon;
    }

    public void setMdIcon(String MdIcon) {
        this.MdIcon = MdIcon;
    }

    public Object getMdNotes() {
        return MdNotes;
    }

    public void setMdNotes(Object MdNotes) {
        this.MdNotes = MdNotes;
    }

    public int getMdOrder() {
        return MdOrder;
    }

    public void setMdOrder(int MdOrder) {
        this.MdOrder = MdOrder;
    }

    public Object getMdReMark() {
        return MdReMark;
    }

    public void setMdReMark(Object MdReMark) {
        this.MdReMark = MdReMark;
    }

    public boolean isMdIsShow() {
        return MdIsShow;
    }

    public void setMdIsShow(boolean MdIsShow) {
        this.MdIsShow = MdIsShow;
    }

    public boolean isMdIsEnable() {
        return MdIsEnable;
    }

    public void setMdIsEnable(boolean MdIsEnable) {
        this.MdIsEnable = MdIsEnable;
    }

    public Object getActionKey() {
        return ActionKey;
    }

    public void setActionKey(Object ActionKey) {
        this.ActionKey = ActionKey;
    }

    public Object getInstanceKey() {
        return InstanceKey;
    }

    public void setInstanceKey(Object InstanceKey) {
        this.InstanceKey = InstanceKey;
    }

    public String getMdUpdateTime() {
        return MdUpdateTime;
    }

    public void setMdUpdateTime(String MdUpdateTime) {
        this.MdUpdateTime = MdUpdateTime;
    }
}
