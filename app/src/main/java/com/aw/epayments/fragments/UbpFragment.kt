package com.aw.epayments.fragments

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast

import com.aw.epayments.R
import com.aw.epayments.api.Api
import com.aw.epayments.models.Response.GetBusinessBillResponse
import com.github.ybq.android.spinkit.style.FadingCircle
import com.toptoche.searchablespinnerlibrary.SearchableSpinner

import java.util.ArrayList

class UbpFragment : BottomSheetDialogFragment() {
    internal lateinit var view: View
    internal lateinit var searchableSpinner: SearchableSpinner
    internal lateinit var businesNumber: EditText
    internal lateinit var check_business_status: Button
    internal lateinit var payUbp: Button
    internal var cancel = false
    internal var focusView: View? = null
    internal var cherCout: Int = 0
    internal lateinit var BusinessYear: String
    internal lateinit var paymentCode: String
    internal lateinit var paymentProgress: FadingCircle
    internal lateinit var check_ubp: LinearLayout
    internal lateinit var ubp_response: RelativeLayout
    internal lateinit var businessName: TextView
    internal lateinit var businessId: TextView
    internal lateinit var amountToPay: TextView
    internal lateinit var description: TextView
    internal var error_message: TextView? = null
    internal lateinit var cancel_action: ImageView
    internal lateinit var back_action: ImageView
    internal lateinit var failedDialog: AlertDialog
    private var adapters: ArrayAdapter<String>? = null
    private val arrayList = ArrayList<String>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        view = inflater.inflate(R.layout.fragment_ubp, container, false)
        cancel_action = view.findViewById(R.id.cancel_action)

        businesNumber = view.findViewById(R.id.businesNumber)
        check_business_status = view.findViewById(R.id.check_business_status)
        check_ubp = view.findViewById(R.id.check_land)
        ubp_response = view.findViewById(R.id.ubp_response)
        businessName = view.findViewById(R.id.business_name)
        businessId = view.findViewById(R.id.bill_number)
        amountToPay = view.findViewById(R.id.amount_to_pay)
        description = view.findViewById(R.id.description)
        payUbp = view.findViewById(R.id.proceed_pay_ubp)
        back_action = view.findViewById(R.id.back_action)
        arrayList.add("2017")
        arrayList.add("2018")
        arrayList.add("2019")
        searchableSpinner = view.findViewById(R.id.business_year)
        searchableSpinner.setTitle("Year")
        adapters = ArrayAdapter(context!!.applicationContext, R.layout.simple_spinner_dropdown_item, arrayList)
        adapters!!.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        searchableSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                BusinessYear = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }
        searchableSpinner.adapter = adapters
        failedDialog = AlertDialog.Builder(context).create()

        val mTextEditorWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

                cherCout = s.length
                if (s.length > 1) {
                    check_business_status.setBackgroundResource(R.drawable.background_shape_preset_button__pressed)
                    check_business_status.setTextColor(Color.WHITE)
                } else {
                    check_business_status.setBackgroundResource(R.drawable.button_shape)
                    check_business_status.setTextColor(Color.BLACK)
                }

            }

            override fun afterTextChanged(s: Editable) {}
        }
        businesNumber.addTextChangedListener(mTextEditorWatcher)

        check_business_status.setOnClickListener {
            if (TextUtils.isDigitsOnly(businesNumber.text) && businesNumber.text.toString() != "") {
                GetBusiness()
                back_action.visibility = View.VISIBLE
            } else {

                focusView = businesNumber
                businesNumber.error = "Invalid Business number"
                cancel = true
            }
            if (cancel) {
                // There was an error; don't attempt login and focus the first
                // form field with an error.
                focusView!!.requestFocus()
            } else {

            }
        }
        payUbp.setOnClickListener {
            /* PaymentFragment payment = new PaymentFragment();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                Bundle bundle = new Bundle();
                bundle.putString("amount", amountToPay.getText().toString());
                bundle.putString("stream", "ubp");
                bundle.putString("year", BusinessYear);
                bundle.putString("business_id", businessId.getText().toString());
                bundle.putString("payment_code", paymentCode);
                payment.setArguments(bundle);
                payment.show(fm.beginTransaction(), payment.getTag());
                payment.setCancelable(false);
                dismiss();*/
        }
        cancel_action.setOnClickListener { dismiss() }
        back_action.setOnClickListener {
            back_action.visibility = View.GONE
            check_ubp.visibility = View.VISIBLE
            ubp_response.visibility = View.GONE
        }
        return view
    }

    fun GetBusiness() {
        paymentProgress = FadingCircle()
        paymentProgress.setBounds(0, 0, 100, 100)
        paymentProgress.color = Color.WHITE
        check_business_status.text = ""
        check_business_status.setCompoundDrawables(null, paymentProgress, null, null)

        paymentProgress.start()
        Api.getVolley(context, Api.POST, Api.GetBusinessBill, "{\n" +
                "  \"business_id\": " + Integer.parseInt(businesNumber.text.toString()) + ",\n" +
                "  \"year\": " + Integer.parseInt(BusinessYear) + "\n" +
                "}", object : Api.VolleyCallback {
            override fun onSuccess(result: String) {
                val billResponse = Api.mGson.fromJson(result, GetBusinessBillResponse::class.java)
                if (billResponse.statusCode == 200) {
                    check_ubp.visibility = View.GONE
                    ubp_response.visibility = View.VISIBLE
                    paymentProgress.stop()
                    paymentProgress.setBounds(0, 0, 800, 60)
                    paymentProgress.color = Color.WHITE
                    check_business_status.text = "CHECK STATUS"
                    check_business_status.setCompoundDrawables(null, null, null, null)
                    businessName.text = billResponse.responseData.businessName
                    businessId.text = billResponse.responseData.businessId
                    amountToPay.text = billResponse.responseData.amountToPay.toString()
                    description.text = billResponse.responseData.charges[0].description
                    paymentCode = billResponse.responseData.transactionCode

                } else {
                    Toast.makeText(activity, "Business Not found", Toast.LENGTH_SHORT).show()
                    paymentProgress.stop()

                    paymentProgress.color = Color.WHITE
                    check_business_status.text = "CHECK STATUS"
                    check_business_status.setCompoundDrawables(null, null, null, null)

                }
            }
        })
    }
}
