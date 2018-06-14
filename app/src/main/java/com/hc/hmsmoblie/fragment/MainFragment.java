package com.hc.hmsmoblie.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.classic.adapter.BaseAdapterHelper;
import com.classic.adapter.CommonRecyclerAdapter;
import com.hc.hmsmoblie.R;

import com.hc.hmsmoblie.activity.LadderControlProjectListActivity;
import com.hc.hmsmoblie.activity.VideoSelectProjectActivity;
import com.hc.hmsmoblie.bean.domain.MainItemBean;
import com.yc.yclibrary.base.YcLazyFragment;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

/**
 *
 */

public class MainFragment extends YcLazyFragment {
    @BindView(R.id.rvMainFragment)
    RecyclerView mRecyclerView;
    CommonRecyclerAdapter<MainItemBean> commonAdapter;

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.main_fragment;
    }

    @Override
    public void initView() {
        List<MainItemBean> tempData = Arrays.asList(new MainItemBean("1", "视频监控"), new MainItemBean("2", "梯控"), new MainItemBean("2", "梯控"), new MainItemBean("2", "梯控"), new MainItemBean("2", "梯控"), new MainItemBean("2", "梯控"), new MainItemBean("2", "梯控"), new MainItemBean("2", "梯控"), new MainItemBean("2", "梯控"), new MainItemBean("2", "梯控"), new MainItemBean("2", "梯控"), new MainItemBean("2", "梯控"), new MainItemBean("2", "梯控"), new MainItemBean("2", "梯控"));
        commonAdapter = new CommonRecyclerAdapter<MainItemBean>(getActivity(), R.layout.main_item) {
            @Override
            public void onUpdate(BaseAdapterHelper helper, MainItemBean item, int position) {

                helper.setText(R.id.tvMainItem, item.getName());
            }
        };
        commonAdapter.addAll(tempData);
        commonAdapter.setOnItemClickListener((RecyclerView.ViewHolder viewHolder, View view, int position) -> {
            switch (position){
                case 0:
                    VideoSelectProjectActivity.newInstance(getActivity());
                    break;
                case 1:
                    LadderControlProjectListActivity.newInstance(getActivity());
                    break;

            }
           // LadderControlProjectListActivity.newInstance(getActivity());
        });
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3, GridLayoutManager.VERTICAL, false);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setAdapter(commonAdapter);

    }
}
