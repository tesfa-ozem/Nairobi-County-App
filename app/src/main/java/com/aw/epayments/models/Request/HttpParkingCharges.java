package com.aw.epayments.models.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HttpParkingCharges {

    @SerializedName("vehicle_category_code")
    @Expose
    private String vehicleCategoryCode;
    @SerializedName("duration_code")
    @Expose
    private String durationCode;
    @SerializedName("zone_code")
    @Expose
    private String zoneCode;
    @SerializedName("registration_number")
    private String NumberPlates;

    public String getNumberPlates() {
        return NumberPlates;
    }

    public void setNumberPlates(String numberPlates) {
        NumberPlates = numberPlates;
    }

    public String getVehicleCategoryCode() {
        return vehicleCategoryCode;
    }

    public void setVehicleCategoryCode(String vehicleCategoryCode) {
        this.vehicleCategoryCode = vehicleCategoryCode;
    }

    public String getDurationCode() {
        return durationCode;
    }

    public void setDurationCode(String durationCode) {
        this.durationCode = durationCode;
    }

    public String getZoneCode() {
        return zoneCode;
    }

    public void setZoneCode(String zoneCode) {
        this.zoneCode = zoneCode;
    }

}
