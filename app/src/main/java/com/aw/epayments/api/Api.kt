package com.aw.epayments.api

import android.content.Context
import android.util.Log

import com.android.volley.AuthFailureError
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyLog
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder

object Api {


    var URL = "https://www.revenuesure.co.ke/RevenueSure"
    var HouseRentUrl = "http://biller.revenuesure.co.ke/houserent"
    var HouseRentReciptUrl = "http://biller.revenuesure.co.ke"

    var GetParking = "/api/Parking/GetParking"
    var GetToken = "/api/Account/GetToken"
    var GetParkingCategories = "/api/Parking/GetParkingCategories"
    var GetCustomerPayments = "/api/Payment/GetCustomerPayments"
    var GetParkingCharge = "/api/Parking/GetParkingCharge"
    var InitiateParkingPayment = "/api/Parking/InitiateParkingPayment"

    var GetTransactionReceipt = "/api/Payment/GetTransactionReceipt?identifierCode="

    var GetLandRate = "/api/Land/GetLandRate"
    var InitiateLandRatePayment = "/api/Land/InitiateLandRatePayment"
    var GetBusinessBill = "/api/Sbp/GetBusinessBill"
    var GetPaymentCode = "/api/Sbp/GetPaymentCode"
    var GetBillDetails = "/api/Billing/GetBillDetails"
    var InitiateBillPayment = "/api/Billing/InitiateBillPayment"
    var Register = "/api/Account/Register"
    var AddSeasonalParkingEntry = "/api/Parking/AddSeasonalParkingEntry"
    var GetParkingEntries = "/api/Parking/GetParkingEntries"
    var RemoveParkingEntry = "/api/Parking/RemoveParkingEntry"
    var InitiateParkingPayments = "/api/Parking/InitiateParkingPayments"
    var GetSeasonalParkingReceipt = "/api/Parking/GetSeasonalParkingReceipt"
    var GetOffstreetZones = "/api/Parking/GetOffstreetZones"
    var OffstreetCheckInCheckout = "/api/Parking/OffstreetCheckInCheckout"
    var InitiateOffstreetPayment = "/api/Parking/InitiateOffstreetPayment"
    var GetPenaltyCharge = "/api/Parking/GetPenaltyCharge"
    var Get_House_type = "/api/housing/house_type"
    var Get_Estates = "/api/housing/estates"
    var Get_House_Number = "/api/housing/house_no"
    var Get_House_Details = "/api/housing/housedetails"
    var Get_Bill = "/api/housing/billing"
    var Push_payments = "/api/housing/payment"
    var get_house_recipt = "/api/billing/GetReceipt"


    var mGson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
            .setPrettyPrinting()
            .setLenient()
            .serializeNulls()
            .create()
    var POST = Request.Method.POST
    var GET = Request.Method.GET

    var MY_SOCKET_TIMEOUT_MS = 50000

    fun getVolley(
            context: Context?,
            requestMethod: Int,
            function: String,
            jsonRequest: String?,
            callback: VolleyCallback,
            apiUrl:String = URL,
            form_data: Map<String, String>? = null
    ) {
        if (context != null) {
            val requestQueue = Volley.newRequestQueue(context)
            val url = apiUrl + function
            Log.i("### URL", url)
            Log.i("### REQUEST", jsonRequest)
            Log.i("###", form_data.toString())

            val stringRequest = object : StringRequest(requestMethod, url, Response.Listener { response ->
                Log.i("### RESPONSE", response)

                callback.onSuccess(response)
            }, Response.ErrorListener { error -> Log.e("VOLLEY", error.toString()) }) {
                @Throws(AuthFailureError::class)
                override fun getHeaders(): Map<String, String> {
                    val params = HashMap<String, String>()
                    params["Authorization"] = "Bearer " + Api.getValue(context, "token")!!
                    params["token"] = "Bearer " + Api.getValue(context, "token")!!
                    params["latitude"] = Api.getValue(context, "latitude")!!
                    params["longitude"] = Api.getValue(context, "longitude")!!
                    params["description"] = Api.getValue(context, "description")!!
                    params["street"] = Api.getValue(context, "street")!!
                    params["versionName"] = context.packageManager
                            .getPackageInfo(context.packageName, 0).versionName!!

                    return params
                }

                override fun getBodyContentType(): String {
                    return "application/json; charset=utf-8"
                }


                @Throws(AuthFailureError::class)
                override fun getBody(): ByteArray? {
                    return try {
                        jsonRequest?.toByteArray(charset("utf-8"))
                    } catch (uee: Exception) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", jsonRequest, "utf-8")
                        null
                    }

                }

                override fun getParams(): Map<String, String>? {
                    return form_data
                }

            }
            stringRequest.retryPolicy = DefaultRetryPolicy(
                    MY_SOCKET_TIMEOUT_MS,
                    2,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)

            requestQueue.add(stringRequest)
        }


    }

    fun getVolley(context: Context?,
                      requestMethod: Int,
                      function: String,
                      callback: VolleyCallback,
                      apiUrl:String = URL,
                  form_data: Map<String, String>?){
        if (context != null) {
            val requestQueue = Volley.newRequestQueue(context)
            val url = apiUrl + function
            Log.i("### URL", url)


            val stringRequest = object : StringRequest(
                    requestMethod,
                    url,
                    Response.Listener { response ->
                Log.i("### RESPONSE", response)

                callback.onSuccess(response)
            },
                    Response.ErrorListener { error -> Log.e("VOLLEY", error.toString()) }) {
                @Throws(AuthFailureError::class)
                override fun getHeaders(): Map<String, String> {
                    val params = HashMap<String, String>()
                    params["Authorization"] = "Bearer " + Api.getValue(context, "token")!!
                    params["token"] = "Bearer " + Api.getValue(context, "token")!!
                    params["versionName"] = context.packageManager
                            .getPackageInfo(context.packageName, 0).versionName!!
                    params["Content-Type"]= "application/x-www-form-urlencoded"

                    return params
                }


                override fun getParams(): Map<String, String> {

                    return form_data!!
                }

            }
            stringRequest.retryPolicy = DefaultRetryPolicy(
                    MY_SOCKET_TIMEOUT_MS,
                    2,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)

            requestQueue.add(stringRequest)
        }
    }

    interface VolleyCallback {
        fun onSuccess(result: String)
    }

    fun save(context: Context, key: String, value: String) {
        val prefs = context.getSharedPreferences("com.aw.enformentclamping.pref", Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putString(key, value)
        editor.apply()

    }

    fun getValue(context: Context, key: String): String? {
        //if 1st time , register the user
        val prefs = context.getSharedPreferences("com.aw.enformentclamping.pref", Context.MODE_PRIVATE)
        return prefs.getString(key, "")
    }

    fun clearValue(context: Context, key: String) {
        val settings = context.getSharedPreferences("com.aw.enformentclamping.pref", Context.MODE_PRIVATE)
        settings.edit().remove(key).apply()
    }

}
