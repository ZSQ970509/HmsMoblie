package com.hc.hmsmoblie.fragment;

import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hc.hmsmoblie.R;
import com.hc.hmsmoblie.activity.LadderControlPictureActivity;
import com.hc.hmsmoblie.bean.json.LadderControlDetailsErrorJson;
import com.hc.hmsmoblie.bean.json.LadderControlDetailsOperationJson;
import com.hc.hmsmoblie.mvp.contact.LadderControlDetailsOperationC;
import com.hc.hmsmoblie.mvp.presenter.LadderControlDetailsOperationP;
import com.hc.hmsmoblie.utils.EmptyUtils;
import com.yc.yclibrary.base.YcLazyFragment;
import com.yc.yclibrary.base.YcMvpLazyFragment;
import com.yc.yclibrary.exception.ApiException;

import butterknife.BindView;

/**
 * 操作记录
 */

public class LadderControlOperationFragment extends YcMvpLazyFragment<LadderControlDetailsOperationP> implements LadderControlDetailsOperationC.V {
    @BindView(R.id.rvLadderControlOperation)
    RecyclerView mRecyclerView;
    @BindView(R.id.srlLadderControlOperation)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private int mPageIndex = 1;
    private final int mPageSize = 10;
    private int mPageTotal = 1;
    private String mDeviceId;
    private BaseItemDraggableAdapter<LadderControlDetailsOperationJson.ListBean, BaseViewHolder> mAdapter;

    public static LadderControlOperationFragment newInstance(String deviceId) {
        LadderControlOperationFragment fragment = new LadderControlOperationFragment();
        fragment.mDeviceId = deviceId;
        return fragment;
    }

    @Override
    protected LadderControlDetailsOperationP loadPresenter() {
        return new LadderControlDetailsOperationP();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.ladder_control_details_operation_fragment;
    }

    @Override
    public void initView() {
        mAdapter = new BaseItemDraggableAdapter<LadderControlDetailsOperationJson.ListBean, BaseViewHolder>(R.layout.ladder_control_details_error_item, null) {
            @Override
            protected void convert(BaseViewHolder helper, LadderControlDetailsOperationJson.ListBean item) {
                helper.setText(R.id.tvDetailsErrorName, "操作员：" + EmptyUtils.getString(item.getRealName()));
                helper.setText(R.id.tvDetailsErrorAccount, "操作账号：" + EmptyUtils.getString(item.getUserName()));
                helper.setText(R.id.tvDetailsErrorState, "操作情况：" + (item.getState() == 0 ? "打开" : "关闭"));
                helper.setText(R.id.tvDetailsErrorTime, "操作时间：" + EmptyUtils.getString(item.getCreateTime()));
                helper.setVisible(R.id.llDetailsErrorImg,false);
            }
        };
        mAdapter.setOnLoadMoreListener(() -> {
            if (mPageIndex >= mPageTotal) {
                showToast("已经是最后一页了！");
                mAdapter.loadMoreEnd();
            } else {
                mPageIndex++;
                searchDeviceList();
            }
        }, mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mSwipeRefreshLayout.setOnRefreshListener(() -> initRefreshAndLoadMore());
    }
    @Override
    protected void lazyLoad() {
        if (mIsFragmentVisible && mIsInitView && mIsFirstLazyLoad) {
            mIsFirstLazyLoad = false;
            if (mIsDataEmpty) {
                showToast(R.string.view_empty);
            } else {
                mSwipeRefreshLayout.setRefreshing(true);
                initRefreshAndLoadMore();
            }
        }
    }
    private void initRefreshAndLoadMore() {
        mPageIndex = 1;
        mPageTotal = 1;
        mAdapter.setNewData(null);//重新开启下拉加载更多
        searchDeviceList();
    }
    private void finishRefreshAndLoadMore() {
        if (mSwipeRefreshLayout != null && mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
        if (mAdapter != null && mAdapter.isLoading()) {
            mAdapter.loadMoreComplete();
        }
        hideLoading();
    }
    private void searchDeviceList() {
        mPresenter.getDetailsOperation(mDeviceId, mPageIndex, mPageSize);
    }


    @Override
    public void onGetDetailsOperationSuccess(LadderControlDetailsOperationJson projectJson) {
        mPageTotal = (projectJson.getTotal() + mPageSize - 1) / mPageSize;
        mAdapter.addData(projectJson.getList());
        finishRefreshAndLoadMore();
    }

    @Override
    public void onGetDetailsOperationFail(ApiException apiException) {
        showToast(apiException.getMessage());
        finishRefreshAndLoadMore();
    }
}
