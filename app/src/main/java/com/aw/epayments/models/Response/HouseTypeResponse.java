package com.aw.epayments.models.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HouseTypeResponse {
    @SerializedName("status_code")
    @Expose
    private int statusCode;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("response_data")
    @Expose
    private List<HouseTypeData> resultData = null;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<HouseTypeData> getResultData() {
        return resultData;
    }

    public void setResultData(List<HouseTypeData> resultData) {
        this.resultData = resultData;
    }



}
