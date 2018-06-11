package com.hc.hmsmoblie.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hc.hmsmoblie.R;
import com.hc.hmsmoblie.mvp.contact.LadderControlC;
import com.hc.hmsmoblie.mvp.presenter.LadderControlP;
import com.yc.yclibrary.base.YcMvpAppCompatActivity;

import butterknife.OnClick;

/**
 *
 */

public class LadderControlActivity extends YcMvpAppCompatActivity<LadderControlP> implements LadderControlC.V {
    public static void newInstance(Activity activity) {
        Intent intent = new Intent(activity, LadderControlActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected LadderControlP loadPresenter() {
        return new LadderControlP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ladder_control_activity;
    }

    @Override
    protected void initView(Bundle bundle) {

    }

//    @OnClick({R.id.btnLadderControl})
//    void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.btnLadderControl:
//                startActivity(new Intent(getActivity(), MainActivity.class));
//                break;
//        }
//    }
}
