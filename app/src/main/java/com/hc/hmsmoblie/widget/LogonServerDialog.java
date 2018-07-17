package com.hc.hmsmoblie.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.classic.adapter.BaseAdapterHelper;
import com.classic.adapter.CommonAdapter;
import com.hc.hmsmoblie.R;
import com.hc.hmsmoblie.bean.type.UserTypeId;

import org.angmarch.views.NiceSpinner;

import java.util.Arrays;
import java.util.List;

/**
 * 登录服务器（对话框）
 */

public class LogonServerDialog extends Dialog {
    private TextView mTvUrl; //地址
    private EditText mTvPort; //端口
    private NiceSpinner mSpUserType;//用户类型
    private TextView mTvLeft;//左侧
    private TextView mTvRight;//右侧
    private OnClick mClickLeft;

    private final List<String> mData = Arrays.asList(UserTypeId.HSM.getTypeName(), UserTypeId.MCC.getTypeName(), UserTypeId.PECN.getTypeName());

    public static LogonServerDialog newInstance(@NonNull Context context) {
        return new LogonServerDialog(context);
    }

    private LogonServerDialog(@NonNull Context context) {
        this(context, R.style.CommonDialogStyle);
    }

    private LogonServerDialog(@NonNull Context context, int theme) {
        super(context, theme);
        initViews(context);
    }

    private void initViews(Context context) {
        setContentView(R.layout.widget_dialog_logon_server);

        mTvUrl = (TextView) findViewById(R.id.edDialogUrl);
        mTvPort = (EditText) findViewById(R.id.edDialogPort);
        mSpUserType = (NiceSpinner) findViewById(R.id.spDialogUserType);
        mSpUserType.setTextColor(Color.parseColor("#333333"));
        mTvRight = (TextView) findViewById(R.id.tvDialogRight);
        mTvLeft = (TextView) findViewById(R.id.tvDialogLeft);
        mSpUserType.attachDataSource(mData);
        mSpUserType.setPadding(0, 0, 0, 0);
        mSpUserType.setGravity(Gravity.CENTER);
        mTvRight.setOnClickListener(v -> dismiss());
        mTvLeft.setOnClickListener(v -> {
            if (mClickLeft != null) {
                mClickLeft.onClick(mTvUrl.getText().toString(), mTvPort.getText().toString(), UserTypeId.getUserTypeId(mSpUserType.getSelectedIndex()));
            }
            dismiss();
        });
    }

    public LogonServerDialog setUrl(String url) {
        if (!TextUtils.isEmpty(url))
            mTvUrl.setText(url);
        return this;
    }

    public LogonServerDialog setPort(String port) {
        if (!TextUtils.isEmpty(port))
            mTvPort.setText(port);
        return this;
    }

    public LogonServerDialog setUserType(UserTypeId userType) {
        mSpUserType.setSelectedIndex(userType.getIndex());
        return this;
    }

    public LogonServerDialog setLeftOnClick(OnClick onClick) {
        mClickLeft = onClick;
        return this;
    }

    public interface OnClick {
        void onClick(String url, String port, UserTypeId userTypeId);
    }
}
