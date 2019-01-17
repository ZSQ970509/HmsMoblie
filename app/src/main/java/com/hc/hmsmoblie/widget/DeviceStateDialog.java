package com.hc.hmsmoblie.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.TextView;

import com.hc.hmsmoblie.R;
import com.hc.hmsmoblie.bean.json.TiltSensorStateJson;
import com.hc.hmsmoblie.utils.BatAndSignalUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 设备状态的对话框
 */
public class DeviceStateDialog extends Dialog {

    TextView mBatTv;
    TextView mSignalTv;
    TextView mAutoSwitchTv;
    TextView mHandSwitchTv;
    TextView mBatStateTv;

    public static DeviceStateDialog newInstance(Context context) {
        return new DeviceStateDialog(context);
    }

    public DeviceStateDialog(Context context) {
        this(context, R.style.CommonDialogStyle);
    }

    private DeviceStateDialog(Context context, int theme) {
        super(context, theme);
        initViews(context);
    }

    private void initViews(Context context) {
        setContentView(R.layout.widget_device_state_dialog);
        mBatTv = findViewById(R.id.deviceStateDialogSignalTv);
        mSignalTv = findViewById(R.id.deviceStateDialogAutoSwitchTv);
        mAutoSwitchTv = findViewById(R.id.deviceStateDialogBatTv);
        mHandSwitchTv = findViewById(R.id.deviceStateDialogHandSwitchTv);
        mBatStateTv = findViewById(R.id.deviceStateDialogBatStateTv);
        findViewById(R.id.deviceStateDialogConfirmBtn).setOnClickListener(v -> dismiss());
        //设置对话框位置大小
        Window dialogWindow = getWindow();
        dialogWindow.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
        dialogWindow.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setAttributes(lp);//此处暂未设置偏移量
        setCanceledOnTouchOutside(false);
        setCancelable(false);
    }

    public DeviceStateDialog setData(TiltSensorStateJson tiltSensorStateJson) {
        String bat = "-";
        String signal = "-";
        String autoSwitch = "-";
        String handSwitch = "-";
        String batState = "-";
        if (tiltSensorStateJson != null && tiltSensorStateJson.getDevices() != null && tiltSensorStateJson.getDevices().size() > 0 && tiltSensorStateJson.getDevices().get(0).getServices() != null && tiltSensorStateJson.getDevices().get(0).getServices().size() > 0) {
            List<TiltSensorStateJson.DevicesBean.ServicesBean> servicesBean = tiltSensorStateJson.getDevices().get(0).getServices();
            for (TiltSensorStateJson.DevicesBean.ServicesBean servicesBeans : servicesBean) {
                if (servicesBeans.getServiceId().equals("CurVal")) {
                    //电量
                    bat = BatAndSignalUtil.changeBatPercentage(servicesBeans.getData().getBat() / 10.0);
                    //信号强度
                    signal = BatAndSignalUtil.changeSignalPercentage(-servicesBeans.getData().getSignal());
                    //设备状态
                    if (servicesBeans.getData().getState() != 0) {
                        String boolAutoOpen = Integer.toBinaryString(servicesBeans.getData().getState()).substring(1, 2);
                        if (boolAutoOpen.equals("0")) {
                            autoSwitch = "开";
                        } else if (boolAutoOpen.equals("1")) {
                            autoSwitch = "关";
                        }
                    } else {
                        autoSwitch = "开";
                    }
                    if (servicesBeans.getData().getState() != 0 && Integer.toBinaryString(servicesBeans.getData().getState()).length() >= 7) {
                        String boolHeadOpen = Integer.toBinaryString(servicesBeans.getData().getState()).substring(0, 1);
                        if (boolHeadOpen.equals("0")) {
                            handSwitch = "开";
                        } else if (boolHeadOpen.equals("1")) {
                            handSwitch = "关";
                        }
                    } else {
                        handSwitch = "开";
                    }

                    //告警状态
                    if (servicesBeans.getData().getWarn() != 0 && Integer.toBinaryString(servicesBeans.getData().getWarn()).length() >= 7) {
                        String boolBatState = Integer.toBinaryString(servicesBeans.getData().getWarn()).substring(0, 1);
                        if (boolBatState.equals("0")) {
                            batState = "电池电量充足";
                        } else if (boolBatState.equals("1")) {
                            batState = "电池电量过量";
                        }
                    } else {
                        batState = "电池电量充足";
                    }
                }
            }
        }
        mBatTv.setText("设备电池电量：" + bat);
        mSignalTv.setText("设备信号强度：" + signal);
        mAutoSwitchTv.setText("自动开关传感器状态：" + autoSwitch);
        mHandSwitchTv.setText("手动开关传感器状态：" + handSwitch);
        mBatStateTv.setText("电池状态：" + batState);
        return this;
    }
}
