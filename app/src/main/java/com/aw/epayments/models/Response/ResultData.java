package com.aw.epayments.models.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultData {

    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("plot_details")
    @Expose
    private PlotDetails plotDetails;
    @SerializedName("amount_due")
    @Expose
    private int amountDue;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public PlotDetails getPlotDetails() {
        return plotDetails;
    }

    public void setPlotDetails(PlotDetails plotDetails) {
        this.plotDetails = plotDetails;
    }

    public int getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(int amountDue) {
        this.amountDue = amountDue;
    }

}