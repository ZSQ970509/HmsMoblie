package com.hc.hmsmoblie.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hc.hmsmoblie.R;
import com.hc.hmsmoblie.base.BaseMvpActivity;
import com.hc.hmsmoblie.bean.json.ProjectJson;
import com.hc.hmsmoblie.mvp.contact.LadderControlProjectListC;
import com.hc.hmsmoblie.mvp.presenter.LadderControlProjectListP;
import com.yc.yclibrary.exception.ApiException;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 梯控项目列表
 */

public class LadderControlProjectListActivity extends BaseMvpActivity<LadderControlProjectListP> implements LadderControlProjectListC.V {
    @BindView(R.id.rvLadderControl)
    RecyclerView mRecyclerView;
    @BindView(R.id.edtLadderControl)
    EditText mEdtKey;
    @BindView(R.id.srlLadderControl)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private int mPageIndex = 1;
    private final int mPageSize = 10;
    private int mPageTotal = 1;
    private BaseItemDraggableAdapter<ProjectJson.ListBean, BaseViewHolder> mAdapter;

    public static void newInstance(Activity activity) {
        Intent intent = new Intent(activity, LadderControlProjectListActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected LadderControlProjectListP loadPresenter() {
        return new LadderControlProjectListP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ladder_control_projcet_list_activity;
    }

    @Override
    protected void initView(Bundle bundle) {
        setToolBar("选择项目");
        mAdapter = new BaseItemDraggableAdapter<ProjectJson.ListBean, BaseViewHolder>(R.layout.item_select_project, null) {
            @Override
            protected void convert(BaseViewHolder helper, ProjectJson.ListBean item) {
                helper.setText(R.id.item_Project_Video_Name, item.getProjName())
                        .setText(R.id.item_Project_Video_Address, item.getProjAddress())
                        .setText(R.id.item_Project_Video_Status, item.getProjStatusCurrent());
            }
        };
        mAdapter.setOnLoadMoreListener(() -> {
            if (mPageIndex >= mPageTotal) {
                showToast("已经是最后一页了！");
                mAdapter.loadMoreEnd();
            } else {
                mPageIndex++;
                searchPro();
            }
        }, mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            initRefreshAndLoadMore();
            searchPro();
        });
        mAdapter.setOnItemClickListener((BaseQuickAdapter adapter, View view, int position) ->
                LadderControlDeviceListActivity.newInstance(getActivity(), ((ProjectJson.ListBean) adapter.getItem(position)).getProjID())
        );
    }

    private void searchPro() {
        mPresenter.getProjectList(mEdtKey.getText().toString(), mPageIndex, mPageSize, "31");
    }

    @Override
    public void onGetProjectListSuccess(ProjectJson projectJson) {
        mPageTotal = (projectJson.getTotal() + mPageSize - 1) / mPageSize;
        mAdapter.addData(projectJson.getList());
        finishRefreshAndLoadMore();
    }

    @Override
    public void onGetProjectListFail(ApiException apiException) {
        showToast(apiException.getMessage());
        finishRefreshAndLoadMore();
    }

    private void initRefreshAndLoadMore() {
        mSwipeRefreshLayout.setRefreshing(true);
        mPageIndex = 1;
        mPageTotal = 1;
        mAdapter.setNewData(null);//重新开启下拉加载更多
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

    @OnClick({R.id.btnLadderControl})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLadderControl:
                initRefreshAndLoadMore();
                searchPro();
                break;
        }
    }

}
