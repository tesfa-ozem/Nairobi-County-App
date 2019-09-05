package com.aw.epayments.models.NewApiResponse

import com.google.gson.annotations.SerializedName

/*
Copyright (c) 2019 Kotlin Data Classes Generated from JSON powered by http://www.json2kotlin.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

For support, please feel free to contact me at https://www.linkedin.com/in/syedabsar */


data class ReciptBase (

        @SerializedName("status_code") val status_code : Int,
        @SerializedName("message") val message : String,
        @SerializedName("response_data") val response_data : ReceiptResponseData
)
data class ReceiptResponseData (

        @SerializedName("billInfo") val billInfo : BillInfo,
        @SerializedName("receiptInfo") val receiptInfo : ReceiptInfo
)
data class BillInfo (

        @SerializedName("id") val id : Int,
        @SerializedName("BillDetailNo") val billDetailNo : Int,
        @SerializedName("BillNo") val billNo : String,
        @SerializedName("IncomeTypeID") val incomeTypeID : Int,
        @SerializedName("CostCenterNo") val costCenterNo : Int,
        @SerializedName("AccountNo") val accountNo : String,
        @SerializedName("FeeID") val feeID : Int,
        @SerializedName("DetailAmount") val detailAmount : Double,
        @SerializedName("SKU") val sKU : String,
        @SerializedName("Currency") val currency : String,
        @SerializedName("DateCreated") val dateCreated : String,
        @SerializedName("DateModified") val dateModified : String,
        @SerializedName("CreatedBy") val createdBy : Int,
        @SerializedName("ModifiedBy") val modifiedBy : Int,
        @SerializedName("Status") val status : Int,
        @SerializedName("Customer") val customer : String,
        @SerializedName("Description") val description : String,
        @SerializedName("FeeDescription") val feeDescription : String,
        @SerializedName("BillTotal") val billTotal : Double,
        @SerializedName("ReducingBalance") val reducingBalance : Double
)
data class ReceiptInfo (

        @SerializedName("id") val id : Int,
        @SerializedName("ReceiptNo") val receiptNo : String,
        @SerializedName("BillNo") val billNo : String,
        @SerializedName("RecieptAmount") val recieptAmount : Double,
        @SerializedName("ReceiptDate") val receiptDate : String,
        @SerializedName("PrintCount") val printCount : Int,
        @SerializedName("DateCreated") val dateCreated : String,
        @SerializedName("DateModified") val dateModified : String,
        @SerializedName("CreatedBy") val createdBy : String,
        @SerializedName("ModifiedBy") val modifiedBy : String,
        @SerializedName("printed_by") val printed_by : Int
)