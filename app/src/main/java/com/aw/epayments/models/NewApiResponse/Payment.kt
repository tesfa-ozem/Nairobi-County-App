package com.aw.epayments.models.NewApiResponse

import com.google.gson.annotations.SerializedName

data class Payment_Base (

        @SerializedName("status_code") val status_code : Int,
        @SerializedName("message") val message : String,
        @SerializedName("response_data") val response_data : Response_data
)
data class Response_data (

        @SerializedName("MerchantRequestID") val merchantRequestID : String,
        @SerializedName("CheckoutRequestID") val checkoutRequestID : String,
        @SerializedName("ResponseCode") val responseCode : Int,
        @SerializedName("ResponseDescription") val responseDescription : String,
        @SerializedName("CustomerMessage") val customerMessage : String
)