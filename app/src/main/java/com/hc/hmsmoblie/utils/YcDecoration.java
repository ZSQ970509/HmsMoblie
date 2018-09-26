package com.hc.hmsmoblie.utils;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

/**
 *  通用分割线
 */

public class YcDecoration extends RecyclerView.ItemDecoration {
    private int mSpace = 0;//item间的间距
    private int mNum = 2;//滑动方向单行item的数量

    public YcDecoration(int space) {
        this.mSpace = space;
    }

    public YcDecoration(int space, int num) {
        this.mSpace = space;
        this.mNum = num;
    }

    public int getSpace() {
        return mSpace;
    }
    public int getNum() {
        return mNum;
    }
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int pos;
        if (view.getLayoutParams() instanceof StaggeredGridLayoutManager.LayoutParams) {//瀑布流布局
            mNum = ((StaggeredGridLayoutManager) parent.getLayoutManager()).getSpanCount();
            pos = ((StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams()).getSpanIndex();//根据params.getSpanIndex()来判断左右边确定分割线
        } else {
            pos = parent.getChildAdapterPosition(view);
        }
        outRect.top = getSpace();
        if (mNum == 1) {
            outRect.left = getSpace();
            outRect.right = getSpace();
        } else if (pos % mNum == 0) {
            outRect.left = getSpace();
            outRect.right = getSpace() / 2;
        } else {
            outRect.left = getSpace() / 2;
            outRect.right = getSpace();
        }
    }
}
