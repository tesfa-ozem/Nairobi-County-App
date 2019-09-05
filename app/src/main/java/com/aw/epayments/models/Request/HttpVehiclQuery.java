package com.aw.epayments.models.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HttpVehiclQuery {

    @SerializedName("registration_no")
    @Expose
    private String registrationNo;

    public String getRegistrationNo() {
        return registrationNo;
    }

    public void setRegistrationNo(String registrationNo) {
        this.registrationNo = registrationNo;
    }

}