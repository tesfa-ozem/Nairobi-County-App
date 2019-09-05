package com.aw.epayments.models.NewApiResponse

import com.google.gson.annotations.SerializedName


    data class BillBase (

            @SerializedName("status_code") val status_code : Int,
            @SerializedName("message") val message : String,
            @SerializedName("response_data") val response_data : Bill_data
    )
    data class Bill_data (

            @SerializedName("bill_number") val bill_number : String,
            @SerializedName("payment_code") val payment_code : String
    )
