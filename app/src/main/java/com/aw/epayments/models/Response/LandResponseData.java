package com.aw.epayments.models.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LandResponseData {

    @SerializedName("LocalAuthorityID")
    @Expose
    private String localAuthorityID;
    @SerializedName("UPN")
    @Expose
    private String uPN;
    @SerializedName("CustomerSupplierName")
    @Expose
    private String customerSupplierName;
    @SerializedName("IDNumber")
    @Expose
    private String iDNumber;
    @SerializedName("BlockLRNumber")
    @Expose
    private String blockLRNumber;
    @SerializedName("PlotNumber")
    @Expose
    private String plotNumber;
    @SerializedName("ZoneWardName")
    @Expose
    private String zoneWardName;
    @SerializedName("MarketCentreName")
    @Expose
    private String marketCentreName;
    @SerializedName("TotalAnnualAmount")
    @Expose
    private String totalAnnualAmount;
    @SerializedName("CurrentBalance")
    @Expose
    private String currentBalance;
    @SerializedName("POBox")
    @Expose
    private String pOBox;
    @SerializedName("LandRates")
    @Expose
    private String landRates;
    @SerializedName("GroundRent")
    @Expose
    private String groundRent;
    @SerializedName("OtherCharges")
    @Expose
    private String otherCharges;
    @SerializedName("LastPaymentDate")
    @Expose
    private String lastPaymentDate;
    @SerializedName("LastPaymentAmount")
    @Expose
    private String lastPaymentAmount;
    @SerializedName("LastReceiptNumber")
    @Expose
    private String lastReceiptNumber;
    @SerializedName("LastBillDueDate")
    @Expose
    private String lastBillDueDate;
    @SerializedName("LandRatesArrears")
    @Expose
    private String landRatesArrears;
    @SerializedName("GroundRentArrears")
    @Expose
    private String groundRentArrears;
    @SerializedName("OtherChargesArrears")
    @Expose
    private String otherChargesArrears;
    @SerializedName("AccumulatedPenalty")
    @Expose
    private String accumulatedPenalty;
    @SerializedName("LandRatesBalance")
    @Expose
    private String landRatesBalance;
    @SerializedName("GroundRentBalance")
    @Expose
    private String groundRentBalance;
    @SerializedName("OtherChargesBalance")
    @Expose
    private String otherChargesBalance;

    public String getLocalAuthorityID() {
        return localAuthorityID;
    }

    public void setLocalAuthorityID(String localAuthorityID) {
        this.localAuthorityID = localAuthorityID;
    }

    public String getUPN() {
        return uPN;
    }

    public void setUPN(String uPN) {
        this.uPN = uPN;
    }

    public String getCustomerSupplierName() {
        return customerSupplierName;
    }

    public void setCustomerSupplierName(String customerSupplierName) {
        this.customerSupplierName = customerSupplierName;
    }

    public String getIDNumber() {
        return iDNumber;
    }

    public void setIDNumber(String iDNumber) {
        this.iDNumber = iDNumber;
    }

    public String getBlockLRNumber() {
        return blockLRNumber;
    }

    public void setBlockLRNumber(String blockLRNumber) {
        this.blockLRNumber = blockLRNumber;
    }

    public String getPlotNumber() {
        return plotNumber;
    }

    public void setPlotNumber(String plotNumber) {
        this.plotNumber = plotNumber;
    }

    public String getZoneWardName() {
        return zoneWardName;
    }

    public void setZoneWardName(String zoneWardName) {
        this.zoneWardName = zoneWardName;
    }

    public String getMarketCentreName() {
        return marketCentreName;
    }

    public void setMarketCentreName(String marketCentreName) {
        this.marketCentreName = marketCentreName;
    }

    public String getTotalAnnualAmount() {
        return totalAnnualAmount;
    }

    public void setTotalAnnualAmount(String totalAnnualAmount) {
        this.totalAnnualAmount = totalAnnualAmount;
    }

    public String getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(String currentBalance) {
        this.currentBalance = currentBalance;
    }

    public String getPOBox() {
        return pOBox;
    }

    public void setPOBox(String pOBox) {
        this.pOBox = pOBox;
    }

    public String getLandRates() {
        return landRates;
    }

    public void setLandRates(String landRates) {
        this.landRates = landRates;
    }

    public String getGroundRent() {
        return groundRent;
    }

    public void setGroundRent(String groundRent) {
        this.groundRent = groundRent;
    }

    public String getOtherCharges() {
        return otherCharges;
    }

    public void setOtherCharges(String otherCharges) {
        this.otherCharges = otherCharges;
    }

    public String getLastPaymentDate() {
        return lastPaymentDate;
    }

    public void setLastPaymentDate(String lastPaymentDate) {
        this.lastPaymentDate = lastPaymentDate;
    }

    public String getLastPaymentAmount() {
        return lastPaymentAmount;
    }

    public void setLastPaymentAmount(String lastPaymentAmount) {
        this.lastPaymentAmount = lastPaymentAmount;
    }

    public String getLastReceiptNumber() {
        return lastReceiptNumber;
    }

    public void setLastReceiptNumber(String lastReceiptNumber) {
        this.lastReceiptNumber = lastReceiptNumber;
    }

    public String getLastBillDueDate() {
        return lastBillDueDate;
    }

    public void setLastBillDueDate(String lastBillDueDate) {
        this.lastBillDueDate = lastBillDueDate;
    }

    public String getLandRatesArrears() {
        return landRatesArrears;
    }

    public void setLandRatesArrears(String landRatesArrears) {
        this.landRatesArrears = landRatesArrears;
    }

    public String getGroundRentArrears() {
        return groundRentArrears;
    }

    public void setGroundRentArrears(String groundRentArrears) {
        this.groundRentArrears = groundRentArrears;
    }

    public String getOtherChargesArrears() {
        return otherChargesArrears;
    }

    public void setOtherChargesArrears(String otherChargesArrears) {
        this.otherChargesArrears = otherChargesArrears;
    }

    public String getAccumulatedPenalty() {
        return accumulatedPenalty;
    }

    public void setAccumulatedPenalty(String accumulatedPenalty) {
        this.accumulatedPenalty = accumulatedPenalty;
    }

    public String getLandRatesBalance() {
        return landRatesBalance;
    }

    public void setLandRatesBalance(String landRatesBalance) {
        this.landRatesBalance = landRatesBalance;
    }

    public String getGroundRentBalance() {
        return groundRentBalance;
    }

    public void setGroundRentBalance(String groundRentBalance) {
        this.groundRentBalance = groundRentBalance;
    }

    public String getOtherChargesBalance() {
        return otherChargesBalance;
    }

    public void setOtherChargesBalance(String otherChargesBalance) {
        this.otherChargesBalance = otherChargesBalance;
    }
}
