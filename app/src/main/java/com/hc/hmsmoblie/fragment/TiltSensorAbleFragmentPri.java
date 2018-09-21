//package com.hc.hmsmoblie.fragment;
//
//import com.hc.hmsmoblie.R;
//import com.hc.hmsmoblie.bean.json.TiltSensorDataJson;
//import com.hc.hmsmoblie.widget.vh.VHAdapterPri;
//import com.hc.hmsmoblie.widget.vh.VHLayout;
//import com.hc.hmsmoblie.widget.vh.VHViewHolder;
//import com.yc.yclibrary.base.YcLazyFragment;
//
//import java.util.ArrayList;
//import java.util.concurrent.TimeUnit;
//
//import butterknife.BindView;
//import io.reactivex.Observable;
//import io.reactivex.android.schedulers.AndroidSchedulers;
//
///**
// * 倾角流水数据表格
// */
//
//public class TiltSensorAbleFragmentPri extends YcLazyFragment {
//    @BindView(R.id.VHLayout)
//    VHLayout VHLayout;
//
//    private int mPageIndex = 1;
//    private final int mPageSize = 10;
//    private int mPageTotal = 1;
//
//    public static TiltSensorAbleFragmentPri newInstance() {
//        return new TiltSensorAbleFragmentPri();
//    }
//
//    @Override
//    public int getLayoutResId() {
//        return R.layout.tilt_sensor_able_fragent;
//    }
//
//    VHAdapterPri mAdapter;
//
//    @Override
//    public void initView() {
//        ArrayList mDataModels = new ArrayList<>();
//        for (int i = 0; i < 20; i++) {
//            TiltSensorDataJson coinInfo = new TiltSensorDataJson();
//            coinInfo.name = "" + (20 - i);
//            coinInfo.priceLast = "20.0";
//            coinInfo.riseRate24 = "0.2";
//            coinInfo.vol24 = "10020";
//            coinInfo.close = "22.2";
//            coinInfo.open = "40.0";
//            coinInfo.bid = "33.2";
//            coinInfo.ask = "19.0";
//            coinInfo.amountPercent = "33.3%";
//            mDataModels.add(coinInfo);
//        }
//
//        VHLayout.setHeaderListData(getResources().getStringArray(R.array.tiltSensorTitleName));
////
//        mAdapter = new VHAdapterPri<TiltSensorDataJson>(getContext(), R.layout.tilt_sensor_item, mDataModels) {
//
//            @Override
//            protected void bindData(VHViewHolder helper, TiltSensorDataJson item, int position) {
//                helper.setText(R.id.tiltSensorItem1, item.name)
//                        .setText(R.id.tiltSensorItem2, item.priceLast)
//                        .setText(R.id.tiltSensorItem3, item.riseRate24)
//                        .setText(R.id.tiltSensorItem4, item.vol24)
//                        .setText(R.id.tiltSensorItem5, item.close)
//                        .setText(R.id.tiltSensorItem6, item.open)
//                        .setText(R.id.tiltSensorItem7, item.bid)
//                        .setText(R.id.tiltSensorItem8, item.ask)
//                        .setText(R.id.tiltSensorItem9, item.amountPercent);
//            }
//
//            @Override
//            public void loadMore() {
//                Observable.timer(2, TimeUnit.SECONDS)
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe((aLong -> {
//                            for (int i = 0; i < 3; i++) {
//                                TiltSensorDataJson coinInfo = new TiltSensorDataJson();
//                                coinInfo.name = "" + (3 - i);
//                                coinInfo.priceLast = "121";
//                                coinInfo.riseRate24 = "12";
//                                coinInfo.vol24 = "21";
//                                coinInfo.close = "22";
//                                coinInfo.open = "33";
//                                coinInfo.bid = "33.2";
//                                coinInfo.ask = "19.0";
//                                coinInfo.amountPercent = "33.3%";
//                                mAdapter.add(coinInfo);
//                            }
//                        }));
//            }
//        };
////        mAdapter.setOnLoadMoreListener(() -> {
////            if (mPageIndex >= mPageTotal) {
////                showToast("已经是最后一页了！");
////                mAdapter.loadMoreEnd();
////            } else {
////                mPageIndex++;
//////                searchDeviceList();
////            }
////        }, VHLayout.getRecyclerView());
//        VHLayout.setAdapter(mAdapter);
//    }
//
//}
