
package com.aw.epayments.models.Response.miscellaneous;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BillingDetail {

    @SerializedName("LocalAuthorityID")
    @Expose
    private Integer localAuthorityID;
    @SerializedName("BillNumber")
    @Expose
    private String billNumber;
    @SerializedName("DetailSerialNo")
    @Expose
    private Integer detailSerialNo;
    @SerializedName("CostCentreID")
    @Expose
    private String costCentreID;
    @SerializedName("AccountNumber")
    @Expose
    private String accountNumber;
    @SerializedName("IncomeTypeID")
    @Expose
    private Integer incomeTypeID;
    @SerializedName("FeesID")
    @Expose
    private Integer feesID;
    @SerializedName("AccountName")
    @Expose
    private String accountName;
    @SerializedName("CostCenterName")
    @Expose
    private String costCenterName;
    @SerializedName("DetailAmount")
    @Expose
    private String detailAmount;

    public Integer getLocalAuthorityID() {
        return localAuthorityID;
    }

    public void setLocalAuthorityID(Integer localAuthorityID) {
        this.localAuthorityID = localAuthorityID;
    }

    public String getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }

    public Integer getDetailSerialNo() {
        return detailSerialNo;
    }

    public void setDetailSerialNo(Integer detailSerialNo) {
        this.detailSerialNo = detailSerialNo;
    }

    public String getCostCentreID() {
        return costCentreID;
    }

    public void setCostCentreID(String costCentreID) {
        this.costCentreID = costCentreID;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Integer getIncomeTypeID() {
        return incomeTypeID;
    }

    public void setIncomeTypeID(Integer incomeTypeID) {
        this.incomeTypeID = incomeTypeID;
    }

    public Integer getFeesID() {
        return feesID;
    }

    public void setFeesID(Integer feesID) {
        this.feesID = feesID;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getCostCenterName() {
        return costCenterName;
    }

    public void setCostCenterName(String costCenterName) {
        this.costCenterName = costCenterName;
    }

    public String getDetailAmount() {
        return detailAmount;
    }

    public void setDetailAmount(String detailAmount) {
        this.detailAmount = detailAmount;
    }

}
