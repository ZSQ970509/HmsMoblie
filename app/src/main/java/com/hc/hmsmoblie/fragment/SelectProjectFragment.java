package com.hc.hmsmoblie.fragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.classic.adapter.BaseAdapterHelper;
import com.classic.adapter.CommonRecyclerAdapter;
import com.hc.hmsmoblie.R;
import com.hc.hmsmoblie.activity.MainActivityNew;
import com.hc.hmsmoblie.activity.SearchProjectActivity;
import com.hc.hmsmoblie.activity.VideoProjectDetailsActivity;
import com.hc.hmsmoblie.adapter.SelectProjectAdapter;
import com.hc.hmsmoblie.adapter.SelectProjectRouteAdapter;
import com.hc.hmsmoblie.bean.json.GetProListByArea;
import com.hc.hmsmoblie.bean.json.ProjectJson;
import com.hc.hmsmoblie.mvp.contact.SelectProjectFragmentC;
import com.hc.hmsmoblie.mvp.presenter.SelectProjectFragmentP;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yc.yclibrary.base.YcMvpLazyFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 新版本搜索项目列表页面
 */

public class SelectProjectFragment extends YcMvpLazyFragment<SelectProjectFragmentP> implements SelectProjectFragmentC.V {
    @BindView(R.id.selectProjectRv)
    RecyclerView mRecyclerView;
    @BindView(R.id.searchProjectRouteRv)
    RecyclerView mRecyclerViewRout;
    @BindView(R.id.selectProjectSRL)
    SmartRefreshLayout mRefreshLayout;
    private String mSysId;
    private SelectProjectAdapter mAdapter;
    private SelectProjectRouteAdapter mRouteAdapter;
    private String mFolderId = "0";
    private int mFolderLevel = 0;
    private int mPageIndex = 1;
    private final int mPageSize = 20;
    private int mSumPage = 0;

    public static SelectProjectFragment newInstance(String sysId) {
        SelectProjectFragment selectProjectFragment = new SelectProjectFragment();
        selectProjectFragment.mSysId = sysId;
        return selectProjectFragment;
    }

    @Override
    protected SelectProjectFragmentP loadPresenter() {
        return new SelectProjectFragmentP();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.select_project_fragment;
    }


    @Override
    public void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new SelectProjectAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                initPage();
                mRefreshLayout.setEnableLoadMore(true);
                getProjectList(true, false);
            }
        });
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (mPageIndex * mPageSize > mSumPage) {
                    mRefreshLayout.finishRefresh();
                    mRefreshLayout.finishLoadMoreWithNoMoreData();
                } else {
                    getProjectList(false, false);
                }
            }
        });
        mAdapter.setFileCall(new SelectProjectAdapter.FileCall() {
            @Override
            public void call(GetProListByArea.FileListBean file) {
                VideoProjectDetailsActivity.newInstance(getActivity(), file.getProId(), mSysId);
            }
        });
        mAdapter.setFolderCall(new SelectProjectAdapter.FolderCall() {
            @Override
            public void call(GetProListByArea.FolderListBean folder) {
                mRouteAdapter.addDataFolder(folder);
                onRefreshData(folder);
            }
        });
        mRouteAdapter = new SelectProjectRouteAdapter(getActivity());
        mRouteAdapter.setFolderCall(new SelectProjectRouteAdapter.FolderCall() {
            @Override
            public void call(GetProListByArea.FolderListBean folder) {
                onRefreshData(folder);
            }
        });
        GetProListByArea.FolderListBean defaultData = new GetProListByArea.FolderListBean();
        defaultData.setFolderId("0");
        defaultData.setFolderLevel(0);
        defaultData.setFolderName("全部");
        mRouteAdapter.addDataFolder(defaultData);
        mRecyclerViewRout.setAdapter(mRouteAdapter);
        mRecyclerViewRout.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        mRefreshLayout.autoRefresh();
    }

    /**
     * 更新列表里的数据
     */
    private void onRefreshData(GetProListByArea.FolderListBean data) {
        initPage();
        finishRefreshAndLoadMore();
        mRefreshLayout.setEnableLoadMore(true);
        mFolderLevel = data.getFolderLevel();
        mFolderId = data.getFolderId();
        getProjectList(true, true);
    }

    private void initPage() {
        mPageIndex = 1;
        mSumPage = 0;
    }

    private void getProjectList(boolean isClearData, boolean isShowDialog) {
        mPresenter.getProListByArea(isClearData, isShowDialog, mFolderId, mFolderLevel, mPageIndex, mPageSize, mSysId);
    }

    @Override
    public void onGetProjectSuccess(GetProListByArea dataBean, boolean isClearData) {
        mAdapter.addAllDataFolder(dataBean.getFolderList(), isClearData);
        mAdapter.addAllDataFile(dataBean.getFileList(), isClearData);
        mSumPage = dataBean.getFileTotal();
        finishRefreshAndLoadMore();
    }

    @Override
    public void onGetProjectFail(String msg) {
        showToast(msg);
        finishRefreshAndLoadMore();
    }

    private void finishRefreshAndLoadMore() {
        if (mRefreshLayout != null) {
            mRefreshLayout.finishLoadMore();
            mRefreshLayout.finishRefresh();
            mRefreshLayout.setEnableRefresh(true);
        }
    }

    @OnClick({R.id.searchProjectTitleLeftIv, R.id.searchProjectTv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.searchProjectTitleLeftIv:
                if (getActivity() instanceof MainActivityNew) {
                    ((MainActivityNew) getActivity()).toggleDrawer();
                }
                break;
            case R.id.searchProjectTv:
                SearchProjectActivity.newInstance(getActivity(), mSysId);
                break;
        }
    }
}
