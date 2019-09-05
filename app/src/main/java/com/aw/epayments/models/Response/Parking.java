package com.aw.epayments.models.Response;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Parking {

    @SerializedName("parking_code")
    @Expose
    private String parkingCode;
    @SerializedName("transaction_code")
    @Expose
    private String transactionCode;
    @SerializedName("padlock_no")
    @Expose
    private String padlockNo;
    @SerializedName("enforcement_status")
    @Expose
    private String enforcementStatus;
    @SerializedName("agent")
    @Expose
    private String agent;
    @SerializedName("payment_mode")
    @Expose
    private String paymentMode;
    @SerializedName("vehicle_reg_no")
    @Expose
    private String vehicleRegNo;
    @SerializedName("zone_code")
    @Expose
    private String zoneCode;
    @SerializedName("street")
    @Expose
    private String street;
    @SerializedName("category_code")
    @Expose
    private String categoryCode;
    @SerializedName("duration_code")
    @Expose
    private String durationCode;
    @SerializedName("parking_cost")
    @Expose
    private Integer parkingCost;
    @SerializedName("amount_paid")
    @Expose
    private Integer amountPaid;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("query_time")
    @Expose
    private String queryTime;
    @SerializedName("expiry_date")
    @Expose
    private String expiryTime;
    @SerializedName("latitude")
    @Expose
    private Integer latitude;
    @SerializedName("longitude")
    @Expose
    private Integer longitude;
    @SerializedName("session_code")
    @Expose
    private String sessionCode;
    @SerializedName("description")
    @Expose
    private String description;

    public String getParkingCode() {
        return parkingCode;
    }

    public void setParkingCode(String parkingCode) {
        this.parkingCode = parkingCode;
    }

    public String getTransactionCode() {
        return transactionCode;
    }

    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
    }

    public String getPadlockNo() {
        return padlockNo;
    }

    public void setPadlockNo(String padlockNo) {
        this.padlockNo = padlockNo;
    }

    public String getEnforcementStatus() {
        return enforcementStatus;
    }

    public void setEnforcementStatus(String enforcementStatus) {
        this.enforcementStatus = enforcementStatus;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getVehicleRegNo() {
        return vehicleRegNo;
    }

    public void setVehicleRegNo(String vehicleRegNo) {
        this.vehicleRegNo = vehicleRegNo;
    }

    public String getZoneCode() {
        return zoneCode;
    }

    public void setZoneCode(String zoneCode) {
        this.zoneCode = zoneCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getDurationCode() {
        return durationCode;
    }

    public void setDurationCode(String durationCode) {
        this.durationCode = durationCode;
    }

    public Integer getParkingCost() {
        return parkingCost;
    }

    public void setParkingCost(Integer parkingCost) {
        this.parkingCost = parkingCost;
    }

    public Integer getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Integer amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getQueryTime() {
        return queryTime;
    }

    public void setQueryTime(String queryTime) {
        this.queryTime = queryTime;
    }

    public String getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(String expiryTime) {
        this.expiryTime = expiryTime;
    }

    public Integer getLatitude() {
        return latitude;
    }

    public void setLatitude(Integer latitude) {
        this.latitude = latitude;
    }

    public Integer getLongitude() {
        return longitude;
    }

    public void setLongitude(Integer longitude) {
        this.longitude = longitude;
    }

    public String getSessionCode() {
        return sessionCode;
    }

    public void setSessionCode(String sessionCode) {
        this.sessionCode = sessionCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

