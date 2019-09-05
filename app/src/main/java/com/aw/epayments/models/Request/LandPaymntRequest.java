package com.aw.epayments.models.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LandPaymntRequest {
    @SerializedName("UPN")
    @Expose
    private String plotNo;
    @SerializedName("specify_amount")
    @Expose
    private boolean specifyAmount;
    @SerializedName("amount")
    @Expose
    private double amount;
    @SerializedName("phone_number")
    @Expose
    private String phoneNumber;

    public String getPlotNo() {
        return plotNo;
    }

    public void setPlotNo(String plotNo) {
        this.plotNo = plotNo;
    }

    public boolean isSpecifyAmount() {
        return specifyAmount;
    }

    public void setSpecifyAmount(boolean specifyAmount) {
        this.specifyAmount = specifyAmount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
