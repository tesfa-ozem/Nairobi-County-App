package com.aw.epayments.models.NewApiResponse
import com.google.gson.annotations.SerializedName


	data class Estates_Base (

			@SerializedName("status_code") val status_code : Int,
			@SerializedName("message") val message : String,
			@SerializedName("response_data") val response_data : List<EstateData>
	)
	data class EstateData (

			@SerializedName("id") val id : Int,
			@SerializedName("EstateID") val estateID : Int,
			@SerializedName("EstateDescription") val estateDescription : String,
			@SerializedName("CostCentreID") val costCentreID : Int,
			@SerializedName("CostCentreName") val costCentreName : String,
			@SerializedName("EntryTypeID") val entryTypeID : Int
	)


