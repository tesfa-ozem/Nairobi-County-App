package com.aw.epayments.fragments

import android.annotation.SuppressLint
import android.graphics.Color
import android.support.design.widget.BottomSheetDialogFragment
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Spinner
import android.widget.Toast

import com.aw.epayments.R
import com.aw.epayments.api.Api
import com.aw.epayments.models.Response.LandResponse
import com.github.ybq.android.spinkit.style.FadingCircle
import kotlinx.android.synthetic.main.fragment_land_rate.view.*

import java.util.ArrayList


class LandRateFragment : BottomSheetDialogFragment() {
    internal lateinit var view: View
    internal var spinerPlot: Spinner? = null
    internal var arrayAdapterPlot: ArrayAdapter<String>? = null
    internal var plotArraylist: ArrayList<String>? = null
    internal var back: ImageView? = null

    internal var progress: ProgressBar? = null
    internal lateinit var amount: String
    internal lateinit var upn: String

    internal var tNumberplate: Int = 0
    internal lateinit var checkStatus: FadingCircle

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)

        view = inflater.inflate(R.layout.fragment_land_rate, container, false)


        /*progress = (ProgressBar) view.findViewById(R.id.landProgress);*/

        /*progess animation*/
        checkStatus = FadingCircle()


        view.statusLand.setTextColor(Color.WHITE)
        val mTextEditorWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                //This sets a textview to the current length
                //Toast.makeText(getContext(), String.valueOf(s.length()), Toast.LENGTH_SHORT).show();
                tNumberplate = s.length
                if (s.length > 3) {

                    view.statusLand.setBackgroundResource(R.drawable.background_shape_preset_button__pressed)
                    view.statusLand.setTextColor(Color.WHITE)
                    /* parkingRate.setVisibility(View.VISIBLE);
                    String x = "Parking Rate = KSH "+ParkingRate;
                    parkingRate.setText(x);*/
                } else if (s.length < 3) {
                    view.statusLand.setBackgroundResource(R.drawable.button_shape)
                    view.statusLand.setTextColor(Color.BLACK)
                    //parkingRate.setVisibility(View.GONE);
                }
            }

            override fun afterTextChanged(s: Editable) {}
        }
        view.plotNumber.addTextChangedListener(mTextEditorWatcher)
        view.statusLand.setOnClickListener {
            view.back_action.visibility = View.VISIBLE
            CalculateLandRate()
        }


        view.proceedLand.setOnClickListener { proceed() }
        view.back_action.setOnClickListener {
            view.check_land.visibility = View.VISIBLE
            view.landRateCard.visibility = View.GONE
            view.back_action.visibility = View.GONE
        }
        view.cancel_action.setOnClickListener { dismiss() }
        return view
    }

    fun proceed() {

        // create a FragmentManager
        val payment = PaymentFragment()
        val fm = childFragmentManager
        val bundle = Bundle()
        bundle.putString("UPN", view.plotNumber.text.toString())
        bundle.putString("amount", amount)
        bundle.putString("stream", "landRate")
        payment.arguments = bundle
        payment.show(fm.beginTransaction(), payment.tag)
        payment.isCancelable = false// save the cha

    }

    fun CalculateLandRate() {
        try {
            checkStatus.setBounds(0, 0, 100, 100)
            view.statusLand.text = ""

            view.statusLand.setCompoundDrawables(null, checkStatus, null, null)
            checkStatus.start()
            Api.getVolley(context, Api.POST, Api.GetLandRate, "{\n" +
                    "  \"plot_number\": \"" + view.plotNumber.text.toString() + "\"\n" +
                    "}", object : Api.VolleyCallback {
                @SuppressLint("SetTextI18n")
                override fun onSuccess(result: String) {
                    val landResponse = Api.mGson.fromJson(result, LandResponse::class.java)
                    if (landResponse.statusCode == 200) {
                        view.check_land.visibility = View.GONE
                        view.landRateCard.visibility = View.VISIBLE
                        view.proceedLand.visibility = View.VISIBLE
                        view.plot_number.text = landResponse.responseData.plotNumber
                        view.plotOwner.text = landResponse.responseData.customerSupplierName
                        view.address.text = landResponse.responseData.zoneWardName
                        view.EamountToPay.text = String.format("KSH %s", landResponse.responseData.currentBalance)
                        amount = landResponse.responseData.currentBalance
                        upn = landResponse.responseData.upn
                        view.upn_land.text = landResponse.responseData.upn
                        view.annual_amount.text = landResponse.responseData.totalAnnualAmount
                        view.total_arrears.text = landResponse.responseData.landRatesArrears
                        view.current_balance.text = landResponse.responseData.currentBalance
                        view.due_date.text = landResponse.responseData.lastBillDueDate
                        view.statusLand.text = "CHECK STATUS"
                        view.statusLand.setTextColor(Color.WHITE)

                        view.statusLand.setCompoundDrawables(null, null, null, null)
                        checkStatus.stop()
                    } else {
                        view.statusLand.text = "CHECK STATUS"
                        view.statusLand.setTextColor(Color.WHITE)

                        view.statusLand.setCompoundDrawables(null, null, null, null)
                        checkStatus.stop()
                        Toast.makeText(activity, landResponse.message + "Please try again.", Toast.LENGTH_LONG).show()
                    }

                }
            })

        } catch (e: Exception) {
            Log.e("", e.localizedMessage)
        }

    }


}
