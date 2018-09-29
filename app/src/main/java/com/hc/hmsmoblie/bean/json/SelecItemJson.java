package com.hc.hmsmoblie.bean.json;

/**
 *
 */

public class SelecItemJson {
    public SelecItemJson(String itemText,boolean isCheck){
        this.itemText = itemText;
        this.isCheck = isCheck;
    }
    public String itemText;
    public boolean isCheck;
    public String getItemText() {
        return itemText;
    }

    public void setItemText(String num) {
        this.itemText = itemText;
    }
    public boolean getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(boolean isCheck) {
        this.isCheck = isCheck;
    }
}
