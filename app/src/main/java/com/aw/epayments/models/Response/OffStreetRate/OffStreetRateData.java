
package com.aw.epayments.models.Response.OffStreetRate;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OffStreetRateData {

    @SerializedName("clock_direction")
    @Expose
    private String clockDirection;
    @SerializedName("clock_in")
    @Expose
    private String clockIn;
    @SerializedName("time_paid")
    @Expose
    private Object timePaid;
    @SerializedName("duration")
    @Expose
    private String duration;
    @SerializedName("clock_out")
    @Expose
    private Object clockOut;
    @SerializedName("exit_delay")
    @Expose
    private Object exitDelay;
    @SerializedName("transaction_code")
    @Expose
    private String transactionCode;
    @SerializedName("total_amount")
    @Expose
    private Integer totalAmount;
    @SerializedName("charges")
    @Expose
    private List<Charge> charges = null;

    public String getClockDirection() {
        return clockDirection;
    }

    public void setClockDirection(String clockDirection) {
        this.clockDirection = clockDirection;
    }

    public String getClockIn() {
        return clockIn;
    }

    public void setClockIn(String clockIn) {
        this.clockIn = clockIn;
    }

    public Object getTimePaid() {
        return timePaid;
    }

    public void setTimePaid(Object timePaid) {
        this.timePaid = timePaid;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Object getClockOut() {
        return clockOut;
    }

    public void setClockOut(Object clockOut) {
        this.clockOut = clockOut;
    }

    public Object getExitDelay() {
        return exitDelay;
    }

    public void setExitDelay(Object exitDelay) {
        this.exitDelay = exitDelay;
    }

    public String getTransactionCode() {
        return transactionCode;
    }

    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<Charge> getCharges() {
        return charges;
    }

    public void setCharges(List<Charge> charges) {
        this.charges = charges;
    }

}
