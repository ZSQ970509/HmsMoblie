package com.hc.hmsmoblie.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hc.hmsmoblie.R;
import com.hc.hmsmoblie.base.BaseMvpActivity;
import com.hc.hmsmoblie.bean.json.ImageLogPanoramaListJson;
import com.hc.hmsmoblie.mvp.contact.ImageLogPanoramaListC;
import com.hc.hmsmoblie.mvp.presenter.ImageLogPanoramaListP;
import com.hc.hmsmoblie.utils.LoadImgUtils;
import com.hc.hmsmoblie.utils.TimePickerUtils;
import com.yc.yclibrary.exception.ApiException;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 全局图列表(影像日志)
 */

public class ImageLogPanoramaListActivity extends BaseMvpActivity<ImageLogPanoramaListP> implements ImageLogPanoramaListC.V {
    @BindView(R.id.rcImageLogPanorama)
    RecyclerView mRecyclerView;
    @BindView(R.id.tvImageLogPanoramaStartTime)
    TextView mTvStartTime;
    @BindView(R.id.tvImageLogPanoramaEndTime)
    TextView mTvEndTime;
    @BindView(R.id.srlImageLogPanorama)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private int mPageIndex = 0;
    private final int mPageSize = 10;
    private int mPageTotal = 0;
    private BaseItemDraggableAdapter<ImageLogPanoramaListJson.ListBean, BaseViewHolder> mAdapter;
    private String mCamId;
    private static final String CAM_ID = "cam_id";

    public static void newInstance(Activity activity, String camId) {
        Intent intent = new Intent(activity, ImageLogPanoramaListActivity.class);
        intent.putExtra(CAM_ID, camId);
        activity.startActivity(intent);
    }

    @Override
    protected ImageLogPanoramaListP loadPresenter() {
        return new ImageLogPanoramaListP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.image_log_panorama_list_activity;
    }

    @Override
    protected void initView(Bundle bundle) {
        setToolBar("全景图列表");
        mCamId = getIntent().getStringExtra(CAM_ID);
//        mCamId = "1032879";
        mAdapter = new BaseItemDraggableAdapter<ImageLogPanoramaListJson.ListBean, BaseViewHolder>(R.layout.image_log_panorama_item, null) {
            @Override
            protected void convert(BaseViewHolder helper, ImageLogPanoramaListJson.ListBean item) {
                ImageView imageView = helper.getView(R.id.ivImageLogPanoramaItem);
                LoadImgUtils.loadImg(getActivity(), item.getPuzzleImg(), imageView);
                helper.setText(R.id.tvImageLogPanoramaItemTime, item.getStartTime());
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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayout.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            initRefreshAndLoadMore();
            searchPro();
        });
        mAdapter.setOnItemClickListener((BaseQuickAdapter adapter, View view, int position) -> {
                    ImageLogPanoramaListJson.ListBean itemData = (ImageLogPanoramaListJson.ListBean) adapter.getItem(position);
                    ImageLogPanoramaDetailActivity.newInstance(getActivity(), itemData.getPuzzleImg(), itemData.getRecordId() + "", itemData.getImageTimes() + "");
                }
        );
        searchPro();
    }

    private void searchPro() {
        mPresenter.getPanoramaList(mCamId, mTvStartTime.getText().toString(), mTvEndTime.getText().toString(), mPageIndex, mPageSize);
    }

    @Override
    public void onPanoramaListSuccess(ImageLogPanoramaListJson json) {
        mPageTotal = (json.getTotal() + mPageSize - 1) / mPageSize;
        mAdapter.addData(json.getList());
        finishRefreshAndLoadMore();
    }

    @Override
    public void onPanoramaListFail(ApiException apiException) {
        showToast(apiException.getMessage());
        finishRefreshAndLoadMore();
    }

    private void initRefreshAndLoadMore() {
        mSwipeRefreshLayout.setRefreshing(true);
        mPageIndex = 0;
        mPageTotal = 0;
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

    @OnClick({R.id.tvImageLogPanoramaSearch, R.id.tvImageLogPanoramaEndTime, R.id.tvImageLogPanoramaStartTime})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvImageLogPanoramaSearch:
                initRefreshAndLoadMore();
                searchPro();
                break;
            case R.id.tvImageLogPanoramaStartTime:
                TimePickerUtils.showPickerView(getActivity(), "", mTvStartTime, mTvStartTime.getText().toString(), "1234-10-11", mTvEndTime.getText().toString());
                break;
            case R.id.tvImageLogPanoramaEndTime:
                if (TextUtils.isEmpty(mTvStartTime.getText().toString()))
                    TimePickerUtils.showPickerView(getActivity(), "", mTvEndTime, mTvEndTime.getText().toString(), "1234-10-11", "");
                else
                    TimePickerUtils.showPickerView(getActivity(), "", mTvEndTime, mTvEndTime.getText().toString(), mTvStartTime.getText().toString(), "");
                break;
        }
    }
}
