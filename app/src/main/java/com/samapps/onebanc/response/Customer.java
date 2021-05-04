package com.samapps.onebanc.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Customer {

    @SerializedName("vPayId")
    @Expose
    private Integer vPayId;
    @SerializedName("vPay")
    @Expose
    private String vPay;

    public Integer getvPayId() {
        return vPayId;
    }

    public void setvPayId(Integer vPayId) {
        this.vPayId = vPayId;
    }

    public String getvPay() {
        return vPay;
    }

    public void setvPay(String vPay) {
        this.vPay = vPay;
    }

}