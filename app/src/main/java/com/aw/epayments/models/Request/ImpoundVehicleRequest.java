package com.aw.epayments.models.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImpoundVehicleRequest {
    @SerializedName("registration_number")
    @Expose
    private String registrationNumber;
    @SerializedName("clamping_reason")
    @Expose
    private String clampingReason;
    @SerializedName("yard_id")
    @Expose
    private String yardId;
    @SerializedName("towing_reason")
    @Expose
    private String towingReason;
    @SerializedName("vehicle_category_code")
    @Expose
    private String vehicleCategoryCode;
    @SerializedName("street_code")
    @Expose
    private String streetCode;
    @SerializedName("request_type")
    @Expose
    private String requestType;

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getClampingReason() {
        return clampingReason;
    }

    public void setClampingReason(String clampingReason) {
        this.clampingReason = clampingReason;
    }

    public String getVehicleCategoryCode() {
        return vehicleCategoryCode;
    }

    public void setVehicleCategoryCode(String vehicleCategoryCode) {
        this.vehicleCategoryCode = vehicleCategoryCode;
    }

    public String getStreetCode() {
        return streetCode;
    }

    public void setStreetCode(String streetCode) {
        this.streetCode = streetCode;
    }

    public String getYardId() {
        return yardId;
    }

    public void setYardId(String yardId) {
        this.yardId = yardId;
    }

    public String getTowingReason() {
        return towingReason;
    }

    public void setTowingReason(String towingReason) {
        this.towingReason = towingReason;
    }
}
