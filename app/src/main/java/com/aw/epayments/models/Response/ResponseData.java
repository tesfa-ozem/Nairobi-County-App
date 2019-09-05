
package com.aw.epayments.models.Response;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseData {

    @SerializedName("business_id")
    @Expose
    private String businessId;
    @SerializedName("bill_number")
    @Expose
    private String billNumber;
    @SerializedName("transaction_code")
    @Expose
    private String transactionCode;
    @SerializedName("business_name")
    @Expose
    private String businessName;
    @SerializedName("amount_to_pay")
    @Expose
    private Integer amountToPay;
    @SerializedName("charges")
    @Expose
    private List<Charge> charges = null;

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }

    public String getTransactionCode() {
        return transactionCode;
    }

    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public Integer getAmountToPay() {
        return amountToPay;
    }

    public void setAmountToPay(Integer amountToPay) {
        this.amountToPay = amountToPay;
    }

    public List<Charge> getCharges() {
        return charges;
    }

    public void setCharges(List<Charge> charges) {
        this.charges = charges;
    }

}
