package com.aw.epayments.models.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EntriesResponse {
    @SerializedName("parking_code")
    @Expose
    private String parkingCode;
    @SerializedName("vehicle_reg_no")
    @Expose
    private String vehicleRegNo;
    @SerializedName("zone_code")
    @Expose
    private Object zoneCode;
    @SerializedName("street")
    @Expose
    private Object street;
    @SerializedName("duration_code")
    @Expose
    private String durationCode;
    @SerializedName("parking_cost")
    @Expose
    private Integer parkingCost;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("query_time")
    @Expose
    private Object queryTime;
    @SerializedName("expiry_time")
    @Expose
    private Object expiryTime;
    @SerializedName("latitude")
    @Expose
    private Integer latitude;
    @SerializedName("longitude")
    @Expose
    private Integer longitude;
    @SerializedName("session_code")
    @Expose
    private String sessionCode;

    public String getParkingCode() {
        return parkingCode;
    }

    public void setParkingCode(String parkingCode) {
        this.parkingCode = parkingCode;
    }

    public String getVehicleRegNo() {
        return vehicleRegNo;
    }

    public void setVehicleRegNo(String vehicleRegNo) {
        this.vehicleRegNo = vehicleRegNo;
    }

    public Object getZoneCode() {
        return zoneCode;
    }

    public void setZoneCode(Object zoneCode) {
        this.zoneCode = zoneCode;
    }

    public Object getStreet() {
        return street;
    }

    public void setStreet(Object street) {
        this.street = street;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getQueryTime() {
        return queryTime;
    }

    public void setQueryTime(Object queryTime) {
        this.queryTime = queryTime;
    }

    public Object getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(Object expiryTime) {
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

}
