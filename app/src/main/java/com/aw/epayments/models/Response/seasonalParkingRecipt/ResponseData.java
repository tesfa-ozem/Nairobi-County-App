
package com.aw.epayments.models.Response.seasonalParkingRecipt;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseData {

    @SerializedName("receipt_number")
    @Expose
    private String receiptNumber;
    @SerializedName("date_paid")
    @Expose
    private String datePaid;
    @SerializedName("amount")
    @Expose
    private Integer amount;
    @SerializedName("payment_for")
    @Expose
    private String paymentFor;
    @SerializedName("payment_mode")
    @Expose
    private String paymentMode;
    @SerializedName("payment_from")
    @Expose
    private String paymentFrom;
    @SerializedName("payment_details")
    @Expose
    private List<PaymentDetail> paymentDetails = null;
    @SerializedName("receiptDate")
    @Expose
    private String receiptDate;

    public String getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public String getDatePaid() {
        return datePaid;
    }

    public void setDatePaid(String datePaid) {
        this.datePaid = datePaid;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getPaymentFor() {
        return paymentFor;
    }

    public void setPaymentFor(String paymentFor) {
        this.paymentFor = paymentFor;
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

    public List<PaymentDetail> getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(List<PaymentDetail> paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    public String getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(String receiptDate) {
        this.receiptDate = receiptDate;
    }

}
