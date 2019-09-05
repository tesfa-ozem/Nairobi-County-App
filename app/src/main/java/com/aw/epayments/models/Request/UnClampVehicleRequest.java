package com.aw.epayments.models.Request;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UnClampVehicleRequest {

    @SerializedName("registration_number")
    @Expose
    private String registrationNumber;
    @SerializedName("unclamping_reason")
    @Expose
    private String unclampingReason;

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getUnclampingReason() {
        return unclampingReason;
    }

    public void setUnclampingReason(String unclampingReason) {
        this.unclampingReason = unclampingReason;
    }

}