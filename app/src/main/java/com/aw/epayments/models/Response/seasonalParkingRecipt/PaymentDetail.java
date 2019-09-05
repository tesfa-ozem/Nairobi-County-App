
package com.aw.epayments.models.Response.seasonalParkingRecipt;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaymentDetail {

    @SerializedName("registration_number")
    @Expose
    private String registrationNumber;
    @SerializedName("duration")
    @Expose
    private String duration;
    @SerializedName("amount")
    @Expose
    private Integer amount;
    @SerializedName("vehicle_category")
    @Expose
    private String vehicleCategory;
    @SerializedName("expiry_date")
    @Expose
    private String expiryDate;
    @SerializedName("parking_code")
    @Expose
    private String parkingCode;
    @SerializedName("effective_date")
    @Expose
    private String effectiveDate;

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getVehicleCategory() {
        return vehicleCategory;
    }

    public void setVehicleCategory(String vehicleCategory) {
        this.vehicleCategory = vehicleCategory;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getParkingCode() {
        return parkingCode;
    }

    public void setParkingCode(String parkingCode) {
        this.parkingCode = parkingCode;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

}
