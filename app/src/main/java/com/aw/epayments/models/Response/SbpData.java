package com.aw.epayments.models.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SbpData {
    @SerializedName("BusinessID")
    @Expose
    private Integer businessID;
    @SerializedName("BusinessName")
    @Expose
    private String businessName;
    @SerializedName("RegistrationDate")
    @Expose
    private String registrationDate;
    @SerializedName("EffectiveDate")
    @Expose
    private String effectiveDate;
    @SerializedName("PINNumber")
    @Expose
    private String pINNumber;
    @SerializedName("VATNumber")
    @Expose
    private String vATNumber;
    @SerializedName("POBox")
    @Expose
    private String pOBox;
    @SerializedName("PostalCode")
    @Expose
    private String postalCode;
    @SerializedName("Town")
    @Expose
    private String town;
    @SerializedName("Telephone1")
    @Expose
    private String telephone1;
    @SerializedName("Telephone2")
    @Expose
    private String telephone2;
    @SerializedName("FaxNumber")
    @Expose
    private String faxNumber;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("PhysicalAddress")
    @Expose
    private String physicalAddress;
    @SerializedName("PlotNumber")
    @Expose
    private String plotNumber;
    @SerializedName("ContactPersonName")
    @Expose
    private String contactPersonName;
    @SerializedName("ContactPersonDesignation")
    @Expose
    private String contactPersonDesignation;
    @SerializedName("ContactPersonPOBox")
    @Expose
    private String contactPersonPOBox;
    @SerializedName("ContactPersonPostalCode")
    @Expose
    private String contactPersonPostalCode;
    @SerializedName("ContactPersonTown")
    @Expose
    private String contactPersonTown;
    @SerializedName("ContactPersonTelephone1")
    @Expose
    private String contactPersonTelephone1;
    @SerializedName("ContactPersonTelephone2")
    @Expose
    private String contactPersonTelephone2;
    @SerializedName("ContactPersonFaxNumber")
    @Expose
    private String contactPersonFaxNumber;
    @SerializedName("BusinessActivityDescription")
    @Expose
    private String businessActivityDescription;
    @SerializedName("OtherBusinessClassificationDetails")
    @Expose
    private String otherBusinessClassificationDetails;
    @SerializedName("PremisesArea")
    @Expose
    private String premisesArea;
    @SerializedName("NumberOfEmployees")
    @Expose
    private String numberOfEmployees;
    @SerializedName("ActivityCode")
    @Expose
    private Integer activityCode;
    @SerializedName("ZoneCode")
    @Expose
    private Integer zoneCode;
    @SerializedName("WardCode")
    @Expose
    private Integer wardCode;
    @SerializedName("RelativeSize")
    @Expose
    private Integer relativeSize;
    @SerializedName("OperationalStatus")
    @Expose
    private Integer operationalStatus;
    @SerializedName("TerminalDate")
    @Expose
    private String terminalDate;
    @SerializedName("UpdatedBy")
    @Expose
    private String updatedBy;
    @SerializedName("DateUpdated")
    @Expose
    private String dateUpdated;
    @SerializedName("IDTypeCode")
    @Expose
    private Integer iDTypeCode;
    @SerializedName("IDDocumentNumber")
    @Expose
    private String iDDocumentNumber;

    public Integer getBusinessID() {
        return businessID;
    }

    public void setBusinessID(Integer businessID) {
        this.businessID = businessID;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getPINNumber() {
        return pINNumber;
    }

    public void setPINNumber(String pINNumber) {
        this.pINNumber = pINNumber;
    }

    public String getVATNumber() {
        return vATNumber;
    }

    public void setVATNumber(String vATNumber) {
        this.vATNumber = vATNumber;
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

    public String getTelephone1() {
        return telephone1;
    }

    public void setTelephone1(String telephone1) {
        this.telephone1 = telephone1;
    }

    public String getTelephone2() {
        return telephone2;
    }

    public void setTelephone2(String telephone2) {
        this.telephone2 = telephone2;
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

    public String getPhysicalAddress() {
        return physicalAddress;
    }

    public void setPhysicalAddress(String physicalAddress) {
        this.physicalAddress = physicalAddress;
    }

    public String getPlotNumber() {
        return plotNumber;
    }

    public void setPlotNumber(String plotNumber) {
        this.plotNumber = plotNumber;
    }

    public String getContactPersonName() {
        return contactPersonName;
    }

    public void setContactPersonName(String contactPersonName) {
        this.contactPersonName = contactPersonName;
    }

    public String getContactPersonDesignation() {
        return contactPersonDesignation;
    }

    public void setContactPersonDesignation(String contactPersonDesignation) {
        this.contactPersonDesignation = contactPersonDesignation;
    }

    public String getContactPersonPOBox() {
        return contactPersonPOBox;
    }

    public void setContactPersonPOBox(String contactPersonPOBox) {
        this.contactPersonPOBox = contactPersonPOBox;
    }

    public String getContactPersonPostalCode() {
        return contactPersonPostalCode;
    }

    public void setContactPersonPostalCode(String contactPersonPostalCode) {
        this.contactPersonPostalCode = contactPersonPostalCode;
    }

    public String getContactPersonTown() {
        return contactPersonTown;
    }

    public void setContactPersonTown(String contactPersonTown) {
        this.contactPersonTown = contactPersonTown;
    }

    public String getContactPersonTelephone1() {
        return contactPersonTelephone1;
    }

    public void setContactPersonTelephone1(String contactPersonTelephone1) {
        this.contactPersonTelephone1 = contactPersonTelephone1;
    }

    public String getContactPersonTelephone2() {
        return contactPersonTelephone2;
    }

    public void setContactPersonTelephone2(String contactPersonTelephone2) {
        this.contactPersonTelephone2 = contactPersonTelephone2;
    }

    public String getContactPersonFaxNumber() {
        return contactPersonFaxNumber;
    }

    public void setContactPersonFaxNumber(String contactPersonFaxNumber) {
        this.contactPersonFaxNumber = contactPersonFaxNumber;
    }

    public String getBusinessActivityDescription() {
        return businessActivityDescription;
    }

    public void setBusinessActivityDescription(String businessActivityDescription) {
        this.businessActivityDescription = businessActivityDescription;
    }

    public String getOtherBusinessClassificationDetails() {
        return otherBusinessClassificationDetails;
    }

    public void setOtherBusinessClassificationDetails(String otherBusinessClassificationDetails) {
        this.otherBusinessClassificationDetails = otherBusinessClassificationDetails;
    }

    public String getPremisesArea() {
        return premisesArea;
    }

    public void setPremisesArea(String premisesArea) {
        this.premisesArea = premisesArea;
    }

    public String getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public void setNumberOfEmployees(String numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
    }

    public Integer getActivityCode() {
        return activityCode;
    }

    public void setActivityCode(Integer activityCode) {
        this.activityCode = activityCode;
    }

    public Integer getZoneCode() {
        return zoneCode;
    }

    public void setZoneCode(Integer zoneCode) {
        this.zoneCode = zoneCode;
    }

    public Integer getWardCode() {
        return wardCode;
    }

    public void setWardCode(Integer wardCode) {
        this.wardCode = wardCode;
    }

    public Integer getRelativeSize() {
        return relativeSize;
    }

    public void setRelativeSize(Integer relativeSize) {
        this.relativeSize = relativeSize;
    }

    public Integer getOperationalStatus() {
        return operationalStatus;
    }

    public void setOperationalStatus(Integer operationalStatus) {
        this.operationalStatus = operationalStatus;
    }

    public String getTerminalDate() {
        return terminalDate;
    }

    public void setTerminalDate(String terminalDate) {
        this.terminalDate = terminalDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(String dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public Integer getIDTypeCode() {
        return iDTypeCode;
    }

    public void setIDTypeCode(Integer iDTypeCode) {
        this.iDTypeCode = iDTypeCode;
    }

    public String getIDDocumentNumber() {
        return iDDocumentNumber;
    }

    public void setIDDocumentNumber(String iDDocumentNumber) {
        this.iDDocumentNumber = iDDocumentNumber;
    }
}
