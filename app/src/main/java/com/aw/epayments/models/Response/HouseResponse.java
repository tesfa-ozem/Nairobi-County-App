package com.aw.epayments.models.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HouseResponse {


    @SerializedName("status_code")
    @Expose
    private Integer statusCode;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("response_data")
    @Expose
    private List<HouseResult> responseData = null;
    @SerializedName("error")
    @Expose
    private Object error;

    public List<HouseResult> getResponseData() {
        return responseData;
    }

    public void setResponseData(List<HouseResult> responseData) {
        this.responseData = responseData;
    }

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


    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }


}
