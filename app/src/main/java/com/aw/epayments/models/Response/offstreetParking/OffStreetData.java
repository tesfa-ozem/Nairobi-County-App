
package com.aw.epayments.models.Response.offstreetParking;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OffStreetData {

    @SerializedName("zone_code")
    @Expose
    private String zoneCode;
    @SerializedName("description")
    @Expose
    private String description;

    public String getZoneCode() {
        return zoneCode;
    }

    public void setZoneCode(String zoneCode) {
        this.zoneCode = zoneCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
