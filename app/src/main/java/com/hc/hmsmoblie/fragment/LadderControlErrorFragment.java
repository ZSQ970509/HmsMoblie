package com.hc.hmsmoblie.fragment;

import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hc.hmsmoblie.R;
import com.hc.hmsmoblie.activity.LadderControlDetailsActivity;
import com.hc.hmsmoblie.activity.LadderControlPictureActivity;
import com.hc.hmsmoblie.adapter.LadderControlErrorAdapter;
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
    private LadderControlErrorAdapter mAdapter;

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
        mAdapter = new LadderControlErrorAdapter(null, new LadderControlErrorAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View tvBottom, View tvVerification, LadderControlDetailsErrorJson.ListBean item) {
                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat
                        .makeSceneTransitionAnimation(getActivity(),
                                new Pair<>(tvBottom, getString(R.string.transitionNameBottomImg)),
                                new Pair<>(tvVerification, getString(R.string.transitionNameVerificationImg)));
                LadderControlPictureActivity.newInstance(getActivity(),item.getRealName(),item.getOrigin(),item.getPhoto(),optionsCompat);
            }
        });
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
