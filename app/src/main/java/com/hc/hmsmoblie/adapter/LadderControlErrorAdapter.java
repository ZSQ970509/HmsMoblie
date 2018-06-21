package com.hc.hmsmoblie.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hc.hmsmoblie.R;
import com.hc.hmsmoblie.activity.LadderControlPictureActivity;
import com.hc.hmsmoblie.bean.json.LadderControlDetailsErrorJson;
import com.hc.hmsmoblie.mvp.contact.LadderControlDetailsErrorC;
import com.hc.hmsmoblie.mvp.presenter.LadderControlDetailsErrorP;
import com.hc.hmsmoblie.utils.EmptyUtils;
import com.yc.yclibrary.base.YcMvpLazyFragment;
import com.yc.yclibrary.exception.ApiException;

import java.util.List;

import butterknife.BindView;

/**
 * 错误日志的Adapter
 */

public class LadderControlErrorAdapter extends BaseItemDraggableAdapter<LadderControlDetailsErrorJson.ListBean, LadderControlErrorAdapter.Holder> {
    public LadderControlErrorAdapter(List<LadderControlDetailsErrorJson.ListBean> data, OnItemClickListener mOnItemClickListener) {
        super(R.layout.ladder_control_details_error_item, data);
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @Override
    protected void convert(Holder helper, LadderControlDetailsErrorJson.ListBean item) {
        helper.setText(R.id.tvDetailsErrorName, "操作员：" + EmptyUtils.getString(item.getRealName()));
        helper.setText(R.id.tvDetailsErrorAccount, "操作账号：" + EmptyUtils.getString(item.getUserName()));
        helper.setText(R.id.tvDetailsErrorState, "操作情况：" + (item.getState() == 0 ? "打开" : "关闭"));
        helper.setText(R.id.tvDetailsErrorTime, "操作时间：" + EmptyUtils.getString(item.getCreateTime()));
        helper.linearLayout.setOnClickListener(v -> mOnItemClickListener.onItemClick(helper.tvBottom, helper.tvVerification, item));
    }

    public static class Holder extends BaseViewHolder {
        LinearLayout linearLayout;
        TextView tvBottom;
        TextView tvVerification;

        public Holder(View view) {
            super(view);
            linearLayout = view.findViewById(R.id.llDetailsErrorImg);
            tvBottom = view.findViewById(R.id.tvDetailsErrorBottomImg);
            tvVerification = view.findViewById(R.id.tvDetailsErrorVerificationImg);
        }
    }

    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View tvBottom, View tvVerification, LadderControlDetailsErrorJson.ListBean item);
    }
}
