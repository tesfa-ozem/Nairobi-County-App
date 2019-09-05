
package com.aw.epayments.models.Response.OffStreetRate;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OffstreetParkingRate {

    @SerializedName("status_code")
    @Expose
    private Integer statusCode;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("response_data")
    @Expose
    private OffStreetRateData responseData;
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

    public OffStreetRateData getResponseData() {
        return responseData;
    }

    public void setResponseData(OffStreetRateData responseData) {
        this.responseData = responseData;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }

}
