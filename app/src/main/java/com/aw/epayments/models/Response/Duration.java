
package com.aw.epayments.models.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Duration {

    @SerializedName("duration_code")
    @Expose
    private String durationCode;
    @SerializedName("description")
    @Expose
    private String description;

    public String getDurationCode() {
        return durationCode;
    }

    public void setDurationCode(String durationCode) {
        this.durationCode = durationCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static class InitiatePaymentResponse {

        @SerializedName("status_code")
        @Expose
        private int statusCode;
        @SerializedName("message")
        @Expose
        private String message;
        @SerializedName("error")
        @Expose
        private Object error;
        @SerializedName("parking_code")
        @Expose
        private String parkingCode;

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

        public Object getError() {
            return error;
        }

        public void setError(Object error) {
            this.error = error;
        }

        public String getParkingCode() {
            return parkingCode;
        }

        public void setParkingCode(String parkingCode) {
            this.parkingCode = parkingCode;
        }

    }
}
