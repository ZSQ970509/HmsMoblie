package com.hc.hmsmoblie.fragment;

import com.hc.hmsmoblie.R;
import com.yc.yclibrary.base.YcLazyFragment;

/**
 *  操作记录
 */

public class LadderControlOperationFragment extends YcLazyFragment{
    public static LadderControlOperationFragment newInstance(){
        return new LadderControlOperationFragment();
    }
    @Override
    public int getLayoutResId() {
        return  R.layout.ladder_control_details_operation_fragment;
    }

    @Override
    public void initView() {

    }
}
