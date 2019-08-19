package com.hc.hmsmoblie.base;

import android.support.v7.widget.RecyclerView;

import com.classic.adapter.BaseAdapterHelper;

/**
 *
 */
public class YcViewHolder extends RecyclerView.ViewHolder {
    private BaseAdapterHelper mAdapterHelper;

    public YcViewHolder(BaseAdapterHelper adapterHelper) {
        super(adapterHelper.getView());
        this.mAdapterHelper = adapterHelper;
    }

    public BaseAdapterHelper getAdapterHelper() {
        return mAdapterHelper;
    }
}