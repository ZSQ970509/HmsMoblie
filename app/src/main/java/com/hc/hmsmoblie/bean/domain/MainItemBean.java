package com.hc.hmsmoblie.bean.domain;

/**
 *  首页选项数据
 */

public class MainItemBean {
    private String id;
    private String name;
    public MainItemBean(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
