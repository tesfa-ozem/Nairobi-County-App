package com.aw.epayments.models.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VehicleParkingRequest {
    @SerializedName("status_code")
    @Expose
    private int statusCode;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("error")
    @Expose
    private Object error;

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

    public static class InitiatePaymentModel {

        @SerializedName("registration_no")
        @Expose
        private String registrationNo;
        @SerializedName("specify_amount")
        @Expose
        private boolean specifyAmount;
        @SerializedName("amount")
        @Expose
        private int amount;
        @SerializedName("vehicle_category_code")
        @Expose
        private String vehicleCategoryCode;
        @SerializedName("zone_code")
        @Expose
        private String zoneCode;
        @SerializedName("duration_code")
        @Expose
        private String durationCode;
        @SerializedName("phone_number")
        @Expose
        private String phoneNumber;

        public String getRegistrationNo() {
            return registrationNo;
        }

        public void setRegistrationNo(String registrationNo) {
            this.registrationNo = registrationNo;
        }

        public boolean isSpecifyAmount() {
            return specifyAmount;
        }

        public void setSpecifyAmount(boolean specifyAmount) {
            this.specifyAmount = specifyAmount;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public String getVehicleCategoryCode() {
            return vehicleCategoryCode;
        }

        public void setVehicleCategoryCode(String vehicleCategoryCode) {
            this.vehicleCategoryCode = vehicleCategoryCode;
        }

        public String getZoneCode() {
            return zoneCode;
        }

        public void setZoneCode(String zoneCode) {
            this.zoneCode = zoneCode;
        }

        public String getDurationCode() {
            return durationCode;
        }

        public void setDurationCode(String durationCode) {
            this.durationCode = durationCode;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

    }
}
