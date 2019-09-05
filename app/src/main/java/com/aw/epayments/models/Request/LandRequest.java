package com.aw.epayments.models.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LandRequest {

    @SerializedName("plot_number")
    @Expose
    private String plotNumber;

    public String getPlotNumber() {
        return plotNumber;
    }

    public void setPlotNumber(String plotNumber) {
        this.plotNumber = plotNumber;
    }

}