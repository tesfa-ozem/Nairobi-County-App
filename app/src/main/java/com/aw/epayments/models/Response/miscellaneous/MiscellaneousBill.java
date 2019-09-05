
package com.aw.epayments.models.Response.miscellaneous;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MiscellaneousBill {

    @SerializedName("status_code")
    @Expose
    private Integer statusCode;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("response_data")
    @Expose
    private MsData responseData;
    @SerializedName("error")
    @Expose
    private String error;

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

    public MsData getResponseData() {
        return responseData;
    }

    public void setResponseData(MsData responseData) {
        this.responseData = responseData;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
