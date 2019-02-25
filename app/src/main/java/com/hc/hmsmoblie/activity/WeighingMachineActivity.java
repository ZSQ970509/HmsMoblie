package com.hc.hmsmoblie.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hc.hmsmoblie.R;
import com.hc.hmsmoblie.base.BaseMvpActivity;
import com.hc.hmsmoblie.bean.json.WeighingMachineJson;
import com.hc.hmsmoblie.bean.json.WeighingMachineMsg;
import com.hc.hmsmoblie.bean.json.WeightGroupJson;
import com.hc.hmsmoblie.db.PrefHelper;
import com.hc.hmsmoblie.db.UserInfoPref;
import com.hc.hmsmoblie.mvp.contact.WeighingMachineC;
import com.hc.hmsmoblie.mvp.presenter.WeighingMachineP;
import com.hc.hmsmoblie.utils.EmptyUtils;
import com.hc.hmsmoblie.utils.TimePickerUtils;
import com.hc.hmsmoblie.widget.WeighingMachineDialog;
import com.yc.yclibrary.exception.ApiException;
import com.zxl.library.DropDownMenu;
import com.zxl.library.WeighingSelectorType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 地磅列表
 */

public class WeighingMachineActivity extends BaseMvpActivity<WeighingMachineP> implements WeighingMachineC.V {
    RecyclerView mRecyclerView;
    SwipeRefreshLayout mSwipeRefreshLayout;
    View contentView;
    @BindView(R.id.tvWeighingMachineStartTime)
    TextView mTvStartTime;
    @BindView(R.id.tvWeighingMachineEndTime)
    TextView mTvEndTime;
    @BindView(R.id.tvWeighingMachineWeighMSum)
    TextView mTvWeighMSum;
    @BindView(R.id.tvWeighingMachineWeighWSum)
    TextView mTvWeighWSum;
    @BindView(R.id.tvWeighingMachineSearch)
    Button tvWeighingMachineSearch;
    @BindView(R.id.dropDownMenu)
    DropDownMenu mDropDownMenu;
    @BindView(R.id.main_drawerLayout)
    LinearLayout maindrawerLayout;
    @BindView(R.id.linearLayoutYinDao)
    RelativeLayout linearLayoutYinDao;
    @BindView(R.id.ImageYinDao)
    ImageView ImageYinDao;
    @BindView(R.id.ImageYinDaoNext)
    ImageView ImageYinDaoNext;
    @BindView(R.id.ImageYinDaoSkip)
    TextView ImageYinDaoSkip;
    //用于标识引导页面
    private int yinDaoIndex = 0;
    TextView mEmptyTv;
    private int mPageIndex = 1;
    private final int mPageSize = 10;
    private int mPageTotal = 1;
    private BaseItemDraggableAdapter<WeighingMachineJson.ListBean, BaseViewHolder> mAdapter;

    private String mProID;
    private static final String PRO_ID = "pro_id";

    private String headers[] = {"品名规格", "供应单位", "司磅人", "总重量"};
    private String mSelectorMerchandise = "";//刷选条件：品名规格
    private String mSelectorSupplier = "";//刷选条件：供应单位
    private String mSelectorWeighing = "";//刷选条件：司磅员
    private double mSelectorWeight = 0;//刷选条件：重量

    public static void newInstance(Activity activity, String proId) {
        Intent intent = new Intent(activity, WeighingMachineActivity.class);
        intent.putExtra(PRO_ID, proId);
        activity.startActivity(intent);
    }

    @Override
    protected WeighingMachineP loadPresenter() {
        return new WeighingMachineP();
    }

    @Override
    protected int getLayoutId() {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS); // 必须
        return R.layout.weight_machine_activity;
    }

    @Override
    protected void initView(Bundle bundle) {
        setToolBar("智能地磅");
        mProID = getIntent().getStringExtra(PRO_ID);
        initFrist();
        initDropDownView();
        mTvStartTime.setText(TimePickerUtils.getMonthFirstDay());
        mTvEndTime.setText(TimePickerUtils.getMonthToday());
        mAdapter = new BaseItemDraggableAdapter<WeighingMachineJson.ListBean, BaseViewHolder>(R.layout.weighing_machine_item, null) {
            @Override
            protected void convert(BaseViewHolder helper, WeighingMachineJson.ListBean item) {
                helper.setText(R.id.tvWeighingMachineProName, EmptyUtils.getString(item.getProjName(), "-"))
                        .setText(R.id.tvWeighingMachineSerialNumber, "流水号:" + EmptyUtils.getString(item.getSwiftNumber(), "-"))
                        .setText(R.id.tvWeighingMachineWeighingAgent, "司磅员:" + EmptyUtils.getString(item.getWeighing(), "-"))
                        .setText(R.id.tvWeighingMachineSpecifications, "品名规格:" + EmptyUtils.getString(item.getMerchandise(), "-"))
                        .setText(R.id.tvWeighingMachineDriverNum, "车牌号:" + EmptyUtils.getString(item.getPlate(), "-"))
                        .setText(R.id.tvWeighingMachineDriver, "司机:" + EmptyUtils.getString(item.getCart(), "-"))
                        .setText(R.id.tvWeighingMachineWeighingWeight, "称重重量:" + EmptyUtils.getString(item.getWeighM() + "", "-", "kg"))
                        .setText(R.id.tvWeighingMachineSettlementWeight, "结算重量:" + EmptyUtils.getString(item.getWeighW() + "", "-", "kg"))
                        .setText(R.id.tvWeighingMachineDate, "称重日期:" + EmptyUtils.getString(item.getCreateTime(), "-"));
            }
        };
        mAdapter.setOnLoadMoreListener(() -> {
            if (mPageIndex >= mPageTotal) {
                showToast("已经是最后一页了！");
                mAdapter.loadMoreEnd();
            } else {
                mPageIndex++;
                searchDeviceList();
            }
        }, mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            initRefreshAndLoadMore();
            searchDeviceList();
        });
        mAdapter.setOnItemClickListener((BaseQuickAdapter adapter, View view, int position) ->
                mPresenter.getWeighbridge(((WeighingMachineJson.ListBean) adapter.getItem(position)).getRecordId() + "")
        );
        initRefreshAndLoadMore();
        searchDeviceList();
        mPresenter.getWeighGroupList(mProID);
    }

    private void initFrist() {
        if (UserInfoPref.getmUserWeighingMachineFrist()) {
            switch (yinDaoIndex) {
                case 0:
                    maindrawerLayout.setVisibility(View.GONE);
                    linearLayoutYinDao.setVisibility(View.VISIBLE);
                    yinDaoIndex = 1;
                    break;
                case 1:
                    ImageYinDao.setImageResource(R.drawable.weighing_guide_2);
                    yinDaoIndex = 2;
                    break;
                case 2:
                    ImageYinDao.setImageResource(R.drawable.weighing_guide_3);
                    ImageYinDaoNext.setImageResource(R.drawable.weighing_guide_ok);
                    yinDaoIndex = 3;
                    break;
            }
        }
    }

    private void initDropDownView() {
        contentView = getLayoutInflater().inflate(R.layout.weight_machine_content_activity, null);
        mRecyclerView = contentView.findViewById(R.id.rvWeighingMachine);
        mSwipeRefreshLayout = contentView.findViewById(R.id.srlWeighingMachine);
        mEmptyTv = contentView.findViewById(R.id.layoutEmptyTv);
        //该监听回调只监听默认类型，如果是自定义view请自行设置，参照demo
        mDropDownMenu.addMenuSelectListener(new DropDownMenu.OnMenuSelectListener() {

            @Override
            public void onSelectDefaultMenu(int type, String date) {
                dropDropFinal(type, date);
            }
        });
    }

    /**
     * 设置类型和数据源：
     * DropDownMenu.KEY对应类型（DropDownMenu中的常量，参考上述核心源码）
     * DropDownMenu.VALUE对应数据源：key不是TYPE_CUSTOM则传递string[],key是TYPE_CUSTOM类型则传递对应view
     */
    private List<HashMap<String, Object>> initDropDownViewData(WeightGroupJson weightGroupJson) {
        List<HashMap<String, Object>> viewDates = new ArrayList<>();
        viewDates.add(createDropDate(weightGroupJson.getMerchandiseList(), WeighingSelectorType.merchandise));
        viewDates.add(createDropDate(weightGroupJson.getSupplierList(), WeighingSelectorType.supplier));
        viewDates.add(createDropDate(weightGroupJson.getWeighingList(), WeighingSelectorType.weighing));
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put(DropDownMenu.KEY, WeighingSelectorType.weight);
        map.put(DropDownMenu.VALUE, getCustomView());
        viewDates.add(map);
        return viewDates;
    }

    private HashMap<String, Object> createDropDate(List<String> date, int key) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put(DropDownMenu.KEY, key);
        List<String> putDate = new ArrayList<>();
        putDate.add("全部");
        putDate.addAll(date);
        map.put(DropDownMenu.VALUE, putDate.toArray(new String[putDate.size()]));
        return map;
    }

    private View getCustomView() {
        View v = getLayoutInflater().inflate(R.layout.custom, null);
        TextView btn = (TextView) v.findViewById(R.id.btn);
        EditText editText = v.findViewById(R.id.weight_Group_EditText);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num;
                try {
                    num = Integer.parseInt(editText.getText().toString());
                } catch (Exception e) {
                    num = 0;
                }
                if (num == 0) {
                    mDropDownMenu.setTabText(3, headers[3]);//设置tab标签文字
                } else {
                    mDropDownMenu.setTabText(3, ">" + num);//设置tab标签文字
                }
                dropDropFinal(WeighingSelectorType.weight, num + "");
                mDropDownMenu.closeMenu();//关闭menu
            }
        });
        return v;
    }

    @Override
    public void onBackPressed() {
        //退出activity前关闭菜单
        if (mDropDownMenu.isShowing()) {
            mDropDownMenu.closeMenu();
        } else {
            super.onBackPressed();
        }
    }

    /**
     * 刷选框结束后调用搜索列表事件
     *
     * @param type 类型（选中了第几个tab）
     * @param date 数据
     */
    private void dropDropFinal(@WeighingSelectorType int type, String date) {
        if (date.equals("全部")) {
            date = "";
            mDropDownMenu.setTabText(type - 1, headers[type - 1]);
        }
        switch (type) {
            case WeighingSelectorType.merchandise:
                mSelectorMerchandise = date;
                break;
            case WeighingSelectorType.supplier:
                mSelectorSupplier = date;
                break;
            case WeighingSelectorType.weighing:
                mSelectorWeighing = date;
                break;
            case WeighingSelectorType.weight:
                try {
                    mSelectorWeight = Double.parseDouble(date);
                } catch (Exception e) {
                    mSelectorWeight = 0;
                }
                break;
        }
        initRefreshAndLoadMore();
        searchDeviceList();
    }

    private void searchDeviceList() {
        mPresenter.getWeighbridgeList(mProID, mPageIndex, mPageSize, mTvStartTime.getText().toString(), mTvEndTime.getText().toString(), mSelectorSupplier, mSelectorMerchandise, mSelectorWeighing, mSelectorWeight);
    }

    @Override
    public void onGetWeighbridgeListSuccess(WeighingMachineJson weighingMachineJson) {
        if (weighingMachineJson == null || weighingMachineJson.getList() == null || weighingMachineJson.getList().size() == 0) {
            mTvWeighMSum.setText("总称重重量:-");
            mTvWeighWSum.setText("总结算重量:-");
            mEmptyTv.setVisibility(View.VISIBLE);
        } else {
            mEmptyTv.setVisibility(View.GONE);
            mTvWeighMSum.setText("总称重重量:" + weighingMachineJson.getWeighMSum() + "(kg)");
            mTvWeighWSum.setText("总结算重量:" + weighingMachineJson.getWeighWSum() + "(kg)");
        }
        mPageTotal = (weighingMachineJson.getTotal() + mPageSize - 1) / mPageSize;
        mAdapter.addData(weighingMachineJson.getList());
        finishRefreshAndLoadMore();
    }

    @Override
    public void onGetWeighbridgeListFail(ApiException apiException) {
        showToast(apiException.getMessage());
        finishRefreshAndLoadMore();
    }

    @Override
    public void onGetWeighbridgeSuccess(WeighingMachineMsg weighingMachineMsg) {
//        Log.e("test", weighingMachineMsg.toString() + "");
        WeighingMachineDialog.newInstance(getActivity())
                .setData(getActivity(), weighingMachineMsg)
                .setImgClick(this::toShowImgActivity)
                .show();
    }

    private void toShowImgActivity(String imgUrl, ImageView imageView) {
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), new Pair<>(imageView, getString(R.string.transitionNameShowImg)));
        ShowImgActivity.newInstance(getActivity(), imgUrl, optionsCompat);
    }

    @Override
    public void onGetWeighbridgeFail(ApiException apiException) {
        showToast(apiException.getMessage());
    }

    @Override
    public void onGetWeighGroupListSuccess(WeightGroupJson weightGroupJson) {
        mDropDownMenu.setDropDownMenu(Arrays.asList(headers), initDropDownViewData(weightGroupJson), contentView);
    }

    @Override
    public void onGetWeighGroupListFail(ApiException apiException) {
        showToast(apiException.getMessage());
    }

    private void initRefreshAndLoadMore() {
        mSwipeRefreshLayout.setRefreshing(true);
        mPageIndex = 1;
        mPageTotal = 1;
        mAdapter.setNewData(null);//重新开启上拉加载更多
    }

    private void finishRefreshAndLoadMore() {
        if (mSwipeRefreshLayout != null && mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
        if (mAdapter != null && mAdapter.isLoading()) {
            mAdapter.loadMoreComplete();
        }
    }

    @OnClick({R.id.tvWeighingMachineSearch, R.id.tvWeighingMachineEndTime, R.id.tvWeighingMachineStartTime, R.id.ImageYinDaoNext, R.id.ImageYinDaoSkip})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvWeighingMachineSearch:
                initRefreshAndLoadMore();
                searchDeviceList();
                break;
            case R.id.tvWeighingMachineStartTime:
                TimePickerUtils.showPickerView(getActivity(), "", mTvStartTime, mTvStartTime.getText().toString(), "1234-10-11", mTvEndTime.getText().toString());
                break;
            case R.id.tvWeighingMachineEndTime:
                if (TextUtils.isEmpty(mTvStartTime.getText().toString()))
                    TimePickerUtils.showPickerView(getActivity(), "", mTvEndTime, mTvEndTime.getText().toString(), "1234-10-11", "");
                else
                    TimePickerUtils.showPickerView(getActivity(), "", mTvEndTime, mTvEndTime.getText().toString(), mTvStartTime.getText().toString(), "");
                break;
            case R.id.ImageYinDaoNext:
                if (yinDaoIndex != 3) {
                    initFrist();
                } else {
                    maindrawerLayout.setVisibility(View.VISIBLE);
                    linearLayoutYinDao.setVisibility(View.GONE);
                    UserInfoPref.setmUserWeighingMachineFrist(false);
                }

                break;
            case R.id.ImageYinDaoSkip:
                maindrawerLayout.setVisibility(View.VISIBLE);
                linearLayoutYinDao.setVisibility(View.GONE);
                UserInfoPref.setmUserWeighingMachineFrist(false);
                break;
        }
    }

}
