package com.aw.epayments.models.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HouseTypeData {
    @SerializedName("ESTATE")
    @Expose
    private String eSTATE;
    @SerializedName("HOUSETYPE")
    @Expose
    private String hOUSETYPE;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("StandardCharge")
    @Expose
    private String standardCharge;
    @SerializedName("ChargeID")
    @Expose
    private Integer chargeID;
    @SerializedName("Status")
    @Expose
    private Integer status;
    @SerializedName("HouseTypeID")
    @Expose
    private Integer houseTypeID;
    @SerializedName("CHARGE_AMOUNT")
    @Expose
    private String cHARGEAMOUNT;

    public String getESTATE() {
        return eSTATE;
    }

    public void setESTATE(String eSTATE) {
        this.eSTATE = eSTATE;
    }

    public String getHOUSETYPE() {
        return hOUSETYPE;
    }

    public void setHOUSETYPE(String hOUSETYPE) {
        this.hOUSETYPE = hOUSETYPE;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStandardCharge() {
        return standardCharge;
    }

    public void setStandardCharge(String standardCharge) {
        this.standardCharge = standardCharge;
    }

    public Integer getChargeID() {
        return chargeID;
    }

    public void setChargeID(Integer chargeID) {
        this.chargeID = chargeID;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getHouseTypeID() {
        return houseTypeID;
    }

    public void setHouseTypeID(Integer houseTypeID) {
        this.houseTypeID = houseTypeID;
    }

    public String getCHARGEAMOUNT() {
        return cHARGEAMOUNT;
    }

    public void setCHARGEAMOUNT(String cHARGEAMOUNT) {
        this.cHARGEAMOUNT = cHARGEAMOUNT;
    }

}
