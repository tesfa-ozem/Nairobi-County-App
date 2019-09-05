package com.aw.epayments.models.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HouseResult {

    @SerializedName("LocalAuthorityID")
    @Expose
    private String localAuthorityID;
    @SerializedName("StatementID")
    @Expose
    private Integer statementID;
    @SerializedName("TenancyID")
    @Expose
    private Integer tenancyID;
    @SerializedName("UHN")
    @Expose
    private String uHN;
    @SerializedName("Status")
    @Expose
    private Integer status;
    @SerializedName("CurrentBalance")
    @Expose
    private String currentBalance;
    @SerializedName("CustomerSupplierID")
    @Expose
    private String customerSupplierID;
    @SerializedName("CustomerSupplierName")
    @Expose
    private String customerSupplierName;
    @SerializedName("EstateID")
    @Expose
    private Integer estateID;
    @SerializedName("EstateDescription")
    @Expose
    private String estateDescription;
    @SerializedName("HouseTypeID")
    @Expose
    private Integer houseTypeID;
    @SerializedName("HouseTypeDescription")
    @Expose
    private String houseTypeDescription;
    @SerializedName("BlockNumber")
    @Expose
    private String blockNumber;
    @SerializedName("PlotNumber")
    @Expose
    private String plotNumber;
    @SerializedName("ZoneName")
    @Expose
    private String zoneName;
    @SerializedName("WardName")
    @Expose
    private String wardName;
    @SerializedName("PhysicalAddress")
    @Expose
    private String physicalAddress;
    @SerializedName("HouseNumber")
    @Expose
    private String houseNumber;
    @SerializedName("TenancyDate")
    @Expose
    private String tenancyDate;
    @SerializedName("PoBox")
    @Expose
    private String poBox;
    @SerializedName("PostalCode")
    @Expose
    private String postalCode;
    @SerializedName("Town")
    @Expose
    private String town;
    @SerializedName("LocationDescription")
    @Expose
    private String locationDescription;
    @SerializedName("CostCentreID")
    @Expose
    private Integer costCentreID;
    @SerializedName("TenancyNumber")
    @Expose
    private String tenancyNumber;
    @SerializedName("FormNumber")
    @Expose
    private String formNumber;
    @SerializedName("Deposit")
    @Expose
    private String deposit;
    @SerializedName("BillingStatus")
    @Expose
    private Integer billingStatus;
    @SerializedName("BillingStatus2")
    @Expose
    private Integer billingStatus2;
    @SerializedName("ISOngoingORComplete")
    @Expose
    private Integer iSOngoingORComplete;
    @SerializedName("StandardRent")
    @Expose
    private String standardRent;
    @SerializedName("OtherCharges")
    @Expose
    private String otherCharges;
    @SerializedName("ZoneCode")
    @Expose
    private String zoneCode;
    @SerializedName("WardID")
    @Expose
    private String wardID;
    @SerializedName("EntryTypeID")
    @Expose
    private Integer entryTypeID;

    public String getLocalAuthorityID() {
        return localAuthorityID;
    }

    public void setLocalAuthorityID(String localAuthorityID) {
        this.localAuthorityID = localAuthorityID;
    }

    public Integer getStatementID() {
        return statementID;
    }

    public void setStatementID(Integer statementID) {
        this.statementID = statementID;
    }

    public Integer getTenancyID() {
        return tenancyID;
    }

    public void setTenancyID(Integer tenancyID) {
        this.tenancyID = tenancyID;
    }

    public String getUHN() {
        return uHN;
    }

    public void setUHN(String uHN) {
        this.uHN = uHN;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(String currentBalance) {
        this.currentBalance = currentBalance;
    }

    public String getCustomerSupplierID() {
        return customerSupplierID;
    }

    public void setCustomerSupplierID(String customerSupplierID) {
        this.customerSupplierID = customerSupplierID;
    }

    public String getCustomerSupplierName() {
        return customerSupplierName;
    }

    public void setCustomerSupplierName(String customerSupplierName) {
        this.customerSupplierName = customerSupplierName;
    }

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

    public Integer getHouseTypeID() {
        return houseTypeID;
    }

    public void setHouseTypeID(Integer houseTypeID) {
        this.houseTypeID = houseTypeID;
    }

    public String getHouseTypeDescription() {
        return houseTypeDescription;
    }

    public void setHouseTypeDescription(String houseTypeDescription) {
        this.houseTypeDescription = houseTypeDescription;
    }

    public String getBlockNumber() {
        return blockNumber;
    }

    public void setBlockNumber(String blockNumber) {
        this.blockNumber = blockNumber;
    }

    public String getPlotNumber() {
        return plotNumber;
    }

    public void setPlotNumber(String plotNumber) {
        this.plotNumber = plotNumber;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }

    public String getPhysicalAddress() {
        return physicalAddress;
    }

    public void setPhysicalAddress(String physicalAddress) {
        this.physicalAddress = physicalAddress;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getTenancyDate() {
        return tenancyDate;
    }

    public void setTenancyDate(String tenancyDate) {
        this.tenancyDate = tenancyDate;
    }

    public String getPoBox() {
        return poBox;
    }

    public void setPoBox(String poBox) {
        this.poBox = poBox;
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

    public String getLocationDescription() {
        return locationDescription;
    }

    public void setLocationDescription(String locationDescription) {
        this.locationDescription = locationDescription;
    }

    public Integer getCostCentreID() {
        return costCentreID;
    }

    public void setCostCentreID(Integer costCentreID) {
        this.costCentreID = costCentreID;
    }

    public String getTenancyNumber() {
        return tenancyNumber;
    }

    public void setTenancyNumber(String tenancyNumber) {
        this.tenancyNumber = tenancyNumber;
    }

    public String getFormNumber() {
        return formNumber;
    }

    public void setFormNumber(String formNumber) {
        this.formNumber = formNumber;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public Integer getBillingStatus() {
        return billingStatus;
    }

    public void setBillingStatus(Integer billingStatus) {
        this.billingStatus = billingStatus;
    }

    public Integer getBillingStatus2() {
        return billingStatus2;
    }

    public void setBillingStatus2(Integer billingStatus2) {
        this.billingStatus2 = billingStatus2;
    }

    public Integer getISOngoingORComplete() {
        return iSOngoingORComplete;
    }

    public void setISOngoingORComplete(Integer iSOngoingORComplete) {
        this.iSOngoingORComplete = iSOngoingORComplete;
    }

    public String getStandardRent() {
        return standardRent;
    }

    public void setStandardRent(String standardRent) {
        this.standardRent = standardRent;
    }

    public String getOtherCharges() {
        return otherCharges;
    }

    public void setOtherCharges(String otherCharges) {
        this.otherCharges = otherCharges;
    }

    public String getZoneCode() {
        return zoneCode;
    }

    public void setZoneCode(String zoneCode) {
        this.zoneCode = zoneCode;
    }

    public String getWardID() {
        return wardID;
    }

    public void setWardID(String wardID) {
        this.wardID = wardID;
    }

    public Integer getEntryTypeID() {
        return entryTypeID;
    }

    public void setEntryTypeID(Integer entryTypeID) {
        this.entryTypeID = entryTypeID;
    }

}
