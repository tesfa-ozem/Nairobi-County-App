package com.aw.epayments.models.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HttpPenaltyCharges {

    @SerializedName("vehicle_category_code")
    @Expose
    private String vehicleCategoryCode;
    @SerializedName("penalty_type_code")
    @Expose
    private String penaltyTypeCode;
    @SerializedName("zone_code")
    @Expose
    private String zoneCode;

    public String getVehicleCategoryCode() {
        return vehicleCategoryCode;
    }

    public void setVehicleCategoryCode(String vehicleCategoryCode) {
        this.vehicleCategoryCode = vehicleCategoryCode;
    }

    public String getPenaltyTypeCode() {
        return penaltyTypeCode;
    }

    public void setPenaltyTypeCode(String penaltyTypeCode) {
        this.penaltyTypeCode = penaltyTypeCode;
    }

    public String getZoneCode() {
        return zoneCode;
    }

    public void setZoneCode(String zoneCode) {
        this.zoneCode = zoneCode;
    }

}