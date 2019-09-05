
package com.aw.epayments.models.Response.parkingStatusResponse;

import com.aw.epayments.models.Response.Parking;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ParkingStatusResponse {

    @SerializedName("status_code")
    @Expose
    private Integer statusCode;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("parking")
    @Expose
    private Parking parking;
    @SerializedName("error")
    @Expose
    private Object error;

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

    public Parking getParking() {
        return parking;
    }

    public void setParking(Parking parking) {
        this.parking = parking;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }

}
