package com.aw.epayments.models.NewApiResponse

import com.google.gson.annotations.SerializedName


    data class HouseTypesBase (

            @SerializedName("status_code") val status_code : Int,
            @SerializedName("message") val message : String,
            @SerializedName("response_data") val response_data : List<HouseTypes_data>
    )

    data class HouseTypes_data (

            @SerializedName("id") val id : Int,
            @SerializedName("EstateID") val estateID : Int,
            @SerializedName("ESTATE") val eSTATE : String,
            @SerializedName("HOUSETYPE") val hOUSETYPE : String,
            @SerializedName("Description") val description : String,
            @SerializedName("StandardCharge") val standardCharge : Double,
            @SerializedName("ChargeID") val chargeID : Int,
            @SerializedName("Status") val status : Int,
            @SerializedName("HouseTypeID") val houseTypeID : Int,
            @SerializedName("CHARGE_AMOUNT") val cHARGE_AMOUNT : Double
    )
