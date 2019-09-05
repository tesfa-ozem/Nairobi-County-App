package com.aw.epayments.models.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseDatum {

    @SerializedName("EstateID")
    @Expose
    private Integer estateID;
    @SerializedName("EstateDescription")
    @Expose
    private String estateDescription;
    @SerializedName("CostCentreID")
    @Expose
    private Integer costCentreID;
    @SerializedName("CostCentreName")
    @Expose
    private String costCentreName;
    @SerializedName("EntryTypeID")
    @Expose
    private Integer entryTypeID;

    public Integer getEstateID() {
        return estateID;
    }

    public void setEstateID(Integer estateID) {
        this.estateID = estateID;
    }

    public String getEstateDescription() {
        return estateDescription;
    }

    public void setEstateDescription(String estateDescription) {
        this.estateDescription = estateDescription;
    }

    public Integer getCostCentreID() {
        return costCentreID;
    }

    public void setCostCentreID(Integer costCentreID) {
        this.costCentreID = costCentreID;
    }

    public String getCostCentreName() {
        return costCentreName;
    }

    public void setCostCentreName(String costCentreName) {
        this.costCentreName = costCentreName;
    }

    public Integer getEntryTypeID() {
        return entryTypeID;
    }

    public void setEntryTypeID(Integer entryTypeID) {
        this.entryTypeID = entryTypeID;
    }

}