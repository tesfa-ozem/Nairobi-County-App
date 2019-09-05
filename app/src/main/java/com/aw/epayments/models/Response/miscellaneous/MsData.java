
package com.aw.epayments.models.Response.miscellaneous;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MsData {

    @SerializedName("BillingDetail")
    @Expose
    private BillingDetail billingDetail;
    @SerializedName("BillingInfo")
    @Expose
    private BillingInfo billingInfo;
    @SerializedName("paymentCode")
    @Expose
    private String paymentCode;

    public BillingDetail getBillingDetail() {
        return billingDetail;
    }

    public void setBillingDetail(BillingDetail billingDetail) {
        this.billingDetail = billingDetail;
    }

    public BillingInfo getBillingInfo() {
        return billingInfo;
    }

    public void setBillingInfo(BillingInfo billingInfo) {
        this.billingInfo = billingInfo;
    }

    public String getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode;
    }

}
