package com.hc.hmsmoblie.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hc.hmsmoblie.R;
import com.hc.hmsmoblie.adapter.SelectProjectVideoAdapter;
import com.hc.hmsmoblie.base.BaseMvpActivity;
import com.hc.hmsmoblie.bean.json.ProjectJson;
import com.hc.hmsmoblie.mvp.contact.SelectProjectVideoC;
import com.hc.hmsmoblie.mvp.presenter.SelectProjectVideoP;
import com.hc.hmsmoblie.widget.CustomLoadMoreView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/6/11.
 */

public class SelectProjectVideoActivity extends BaseMvpActivity<SelectProjectVideoP> implements SelectProjectVideoC.V {
    @BindView(R.id.btn_Search_SelectProject)
    Button btnSearchSelectProject;
    @BindView(R.id.edit_Search_SelectProject)
    EditText editSearchSelectProject;
    @BindView(R.id.rv_SelectProject)
    RecyclerView recyclerViewSelectProject;
    ArrayList<ProjectJson.ListBean> dataList = new ArrayList<ProjectJson.ListBean>();
    SelectProjectVideoAdapter selectProjectVideoAdapter;
    int pageIndex = 0;
    int sumPage;

    public static void newInstance(Activity activity) {
        Intent intent = new Intent(activity, SelectProjectVideoActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected SelectProjectVideoP loadPresenter() {
        return new SelectProjectVideoP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.video_select_project_activity;
    }

    @Override
    protected void initView(Bundle bundle) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewSelectProject.setLayoutManager(linearLayoutManager);
        selectProjectVideoAdapter = new SelectProjectVideoAdapter(R.layout.item_select_project, dataList);
        recyclerViewSelectProject.setAdapter(selectProjectVideoAdapter);
        //recyclerViewSelectProject.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        selectProjectVideoAdapter.setLoadMoreView(new CustomLoadMoreView());
        selectProjectVideoAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                recyclerViewSelectProject.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (pageIndex > sumPage) {
                            showToast("已经是最后一页了");
                            selectProjectVideoAdapter.loadMoreEnd();
                        } else {
                            //userbean 暂时写死
                            //sysId:11视频监控、26超视野、31梯控、21环境
                            mPresenter.getVideoProject(editSearchSelectProject.getText().toString(), pageIndex, 10, "11", "100039");
                        }

                    }

                }, 1000);
            }
        }, recyclerViewSelectProject);
        selectProjectVideoAdapter.disableLoadMoreIfNotFullPage();
        selectProjectVideoAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //Toast.makeText(MainActivity.this, "onItemClick" + position, Toast.LENGTH_SHORT).show();
                try {
                 /*   intent = getIntent();
                    intent.setClass(SelectProjectActivity.this, SelectDriverActivity.class);
                    intent.putExtra("Project", dataList.get(position));
                    startActivity(intent);*/
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @OnClick({R.id.btn_Search_SelectProject})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_Search_SelectProject:
                dataList.clear();
                selectProjectVideoAdapter.notifyDataSetChanged();
                //userbean 暂时写死
                //sysId:11视频监控、26超视野、31梯控、21环境
                mPresenter.getVideoProject(editSearchSelectProject.getText().toString(), pageIndex, 10, "11", "100039");
                break;
        }
    }

    @Override
    public void onGetVideoProjectSuccess(ProjectJson dataBean) {
        if (dataBean.getList().size() == 0) {
            showToast("暂无数据！");
        } else {
            showToast("数据加载成功！");
        }
        dataList.addAll(dataBean.getList());
        selectProjectVideoAdapter.notifyDataSetChanged();
        sumPage = (dataBean.getTotal() + 10 - 1) / 10;
        if (pageIndex <= sumPage) {
            pageIndex++;
        }
        selectProjectVideoAdapter.loadMoreComplete();

    }

    @Override
    public void onGetVideoProjectFail(String msg) {
        showToast(msg);
    }
}