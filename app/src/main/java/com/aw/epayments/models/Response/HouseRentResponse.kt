package com.aw.epayments.models.Response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class HouseRentResponse {

    @SerializedName("status_code")
    @Expose
    var statusCode: Int = 0
    @SerializedName("message")
    @Expose
    var message: String? = null
    @SerializedName("response_data")
    @Expose
    var responseData: List<ResponseDatum>? = null

}