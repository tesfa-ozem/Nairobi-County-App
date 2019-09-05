
package com.aw.epayments.models.Response.miscellaneous;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BillingInfo {

    @SerializedName("LocalAuthorityID")
    @Expose
    private Integer localAuthorityID;
    @SerializedName("BillNumber")
    @Expose
    private String billNumber;
    @SerializedName("BillType")
    @Expose
    private Integer billType;
    @SerializedName("DateIssued")
    @Expose
    private String dateIssued;
    @SerializedName("ClientsID")
    @Expose
    private String clientsID;
    @SerializedName("ClientsName")
    @Expose
    private String clientsName;
    @SerializedName("BillAmount")
    @Expose
    private String billAmount;
    @SerializedName("BillStatus")
    @Expose
    private Integer billStatus;
    @SerializedName("REFReceiptNumber")
    @Expose
    private String rEFReceiptNumber;
    @SerializedName("DatePaid")
    @Expose
    private String datePaid;

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

    public Integer getBillType() {
        return billType;
    }

    public void setBillType(Integer billType) {
        this.billType = billType;
    }

    public String getDateIssued() {
        return dateIssued;
    }

    public void setDateIssued(String dateIssued) {
        this.dateIssued = dateIssued;
    }

    public String getClientsID() {
        return clientsID;
    }

    public void setClientsID(String clientsID) {
        this.clientsID = clientsID;
    }

    public String getClientsName() {
        return clientsName;
    }

    public void setClientsName(String clientsName) {
        this.clientsName = clientsName;
    }

    public String getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(String billAmount) {
        this.billAmount = billAmount;
    }

    public Integer getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(Integer billStatus) {
        this.billStatus = billStatus;
    }

    public String getREFReceiptNumber() {
        return rEFReceiptNumber;
    }

    public void setREFReceiptNumber(String rEFReceiptNumber) {
        this.rEFReceiptNumber = rEFReceiptNumber;
    }

    public String getDatePaid() {
        return datePaid;
    }

    public void setDatePaid(String datePaid) {
        this.datePaid = datePaid;
    }

}
