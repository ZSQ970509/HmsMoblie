package com.hc.hmsmoblie.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.TextView;

import com.hc.hmsmoblie.R;
import com.hc.hmsmoblie.bean.type.UserTypeId;

import org.angmarch.views.NiceSpinner;

import java.util.Arrays;

/**
 * 登录服务器（对话框）
 */

public class LoginServerDialog extends Dialog {
    private TextView mTvUrl; //地址
    private EditText mTvPort; //端口
    private NiceSpinner mSpUserType;//用户类型
    private TextView mTvLeft;//左侧
    private TextView mTvRight;//右侧
    private OnClick mClickLeft;

//    private final List<String> mData = Arrays.asList(UserTypeId.HSM.getTypeName(), UserTypeId.PECN.getTypeName(), UserTypeId.MCC.getTypeName());

    public static LoginServerDialog newInstance(@NonNull Context context) {
        return new LoginServerDialog(context);
    }

    private LoginServerDialog(@NonNull Context context) {
        this(context, R.style.CommonDialogStyle);
    }

    private LoginServerDialog(@NonNull Context context, int theme) {
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
        mSpUserType.attachDataSource(Arrays.asList(UserTypeId.mName));
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

    public LoginServerDialog setUrl(String url) {
        if (!TextUtils.isEmpty(url))
            mTvUrl.setText(url);
        return this;
    }

    public LoginServerDialog setPort(String port) {
        if (!TextUtils.isEmpty(port))
            mTvPort.setText(port);
        return this;
    }

    public LoginServerDialog setUserType(UserTypeId userType) {
        mSpUserType.setSelectedIndex(userType.getIndex());
        return this;
    }

    public LoginServerDialog setLeftOnClick(OnClick onClick) {
        mClickLeft = onClick;
        return this;
    }

    public interface OnClick {
        void onClick(String url, String port, UserTypeId userTypeId);
    }
}
