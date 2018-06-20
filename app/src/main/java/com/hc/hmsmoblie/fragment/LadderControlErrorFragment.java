package com.hc.hmsmoblie.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hc.hmsmoblie.R;
import com.hc.hmsmoblie.activity.LadderControlDetailsActivity;
import com.hc.hmsmoblie.bean.json.LadderControlDetailsErrorJson;
import com.hc.hmsmoblie.bean.json.LadderControlDetailsErrorJson;
import com.hc.hmsmoblie.bean.json.ProjectJson;
import com.hc.hmsmoblie.mvp.contact.LadderControlDetailsErrorC;
import com.hc.hmsmoblie.mvp.presenter.LadderControlDetailsErrorP;
import com.hc.hmsmoblie.utils.EmptyUtils;
import com.yc.yclibrary.base.YcLazyFragment;
import com.yc.yclibrary.base.YcMvpLazyFragment;
import com.yc.yclibrary.exception.ApiException;

import butterknife.BindView;

/**
 * 错误日志
 */

public class LadderControlErrorFragment extends YcMvpLazyFragment<LadderControlDetailsErrorP> implements LadderControlDetailsErrorC.V {
    @BindView(R.id.rvLadderControlError)
    RecyclerView mRecyclerView;
    @BindView(R.id.srlLadderControlError)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private int mPageIndex = 1;
    private final int mPageSize = 10;
    private int mPageTotal = 1;
    private String mProId;
    private BaseItemDraggableAdapter<LadderControlDetailsErrorJson.ListBean, BaseViewHolder> mAdapter;

    public static LadderControlErrorFragment newInstance(String proId) {
        LadderControlErrorFragment fragment = new LadderControlErrorFragment();
        fragment.mProId = proId;
        return fragment;
    }

    @Override
    protected LadderControlDetailsErrorP loadPresenter() {
        return new LadderControlDetailsErrorP();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.ladder_control_details_error_fragment;
    }

    @Override
    public void initView() {
        mAdapter = new BaseItemDraggableAdapter<LadderControlDetailsErrorJson.ListBean, BaseViewHolder>(R.layout.ladder_control_details_error_item, null) {
            @Override
            protected void convert(BaseViewHolder helper, LadderControlDetailsErrorJson.ListBean item) {
                helper.setText(R.id.tvDetailsErrorName, "操作员：" + item.getRealName());
                helper.setText(R.id.tvDetailsErrorAccount, "操作账号：" + item.getUserName());
                helper.setText(R.id.tvDetailsErrorState, "操作情况：" + (item.getState() == 0 ? "打开" : "关闭"));
                helper.setText(R.id.tvDetailsErrorTime, "操作时间：" + item.getCreateTime());
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
        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            initRefreshAndLoadMore();
            searchDeviceList();
        });
    }

    private void searchDeviceList() {
        mPresenter.getDetailsError(mProId, mPageIndex, mPageSize);
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

    private void initRefreshAndLoadMore() {
        mSwipeRefreshLayout.setRefreshing(true);
        mPageIndex = 1;
        mPageTotal = 1;
        mAdapter.setNewData(null);//重新开启下拉加载更多
    }

    @Override
    public void onGetDetailsErrorSuccess(LadderControlDetailsErrorJson projectJson) {
        mPageTotal = (projectJson.getTotal() + mPageSize - 1) / mPageSize;
        mAdapter.addData(projectJson.getList());
        finishRefreshAndLoadMore();
    }

    @Override
    public void onGetDetailsErrorFail(ApiException apiException) {
        showToast(apiException.getMessage());
        finishRefreshAndLoadMore();
    }
}
