package com.aw.epayments.fragments

import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast

import com.aw.epayments.R
import com.aw.epayments.api.Api
import com.aw.epayments.models.Response.miscellaneous.MiscellaneousBill
import com.github.ybq.android.spinkit.style.FadingCircle

import java.util.HashMap

class MiscellaneousFragment : BottomSheetDialogFragment() {
    internal lateinit var view: View
    internal lateinit var proceedToPayment: Button
    internal var vehiclesCtegories = HashMap<String, String>()
    internal var DurationCtegories = HashMap<String, String>()
    internal lateinit var proceed: Button
    internal lateinit var bill_number: EditText
    internal lateinit var ms_bill_number: TextView
    internal lateinit var discription: TextView
    internal lateinit var client_name: TextView
    internal lateinit var date_raised: TextView
    internal lateinit var amount_topay: TextView
    internal lateinit var msLayout: ScrollView
    internal lateinit var ms_input: LinearLayout
    internal lateinit var cancel_action: ImageView
    internal lateinit var back_action: ImageView
    internal lateinit var amountTopay: String
    internal lateinit var progress: FadingCircle
    private var tNumberplate: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        view = inflater.inflate(R.layout.fragment_miscellaneous, container, false)
        proceed = view.findViewById(R.id.payment_button)
        bill_number = view.findViewById(R.id.bill_number)
        ms_bill_number = view.findViewById(R.id.ms_bill_number)
        discription = view.findViewById(R.id.discription)
        client_name = view.findViewById(R.id.client_name)
        date_raised = view.findViewById(R.id.date_raised)
        amount_topay = view.findViewById(R.id.amount_topay)
        msLayout = view.findViewById(R.id.ms_layout)
        ms_input = view.findViewById(R.id.ms_input)
        proceedToPayment = view.findViewById(R.id.proceed_ms)
        back_action = view.findViewById(R.id.back_action)

        back_action.setOnClickListener {
            ms_input.visibility = View.VISIBLE
            msLayout.visibility = View.GONE
            back_action.visibility = View.VISIBLE
        }
        cancel_action = view.findViewById(R.id.cancel_action)
        cancel_action.setOnClickListener { dismiss() }
        progress = FadingCircle()
        val mTextEditorWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                //This sets a textview to the current length
                //Toast.makeText(getContext(), String.valueOf(s.length()), Toast.LENGTH_SHORT).show();
                tNumberplate = s.length
                if (s.length > 3) {
                    proceed.setBackgroundResource(R.drawable.background_shape_preset_button__pressed)
                }
            }

            override fun afterTextChanged(s: Editable) {}
        }
        proceed.setOnClickListener {
            back_action.visibility = View.VISIBLE
            CheckBill()
        }
        proceedToPayment.setOnClickListener {
            val payment = PaymentFragment()
            val fm = childFragmentManager
            val bundle = Bundle()
            bundle.putString("stream", "ms")
            bundle.putString("billNumber", bill_number.text.toString())
            bundle.putString("amount", amountTopay)
            payment.arguments = bundle
            payment.show(fm.beginTransaction(), payment.tag)
            payment.isCancelable = false
        }
        return view
    }


    fun CheckBill() {
        try {
            progress.setBounds(0, 0, 100, 100)
            proceed.setCompoundDrawables(null, progress, null, null)
            proceed.text = ""
            progress.start()
            Api.getVolley(context, Api.POST, Api.GetBillDetails, "{\n" +
                    "  \"BillNumber\": \"" + bill_number.text.toString() + "\"\n" +
                    "}", object : Api.VolleyCallback {
                override fun onSuccess(result: String) {
                    val response = Api.mGson.fromJson(result, MiscellaneousBill::class.java)
                    if (response.statusCode == 200) {
                        val amount = "KES " + response.responseData.billingInfo.billAmount
                        ms_input.visibility = View.GONE
                        ms_bill_number.text = response.responseData.billingDetail.billNumber
                        discription.text = ""
                        client_name.text = response.responseData.billingInfo.clientsName
                        date_raised.text = response.responseData.billingInfo.datePaid
                        amount_topay.text = amount

                        msLayout.visibility = View.VISIBLE
                        amountTopay = response.responseData.billingInfo.billAmount
                        proceed.setCompoundDrawables(null, null, null, null)
                        progress.stop()
                        proceed.text = "CHECK BILL"
                        proceed.setTextColor(Color.WHITE)
                    } else {
                        Toast.makeText(activity, response.message, Toast.LENGTH_LONG).show()
                        proceed.setCompoundDrawables(null, null, null, null)
                        progress.stop()
                        proceed.text = "CHECK BILL"
                        proceed.setTextColor(Color.WHITE)
                    }
                }
            })
        } catch (e: Exception) {
            Log.e("CheckBill", e.localizedMessage)
        }

    }


}
