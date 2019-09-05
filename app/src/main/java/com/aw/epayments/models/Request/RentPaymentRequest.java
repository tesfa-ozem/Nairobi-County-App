package com.aw.epayments.models.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RentPaymentRequest {
    @SerializedName("UHN")
    @Expose
    private String uHN;
    @SerializedName("tenancyId")
    @Expose
    private Integer tenancyId;

    public String getUHN() {
        return uHN;
    }

    public void setUHN(String uHN) {
        this.uHN = uHN;
    }

    public Integer getTenancyId() {
        return tenancyId;
    }

    public void setTenancyId(Integer tenancyId) {
        this.tenancyId = tenancyId;
    }
}
