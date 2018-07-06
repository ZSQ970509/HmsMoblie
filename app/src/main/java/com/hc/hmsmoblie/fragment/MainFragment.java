package com.hc.hmsmoblie.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.classic.adapter.BaseAdapterHelper;
import com.classic.adapter.CommonRecyclerAdapter;
import com.hc.hmsmoblie.R;

import com.hc.hmsmoblie.activity.EnvironmentSelectProjectActivity;
import com.hc.hmsmoblie.activity.ImageLogProjectListActivity;
import com.hc.hmsmoblie.activity.LadderControlProjectListActivity;
import com.hc.hmsmoblie.activity.MainActivity;
import com.hc.hmsmoblie.activity.VideoSelectProjectActivity;
import com.hc.hmsmoblie.bean.domain.MainItemBean;
import com.hc.hmsmoblie.bean.json.MainJson;
import com.hc.hmsmoblie.db.UserInfoPref;
import com.hc.hmsmoblie.mvp.contact.MainC;
import com.hc.hmsmoblie.mvp.presenter.MainP;
import com.yc.yclibrary.base.YcLazyFragment;
import com.yc.yclibrary.base.YcMvpAppCompatActivity;
import com.yc.yclibrary.base.YcMvpLazyFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 *
 */

public class MainFragment extends YcMvpLazyFragment<MainP> implements MainC.V {
    @BindView(R.id.rvMainFragment)
    RecyclerView mRecyclerView;
    @BindView(R.id.iv_List_Main)
    ImageView ivListMain;
    CommonRecyclerAdapter<MainJson> commonAdapter;
    int[] icon = {R.drawable.main_video, R.drawable.main_super_video, R.drawable.main_reinforced, R.drawable.main_imaging_log
            , R.drawable.main_environment, R.drawable.main_moble_attendance, R.drawable.mian_ladder_control, R.drawable.main_outages_network_power,};

    public static MainFragment newInstance() {
        return new MainFragment();
    }


    @Override
    protected MainP loadPresenter() {
        return new MainP();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.main_fragment;
    }

    @Override
    public void initView() {
        mPresenter.GetModulesList(UserInfoPref.getUserAccount(), UserInfoPref.getUserId());
    }

    @OnClick({R.id.iv_List_Main})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_List_Main:
                Activity activity1 = getActivity();
                if (activity1 instanceof MainActivity) {
                    ((MainActivity) activity1).toggleDrawer();
                }
                break;
        }
    }

    @Override
    public void onGetModulesListSuccess(ArrayList<MainJson> mainJson) {
        if (mainJson.size() == 0) {
            showToast("暂无数据！");
        } else {
            commonAdapter = new CommonRecyclerAdapter<MainJson>(getActivity(), R.layout.main_item) {
                @Override
                public void onUpdate(BaseAdapterHelper helper, MainJson item, int position) {

                    helper.setText(R.id.tvMainItem, item.getMdName());
                    switch (item.getMdID()) {
                        case 1381:
                            helper.setImageResource(R.id.ivMainItem, icon[0]);
                            break;
                        case 1382:
                            helper.setImageResource(R.id.ivMainItem, icon[1]);
                            break;
                        case 1383:
                            helper.setImageResource(R.id.ivMainItem, icon[2]);
                            break;
                        case 1384:
                            helper.setImageResource(R.id.ivMainItem, icon[3]);
                            break;
                        case 1385:
                            helper.setImageResource(R.id.ivMainItem, icon[4]);
                            break;
                        case 1386:
                            helper.setImageResource(R.id.ivMainItem, icon[5]);
                            break;
                        case 1387:
                            helper.setImageResource(R.id.ivMainItem, icon[6]);
                            break;
                        case 1388:
                            helper.setImageResource(R.id.ivMainItem, icon[7]);
                            break;
                    }
                }
            };
            commonAdapter.addAll(mainJson);
            commonAdapter.setOnItemClickListener((RecyclerView.ViewHolder viewHolder, View view, int position) -> {
                switch (mainJson.get(position).getMdID()) {
                    case 1381:
                        VideoSelectProjectActivity.newInstance(getActivity(), "11");
                        break;
                    case 1382:
                        VideoSelectProjectActivity.newInstance(getActivity(), "26");
                        break;
                    case 1383:
                        showToast("开发中");
                        break;
                    case 1384:
                        ImageLogProjectListActivity.newInstance(getActivity());
                        break;
                    case 1385:
                        EnvironmentSelectProjectActivity.newInstance(getActivity());
                        break;
                    case 1386:
                        showToast("开发中");
                        break;
                    case 1387:
                        LadderControlProjectListActivity.newInstance(getActivity());
                        break;
                    case 1388:
                        showToast("开发中");
                        break;
                    default:
                        showToast("开发中");
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

    @Override
    public void onGetModulesListFail(String msg) {

    }

}
