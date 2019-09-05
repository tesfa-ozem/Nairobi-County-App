package com.aw.epayments.models.Response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultDatum {

    @SerializedName("BlockLRNumber")
    @Expose
    private String blockLRNumber;
    @SerializedName("PlotNumber")
    @Expose
    private String plotNumber;
    @SerializedName("DocumentNumber")
    @Expose
    private String documentNumber;
    @SerializedName("DocumentType")
    @Expose
    private int documentType;
    @SerializedName("MinuteNumber")
    @Expose
    private String minuteNumber;
    @SerializedName("MinuteDate")
    @Expose
    private String minuteDate;
    @SerializedName("ZoneWardCode")
    @Expose
    private int zoneWardCode;
    @SerializedName("PhysicalAddress")
    @Expose
    private String physicalAddress;
    @SerializedName("Measurements")
    @Expose
    private String measurements;
    @SerializedName("Area")
    @Expose
    private double area;
    @SerializedName("AreaInHa")
    @Expose
    private double areaInHa;
    @SerializedName("UnitOfMeasure")
    @Expose
    private int unitOfMeasure;
    @SerializedName("RollTypeID")
    @Expose
    private int rollTypeID;
    @SerializedName("PropertyUseID")
    @Expose
    private int propertyUseID;
    @SerializedName("NatureOfInterest")
    @Expose
    private int natureOfInterest;
    @SerializedName("SiteValue")
    @Expose
    private String siteValue;
    @SerializedName("ValuationMethod")
    @Expose
    private int valuationMethod;
    @SerializedName("ExemptAmount")
    @Expose
    private String exemptAmount;
    @SerializedName("ExemptAuthorityReference")
    @Expose
    private String exemptAuthorityReference;
    @SerializedName("LandRatesMethod")
    @Expose
    private int landRatesMethod;
    @SerializedName("GroundRentMethod")
    @Expose
    private int groundRentMethod;
    @SerializedName("GroundRent")
    @Expose
    private String groundRent;
    @SerializedName("RateableValue")
    @Expose
    private String rateableValue;
    @SerializedName("LandRates")
    @Expose
    private String landRates;
    @SerializedName("OtherCharges")
    @Expose
    private String otherCharges;
    @SerializedName("TotalAnnualAmount")
    @Expose
    private String totalAnnualAmount;
    @SerializedName("TotalArrears")
    @Expose
    private String totalArrears;
    @SerializedName("AccumulatedPenalty")
    @Expose
    private String accumulatedPenalty;
    @SerializedName("CurrentBalance")
    @Expose
    private String currentBalance;
    @SerializedName("POBox")
    @Expose
    private String pOBox;
    @SerializedName("PostalCode")
    @Expose
    private String postalCode;
    @SerializedName("Town")
    @Expose
    private String town;
    @SerializedName("TelephoneNumber")
    @Expose
    private String telephoneNumber;
    @SerializedName("FaxNumber")
    @Expose
    private String faxNumber;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("CoOwner")
    @Expose
    private String coOwner;
    @SerializedName("LastBillNumber")
    @Expose
    private String lastBillNumber;
    @SerializedName("LastBillYear")
    @Expose
    private int lastBillYear;
    @SerializedName("LastBillAmount")
    @Expose
    private String lastBillAmount;
    @SerializedName("LastBillDueDate")
    @Expose
    private String lastBillDueDate;
    @SerializedName("LastPaymentDate")
    @Expose
    private Object lastPaymentDate;
    @SerializedName("Status")
    @Expose
    private int status;
    @SerializedName("LastPenaltyDate")
    @Expose
    private String lastPenaltyDate;
    @SerializedName("MarketCentreID")
    @Expose
    private int marketCentreID;
    @SerializedName("LastPaymentAmount")
    @Expose
    private String lastPaymentAmount;
    @SerializedName("LastReceiptNumber")
    @Expose
    private Object lastReceiptNumber;
    @SerializedName("LandRatesArrears")
    @Expose
    private String landRatesArrears;
    @SerializedName("GroundRentArrears")
    @Expose
    private String groundRentArrears;
    @SerializedName("OtherChargesArrears")
    @Expose
    private String otherChargesArrears;
    @SerializedName("LandRatesBalance")
    @Expose
    private String landRatesBalance;
    @SerializedName("GroundRentBalance")
    @Expose
    private String groundRentBalance;
    @SerializedName("OtherChargesBalance")
    @Expose
    private String otherChargesBalance;

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

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public int getDocumentType() {
        return documentType;
    }

    public void setDocumentType(int documentType) {
        this.documentType = documentType;
    }

    public String getMinuteNumber() {
        return minuteNumber;
    }

    public void setMinuteNumber(String minuteNumber) {
        this.minuteNumber = minuteNumber;
    }

    public String getMinuteDate() {
        return minuteDate;
    }

    public void setMinuteDate(String minuteDate) {
        this.minuteDate = minuteDate;
    }

    public int getZoneWardCode() {
        return zoneWardCode;
    }

    public void setZoneWardCode(int zoneWardCode) {
        this.zoneWardCode = zoneWardCode;
    }

    public String getPhysicalAddress() {
        return physicalAddress;
    }

    public void setPhysicalAddress(String physicalAddress) {
        this.physicalAddress = physicalAddress;
    }

    public String getMeasurements() {
        return measurements;
    }

    public void setMeasurements(String measurements) {
        this.measurements = measurements;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getAreaInHa() {
        return areaInHa;
    }

    public void setAreaInHa(double areaInHa) {
        this.areaInHa = areaInHa;
    }

    public int getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(int unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public int getRollTypeID() {
        return rollTypeID;
    }

    public void setRollTypeID(int rollTypeID) {
        this.rollTypeID = rollTypeID;
    }

    public int getPropertyUseID() {
        return propertyUseID;
    }

    public void setPropertyUseID(int propertyUseID) {
        this.propertyUseID = propertyUseID;
    }

    public int getNatureOfInterest() {
        return natureOfInterest;
    }

    public void setNatureOfInterest(int natureOfInterest) {
        this.natureOfInterest = natureOfInterest;
    }

    public String getSiteValue() {
        return siteValue;
    }

    public void setSiteValue(String siteValue) {
        this.siteValue = siteValue;
    }

    public int getValuationMethod() {
        return valuationMethod;
    }

    public void setValuationMethod(int valuationMethod) {
        this.valuationMethod = valuationMethod;
    }

    public String getExemptAmount() {
        return exemptAmount;
    }

    public void setExemptAmount(String exemptAmount) {
        this.exemptAmount = exemptAmount;
    }

    public String getExemptAuthorityReference() {
        return exemptAuthorityReference;
    }

    public void setExemptAuthorityReference(String exemptAuthorityReference) {
        this.exemptAuthorityReference = exemptAuthorityReference;
    }

    public int getLandRatesMethod() {
        return landRatesMethod;
    }

    public void setLandRatesMethod(int landRatesMethod) {
        this.landRatesMethod = landRatesMethod;
    }

    public int getGroundRentMethod() {
        return groundRentMethod;
    }

    public void setGroundRentMethod(int groundRentMethod) {
        this.groundRentMethod = groundRentMethod;
    }

    public String getGroundRent() {
        return groundRent;
    }

    public void setGroundRent(String groundRent) {
        this.groundRent = groundRent;
    }

    public String getRateableValue() {
        return rateableValue;
    }

    public void setRateableValue(String rateableValue) {
        this.rateableValue = rateableValue;
    }

    public String getLandRates() {
        return landRates;
    }

    public void setLandRates(String landRates) {
        this.landRates = landRates;
    }

    public String getOtherCharges() {
        return otherCharges;
    }

    public void setOtherCharges(String otherCharges) {
        this.otherCharges = otherCharges;
    }

    public String getTotalAnnualAmount() {
        return totalAnnualAmount;
    }

    public void setTotalAnnualAmount(String totalAnnualAmount) {
        this.totalAnnualAmount = totalAnnualAmount;
    }

    public String getTotalArrears() {
        return totalArrears;
    }

    public void setTotalArrears(String totalArrears) {
        this.totalArrears = totalArrears;
    }

    public String getAccumulatedPenalty() {
        return accumulatedPenalty;
    }

    public void setAccumulatedPenalty(String accumulatedPenalty) {
        this.accumulatedPenalty = accumulatedPenalty;
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

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCoOwner() {
        return coOwner;
    }

    public void setCoOwner(String coOwner) {
        this.coOwner = coOwner;
    }

    public String getLastBillNumber() {
        return lastBillNumber;
    }

    public void setLastBillNumber(String lastBillNumber) {
        this.lastBillNumber = lastBillNumber;
    }

    public int getLastBillYear() {
        return lastBillYear;
    }

    public void setLastBillYear(int lastBillYear) {
        this.lastBillYear = lastBillYear;
    }

    public String getLastBillAmount() {
        return lastBillAmount;
    }

    public void setLastBillAmount(String lastBillAmount) {
        this.lastBillAmount = lastBillAmount;
    }

    public String getLastBillDueDate() {
        return lastBillDueDate;
    }

    public void setLastBillDueDate(String lastBillDueDate) {
        this.lastBillDueDate = lastBillDueDate;
    }

    public Object getLastPaymentDate() {
        return lastPaymentDate;
    }

    public void setLastPaymentDate(Object lastPaymentDate) {
        this.lastPaymentDate = lastPaymentDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getLastPenaltyDate() {
        return lastPenaltyDate;
    }

    public void setLastPenaltyDate(String lastPenaltyDate) {
        this.lastPenaltyDate = lastPenaltyDate;
    }

    public int getMarketCentreID() {
        return marketCentreID;
    }

    public void setMarketCentreID(int marketCentreID) {
        this.marketCentreID = marketCentreID;
    }

    public String getLastPaymentAmount() {
        return lastPaymentAmount;
    }

    public void setLastPaymentAmount(String lastPaymentAmount) {
        this.lastPaymentAmount = lastPaymentAmount;
    }

    public Object getLastReceiptNumber() {
        return lastReceiptNumber;
    }

    public void setLastReceiptNumber(Object lastReceiptNumber) {
        this.lastReceiptNumber = lastReceiptNumber;
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