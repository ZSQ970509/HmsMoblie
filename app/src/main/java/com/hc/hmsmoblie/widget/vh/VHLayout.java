package com.hc.hmsmoblie.widget.vh;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hc.hmsmoblie.R;

import java.util.ArrayList;
import java.util.List;

import static com.hc.hmsmoblie.utils.DensityUtils.dip2px;

/**
 *
 */

public class VHLayout extends RelativeLayout {
    //右侧标题栏里的标题
    private List<TextView> mTitleTvs = new ArrayList<>();
    //头部title布局
    private LinearLayout mRightTitleLayout;
    //手指按下时的位置
    private float mStartX = 0;
    //滑动时和按下时的差值
    private int mMoveOffsetX = 0;
    //最大可滑动差值
    private int mFixX = 0;
    //左边标题集合
    private String[] mLeftTextList;
    //左边标题的宽度集合
    private int[] mLeftTextWidthList;
    //右边标题集合
    private String[] mRightTitleList = new String[]{};
    //右边标题的宽度集合
    private int[] mRightTitleWidthList = null;
    //展示数据时使用的RecycleView
    private RecyclerView mRecyclerView;
    //RecycleView的Adapter
    private VHAdapter mAdapter;
    //需要滑动的View集合
//    private ArrayList<View> mMoveViewList = new ArrayList();
    private Context context;
    //右边可滑动的总宽度
    private int mRightTotalWidth = 0;
    //右边单个view的宽度
    private int mRightItemWidth = 60;
    //左边view的宽度
    private int mLeftViewWidth = 60;
    //左边view的高度
    private int mLeftViewHeight = 40;
    //触发拦截手势的最小值
    private int mTriggerMoveDis = 30;

    public VHLayout(Context context) {
        this(context, null);
    }

    public VHLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VHLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    private void initView() {
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.addView(createHeadLayout());
        linearLayout.addView(createMoveRecyclerView());
        addView(linearLayout, new LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    /**
     * 创建头部布局
     *
     * @return
     */
    private View createHeadLayout() {
        LinearLayout headLayout = new LinearLayout(getContext());
        headLayout.setGravity(Gravity.CENTER);
        LinearLayout leftLayout = new LinearLayout(getContext());
        addListHeaderTextView(mLeftTextList[0], mLeftTextWidthList[0], leftLayout);
        leftLayout.setGravity(Gravity.CENTER);
        headLayout.addView(leftLayout, 0, new ViewGroup.LayoutParams(dip2px(context, mLeftViewWidth), dip2px(context, mLeftViewHeight)));

        mRightTitleLayout = new LinearLayout(getContext());
        for (int i = 0; i < mRightTitleList.length; i++) {
            addListHeaderTextView(mRightTitleList[i], mRightTitleWidthList[i], mRightTitleLayout);
        }
        headLayout.addView(mRightTitleLayout);
        return headLayout;
    }

    SwipeRefreshLayout mSwipeRefreshLayout;

    /**
     * 创建数据展示布局
     *
     * @return
     */
    private View createMoveRecyclerView() {
//        RelativeLayout linearLayout = new RelativeLayout(getContext());
        mSwipeRefreshLayout = new SwipeRefreshLayout(getContext());
        mRecyclerView = new RecyclerView(getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        if (null != mAdapter) {
            mRecyclerView.setAdapter(mAdapter);
        }
        mSwipeRefreshLayout.addView(mRecyclerView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            if (mOnRefresh != null)
                mOnRefresh.onRefresh();
        });
//        linearLayout.addView(mSwipeRefreshLayout, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        return mSwipeRefreshLayout;
    }

    /**
     * 设置刷新监听
     *
     * @param onRefresh
     */
    public void setRefresh(OnRefresh onRefresh) {
        mOnRefresh = onRefresh;
    }

    /**
     * 开始刷新
     */
    public void startRefresh() {
        if (mSwipeRefreshLayout != null && !mSwipeRefreshLayout.isRefreshing())
            mSwipeRefreshLayout.setRefreshing(true);
    }

    /**
     * 结束刷新
     */
    public void finishRefresh() {
        if (mSwipeRefreshLayout != null && mSwipeRefreshLayout.isRefreshing())
            mSwipeRefreshLayout.setRefreshing(false);
    }

    private OnRefresh mOnRefresh;

    public interface OnRefresh {
        void onRefresh();
    }

    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    /**
     * 设置adapter
     *
     * @param adapter
     */
    public void setAdapter(VHAdapter adapter) {
        mAdapter = adapter;
        initView();
    }

    /**
     * 设置头部title单个布局
     *
     * @param headerName
     * @param width
     * @param leftLayout
     * @return
     */
    private TextView addListHeaderTextView(String headerName, int width, LinearLayout leftLayout) {
        TextView textView = new TextView(getContext());
        textView.setText(headerName);
        textView.setGravity(Gravity.CENTER);
        TextPaint tp = textView.getPaint();
        tp.setFakeBoldText(true);
        leftLayout.addView(textView, width, dip2px(context, 50));
        mTitleTvs.add(textView);
        return textView;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mStartX = ev.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = (int) Math.abs(ev.getX() - mStartX);
                if (offsetX > mTriggerMoveDis) {//水平移动大于30触发拦截
                    return true;
                } else {
                    return false;
                }
        }
        return super.onInterceptTouchEvent(ev);
    }

    /**
     * 右边可滑动的总宽度
     *
     * @return
     */
    private int rightTitleTotalWidth() {
        if (0 == mRightTotalWidth) {
            for (int i = 0; i < mRightTitleWidthList.length; i++) {
                mRightTotalWidth = mRightTotalWidth + mRightTitleWidthList[i];
            }
        }
        return mRightTotalWidth;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                return true;
            case MotionEvent.ACTION_MOVE:
                int offsetX = (int) Math.abs(event.getX() - mStartX);
                if (offsetX > 30) {
                    mMoveOffsetX = (int) (mStartX - event.getX() + mFixX);
                    if (0 > mMoveOffsetX) {
                        mMoveOffsetX = 0;
                    } else {
                        //当滑动大于最大宽度时，不在滑动（右边到头了）
                        if ((mRightTitleLayout.getWidth() + mMoveOffsetX) > rightTitleTotalWidth()) {
                            mMoveOffsetX = rightTitleTotalWidth() - mRightTitleLayout.getWidth();
                        }
                    }
                    //跟随手指向右滚动
                    mRightTitleLayout.scrollTo(mMoveOffsetX, 0);
                    mAdapter.refreshLayoutMove(mMoveOffsetX);
                }
                break;
            case MotionEvent.ACTION_UP:
                mFixX = mMoveOffsetX; //设置最大水平平移的宽度
                //每次左右滑动都要更新VHAdapter中的mFixX的值
                mAdapter.setFixX(mFixX);
                break;
        }
        return super.onTouchEvent(event);
    }

    /**
     * j
     * 列表头部数据
     *
     * @param headerListData
     */
    public void setHeaderListData(String[] headerListData) {
        mRightTitleList = headerListData;
        mRightTitleWidthList = new int[headerListData.length];
        for (int i = 0; i < headerListData.length; i++) {
//            mRightTitleWidthList[i] = dip2px(context, mRightItemWidth);
            switch (i){
                case 1:
                    mRightTitleWidthList[i] = getResources().getDimensionPixelSize(R.dimen.tiltSensorItemTimeWidth);
                    break;
                case 2:
                    mRightTitleWidthList[i] = getResources().getDimensionPixelSize(R.dimen.tiltSensorItemSamllWidth);
                    break;
                case 3:
                    mRightTitleWidthList[i] = getResources().getDimensionPixelSize(R.dimen.tiltSensorItemSamllWidth);
                    break;
                case 4:
                    mRightTitleWidthList[i] = getResources().getDimensionPixelSize(R.dimen.tiltSensorItemSamllWidth);
                    break;
                case 6:
                    mRightTitleWidthList[i] = getResources().getDimensionPixelSize(R.dimen.tiltSensorItemBigWidth);
                    break;
                case 8:
                    mRightTitleWidthList[i] = getResources().getDimensionPixelSize(R.dimen.tiltSensorItemBigWidth);
                    break;
                case 10:
                    mRightTitleWidthList[i] = getResources().getDimensionPixelSize(R.dimen.tiltSensorItemBigWidth);
                    break;
                case 11:
                    mRightTitleWidthList[i] = getResources().getDimensionPixelSize(R.dimen.tiltSensorItemTimeWidth);
                    break;
                case 12:
                    mRightTitleWidthList[i] = getResources().getDimensionPixelSize(R.dimen.tiltSensorItemTimeWidth);
                    break;
                default:
                    mRightTitleWidthList[i] = getResources().getDimensionPixelSize(R.dimen.tiltSensorItemWidth);
                    break;
            }
        }
        mLeftTextWidthList = new int[]{getResources().getDimensionPixelSize(R.dimen.tiltSensorItemWidth)};
        mLeftTextList = new String[]{"序号"};
    }

    public void setTitleVisibility(SparseArray<Boolean> titleVisibility) {
        for (int i = 0; i < mTitleTvs.size(); i++) {
            if (titleVisibility.get(i) == null || titleVisibility.get(i)) {
                mTitleTvs.get(i).setVisibility(VISIBLE);
            } else {
                mTitleTvs.get(i).setVisibility(GONE);
            }
        }
        mAdapter.setTitleVisibility(titleVisibility);
    }
}
