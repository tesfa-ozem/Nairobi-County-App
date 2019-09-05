
package com.aw.epayments.models.PenaltyResponse;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PenaltyResponse {

    @SerializedName("status_code")
    @Expose
    private Integer statusCode;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("charges")
    @Expose
    private List<Charge> charges = null;
    @SerializedName("clock_in")
    @Expose
    private Object clockIn;
    @SerializedName("error")
    @Expose
    private Object error;
    @SerializedName("transaction_charge")
    @Expose
    private Object transactionCharge;

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Charge> getCharges() {
        return charges;
    }

    public void setCharges(List<Charge> charges) {
        this.charges = charges;
    }

    public Object getClockIn() {
        return clockIn;
    }

    public void setClockIn(Object clockIn) {
        this.clockIn = clockIn;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }

    public Object getTransactionCharge() {
        return transactionCharge;
    }

    public void setTransactionCharge(Object transactionCharge) {
        this.transactionCharge = transactionCharge;
    }

}
