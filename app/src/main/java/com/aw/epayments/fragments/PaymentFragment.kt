package com.aw.epayments.fragments

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.BottomSheetDialogFragment
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView

import com.aw.epayments.Adapters.ParkingChargeAdapter
import com.aw.epayments.Adapters.PaymentAdpater
import com.aw.epayments.R
import com.aw.epayments.api.Api
import com.aw.epayments.fragments.payments.AirtelMoneyFragment
import com.aw.epayments.fragments.payments.CardPaymentFragment
import com.aw.epayments.fragments.payments.MobileMoneyFragment
import com.aw.epayments.models.NewApiResponse.BillBase
import com.aw.epayments.models.Response.HouseResult
import com.aw.epayments.models.Response.OffStreetRate.OffstreetParkingRate
import com.aw.epayments.models.Response.ParkingAmount
import com.aw.epayments.models.Response.ParkingChargeResponse
import com.google.gson.Gson

import java.util.ArrayList
import java.util.HashMap

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class PaymentFragment : BottomSheetDialogFragment(), PaymentAdpater.ItemClickListener {
    internal var view: View? = null
    private lateinit var paymentRecycler: RecyclerView
    private lateinit var parkingChargesRecycler: RecyclerView
    private lateinit var horizontalLayoutManager: LinearLayoutManager
    private lateinit var adpater: PaymentAdpater
    private lateinit var TotalAmount: TextView
    private lateinit var amount: TextView
    private lateinit var description: TextView
    private lateinit var check_out_stream: TextView
    private lateinit var advance_description: TextView
    private lateinit var advance_amount: TextView
    private var vehicleCode: String? = null
    private var zoneCode: String? = null
    private var numberPlate: String? = null
    private var totalParkingFee: String? = null
    private var duration: String? = null
    private lateinit var rlLayoutHouseRent: RelativeLayout
    private lateinit var advance_payments: RelativeLayout
    private var bundle: Bundle? = null
    private var Upn: String? = null
    private var billNumber: String? = null
    private var billAmount: String? = null
    private var businessId: String? = null
    private var transactionId: String? = null
    private var year: String? = null
    private lateinit var cancle_checkout: ImageView
    private var houseRentAmount: String = ""
    private var succeed = true
    private var h: Handler? = null
    private var landRateTopay: String? = null
    private lateinit var offStreetResponse: OffstreetParkingRate
    var Unh: String = ""
    var estateId: Int = 0
    var houseTypeId: Int = 0
    var houseNumber :String =""

    @SuppressLint("DefaultLocale")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        view = inflater.inflate(R.layout.universal_checkout, container, false)
        parkingChargesRecycler = view!!.findViewById(R.id.parking_rates)
        check_out_stream = view!!.findViewById(R.id.check_out_stream)
        TotalAmount = view!!.findViewById(R.id.total)
        cancle_checkout = view!!.findViewById(R.id.cancle_checkout)
        advance_description = view!!.findViewById(R.id.advance_description)
        advance_amount = view!!.findViewById(R.id.advance_amount)
        advance_payments = view!!.findViewById(R.id.advance_payments)

        bundle = arguments
        val paymetTabs = ArrayList<Int>()
        val safaricon_icon = this.resources.getIdentifier("mpesa", "drawable", activity!!.packageName)
        val safaricon_express_icon = this.resources.getIdentifier("ic_mpesa_express", "drawable", activity!!.packageName)
        val artel = this.resources.getIdentifier("airtel", "drawable", activity!!.packageName)
        val card = this.resources.getIdentifier("card", "drawable", activity!!.packageName)

        paymetTabs.add(safaricon_icon)
        paymetTabs.add(artel)
        paymetTabs.add(card)

        rlLayoutHouseRent = view!!.findViewById(R.id.rlLayoutHouseRent)
        description = view!!.findViewById(R.id.description)
        amount = view!!.findViewById(R.id.amount)
        rlLayoutHouseRent.visibility = View.GONE


        paymentRecycler = view!!.findViewById(R.id.payment_options)
        horizontalLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        paymentRecycler.layoutManager = horizontalLayoutManager
        adpater = PaymentAdpater(context, paymetTabs)
        adpater.setClickListener(this@PaymentFragment)
        paymentRecycler.adapter = adpater

        assert(bundle != null)
        if (bundle!!.getString("stream") === "parking") {
            if (bundle!!.getString("duration") === "Daily") {

            }
            bundle?.let { GetParkingRate(it) }
        } else if (bundle!!.getString("stream") === "houseRent") {

            GetHouseAccountBalance(bundle!!)
        } else if (bundle!!.getString("stream") == "landRate") {

            GetLandRateBalance(bundle!!)
        } else if (bundle!!.getString("stream") == "ubp") {

            GetUbpRate(bundle!!)
        } else if (bundle!!.getString("stream") == "ms") {
            TotalAmount.text = String.format("KSH %s", bundle!!.getString("amount"))
            GetMsRateBalance(bundle!!)
        } else if (bundle!!.getString("stream") == "seasonalParking") {
            TotalAmount.text = String.format("KSH%s", bundle!!.getString("total"))
        } else if (bundle!!.getString("stream") == "offstreet") {
            offStreetResponse = Api.mGson.fromJson(bundle!!.getString("response"), OffstreetParkingRate::class.java)
            TotalAmount.text = String.format("KSH %d", offStreetResponse.responseData.totalAmount)
            zoneCode = bundle!!.getString("zone_code")
        } else if (bundle!!.getString("stream") == "parkingPenalties") {
            totalParkingFee = bundle!!.getString("penalty")
            numberPlate = bundle!!.getString("NumberPalte")
            TotalAmount.text = String.format("KSH %s", bundle!!.getString("penalty"))
            vehicleCode = "private"
            duration = ""
            zoneCode = "cbd"
        }
        cancle_checkout.setOnClickListener { dismiss() }

        return if (view != null) {
            view
        } else view


    }

    private fun GetMsRateBalance(bundle: Bundle) {
        try {
            billNumber = bundle.getString("billNumber")
            billAmount = bundle.getString("amount")
        } catch (e: Exception) {
            Log.e("", e.toString())
        }

    }

    private fun GetUbpRate(bundle: Bundle) {
        try {
            rlLayoutHouseRent.visibility = View.VISIBLE
            description.text = "Single Business Permit"
            amount.text = "KSH " + bundle.getString("amount")!!
            TotalAmount.text = "KSH " + bundle.getString("amount")!!
            businessId = bundle.getString("business_id")
            transactionId = bundle.getString("payment_code")
            year = bundle.getString("year")
        } catch (ex: Exception) {
            Log.e("", ex.toString())
        }

    }

    fun GetHouseAccountBalance(bundle: Bundle) {
        try {
            val value = bundle.getString("amount")
            houseRentAmount = bundle.getString("amount")
            setAdvancepayment(value, "houseRent")
            houseNumber = bundle.getString("house_number")
            Unh = bundle.getString("Unh")
            estateId = bundle.getInt("estateId")
            houseTypeId = bundle.getInt("houseTypeId")
            rlLayoutHouseRent.visibility = View.VISIBLE
            description.text = "Rent Balance"
            if (houseRentAmount <= "0.0") {
                amount.setText("KSH $houseRentAmount").toString()
                TotalAmount.text = "House Rent Paid"
            } else {
                amount.text = "KSH $houseRentAmount"
                TotalAmount.text = "KSH $houseRentAmount"
            }
        } catch (e: Exception) {
            Log.e("", e.toString())
        }
        /*if (rentResponse.statusCode == 200) {


        }*/

        /* Toast.makeText(context, bundle.getString("Unh"), Toast.LENGTH_SHORT).show()
         Api.getVolley(context, Api.POST, Api.GetHouseAccountBalance, "{\n" +
                 "  \"UHN\": \"" + bundle.getString("Unh") + "\",\n" +
                 "  \"tenancyId\": " + bundle.getInt("tenencyId") + "\n" +
                 "}", object : Api.VolleyCallback {
             @SuppressLint("SetTextI18n")
             override fun onSuccess(result: String) {
                 val rentResponse = Api.mGson.fromJson(result, HouseRentResponse::class.java)

             }
         })*/
    }

    private fun GetLandRateBalance(bundle: Bundle) {
        try {
            rlLayoutHouseRent.visibility = View.VISIBLE
            description.text = "Land Rate Balance"
            amount.text = "KSH " + bundle.getString("amount")!!
            TotalAmount.text = "KSH " + bundle.getString("amount")!!
            Upn = bundle.getString("UPN")
            landRateTopay = bundle.getString("amount")
        } catch (ex: Exception) {
            Log.e("", ex.toString())
        }

    }

    /*private fun GetSeasonalparkingRate(bundle: Bundle) {

    }*/

    override fun onItemClick(view: View, position: Int, drawable: ImageView, isActive: Boolean) {
        try {
            when (position) {
                0 -> {
                    succeed = true


                    if (bundle!!.getString("stream") === "parking" && Integer.parseInt(totalParkingFee!!) > 0) {

                        loadParkingFragment(MobileMoneyFragment())

                    }

                    if (bundle!!.getString("stream") === "houseRent" && houseRentAmount > "0.0") {
                        loadRentFragment(MobileMoneyFragment())

                    }
                    if (bundle!!.getString("stream") === "landRate") {
                        loadLandRateFragment(MobileMoneyFragment())
                    }
                    if (bundle!!.getString("stream") === "ubp") {
                        loadUbpFragment(MobileMoneyFragment())
                    }
                    if (bundle!!.getString("stream") === "ms") {
                        loadMsFragment(MobileMoneyFragment())
                    }
                    if (bundle!!.getString("stream") === "seasonalParking") {
                        loadSeasonalParkingFragment(MobileMoneyFragment())
                    }
                    if (bundle!!.getString("stream") === "offstreet") {
                        LoadOffstreetFragment(MobileMoneyFragment())
                    }
                    if (bundle!!.getString("stream") === "parkingPenalties") {
                        loadParkingFragment(MobileMoneyFragment())
                    }

                }
                1 -> {


                    loadParkingFragment(AirtelMoneyFragment())
                }
                2 -> {

                    loadParkingFragment(CardPaymentFragment())
                }
                else -> {

                }
            }
        } catch (ex: Exception) {
            Log.e("", ex.toString())
        }


    }

    private fun loadMsFragment(fragment: Fragment) {
        try {
            val fm = childFragmentManager
            val bundle = Bundle()

            bundle.putString("option", "ms")
            bundle.putString("BillNumber", billNumber)
            bundle.putString("billAmount ", billAmount)
            fragment.arguments = bundle
            fm.beginTransaction()
                    .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                    .replace(R.id.payment_streams, fragment)
                    .commit()
        } catch (e: Exception) {
            Log.e("Ms", e.localizedMessage)
        }

    }

    private fun loadParkingFragment(fragment: Fragment) {
        try {
            val fm = childFragmentManager
            val bundle = Bundle()
            bundle.putString("VehicleType", vehicleCode)
            bundle.putString("Zone", zoneCode)
            bundle.putString("NumberPalte", numberPlate)
            bundle.putString("Amount", totalParkingFee)
            bundle.putString("duration", duration)
            bundle.putString("option", "parking")
            fragment.arguments = bundle
            fm.beginTransaction()
                    .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                    .replace(R.id.payment_streams, fragment)
                    .commit() // save the changes
        } catch (e: Exception) {
            Log.e("parking", e.localizedMessage)
        }

        // create a FragmentManager

    }

    private fun loadSeasonalParkingFragment(fragment: Fragment) {
        // create a FragmentManager
        try {
            val fm = childFragmentManager
            val bundle = Bundle()

            bundle.putString("option", "seasonal")
            fragment.arguments = bundle
            fm.beginTransaction()
                    .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                    .replace(R.id.payment_streams, fragment)
                    .commit() // save the changes
        } catch (e: Exception) {
            Log.e("seasonal", e.localizedMessage)
        }

    }

    private fun loadRentFragment(fragment: Fragment) {
        try {
            val fm = childFragmentManager
            val bundle = Bundle()
            bundle.putString("option", "houseRent")
            bundle.putString("EstateID", estateId.toString())
            bundle.putString("HouseNumber", houseNumber)
            bundle.putString("UHN", Unh)
            bundle.putString("Amount", houseRentAmount)
            bundle.putString("houseTypeId", houseTypeId.toString())
            //
            fragment.arguments = bundle
            fm.beginTransaction()
                    .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                    .replace(R.id.payment_streams, fragment)
                    .commit() // save the changes



        } catch (e: Exception) {
            Log.e("rent", e.localizedMessage)
        }

    }

    private fun loadLandRateFragment(fragment: Fragment) {
        try {
            val fm = childFragmentManager
            val bundle = Bundle()

            bundle.putString("option", "landRate")
            bundle.putString("UPN", Upn)
            bundle.putString("amount", landRateTopay)
            fragment.arguments = bundle
            fm.beginTransaction()
                    .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                    .replace(R.id.payment_streams, fragment)
                    .commit() // save the changes
        } catch (e: Exception) {
            Log.e("landrates", e.localizedMessage)
        }

    }

    private fun loadUbpFragment(fragment: Fragment) {
        try {
            val fm = childFragmentManager
            val bundle = Bundle()
            bundle.putString("option", "ubp")
            bundle.putString("paymentCode ", transactionId)
            bundle.putString("busines_id", businessId)
            bundle.putString("year", year)

            bundle.putString("amount", TotalAmount.text.toString())
            fragment.arguments = bundle
            fm.beginTransaction()
                    .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                    .replace(R.id.payment_streams, fragment)
                    .commit() // save the changes
        } catch (ex: Exception) {
            Log.e("", ex.toString())
        }

    }

    private fun LoadOffstreetFragment(fragment: Fragment) {
        try {
            val TrCode = offStreetResponse.responseData.transactionCode
            val fm = childFragmentManager
            val bundle = Bundle()
            bundle.putString("option", "offstreet")
            bundle.putString("paymentCode ", TrCode)
            bundle.putString("zoneCode", zoneCode)

            fragment.arguments = bundle
            fm.beginTransaction()
                    .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                    .replace(R.id.payment_streams, fragment)
                    .commit()
        } catch (e: Exception) {
            Log.e("Load Off Street", e.localizedMessage)
        }

    }

    fun GetParkingRate(bundle: Bundle) {
        try {
            Api.getVolley(context, Api.POST, Api.GetParkingCharge, "{\n" +
                    "  \"vehicle_category_code\": \"" + bundle.getString("VehicleType") + "\",\n" +
                    "  \"duration_code\": \"" + bundle.getString("duration") + "\",\n" +
                    "  \"zone_code\": \"" + bundle.getString("Zone") + "\",\n" +
                    "  \"registration_number\": \"" + bundle.getString("NumberPalte") + "\"\n" +
                    "}", object : Api.VolleyCallback {
                override fun onSuccess(result: String) {
                    vehicleCode = bundle.getString("VehicleType")
                    duration = bundle.getString("duration")
                    zoneCode = bundle.getString("Zone")
                    numberPlate = bundle.getString("NumberPalte")

                    LoadParkingrate(result)
                }
            })
        } catch (e: Exception) {
            Log.e("parking rate api", e.localizedMessage)
        }

    }

    @TargetApi(Build.VERSION_CODES.N)
    fun LoadParkingrate(response: String) {
        try {
            var valuesAdded = 0
            val charges = Api.mGson.fromJson(response, ParkingChargeResponse::class.java)
            if (charges.statusCode == 200) {
                parkingCharges = charges.amount
                InflateViews(charges.amount)
                // totalParkingFee = String.valueOf(parkingCharges.stream().mapToInt(ParkingAmount::getAmount).sum());
                for (x in charges.amount) {
                    valuesAdded += x.amount!!
                }
                totalParkingFee = valuesAdded.toString()
                if (Integer.parseInt(totalParkingFee!!) > 0) {
                    TotalAmount.text = String.format("KSH %s", totalParkingFee)
                } else {
                    TotalAmount.text = "Parking is Paid"
                }
            } else {
                TotalAmount.text = "Parking Rate Not Available"
            }
        } catch (e: Exception) {
            Log.e("ParkingRate Cahrges", e.localizedMessage)
        }

    }

    fun InflateViews(transActions: List<ParkingAmount>) {
        try {
            val adapter = ParkingChargeAdapter(transActions, context)
            // Attach the adapter to the recyclerview to populate items
            parkingChargesRecycler.itemAnimator = DefaultItemAnimator()
            parkingChargesRecycler.adapter = adapter
            // Set layout manager to position the items
            parkingChargesRecycler.layoutManager = LinearLayoutManager(context)
        } catch (ex: Exception) {
            Log.e("", ex.toString())
        }
        // Create adapter passing in the sample user data

    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (view != null) {
            val parent = view!!.parent as ViewGroup
            parent.removeAllViews()
        }
    }

    @SuppressLint("SetTextI18n")
    fun setAdvancepayment(value: String, Stream: String) {
        try {
            val formattedvalue = value.split("-".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            if ((value.contains("-") || value === "0.0") && Stream === "houseRent") {
                advance_payments.visibility = View.VISIBLE
                rlLayoutHouseRent.visibility = View.VISIBLE
                description.text = "Advance Payment"
                amount.text = "KSH " + formattedvalue[1]
                TotalAmount.text = "House Rent Paid"
                advance_description.text = "Rent Balance"
                advance_amount.text = "KSH 0.0"
            } else {
                advance_payments.visibility = View.GONE
                rlLayoutHouseRent.visibility = View.VISIBLE
                description.text = "Rent Balance"
                amount.text = "KSH $value"
                TotalAmount.text = "KSH $value"
            }
        } catch (ex: Exception) {
            Log.e("", ex.toString())
        }

    }

    companion object {
        internal lateinit var parkingCharges: List<ParkingAmount>
    }
}
