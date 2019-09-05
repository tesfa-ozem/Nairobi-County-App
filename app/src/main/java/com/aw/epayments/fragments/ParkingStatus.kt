package com.aw.epayments.fragments

import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast

import com.aw.epayments.R
import com.aw.epayments.api.Api
import com.aw.epayments.models.Response.parkingStatusResponse.ParkingStatusResponse
import com.github.ybq.android.spinkit.style.FadingCircle
import kotlinx.android.synthetic.main.fragment_penalty.view.*
import kotlinx.android.synthetic.main.parking_status_response.view.*


class ParkingStatus : BottomSheetDialogFragment() {
    internal lateinit var view: View
    internal var checkStatus: FadingCircle? = null
    internal var cancel = false
    internal var focusView: View? = null
    internal lateinit var paymentProgress: FadingCircle


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        try {
            view = inflater.inflate(R.layout.fragment_penalty, container, false)

            view.back_action.setOnClickListener {

                view.back_action.visibility = View.GONE
                view.parking_status_response.visibility = View.GONE
                view.parking_status_field.visibility = View.VISIBLE
            }
            val checkField = object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

                }

                override fun afterTextChanged(s: Editable) {

                }
            }
            view.parking_button.setOnClickListener {
                if (view.penalty_number_plate.text.toString() != "") {
                    view.back_action.visibility = View.VISIBLE
                    CheckparkingStatus(view.penalty_number_plate.text.toString())

                } else {
                    view.penalty_number_plate.error = "Can Not Be Blank"
                    focusView = view.penalty_number_plate
                    cancel = true

                }
                if (cancel) {

                    focusView!!.requestFocus()
                } else {
                    view.penalty_number_plate.error = null
                    cancel = false
                    focusView = null
                }
            }

            dialog.window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
            view.cancel_action.setOnClickListener {

                dismiss()
            }


        } catch (e: Exception) {
            Log.e(e.message, e.localizedMessage)
        }

        return view
    }

    fun CheckparkingStatus(searchvalue: String) {

        paymentProgress = FadingCircle()
        paymentProgress.setBounds(0, 0, 1000, 100)
        paymentProgress.color = Color.WHITE
        view.parking_button.text = ""
        view.parking_button.setCompoundDrawables(null, paymentProgress, null, null)
        paymentProgress.start()
        Api.getVolley(context, Api.POST, Api.GetParking, "{\n" +
                "  \"search_criteria\": 0,\n" +
                "  \"search_value\": \"" + searchvalue + "\"\n" +
                "}", object : Api.VolleyCallback {
            override fun onSuccess(result: String) {
                try {
                    val response = Api.mGson.fromJson(result, ParkingStatusResponse::class.java)
                    if (response.statusCode == 200) {
                        view.parking_status_field.visibility = View.GONE
                        view.parking_status_response.visibility = View.VISIBLE
                        view.parking_button.text = "PROCEED"
                        view.parking_button.setTextColor(Color.WHITE)
                        view.parking_button.setCompoundDrawables(null, null, null, null)
                        paymentProgress.stop()

                        view.number_plate.text = response.parking.vehicleRegNo
                        /*street.setText(response.getParking().getZoneCode());*/
                        view.duration.text = response.parking.durationCode
                        view.time.text = response.parking.expiryTime
                        view.parking_cost.text = response.parking.amountPaid.toString()
                        view.status.text = response.parking.status
                        view.status.setTextColor(Color.parseColor("#629749"))


                    } else if (response.statusCode == 404) {
                        view.parking_status_field.visibility = View.GONE
                        view.parking_status_response.visibility = View.VISIBLE
                        view.parking_button.text = "PROCEED"
                        view.parking_button.setTextColor(Color.WHITE)
                        view.parking_button.setCompoundDrawables(null, null, null, null)
                        paymentProgress.stop()

                        view.number_plate.text = response.parking.vehicleRegNo
                        view.street.text = response.parking.zoneCode
                        view.duration.text = response.parking.durationCode
                        view.time.text = response.parking.expiryTime
                        view.parking_cost.text = response.parking.amountPaid.toString()
                        view.status.text = response.parking.status


                    }
                } catch (e: Exception) {
                    Log.e("", e.localizedMessage)
                    Toast.makeText(context, "Please try again", Toast.LENGTH_SHORT).show()
                    view.parking_button.text = "PROCEED"
                    view.parking_button.setTextColor(Color.WHITE)
                    view.parking_button.setCompoundDrawables(null, null, null, null)
                    paymentProgress.stop()
                }

            }
        })
    }



    override fun onCancel(dialog: DialogInterface?) {
        super.onCancel(dialog)
        Toast.makeText(context, "CANCEL", Toast.LENGTH_SHORT).show()
    }


}