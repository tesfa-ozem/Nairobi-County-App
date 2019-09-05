package com.aw.epayments.models.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClampVehicleRequest {




    @SerializedName("registration_number")
    @Expose
    private String registrationNumber;
    @SerializedName("clamping_reason")
    @Expose
    private String clampingReason;
    @SerializedName("vehicle_category_code")
    @Expose
    private String vehicleCategoryCode;
    @SerializedName("street_code")
    @Expose
    private String streetCode;

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

}


