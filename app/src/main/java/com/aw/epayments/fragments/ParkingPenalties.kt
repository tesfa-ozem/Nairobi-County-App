package com.aw.epayments.fragments

import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

import com.aw.epayments.Adapters.ParkingChargeAdapter
import com.aw.epayments.R
import com.aw.epayments.api.Api
import com.aw.epayments.models.PenaltyResponse.Charge
import com.aw.epayments.models.PenaltyResponse.PenaltyResponse
import com.aw.epayments.models.Response.ParkingAmount
import com.aw.epayments.models.Response.ParkingChargeResponse
import com.github.ybq.android.spinkit.style.FadingCircle

class ParkingPenalties : BottomSheetDialogFragment() {
    internal lateinit var view: View
    internal lateinit var cancel_action: ImageView
    internal lateinit var back_action: ImageView
    internal lateinit var parking_button: Button
    internal lateinit var pay_penalty: Button
    internal var checkStatus: FadingCircle? = null
    internal lateinit var penalty_number_plate: EditText
    internal var cancel = false
    internal var focusView: View? = null
    internal lateinit var paymentProgress: FadingCircle
    internal var numberPlate: TextView? = null
    internal var duration: TextView? = null
    internal var street: TextView? = null
    internal var expirayTime: TextView? = null
    internal var status: TextView? = null
    internal var parking_cost: TextView? = null
    internal var heading: TextView? = null
    internal lateinit var parking_status_response: LinearLayout
    internal lateinit var parking_status_field: LinearLayout
    internal lateinit var parkingChargesRecycler: RecyclerView

    private var parkingCharges: List<Charge>? = null
    private var valuesAdded = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        view = inflater.inflate(R.layout.parking_penalties, container, false)
        parking_button = view.findViewById(R.id.parking_button)
        penalty_number_plate = view.findViewById(R.id.penalty_number_plate)
        parking_status_response = view.findViewById(R.id.parking_status_response)
        back_action = view.findViewById(R.id.back_action)
        parking_status_field = view.findViewById(R.id.parking_status_field)
        parkingChargesRecycler = view.findViewById(R.id.penalties)
        pay_penalty = view.findViewById(R.id.pay_penalty)
        //penalty_status = view.findViewById(R.id.penalty_status);

        pay_penalty.setOnClickListener {
            val payment = PaymentFragment()
            val fm = activity!!.supportFragmentManager
            val bundle = Bundle()
            bundle.putString("VehicleType", "Private")
            bundle.putString("Zone", "Cbd")
            bundle.putString("duration", "")
            bundle.putString("Amount", valuesAdded.toString())
            bundle.putString("penalty", valuesAdded.toString())
            bundle.putString("stream", "parkingPenalties")
            bundle.putString("NumberPalte", penalty_number_plate.text.toString())
            payment.arguments = bundle
            payment.show(fm.beginTransaction(), payment.tag)
            payment.isCancelable = false
        }
        back_action.setOnClickListener {
            back_action.visibility = View.GONE
            parking_status_response.visibility = View.GONE
            parking_status_field.visibility = View.VISIBLE
        }
        val checkField = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {

            }
        }
        parking_button.setOnClickListener {
            if (penalty_number_plate.text.toString() != "") {
                back_action.visibility = View.VISIBLE
                CheckpenaltyStatus(penalty_number_plate.text.toString())
            } else {
                penalty_number_plate.error = "Can Not Be Blank"
                focusView = penalty_number_plate
                cancel = true

            }
            if (cancel) {

                focusView!!.requestFocus()
            } else {
                penalty_number_plate.error = null
                cancel = false
                focusView = null
            }
        }
        cancel_action = view.findViewById(R.id.cancel_action)
        dialog.window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        cancel_action.setOnClickListener { dismiss() }
        return view
    }

    fun CheckpenaltyStatus(searchvalue: String) {
        paymentProgress = FadingCircle()
        paymentProgress.setBounds(0, 0, 1000, 100)
        paymentProgress.color = Color.WHITE
        parking_button.text = ""
        parking_button.setCompoundDrawables(null, paymentProgress, null, null)
        paymentProgress.start()
        Api.getVolley(context, Api.POST, Api.GetPenaltyCharge, "{\n" +
                "  \"vehicle_category_code\": \"Private\",\n" +
                "  \"duration_code\": \"Daily\",\n" +
                "  \"zone_code\": \"kilimani\",\n" +
                "  \"registration_number\": \"" + searchvalue + "\",\n" +
                "  \"phone_number\": \"string\",\n" +
                "  \"lapse_duration\": 0\n" +
                "}", object : Api.VolleyCallback {
            override fun onSuccess(result: String) {
                val charges = Api.mGson.fromJson(result, ParkingChargeResponse::class.java)
                val response = Api.mGson.fromJson(result, PenaltyResponse::class.java)
                if (response.statusCode == 200) {
                    parking_button.text = "PROCEED"
                    parking_button.setCompoundDrawables(null, null, null, null)
                    paymentProgress.stop()
                    parkingCharges = response.charges
                    InflateViews(charges.amount)
                    if (charges.amount.size > 0) {
                        parking_status_response.visibility = View.VISIBLE
                        parking_status_field.visibility = View.GONE
                        for (x in charges.amount) {
                            Toast.makeText(context, x.amount.toString(), Toast.LENGTH_SHORT).show()
                            valuesAdded += x.amount!!
                        }
                    } else {
                        parking_button.text = "PROCEED"
                        parking_button.setCompoundDrawables(null, null, null, null)
                        paymentProgress.stop()
                        //penalty_status.setVisibility(View.VISIBLE);
                        Toast.makeText(context, "No penalty found", Toast.LENGTH_LONG).show()


                    }


                } else {
                    Toast.makeText(context, response.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    fun InflateViews(transActions: List<ParkingAmount>) {

        // Create adapter passing in the sample user data
        val adapter = ParkingChargeAdapter(transActions, context)
        // Attach the adapter to the recyclerview to populate items
        parkingChargesRecycler.itemAnimator = DefaultItemAnimator()
        parkingChargesRecycler.adapter = adapter
        // Set layout manager to position the items
        parkingChargesRecycler.layoutManager = LinearLayoutManager(context)
    }
}
