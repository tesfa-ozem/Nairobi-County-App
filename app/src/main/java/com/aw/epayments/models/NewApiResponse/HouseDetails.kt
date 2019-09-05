package com.aw.epayments.models.NewApiResponse

import com.google.gson.annotations.SerializedName


    data class HouseDetailsBase (

            @SerializedName("status_code") val status_code : Int,
            @SerializedName("message") val message : String,
            @SerializedName("response_data") val response_data : HouseDetails_data
    )
    data class HouseDetails_data (

            @SerializedName("id") val id : Int,
            @SerializedName("UHN") val uHN : String,
            @SerializedName("HouseNumber") val houseNumber : String,
            @SerializedName("HouseTypeID") val houseTypeID : Int,
            @SerializedName("HouseType") val houseType : String,
            @SerializedName("EstateID") val estateID : Int,
            @SerializedName("Estate") val estate : String,
            @SerializedName("CustomerSupplierName") val customerSupplierName : String,
            @SerializedName("RentArrears") val rentArrears : Double,
            @SerializedName("OtherArrears") val otherArrears : Double,
            @SerializedName("RentCharges") val rentCharges : Double,
            @SerializedName("OtherCharges") val otherCharges : Double,
            @SerializedName("ReceiptAmount") val receiptAmount : Double,
            @SerializedName("Adjustment") val adjustment : Double,
            @SerializedName("PlotNumber") val plotNumber : String,
            @SerializedName("PhysicalAddress") val physicalAddress : String,
            @SerializedName("CurrentBalance") val currentBalance : Double,
            @SerializedName("StandardRent") val standardRent : Double,
            @SerializedName("ActualRent") val actualRent : Double,
            @SerializedName("TransactionDate") val transactionDate : String,
            @SerializedName("BillingMonth") val billingMonth : String
    )
