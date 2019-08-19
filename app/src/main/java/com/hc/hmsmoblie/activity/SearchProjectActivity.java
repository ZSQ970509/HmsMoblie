package com.hc.hmsmoblie.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.classic.adapter.BaseAdapterHelper;
import com.classic.adapter.CommonRecyclerAdapter;
import com.hc.hmsmoblie.R;
import com.hc.hmsmoblie.base.BaseMvpActivity;
import com.hc.hmsmoblie.bean.json.ProjectJson;
import com.hc.hmsmoblie.db.UserInfoPref;
import com.hc.hmsmoblie.mvp.contact.SearchProjectActivityC;
import com.hc.hmsmoblie.mvp.contact.VideoSelectProjectFragmentC;
import com.hc.hmsmoblie.mvp.presenter.SearchProjectActivityP;
import com.hc.hmsmoblie.mvp.presenter.VideoSelectProjectFragmentP;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yc.yclibrary.base.YcMvpAppCompatActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 搜索项目页面
 */

public class SearchProjectActivity extends BaseMvpActivity<SearchProjectActivityP> implements SearchProjectActivityC.V {
    @BindView(R.id.searchProjectKeywordEdt)
    EditText mKeywordEdt;
    @BindView(R.id.searchProjectRv)
    RecyclerView mRecyelerView;
    @BindView(R.id.searchProjectSRL)
    SmartRefreshLayout mRefreshLayout;
    private CommonRecyclerAdapter<ProjectJson.ListBean> mAdapter;
    private static final String KEY_SYS_ID = "key_sys_id";

    private String mSysID = "";
    private int pageIndex = 1;
    private int sumPage;

    public static void newInstance(Activity activity, String sysId) {
        Intent intent = new Intent(activity, SearchProjectActivity.class);
        intent.putExtra(KEY_SYS_ID, sysId);
        activity.startActivity(intent);
    }

    @Override
    protected SearchProjectActivityP loadPresenter() {
        return new SearchProjectActivityP();
    }

    @Override
    public int getLayoutId() {
        return R.layout.search_project_activity;
    }

    @Override
    public void initView(Bundle bundle) {
        setToolBar("搜索项目");
        mSysID = getIntent().getStringExtra(KEY_SYS_ID);
        mRecyelerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mAdapter = new CommonRecyclerAdapter<ProjectJson.ListBean>(getActivity(), R.layout.item_select_project) {
            @Override
            public void onUpdate(BaseAdapterHelper helper, ProjectJson.ListBean item, int position) {
                helper.setText(R.id.itemSelectProFileName, item.getProjName())
                        .setText(R.id.itemSelectProFileAddress, item.getProjAddress())
                        .setText(R.id.itemSelectProFileStatus, item.getProjStatusCurrent());
            }
        };
        mRecyelerView.setAdapter(mAdapter);
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (pageIndex > sumPage) {
                    mRefreshLayout.finishRefresh();
                    mRefreshLayout.finishLoadMoreWithNoMoreData();
                } else {
                    searchProject(false, false);
                }
            }
        });
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageIndex = 1;
                sumPage = 0;
                searchProject(true, false);
            }
        });
        mAdapter.setOnItemClickListener(new CommonRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView.ViewHolder viewHolder, View view, int position) {
                VideoProjectDetailsActivity.newInstance(getActivity(), mAdapter.getItem(position).getProjID(), mSysID);
            }
        });
    }

    private void searchProject(boolean isClear, boolean isShowDialog) {
        mPresenter.searchProject(isClear, isShowDialog, mKeywordEdt.getText().toString(), pageIndex, 10, mSysID);
    }

    @Override
    public void onSearchProjectSuccess(boolean isClear, ProjectJson dataBean) {
        if (isClear && mAdapter != null)
            mAdapter.clear();
        if (dataBean != null && dataBean.getList() != null && dataBean.getList().size() > 0) {
            mAdapter.addAll(dataBean.getList());
            sumPage = (dataBean.getTotal() + 10 - 1) / 10;
            if (pageIndex <= sumPage) {
                pageIndex++;
            }
        }
        finishRefreshAndLoadMore();
    }

    @Override
    public void onSearchProjectFail(String msg) {
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

    @OnClick({R.id.searchProjectBtn})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.searchProjectBtn:
                searchProject(true, true);
                break;
        }
    }
}
