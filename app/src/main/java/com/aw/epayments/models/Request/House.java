package com.aw.epayments.models.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class House {
    @SerializedName("EstateId")
    @Expose
    private int estateId;
    @SerializedName("HouseTypeId")
    @Expose
    private int houseTypeId;

    public int getEstateId() {
        return estateId;
    }

    public void setEstateId(int estateId) {
        this.estateId = estateId;
    }

    public int getHouseTypeId(int hseTypeId) {
        return houseTypeId;
    }

    public void setHouseTypeId(int houseTypeId) {
        this.houseTypeId = houseTypeId;
    }


}
