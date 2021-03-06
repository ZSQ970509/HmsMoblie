package com.hc.hmsmoblie.widget.vh;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.hc.hmsmoblie.R;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */

public abstract class VHAdapterPri<T> extends RecyclerView.Adapter<VHViewHolder> {
    private Context mContext;
    @LayoutRes
    private int mLayoutId;
    private int mFixX;
    private ArrayList<View> mMoveViewList = new ArrayList<>();
    private List<T> mData = new ArrayList<>();
    private boolean isHasLoadMoreView = true;//是否有加载更多视图
    private boolean isLoadMoreFinal = false;//是否有加载完成

    public VHAdapterPri(Context mContext, int mLayoutId, List<T> mData) {
        this.mContext = mContext;
        this.mLayoutId = mLayoutId;
        this.mData = mData;
    }

    @Override
    public VHViewHolder onCreateViewHolder(ViewGroup parent, @ViewType int viewType) {
        View itemView = null;
        switch (viewType) {
            case ViewType.VIEW_EMPTY:
                itemView = LayoutInflater.from(mContext).inflate(mLayoutId, parent, false);
                break;
            case ViewType.VIEW_ITEM:
                itemView = LayoutInflater.from(mContext).inflate(mLayoutId, parent, false);
                break;
            case ViewType.VIEW_LOAD_MORE:
                itemView = LayoutInflater.from(mContext).inflate(R.layout.widget_load_more, parent, false);
                break;
        }
        VHViewHolder holder = new VHViewHolder(itemView);
        return holder;
    }

    @Override
    public @ViewType
    int getItemViewType(int position) {
        if (getItemCount() == 0) {
            return ViewType.VIEW_EMPTY;
        } else if (isHasLoadMoreView && position == getItemCount() - 1) {
            return ViewType.VIEW_LOAD_MORE;
        } else if (position < getItemCount()) {
            return ViewType.VIEW_ITEM;
        }
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(VHViewHolder helper, int position) {
        if (position == getItemCount() - 1 && isHasLoadMoreView) {
            if (isLoadMoreFinal) {
                helper.setText(R.id.widgetLoadMoreTv, mContext.getResources().getString(R.string.LoadMoreFinal));
            } else {
                helper.setText(R.id.widgetLoadMoreTv, mContext.getResources().getString(R.string.LoadingMore));
                loadMore();
            }
            return;
        } else {
            LinearLayout moveLayout = helper.getView(R.id.id_move_layout);
            mMoveViewList.add(moveLayout);
            moveLayout.scrollTo(mFixX, 0);
            bindData(helper, getItem(position), position);
        }
    }

    public T getItem(int position) {
        if (mData != null && mData.size() > position)
            return mData.get(position);
        else return null;
    }

    @Override
    public int getItemCount() {
        int count = mData.size();
        if (isHasLoadMoreView) {
            count++;
        }
        return count;
    }

    protected abstract void bindData(VHViewHolder helper, T item, int position);

    public void refreshLayoutMove(int moveOffsetX) {
        if (null != mMoveViewList) {
            for (int i = 0; i < mMoveViewList.size(); i++) {
                //使每个item随着手指向右滚动
                mMoveViewList.get(i).scrollTo(moveOffsetX, 0);
            }
        }
    }

    public int getFixX() {
        return mFixX;
    }

    public void setFixX(int fixX) {
        mFixX = fixX;
    }

    public void finishLoadMor() {
        isLoadMoreFinal = true;
    }

    public void resetLoadMore() {
        isLoadMoreFinal = false;
    }
    public void add(T t){
        mData.add(t);
        notifyDataSetChanged();
    }
    public abstract void loadMore();
}
