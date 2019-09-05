package com.aw.epayments.models.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaidRentRequest {
    @SerializedName("estate_id")
    @Expose
    private String estateId;
    @SerializedName("house_type_id")
    @Expose
    private String houseTypeId;
    @SerializedName("UHN")
    @Expose
    private String uHN;
    @SerializedName("specify_amount")
    @Expose
    private Boolean specifyAmount;
    @SerializedName("amount")
    @Expose
    private Integer amount;
    @SerializedName("phone_number")
    @Expose
    private String phoneNumber;

    public String getEstateId() {
        return estateId;
    }

    public void setEstateId(String estateId) {
        this.estateId = estateId;
    }

    public String getHouseTypeId() {
        return houseTypeId;
    }

    public void setHouseTypeId(String houseTypeId) {
        this.houseTypeId = houseTypeId;
    }

    public String getUHN() {
        return uHN;
    }

    public void setUHN(String uHN) {
        this.uHN = uHN;
    }

    public Boolean getSpecifyAmount() {
        return specifyAmount;
    }

    public void setSpecifyAmount(Boolean specifyAmount) {
        this.specifyAmount = specifyAmount;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
