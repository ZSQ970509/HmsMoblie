package com.hc.hmsmoblie.bean.domain;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 *
 */
public class ProjectAdapterBean implements MultiItemEntity {
    public static final int TYPE_SERIES = 1;
    public static final int TYPE_PESALE = 2;
    private int itemType;
    @Override
    public int getItemType() {
        return itemType;
    }
}
