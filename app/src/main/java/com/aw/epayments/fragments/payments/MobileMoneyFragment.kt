package com.aw.epayments.fragments.payments

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.aw.epayments.R
import com.aw.epayments.activity.Recipts
import com.aw.epayments.api.Api
import com.aw.epayments.api.Const
import com.aw.epayments.activity.HouseRentReciept
import com.aw.epayments.layouts.SeasonalParkingRecieptActivity
import com.aw.epayments.models.NewApiResponse.BillBase
import com.aw.epayments.models.NewApiResponse.Payment_Base
import com.aw.epayments.models.NewApiResponse.ReciptBase

import com.aw.epayments.models.Response.PushNotificationResponse
import com.aw.epayments.models.Response.TransactionStatusResponse
import com.aw.epayments.models.Response.offStreetPayment.OffStreetPaymentResponse
import com.aw.epayments.models.Response.seasonalParkingPaymentResponse.SnPresponse
import com.aw.epayments.models.Response.seasonalParkingRecipt.SeasonalParkingRecipt
import com.github.ybq.android.spinkit.style.Wave
import com.google.gson.Gson
import kotlinx.android.synthetic.main.alert_daily_parking.view.*
import kotlinx.android.synthetic.main.fragment_mobile_money.*
import kotlinx.android.synthetic.main.fragment_mobile_money.view.*
import kotlinx.android.synthetic.main.transaction_failed.view.*
import java.util.HashMap

import java.util.Objects



class MobileMoneyFragment : Fragment() {
    internal lateinit var view: View


    private var phoneNumberCount: Int = 0
    private var cancel = false
    private var focusView: View? = null
    private lateinit var unqCode: String
    private var bundle: Bundle? = null
    private lateinit var mWaveDrawable: Wave
    private lateinit var alertadd: AlertDialog
    private lateinit var failedDialog: AlertDialog
    private lateinit var paymentThread: PaymentThread
    private lateinit var factory: LayoutInflater
    private lateinit var AlertView: View
    private var IsSeasonal: Boolean? = false
    private var amount = 0
    private var paymentInstructions = ""
    private var checkDelay = 1
    var stream: String = ""
    var bill: String = ""
    lateinit var result_data: BillBase
    lateinit var reciept_data: ReciptBase



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        view = inflater.inflate(R.layout.fragment_mobile_money, container, false)
        factory = LayoutInflater.from(context)
        AlertView = factory.inflate(R.layout.alert_daily_parking, null)
        paymentThread = PaymentThread()
        h = Handler()
        alertadd = AlertDialog.Builder(
                context!!).create()
        failedDialog = AlertDialog.Builder(context!!).create()
        mWaveDrawable = Wave()
        bundle = arguments
        val mTextEditorWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

                phoneNumberCount = s.length
                if (s.length == 10) {
                    view.payment_button.setBackgroundResource(R.drawable.background_shape_preset_button__pressed)
                }

            }

            override fun afterTextChanged(s: Editable) {}
        }
        if (bundle!!.getString("option") === "houseRent") {
            GenerateBill(bundle)

        }
        view.phoneNumber!!.addTextChangedListener(mTextEditorWatcher)
        view.payment_button.setOnClickListener {
            if (phoneNumberCount == 10) {

                if (bundle!!.getString("option") === "parking") {
                    view.amount_topay.isFocusable = false
                    InitiateparkingPayment()
                    IsSeasonal = false
                } else if (bundle!!.getString("option") === "houseRent" && bill != "") {
                    InitiateRentPayment()
                } else if (bundle!!.getString("option") === "landRate") {
                    view.edit_amount.visibility = View.VISIBLE
                    payLandRate()
                } else if (bundle!!.getString("option") === "ubp") {

                    InitiateUbpPayment()
                } else if (bundle!!.getString("option") === "ms") {

                    InitiateMsPayment()
                } else if (bundle!!.getString("option") === "seasonal") {
                    InitiateSeasonalParkingpayments()
                    IsSeasonal = true
                } else if (bundle!!.getString("option") === "offstreet") {
                    InitiateOffStreetPayment()
                } else if (bundle!!.getString("option") === "houseRent" && bill == "") {
                    Toast.makeText(context, "Please try again", Toast.LENGTH_LONG).show()
                    GenerateBill(bundle)
                }

            } else {
                view.phoneNumber!!.error = "Check Number"
                focusView = phoneNumber
                cancel = true
            }
            if (cancel) {
                // There was an error; don't attempt login and focus the first
                // form field with an error.
                focusView!!.requestFocus()
            }
        }


        if (bundle!!.getString("option") === "houseRent") {
            stream = bundle!!.getString("option")

            //view.edit_amount.visibility = View.VISIBLE
            view.amount_topay.setText(bundle!!.getString("Amount"))
        } else if (bundle!!.getString("option") === "landRate") {
            //view.edit_amount.visibility = View.VISIBLE
            view.amount_topay.setText(bundle!!.getString("amount"))
        }
        try {
            handler = object : Handler() {
                override fun handleMessage(msg: Message) {

                    when (msg.what) {
                        0 -> alertadd.dismiss()
                        1 ->
                            paymentThread.run()
                        2 -> {
                            paymentInstructions = String.format("" +
                                    " 1.Go to Your M-pesa menu on your phone.\n" +
                                    " 2.Select lipa na M-pesa\n" +
                                    " 3.Select Paybill\n" +
                                    " 4.Input paybill No. 367776.\n" +
                                    " 5.Input %1\$s as the account No.\n" +
                                    " 6.Enter Kes. %2\$s and confirm.\n" +
                                    " 7.Wait for a Confirmation SMS from " +
                                    "   mpesa and click confirm payment button below.\n", unqCode, amount.toString())
                            alertadd.dismiss()
                            val factory1 = LayoutInflater.from(context)
                            val AlertView1 = factory1.inflate(R.layout.transaction_failed, null)
                            AlertView1.payment_instructions.text = paymentInstructions
                            failedDialog.setView(AlertView1)
                            failedDialog.show()
                            Log.e("RESPONSE: ", "Failed")
                            Log.i("##", checkDelay.toString())
                        }
                        3 -> {
                            try {
                                AlertView.cancel_action.setOnClickListener {
                                    handler.removeCallbacks(runnable)
                                    h.removeCallbacks(runnable)
                                    alertadd.dismiss()
                                }
                                alertadd.setCancelable(false)
                                alertadd.setView(AlertView)
                                alertadd.show()
                            } catch (ex: Exception) {
                                Log.e("handleMessage 3", ex.localizedMessage)
                            }

                        }
                        else -> {
                        }
                    }//Something else
                }
            }
        } catch (ex: Exception) {
            Log.e("handler ", ex.localizedMessage)
        }

        return view
    }

    private fun InitiateSeasonalParkingpayments() {
        try {
            handler.obtainMessage(3).sendToTarget()

            Api.getVolley(context, Api.POST, Api.InitiateParkingPayments, "{\n" +
                    "  \"phone_number\": \"" + phoneNumber!!.text.toString() + "\"\n" +
                    "}", object : Api.VolleyCallback {
                @SuppressLint("SetTextI18n")
                override fun onSuccess(result: String) {
                    val transaction = Api.mGson.fromJson(result, SnPresponse::class.java)
                    if (transaction.statusCode == 200) {
                        unqCode = transaction.responseData
                        //checkPayments();
                        paymentThread.run()

                    } else {
                        handler.obtainMessage(0).sendToTarget()
                        Toast.makeText(activity, transaction.message, Toast.LENGTH_LONG).show()
                    }


                }
            })
        } catch (e: Exception) {
            Log.e("InitSeasonalParking", e.localizedMessage)
        }


    }

    @SuppressLint("SetTextI18n")
    fun InitiateparkingPayment() {
        handler.obtainMessage(3).sendToTarget()
        amount = RemoveComma(bundle!!.getString("Amount"))
        if (amount > 0) {
            Api.getVolley(context, Api.POST, Api.InitiateParkingPayment, "{\n" +
                    "  \"payment_reason\": 0,\n" +
                    "  \"registration_no\": \"" + bundle!!.getString("NumberPalte") + "\",\n" +
                    "  \"specify_amount\": true,\n" +
                    "  \"amount\": " + amount + ",\n" +
                    "  \"vehicle_category_code\": \"" + bundle!!.getString("VehicleType") + "\",\n" +
                    "  \"zone_code\": \"" + bundle!!.getString("Zone") + "\",\n" +
                    "  \"duration_code\": \"" + bundle!!.getString("duration") + "\",\n" +
                    "  \"phone_number\": \"" + phoneNumber!!.text.toString() + "\"\n" +
                    "}", object : Api.VolleyCallback {
                @SuppressLint("SetTextI18n")
                override fun onSuccess(result: String) {
                    try {
                        val transaction = Api.mGson.fromJson(result, PushNotificationResponse::class.java)
                        if (transaction.statusCode == 200) {
                            unqCode = transaction.parkingCode
                            //checkPayments();
                            paymentThread.run()
                        } else {
                            handler.obtainMessage(0).sendToTarget()
                            Toast.makeText(activity, transaction.message, Toast.LENGTH_LONG).show()
                        }
                    } catch (e: Exception) {
                        Log.e("", e.localizedMessage)
                    }

                }
            })
        }
    }

    private fun GetRecipt() {
        try {
            if (IsSeasonal!!) {
                Api.getVolley(parentFragment!!.context, Api.POST, Api.GetSeasonalParkingReceipt, "{\n" +
                        "  \"receipt_number\": \"" + unqCode + "\"\n" +
                        "}", object : Api.VolleyCallback {
                    override fun onSuccess(result: String) {
                        val response = Api.mGson.fromJson(result, SeasonalParkingRecipt::class.java)
                        if (response.statusCode == 200) {
                            checkDelay = 1
                            Const.getInstance().seasonalParkingRecipt = response
                            h.removeCallbacks(runnable)
                            alertadd.dismiss()

                            startActivity(Intent(Objects.requireNonNull<Context>(context).applicationContext, SeasonalParkingRecieptActivity::class.java))
                            handler.obtainMessage(0).sendToTarget()
                        } else if (checkDelay < 60000) {
                            handler.obtainMessage(1).sendToTarget()
                        } else if (checkDelay > 60000) {
                            checkDelay = 1
                            handler.obtainMessage(2).sendToTarget()
                        }

                    }
                })

            } else {
                Api.getVolley(parentFragment!!.context, Api.GET, Api.GetTransactionReceipt + unqCode, "", object : Api.VolleyCallback {
                    override fun onSuccess(result: String) {
                        /*Toast.makeText(getActivity(), String.valueOf(checkDelay), Toast.LENGTH_SHORT).show();*/
                        val status = Api.mGson.fromJson(result, TransactionStatusResponse::class.java)
                        if (status.statusCode == 200) {
                            checkDelay = 1
                            Const.getInstance().transactionStatusResponse = status
                            handler.removeCallbacks(runnable)
                            alertadd.dismiss()
                            startActivity(Intent(Objects.requireNonNull<Context>(context).applicationContext, Recipts::class.java))
                            handler.obtainMessage(0).sendToTarget()
                        } else if (checkDelay < 30000) {
                            handler.obtainMessage(1).sendToTarget()
                        } else if (checkDelay > 30000) {
                            checkDelay = 1
                            handler.obtainMessage(2).sendToTarget()
                        }


                        // h.removeCallbacks(runnable);


                    }
                })

            }
        } catch (e: Exception) {
            Log.e("", e.localizedMessage)
        }


        //Toast.makeText(getActivity(), String.valueOf(checkDelay), Toast.LENGTH_SHORT).show();


    }

    @TargetApi(Build.VERSION_CODES.M)
    private fun GetHouseRecipt() {
        try {
            val gson = Gson()
            val params = HashMap<String, String>()
            params["BillNumber"] = bill

            Api.getVolley(context, Api.POST, Api.get_house_recipt, object : Api.VolleyCallback {
                override fun onSuccess(result: String) {
                    reciept_data = gson.fromJson(result, ReciptBase::class.java)
                    if (reciept_data.status_code == 200) {
                        checkDelay = 1
                        //Const.getInstance().seasonalParkingRecipt = response
                        h.removeCallbacks(runnable)
                        GetReceiptFragment(result)
                        //Toast.makeText(context,"Hello",Toast.LENGTH_LONG).show()
                        alertadd.dismiss()

                        //startActivity(Intent(Objects.requireNonNull<Context>(context).getApplicationContext(), SeasonalParkingRecieptActivity::class.java))
                        handler.obtainMessage(0).sendToTarget()

                    } else if (checkDelay < 45000) {
                        handler.obtainMessage(1).sendToTarget()
                    } else if (checkDelay > 45000) {
                        checkDelay = 1
                        handler.obtainMessage(2).sendToTarget()
                    }
                    /*when {
                        reciept_data.status_code == 200 -> {
                            *//*checkDelay = 1

                            //handler.removeCallbacks(runnable)
                            GetReceiptFragment(HouseRentReciept(),result)
                            //alertadd.dismiss()
                            //startActivity(Intent(Objects.requireNonNull<Context>(context).applicationContext, Recipts::class.java))
                            handler.obtainMessage(0).sendToTarget()*//*
                        }
                        checkDelay < 30000 -> handler.obtainMessage(1).sendToTarget()
                        checkDelay > 30000 -> {
                            checkDelay = 1
                            handler.obtainMessage(2).sendToTarget()
                        }
                    }*/


                }
            }, apiUrl = Api.HouseRentReciptUrl, form_data = params)

        } catch (ex: Exception) {

        }
    }

    private fun GetReceiptFragment(receipt:String){
        try {
            val intent = Intent(activity, HouseRentReciept::class.java)
            val name = "Transporter"
            intent.putExtra("name", receipt)
            startActivity(intent)
            //startActivity(Intent(Objects.requireNonNull<Context>(context).applicationContext, HouseRentReciept::class.java))
        } catch (e: Exception) {
            Log.e("landrates", e.localizedMessage)
        }
    }
    fun InitiateRentPayment() {
        try {
            val gson = Gson()
            val params = HashMap<String, String>()
            params["BillNumber"] = bill

            params["PhoneNumber"] = phoneNumber.text.toString()
            handler.obtainMessage(3).sendToTarget()
            Api.getVolley(context, Api.POST, Api.Push_payments, object : Api.VolleyCallback {
                override fun onSuccess(result: String) {

                    Toast.makeText(context, gson.fromJson(result, Payment_Base::class.java).message, Toast.LENGTH_LONG).show()
                    val paymentDetails: Payment_Base = gson.fromJson(result, Payment_Base::class.java)

                    if (paymentDetails.status_code == 200) {
                        unqCode = bill
                        paymentThread.run()
                    } else {
                        handler.obtainMessage(0).sendToTarget()

                    }


                }
            }, apiUrl = Api.HouseRentUrl, form_data = params)

        } catch (ex: java.lang.Exception) {
            Toast.makeText(context, ex.toString(), Toast.LENGTH_LONG).show()
            Log.e("Not workig", ex.toString())
        }
    }

    fun GenerateBill(bundle: Bundle?) {
        try {
            val gson = Gson()

            val params = HashMap<String, String>()
            params["EstateID"] = bundle!!.getString("EstateID")
            params["HouseTypeId"] = bundle.getString("houseTypeId")
            params["HouseNumber"] = bundle.getString("HouseNumber")
            params["UHN"] = bundle.getString("UHN")
            params["Amount"] =bundle.getString("Amount")
            //Toast.makeText(context,bundle.getString("Amount"),Toast.LENGTH_LONG).show()
            Api.getVolley(context, Api.POST, Api.Get_Bill, object : Api.VolleyCallback {
                override fun onSuccess(result: String) {
                    result_data = gson.fromJson(result, BillBase::class.java)
                    if (result_data.status_code == 200) {
                        bill = result_data.response_data.bill_number
                        Toast.makeText(context, result_data.response_data.bill_number, Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(context, result_data.message, Toast.LENGTH_LONG).show()
                    }

                }
            }, apiUrl = Api.HouseRentUrl, form_data = params)
        } catch (ex: java.lang.Exception) {
            //Toast.makeText(context,ex.localizedMessage,Toast.LENGTH_LONG).show()
            Log.e("", ex.toString())
        }


    }

    fun payLandRate() {
        try {
            amount = RemoveComma(amount_topay.text.toString())
            handler.obtainMessage(3).sendToTarget()

            Api.getVolley(context, Api.POST, Api.InitiateLandRatePayment, "{\n" +
                    "  \"UPN\": \"" + bundle!!.get("UPN") + "\",\n" +
                    "  \"specify_amount\": true,\n" +
                    "  \"amount\": " + java.lang.Double.parseDouble(amount_topay.text.toString()) + ",\n" +
                    "  \"phone_number\": \"" + phoneNumber!!.text + "\"\n" +
                    "}", object : Api.VolleyCallback {
                override fun onSuccess(result: String) {
                    val transaction = Api.mGson.fromJson(result, PushNotificationResponse::class.java)
                    if (transaction.statusCode == 200) {
                        unqCode = transaction.parkingCode
                        paymentThread.run()

                    } else {
                        handler.obtainMessage(0).sendToTarget()
                        Toast.makeText(activity, transaction.message, Toast.LENGTH_LONG).show()
                    }
                }
            })
        } catch (e: Exception) {
            Log.e("", e.localizedMessage)
        }


    }

    fun InitiateUbpPayment() {
        try {
            handler.obtainMessage(3).sendToTarget()
            Api.getVolley(context, Api.POST, Api.GetPaymentCode, "{\n" +
                    "  \"business_id\": \"" + bundle!!.getString("busines_id") + "\",\n" +
                    "  \"year\": " + Integer.parseInt(Objects.requireNonNull(bundle!!.getString("year"))) + ",\n" +
                    "  \"PhoneNumber\": \"" + phoneNumber!!.text + "\",\n" +
                    "  \"PaymentCode\": \"" + bundle!!.getString("paymentCode") + "\"\n" +
                    "}", object : Api.VolleyCallback {
                override fun onSuccess(result: String) {
                    val transaction = Api.mGson.fromJson(result, PushNotificationResponse::class.java)
                    if (transaction.statusCode == 200) {

                        unqCode = transaction.parkingCode
                        paymentThread.run()


                    } else {
                        handler.obtainMessage(0).sendToTarget()
                        Toast.makeText(activity, transaction.message, Toast.LENGTH_LONG).show()
                    }
                }
            })
        } catch (e: Exception) {
            Log.e("", e.localizedMessage)
        }

    }

    /*TODO:Bind amount*/
    fun InitiateMsPayment() {
        try {
            handler.obtainMessage(3).sendToTarget()
            Api.getVolley(context, Api.POST, Api.InitiateBillPayment, "{\n" +
                    "  \"BillNumber\": \"" + bundle!!.getString("BillNumber") + "\",\n" +
                    "  \"Amount\":  1,\n" +
                    "  \"PhoneNumber\": \"" + phoneNumber!!.text + "\"\n" +
                    "}", object : Api.VolleyCallback {
                override fun onSuccess(result: String) {
                    val transaction = Api.mGson.fromJson(result, PushNotificationResponse::class.java)
                    if (transaction.statusCode == 200) {
                        unqCode = transaction.parkingCode
                        paymentThread.run()
                    } else {
                        handler.obtainMessage(0).sendToTarget()
                        Toast.makeText(activity, transaction.message, Toast.LENGTH_LONG).show()
                    }
                }
            })
        } catch (e: Exception) {
            Log.e("", e.localizedMessage)
        }


    }

    fun InitiateOffStreetPayment() {
        try {
            Api.getVolley(context, Api.POST, Api.InitiateOffstreetPayment, "{\n" +
                    "  \"transaction_code\": \"PKF05311539\",\n" +
                    "  \"zone_code\": \"" + bundle!!.getString("zoneCode") + "\",\n" +
                    "  \"account_from\": \"" + phoneNumber!!.text + "\"\n" +
                    "}", object : Api.VolleyCallback {
                override fun onSuccess(result: String) {
                    val response = Api.mGson.fromJson(result, OffStreetPaymentResponse::class.java)
                    if (response.statusCode == 200) {
                        unqCode = response.responseData
                        paymentThread.run()
                    } else {
                        handler.obtainMessage(0).sendToTarget()
                        Toast.makeText(activity, response.message, Toast.LENGTH_LONG).show()
                    }
                }
            })
        } catch (e: Exception) {
            Log.e("", e.localizedMessage)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        h.removeCallbacks(runnable)
    }

    private fun RemoveComma(x: String?): Int {


        val d = java.lang.Double.parseDouble(x!!)

        return d.toInt()
    }

    inner class PaymentThread : Thread() {
        override fun run() {

            Handler().postDelayed({

                if (stream == "houseRent") {
                    GetHouseRecipt()
                    checkDelay += 3000
                } else {
                    checkDelay += 3000
                    GetRecipt()
                }


            }, 4500)


        }
    }

    override fun onAttachFragment(fragment: Fragment?) {
        if (fragment!!.isAdded)
            return
        super.onAttachFragment(fragment)
    }

    companion object {

        internal lateinit var h: Handler
        internal lateinit var handler: Handler
        internal var delay = 5000
        internal var runnable: Runnable? = null
    }

}
