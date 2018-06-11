package com.hc.hmsmoblie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hc.hmsmoblie.R;
import com.hc.hmsmoblie.adapter.SelectProjectVideoAdapter;
import com.hc.hmsmoblie.mvp.contact.LoginC;
import com.hc.hmsmoblie.mvp.contact.SelectProjectVideoC;
import com.hc.hmsmoblie.mvp.presenter.LoginP;
import com.hc.hmsmoblie.mvp.presenter.SelectProjectVideoP;
import com.yc.yclibrary.base.YcMvpAppCompatActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/6/11.
 */

public class SelectProjectVideo  extends YcMvpAppCompatActivity<SelectProjectVideoP> implements SelectProjectVideoC.V{
    @BindView(R.id.btn_Search_SelectProject)
    Button btnSearchSelectProject;
    @BindView(R.id.edit_Search_SelectProject)
    EditText editSearchSelectProject;
    @BindView(R.id.rv_SelectProject)
    RecyclerView recyclerViewSelectProject;
    SelectProjectVideoAdapter selectProjectVideoAdapter;
    @Override
    protected SelectProjectVideoP loadPresenter() {
        return new SelectProjectVideoP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_select_project_video;
    }

    @Override
    protected void initView(Bundle bundle) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewSelectProject.setLayoutManager(linearLayoutManager);
        selectProjectVideoAdapter = new SelectProjectVideoAdapter(R.layout.item_select_project, dataList);
    }
    @OnClick({R.id.btn_Search_SelectProject})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_Search_SelectProject:
                startActivity(new Intent(getActivity(), MainActivity.class));
                break;
        }
    }

}