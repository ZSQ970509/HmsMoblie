package com.hc.hmsmoblie.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.classic.adapter.BaseAdapterHelper;
import com.classic.adapter.CommonAdapter;
import com.hc.hmsmoblie.R;
import com.hc.hmsmoblie.bean.domain.TiltSensorSettingBean;
import com.hc.hmsmoblie.bean.json.TiltSensorStateJson;
import com.hc.hmsmoblie.bean.json.WeighingMachineMsg;
import com.hc.hmsmoblie.utils.BatAndSignalUtil;
import com.hc.hmsmoblie.utils.FormatUtils;
import com.hc.hmsmoblie.utils.LoadImgUtils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */

public class WeighingMachineDialog extends Dialog {
    TextView weighingMachineDialogProName;
    TextView weighingMachineDialogSupplyUnit;
    TextView weighingMachineDialogDate;
    TextView weighingMachineDialogSerialNumber;
    TextView weighingMachineDialogSpecifications;
    TextView weighingMachineDialogWeighingAgent;
    TextView weighingMachineDialogCardNum;
    TextView weighingMachineDialogDriver;
    TextView weighingMachineDialogWeighingWeight;
    TextView weighingMachineDialogSettlementWeight;
    TextView weighing_Machine_Dialog_Note;
    ImageView weighing_Machine_Dialog_Panorama;
    ImageView weighing_Machine_Dialog_HeadstockPlan;
    ImageView weighing_Machine_Dialog_Tail_Diagram;

    public static WeighingMachineDialog newInstance(Context context) {
        return new WeighingMachineDialog(context);
    }

    public WeighingMachineDialog(Context context) {
        this(context, R.style.CommonDialogStyle);
    }

    private WeighingMachineDialog(Context context, int theme) {
        super(context, theme);
        initViews(context);
    }

    private void initViews(Context context) {
        setContentView(R.layout.weighing_machine_dialog);
        weighingMachineDialogProName = findViewById(R.id.weighing_Machine_Dialog_ProName);
        weighingMachineDialogSupplyUnit = findViewById(R.id.weighing_Machine_Dialog_Supply_Unit);
        weighingMachineDialogDate = findViewById(R.id.weighing_Machine_Dialog_Date);
        weighingMachineDialogSerialNumber = findViewById(R.id.weighing_Machine_Dialog_Serial_Number);
        weighingMachineDialogSpecifications = findViewById(R.id.weighing_Machine_Dialog_Specifications);
        weighingMachineDialogWeighingAgent = findViewById(R.id.weighing_Machine_Dialog_WeighingAgent);
        weighingMachineDialogCardNum = findViewById(R.id.weighing_Machine_Dialog_CardNum);
        weighingMachineDialogDriver = findViewById(R.id.weighing_Machine_Dialog_Driver);
        weighingMachineDialogWeighingWeight = findViewById(R.id.weighing_Machine_Dialog_Weighing_Weight);
        weighingMachineDialogSettlementWeight = findViewById(R.id.weighing_Machine_Dialog_Settlement_Weight);
        weighing_Machine_Dialog_Note = findViewById(R.id.weighing_Machine_Dialog_Note);
        weighing_Machine_Dialog_Panorama = findViewById(R.id.weighing_Machine_Dialog_Panorama);
        weighing_Machine_Dialog_HeadstockPlan = findViewById(R.id.weighing_Machine_Dialog_HeadstockPlan);
        weighing_Machine_Dialog_Tail_Diagram = findViewById(R.id.weighing_Machine_Dialog_Tail_Diagram );
        findViewById(R.id.weighing_Machine_Dialog_ConfirmBtn).setOnClickListener(v -> dismiss());
        //设置对话框位置大小
        Window dialogWindow = getWindow();
        dialogWindow.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
        dialogWindow.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setAttributes(lp);//此处暂未设置偏移量
        setCanceledOnTouchOutside(false);
        setCancelable(false);
    }

    public WeighingMachineDialog setData(Activity context, WeighingMachineMsg weighingMachineMsg) {
        weighingMachineDialogProName.setText(weighingMachineMsg.getProjName());
        weighingMachineDialogSupplyUnit.setText(weighingMachineMsg.getSupplier());
        weighingMachineDialogDate.setText(weighingMachineMsg.getCreateTime());
        weighingMachineDialogSerialNumber.setText(weighingMachineMsg.getSwiftNumber());
        weighingMachineDialogSpecifications.setText(weighingMachineMsg.getMerchandise());
        weighingMachineDialogWeighingAgent.setText(weighingMachineMsg.getWeighing());
        weighingMachineDialogCardNum.setText(weighingMachineMsg.getPlate());
        weighingMachineDialogDriver.setText(weighingMachineMsg.getCart());
        weighingMachineDialogWeighingWeight.setText(weighingMachineMsg.getWeighM()+"");
        weighingMachineDialogSettlementWeight.setText(weighingMachineMsg.getWeighW()+"");
        weighing_Machine_Dialog_Note.setText(weighingMachineMsg.getNote());
        LoadImgUtils.loadImg(context,weighingMachineMsg.getPuzzleImg(),weighing_Machine_Dialog_Panorama);
        LoadImgUtils.loadImg(context,weighingMachineMsg.getCartBImg(),weighing_Machine_Dialog_HeadstockPlan);
        LoadImgUtils.loadImg(context,weighingMachineMsg.getCartEImg(),weighing_Machine_Dialog_Tail_Diagram);
        return this;
    }
}
