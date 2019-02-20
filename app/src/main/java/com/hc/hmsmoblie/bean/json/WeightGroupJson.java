package com.hc.hmsmoblie.bean.json;

import java.util.List;

/**
 * Created by Administrator on 2019/2/19.
 */

public class WeightGroupJson {

    private List<String> SupplierList;
    private List<String> MerchandiseList;
    private List<String> WeighingList;

    public List<String> getSupplierList() {
        return SupplierList;
    }

    public void setSupplierList(List<String> SupplierList) {
        this.SupplierList = SupplierList;
    }

    public List<String> getMerchandiseList() {
        return MerchandiseList;
    }

    public void setMerchandiseList(List<String> MerchandiseList) {
        this.MerchandiseList = MerchandiseList;
    }
    public List<String> getWeighingList() {
        return WeighingList;
    }

    public void setWeighingList(List<String> WeighingList) {
        this.WeighingList = WeighingList;
    }
}
