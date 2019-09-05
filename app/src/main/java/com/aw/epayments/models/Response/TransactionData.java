
package com.aw.epayments.models.Response;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TransactionData {

    @SerializedName("identifier")
    @Expose
    private String identifier;
    @SerializedName("street")
    @Expose
    private String street;
    @SerializedName("zone")
    @Expose
    private String zone;
    @SerializedName("receipt_for")
    @Expose
    private String receiptFor;
    @SerializedName("total_amount")
    @Expose
    private Integer totalAmount;
    @SerializedName("payment_mode")
    @Expose
    private String paymentMode;
    @SerializedName("payment_from")
    @Expose
    private String paymentFrom;
    @SerializedName("receipt_number")
    @Expose
    private String receiptNumber;
    @SerializedName("duration")
    @Expose
    private Object duration;
    @SerializedName("transaction_time")
    @Expose
    private String transactionTime;
    @SerializedName("transaction_details")
    @Expose
    private List<TransactionDetail> transactionDetails = null;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getReceiptFor() {
        return receiptFor;
    }

    public void setReceiptFor(String receiptFor) {
        this.receiptFor = receiptFor;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getPaymentFrom() {
        return paymentFrom;
    }

    public void setPaymentFrom(String paymentFrom) {
        this.paymentFrom = paymentFrom;
    }

    public String getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public Object getDuration() {
        return duration;
    }

    public void setDuration(Object duration) {
        this.duration = duration;
    }

    public String getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(String transactionTime) {
        this.transactionTime = transactionTime;
    }

    public List<TransactionDetail> getTransactionDetails() {
        return transactionDetails;
    }

    public void setTransactionDetails(List<TransactionDetail> transactionDetails) {
        this.transactionDetails = transactionDetails;
    }

}
