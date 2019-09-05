package com.aw.epayments.fragments

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.support.constraint.ConstraintLayout
import android.support.design.chip.Chip
import android.support.design.widget.BottomSheetDialogFragment
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast

import com.aw.epayments.Adapters.BulkParkingAdapter
import com.aw.epayments.R
import com.aw.epayments.api.Api
import com.aw.epayments.models.Response.AddCarListResponse
import com.aw.epayments.models.Response.EntriesResponse
import com.aw.epayments.models.Response.GetParkingEntriesResponse
import com.aw.epayments.models.Response.HttpParkingCategories
import com.aw.epayments.models.Response.LandResponse
import com.aw.epayments.models.Response.OffStreetRate.OffstreetParkingRate
import com.aw.epayments.models.Response.ParkingChargeResponse
import com.aw.epayments.models.Response.offstreetParking.OffStreetParkingResponse
import com.github.ybq.android.spinkit.style.FadingCircle
import com.toptoche.searchablespinnerlibrary.SearchableSpinner
import java.lang.Exception

import java.util.ArrayList
import java.util.Collections
import java.util.HashMap
import kotlin.math.roundToInt

class DailyParkingFragment : BottomSheetDialogFragment() {
    internal lateinit var view: View
    internal lateinit var proceed: Button
    internal lateinit var AddCar: Button
    internal lateinit var seasonal_payment: Button
    internal lateinit var proceed_offstreet: Button
    internal var vehiclesCtegories = HashMap<String, String>()
    internal var ZoneCtegories = HashMap<String, String>()
    internal var DurationCtegories = HashMap<String, String>()
    internal var OffStreetHush = HashMap<String, String>()
    private val spinner: Spinner? = null
    private val arrayList = ArrayList<String>()
    private var adapters: ArrayAdapter<String>? = null
    private val spinnerZone: Spinner? = null
    private val arrayListoffStreet = ArrayList<String>()
    private val arrayListZone = ArrayList<String>()
    private var adaptersZone: ArrayAdapter<String>? = null
    private var adapterOffstreet_zone: ArrayAdapter<String>? = null
    internal lateinit var searchableSpinner: SearchableSpinner
    internal lateinit var searchableSpinnerZone: SearchableSpinner
    internal lateinit var searchableSpinnerDuration: SearchableSpinner
    internal lateinit var sn_vehicle_category: SearchableSpinner
    internal lateinit var sn_duration_categories: SearchableSpinner
    internal lateinit var offStreet_zones: SearchableSpinner
    internal lateinit var vehicle_category_offstreet: SearchableSpinner
    private val arrayListDuration = ArrayList<String>()
    private var adaptersDuration: ArrayAdapter<String>? = null
    internal lateinit var numberPlate: EditText
    internal lateinit var sn_number_plate: EditText
    private var tNumberplate: Int = 0
    internal var cancel = false
    internal var focusView: View? = null
    internal var vehicleCode: String? = null
    internal var ZoneCode: String? = null
    internal var ParkingRate: Int = 0
    internal lateinit var parkingRate: LinearLayout
    internal var DurationCode: String? = null
    internal lateinit var cancel_action: ImageView
    internal lateinit var parking_display: TextView
    internal lateinit var parking_rate_offStreet: TextView
    internal lateinit var list: MutableList<EntriesResponse>
    internal lateinit var adapter: BulkParkingAdapter
    internal lateinit var rvContacts: RecyclerView
    internal lateinit var daily: Chip
    internal lateinit var seasonal: Chip
    internal lateinit var topUp: Chip
    internal lateinit var offStreet: Chip
    internal lateinit var multiple_seasonal: ConstraintLayout
    internal lateinit var offStreet_parking: ConstraintLayout
    internal lateinit var single_parking: LinearLayout
    internal lateinit var top_up: LinearLayout
    internal lateinit var parkingRate_offstreet: LinearLayout
    internal var TotalSeaonal = ""
    internal lateinit var checkStatus: FadingCircle
    private var canProceed: Boolean = false
    lateinit var offstreetResponse: String
    internal var isRemoved = false
    var value = ""
    internal lateinit var number_plate_offstreet: EditText
    private var shortAnimationDuration: Int = 0
    internal lateinit var loginFragment: LoginFragment

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        try {
            view = inflater.inflate(R.layout.fragment_daily_parking, container, false)

            numberPlate = view.findViewById(R.id.number_plate)
            proceed = view.findViewById(R.id.proceed_daily_parking)
            parkingRate = view.findViewById(R.id.parkingRate)
            searchableSpinnerDuration = view.findViewById(R.id.duration_categories)
            parking_display = view.findViewById(R.id.parking_display)
            cancel_action = view.findViewById(R.id.cancel_action)
            AddCar = view.findViewById(R.id.add_car)
            rvContacts = view.findViewById(R.id.list_of_cars)
            multiple_seasonal = view.findViewById(R.id.multiple_seasonal)
            single_parking = view.findViewById(R.id.single_parking)
            top_up = view.findViewById(R.id.top_up)
            daily = view.findViewById(R.id.daily_parking_view)
            seasonal = view.findViewById(R.id.seasonal_parking)
            topUp = view.findViewById(R.id.topUp)
            seasonal_payment = view.findViewById(R.id.seasonal_payment)
            sn_vehicle_category = view.findViewById(R.id.sn_vehicle_category)
            sn_duration_categories = view.findViewById(R.id.sn_duration_categories)
            sn_number_plate = view.findViewById(R.id.sn_number_plate)
            offStreet_parking = view.findViewById(R.id.offStreet_parking)
            offStreet = view.findViewById(R.id.offStreet)
            offStreet_zones = view.findViewById(R.id.offStreet_zones)
            proceed_offstreet = view.findViewById(R.id.proceed_offstreet)
            parkingRate_offstreet = view.findViewById(R.id.parkingRate_offstreet)
            parking_rate_offStreet = view.findViewById(R.id.parking_rate_offStreet)
            number_plate_offstreet = view.findViewById(R.id.number_plate_offstreet)
            checkStatus = FadingCircle()

            daily.setOnClickListener {
                shortAnimationDuration = resources.getInteger(android.R.integer.config_shortAnimTime)
                single_parking.visibility = View.VISIBLE
                multiple_seasonal.visibility = View.GONE
                top_up.visibility = View.GONE
                offStreet_parking.visibility = View.GONE
            }
            seasonal.setOnClickListener {
                if (Api.getValue(activity!!, "LoginStatus") == "login") {

                    single_parking.visibility = View.GONE
                    multiple_seasonal.visibility = View.VISIBLE
                    top_up.visibility = View.GONE
                    offStreet_parking.visibility = View.GONE

                } else {

                    val loginFragment = LoginFragment()
                    val fm = this.activity?.fragmentManager
                    LoginFragment.isLogin = true
                    loginFragment.show(fm, loginFragment.tag)
                    Toast.makeText(context, "Please Login to access", Toast.LENGTH_SHORT).show()
                }
            }
            topUp.setOnClickListener {
                single_parking.visibility = View.VISIBLE
                multiple_seasonal.visibility = View.GONE
                top_up.visibility = View.GONE
                offStreet_parking.visibility = View.GONE
            }
            offStreet.setOnClickListener {
                GetOffStreetZones()
                single_parking.visibility = View.GONE
                multiple_seasonal.visibility = View.GONE
                top_up.visibility = View.GONE
                offStreet_parking.visibility = View.VISIBLE
            }

            // Make us non-modal, so that others can receive touch events.
            adaptersDuration = ArrayAdapter(activity!!.applicationContext, R.layout.simple_spinner_dropdown_item, arrayListDuration)
            adaptersDuration!!.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)

            searchableSpinnerDuration.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    val x = parent.getItemAtPosition(position).toString()
                    DurationCode = DurationCtegories[x]
                    if (DurationCode!!.contains("Hourly") || DurationCode!!.contains("Daily")) {
                        searchableSpinnerZone.visibility = View.VISIBLE


                    } else {
                        searchableSpinnerZone.visibility = View.GONE
                        ZoneCode = ""

                    }

                    GetParkingRate()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {

                }
            }
            searchableSpinnerDuration.setTitle("Select Duration ")
            searchableSpinnerDuration.adapter = adaptersDuration


            searchableSpinner = view.findViewById(R.id.search_vehicle_category)
            searchableSpinner.setTitle("Select Vehicle Type ")
            searchableSpinner.setPositiveButton("OK")

            adapters = ArrayAdapter(activity!!.applicationContext, R.layout.simple_spinner_dropdown_item, arrayList)
            adapters!!.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
            searchableSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    val x = parent.getItemAtPosition(position).toString()
                    vehicleCode = vehiclesCtegories[x]
                    /*if (!DurationCode.equals("Daily")&&vehicleCode!=null) {
                }
                else if(DurationCode==null){
                    Toast.makeText(getContext(), "Please select the Vehicle Type", Toast.LENGTH_LONG).show();
                }*/
                    GetParkingRate()
                    parkingRate.visibility = View.VISIBLE
                }

                override fun onNothingSelected(parent: AdapterView<*>) {

                }
            }

            searchableSpinner.adapter = adapters

            vehicle_category_offstreet = view.findViewById(R.id.vehicle_category_offstreet)
            vehicle_category_offstreet.setTitle("Select ")
            vehicle_category_offstreet.setPositiveButton("OK")

            adapters = ArrayAdapter(activity!!.applicationContext, R.layout.simple_spinner_dropdown_item, arrayList)
            adapters!!.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
            vehicle_category_offstreet.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    val x = parent.getItemAtPosition(position).toString()
                    vehicleCode = vehiclesCtegories[x]
                    /*if (!DurationCode.equals("Daily")&&vehicleCode!=null) {
                }
                else if(DurationCode==null){
                    Toast.makeText(getContext(), "Please select the Vehicle Type", Toast.LENGTH_LONG).show();
                }*/
                    GetParkingRate()

                }

                override fun onNothingSelected(parent: AdapterView<*>) {

                }
            }
            vehicle_category_offstreet.adapter = adapters

            /*seasonal Parking Adapters*/
            sn_vehicle_category.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    val x = parent.getItemAtPosition(position).toString()
                    vehicleCode = vehiclesCtegories[x]
                    /*if (!DurationCode.equals("Daily")&&vehicleCode!=null) {
                }
                else if(DurationCode==null){
                    Toast.makeText(getContext(), "Please select the Vehicle Type", Toast.LENGTH_LONG).show();
                }*/
                    GetParkingRate()
                    parkingRate.visibility = View.VISIBLE
                }

                override fun onNothingSelected(parent: AdapterView<*>) {

                }
            }
            sn_vehicle_category.adapter = adapters

            sn_duration_categories.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    val x = parent.getItemAtPosition(position).toString()
                    DurationCode = DurationCtegories[x]
                    if (position < 1) {
                        searchableSpinnerZone.visibility = View.VISIBLE
                    } else {
                        searchableSpinnerZone.visibility = View.GONE

                    }
                    Toast.makeText(context, DurationCode, Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {

                }
            }
            sn_duration_categories.adapter = adaptersDuration

            searchableSpinnerZone = view.findViewById(R.id.zone_categories)
            adaptersZone = ArrayAdapter(activity!!.applicationContext, R.layout.simple_spinner_dropdown_item, arrayListZone)
            adaptersZone!!.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
            searchableSpinnerZone.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    val x = parent.getItemAtPosition(position).toString()
                    ZoneCode = ZoneCtegories[x]
                    parkingRate.visibility = View.VISIBLE
                    GetParkingRate()
                    if (DurationCode == "Daily" && vehicleCode != null) {


                    } else {
                        Toast.makeText(context, "Please select the Vehicle Type", Toast.LENGTH_LONG).show()
                    }

                }

                override fun onNothingSelected(parent: AdapterView<*>) {

                }
            }
            searchableSpinnerZone.adapter = adaptersZone
            searchableSpinnerZone.setTitle("Select Zone")
            val mTextEditorWatcher = object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    //This sets a textview to the current length
                    //Toast.makeText(getContext(), String.valueOf(s.length()), Toast.LENGTH_SHORT).show();
                    tNumberplate = s.length
                    if (s.length > 3) {
                        proceed.setBackgroundResource(R.drawable.background_shape_preset_button__pressed)
                        proceed.setTextColor(Color.WHITE)
                    } else if (s.length < 3) {
                        proceed.setBackgroundResource(R.drawable.button_shape)
                        proceed.setTextColor(Color.WHITE)

                    }
                }

                override fun afterTextChanged(s: Editable) {}
            }
            numberPlate.addTextChangedListener(mTextEditorWatcher)

            /*off Street parking*/
            adapterOffstreet_zone = ArrayAdapter(activity!!.applicationContext, R.layout.simple_spinner_dropdown_item, arrayListoffStreet)
            adapterOffstreet_zone!!.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
            offStreet_zones.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    val x = parent.getItemAtPosition(position).toString()
                    ZoneCode = OffStreetHush[x]
                }

                override fun onNothingSelected(parent: AdapterView<*>) {

                }
            }
            offStreet_zones.adapter = adapterOffstreet_zone
            /*seasonal parking*/
            list = ArrayList()
            Collections.reverse(list)

            adapter = BulkParkingAdapter(list, activity!!)
            if (Api.getValue(activity!!, "parkingCategory")!!.matches("".toRegex())) {
                SelectParkingOption()
            } else {
                LoadParkingCategories(Api.getValue(activity!!, "parkingCategory"))
            }
            if (Api.getValue(activity!!, "vehicleRegistration") != "") {
                numberPlate.setText(Api.getValue(activity!!, "vehicleRegistration"))
            }

            AddCar.setOnClickListener {
                list.clear()
                AddSeasonalParking()
            }
            cancel_action.setOnClickListener { dismiss() }

            proceed.setOnClickListener {
                if (numberPlate.text.toString() != "" && vehicleCode != null && DurationCode != null) {

                    if (value != "No Rate Available") {
                        val xone = if (ZoneCode == null) "" else ZoneCode
                        val payment = PaymentFragment()
                        val fm = activity!!.supportFragmentManager
                        val bundle = Bundle()
                        bundle.putString("VehicleType", vehicleCode)
                        bundle.putString("Zone", xone)
                        bundle.putString("duration", DurationCode)
                        bundle.putString("stream", "parking")
                        bundle.putString("NumberPalte", numberPlate.text.toString())
                        payment.arguments = bundle
                        payment.show(fm.beginTransaction(), payment.tag)
                        payment.isCancelable = false
                        if (!Api.getValue(activity!!, "vehicleRegistration")!!.matches("".toRegex())) {
                            Api.save(activity!!, "vehicleRegistration", numberPlate.text.toString())
                        }
                    }


                } else {
                    numberPlate.error = "Please Fill All Fields"
                    focusView = numberPlate
                    cancel = true
                }

                if (cancel) {
                    // There was an error; don't attempt login and focus the first
                    // form field with an error.
                    focusView!!.requestFocus()
                } else {
                    focusView = null
                    cancel = false
                }
            }
            seasonal_payment.setOnClickListener {
                if (sn_number_plate.text != null && vehicleCode !== "") {
                    val payment = PaymentFragment()
                    val fm = activity!!.supportFragmentManager
                    val bundle = Bundle()
                    bundle.putString("total", TotalSeaonal)
                    bundle.putString("stream", "seasonalParking")

                    payment.arguments = bundle
                    payment.show(fm.beginTransaction(), payment.tag)
                    payment.isCancelable = false
                } else {
                    Toast.makeText(activity, "Set All Fields", Toast.LENGTH_SHORT).show()
                }
            }

            proceed_offstreet.setOnClickListener {
                if (canProceed) {
                    val payment = PaymentFragment()
                    val fm = activity!!.supportFragmentManager
                    val bundle = Bundle()
                    bundle.putString("response", offstreetResponse)
                    bundle.putString("stream", "offstreet")
                    bundle.putString("zone_code", vehicleCode)

                    payment.arguments = bundle
                    payment.show(fm.beginTransaction(), payment.tag)
                    payment.isCancelable = false
                } else {
                    GetOfStreetRate()
                }
            }
            GetParkinggEntries()
            val trashBinIcon = resources.getDrawable(
                    R.drawable.ic_delete,
                    null
            )
            /*Swip Action*/
            val simpleCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
                override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
                                    target: RecyclerView.ViewHolder): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    TotalSeaonal = "0"
                    val position = viewHolder.adapterPosition
                    RemoveSeasonalParking(list[position].parkingCode)
                    if (isRemoved) {
                        list.removeAt(position)

                        adapter.notifyItemChanged(position)
                    } else {
                        adapter.notifyDataSetChanged()
                    }

                }

                override fun onChildDraw(
                        c: Canvas,
                        recyclerView: RecyclerView,
                        viewHolder: RecyclerView.ViewHolder,
                        dX: Float,
                        dY: Float,
                        actionState: Int,
                        isCurrentlyActive: Boolean
                ) {


                    super.onChildDraw(c, recyclerView, viewHolder,
                            dX, dY, actionState, isCurrentlyActive)
                    c.clipRect(0f, viewHolder.itemView.top.toFloat(),
                            dX, viewHolder.itemView.bottom.toFloat())
                    val textMargin = resources.getDimension(R.dimen.text_margin)
                            .roundToInt()

                    trashBinIcon.bounds = Rect(
                            textMargin,
                            viewHolder.itemView.top + textMargin,
                            textMargin + trashBinIcon.intrinsicWidth,
                            viewHolder.itemView.top + trashBinIcon.intrinsicHeight
                                    + textMargin
                    )

                    trashBinIcon.draw(c)
                }
            }

            val itemTouchHelper = ItemTouchHelper(simpleCallback)
            itemTouchHelper.attachToRecyclerView(rvContacts)
        } catch (e: Exception) {

        }



        return view
    }

    fun GetParkinggEntries() {
        try {
            Api.getVolley(activity, Api.POST, Api.GetParkingEntries, "", object : Api.VolleyCallback {
                override fun onSuccess(result: String) {
                    val response = Api.mGson.fromJson(result, GetParkingEntriesResponse::class.java)
                    if (response.statusCode == 200) {

                        AddCar.text = "ADD"

                        AddCar.setCompoundDrawables(null, null, null, null)
                        checkStatus.stop()
                        list.addAll(response.responseData)
                        Toast.makeText(context, TotalSeaonal, Toast.LENGTH_SHORT).show()
                        TotalSeaonal = TotalAmount()
                        InflateViews()


                    }
                }
            })
        }catch (ex:Exception){
            Log.e("",ex.localizedMessage)
        }

    }

    fun AddSeasonalParking() {
        checkStatus.setBounds(0, 0, 100, 100)
        AddCar.text = ""

        AddCar.setCompoundDrawables(null, checkStatus, null, null)
        checkStatus.start()
        Api.getVolley(activity, Api.POST, Api.AddSeasonalParkingEntry, "{\n" +
                "  \"payment_reason\": 0,\n" +
                "  \"registration_no\": \"" + sn_number_plate.text.toString() + "\",\n" +
                "  \"specify_amount\": true,\n" +
                "  \"amount\": 0,\n" +
                "  \"vehicle_category_code\": \"" + vehicleCode + "\",\n" +
                "  \"zone_code\": \"CBD\",\n" +
                "  \"duration_code\": \"" + DurationCode + "\",\n" +
                "  \"phone_number\": \"0727292911\"\n" +
                "}", object : Api.VolleyCallback {
            override fun onSuccess(result: String) {
                val response = Api.mGson.fromJson(result, AddCarListResponse::class.java)
                if (response.statusCode == 200) {

                    Handler().postDelayed({ GetParkinggEntries() }, 2000)
                } else {
                    Toast.makeText(activity, response.message, Toast.LENGTH_SHORT).show()
                    AddCar.text = "ADD"

                    AddCar.setCompoundDrawables(null, null, null, null)
                    checkStatus.stop()
                }
            }
        })

    }

    fun RemoveSeasonalParking(parkingCode: String) {

        Api.getVolley(activity, Api.POST, Api.RemoveParkingEntry, "{\n" +
                "  \"parking_code\": \"" + parkingCode + "\"\n" +
                "}", object : Api.VolleyCallback {
            override fun onSuccess(result: String) {
                val response = Api.mGson.fromJson(result, LandResponse::class.java)
                if (response.statusCode == 200) {

                    GetParkinggEntries()
                    isRemoved = true
                    Toast.makeText(context, "Successfully Removed", Toast.LENGTH_SHORT).show()
                } else {
                    GetParkinggEntries()
                    Toast.makeText(context, "Not Removed", Toast.LENGTH_SHORT).show()
                    isRemoved = false
                }

            }
        })

    }

    fun SelectParkingOption() {

        Api.getVolley(activity, Api.GET, Api.GetParkingCategories, "", object : Api.VolleyCallback {
            override fun onSuccess(result: String) {

                LoadParkingCategories(result)

            }
        })


    }

    fun LoadParkingCategories(result: String?) {
        try {
            val parkingCategories = Api.mGson.fromJson(result, HttpParkingCategories::class.java)
            if (parkingCategories.statusCode == 200) {
                Api.save(activity!!, "parkingCategory", result!!)

                for (vehicle in parkingCategories.vehicleCategories) {
                    arrayList.add(vehicle.description)
                    vehiclesCtegories[vehicle.description] = vehicle.categoryCode
                }


                for (zone in parkingCategories.zones) {
                    adaptersZone!!.add(zone.description)
                    ZoneCtegories[zone.description] = zone.zoneCode
                }
                for (duration in parkingCategories.durations) {
                    arrayListDuration.add(duration.description)
                    DurationCtegories[duration.description] = duration.durationCode
                }

            }
        }catch (ex:Exception){
            Log.e("",ex.localizedMessage)
        }

    }

    private fun GetParkingRate() {
        if (vehicleCode != null) {
            val xone = if (ZoneCode == null) "" else ZoneCode
            Api.getVolley(context, Api.POST, Api.GetParkingCharge, "{\n" +
                    "  \"vehicle_category_code\": \"" + vehicleCode + "\",\n" +
                    "  \"duration_code\": \"" + DurationCode + "\",\n" +
                    "  \"zone_code\": \"" + xone + "\",\n" +
                    "  \"registration_number\": \"" + numberPlate.text.toString() + "\",\n" +
                    "  \"phone_number\": \"string\"\n" +
                    "}", object : Api.VolleyCallback {
                override fun onSuccess(result: String) {
                    val valuesAdded = 0
                    val charges = Api.mGson.fromJson(result, ParkingChargeResponse::class.java)
                    if (charges.statusCode == 200) {
                        ParkingRate = charges.amount[0].amount!!
                        value = "KSH $ParkingRate.0"
                        parking_display.text = value
                        //Toast.makeText(getContext(), value, Toast.LENGTH_SHORT).show();
                    } else {
                        value = "No Rate Available"
                        parking_display.text = value
                    }
                }
            })
        } else {
            Toast.makeText(context, "Please Select categories", Toast.LENGTH_SHORT).show()
        }

    }

    /*public void LoadParkingrate(String response) {

    }*/


    override fun onAttachFragment(fragment: Fragment?) {
        if (fragment!!.isAdded)
            return
        super.onAttachFragment(fragment)
    }

    private fun InflateViews() {


        adapter.notifyDataSetChanged()
        /*adapter.setClickListener(naypayDasboard.this);*/
        // Attach the adapter to the recyclerview to populate items
        rvContacts.itemAnimator = DefaultItemAnimator()
        rvContacts.adapter = adapter
        // Set layout manager to position the items
        rvContacts.layoutManager = LinearLayoutManager(context)
    }

    fun TotalAmount(): String {
        var value = 0
        for (x in list) {
            value = x.parkingCost!! + value

        }
        Toast.makeText(activity, value.toString(), Toast.LENGTH_SHORT).show()
        return value.toString()
    }

    fun GetOffStreetZones() {
        Api.getVolley(activity, Api.POST, Api.GetOffstreetZones, "", object : Api.VolleyCallback {
            override fun onSuccess(result: String) {
                val response = Api.mGson.fromJson(result, OffStreetParkingResponse::class.java)
                if (response.statusCode == 200) {
                    for (x in response.responseData) {
                        adapterOffstreet_zone!!.add(x.description)
                        OffStreetHush[x.description] = x.zoneCode

                    }
                }
            }
        })
    }

    fun GetOfStreetRate() {
        checkStatus.setBounds(0, 0, 100, 100)
        proceed_offstreet.text = ""

        proceed_offstreet.setCompoundDrawables(null, checkStatus, null, null)
        checkStatus.start()
        Api.getVolley(activity, Api.POST, Api.OffstreetCheckInCheckout, "{\n" +
                "  \"registration_no\": \"" + number_plate_offstreet.text.toString() + "\",\n" +
                "  \"vehicle_category\": \" private\",\n" +
                "  \"zone_code\": \" cbd\"\n" +
                "}", object : Api.VolleyCallback {
            @SuppressLint("DefaultLocale")
            override fun onSuccess(result: String) {
                offstreetResponse = result
                val response = Api.mGson.fromJson(result, OffstreetParkingRate::class.java)

                if (response.statusCode == 200) {
                    canProceed = true
                    proceed_offstreet.text = "PAY"
                    proceed_offstreet.setCompoundDrawables(null, null, null, null)
                    checkStatus.stop()
                    proceed_offstreet.setBackgroundResource(R.drawable.background_shape_preset_button__pressed)
                    proceed_offstreet.setTextColor(Color.WHITE)
                    parkingRate_offstreet.visibility = View.VISIBLE
                    parking_rate_offStreet.text = String.format("KSH %d", response.responseData.totalAmount)
                } else {
                    proceed_offstreet.text = "PROCEED"
                    proceed_offstreet.setTextColor(Color.WHITE)
                    proceed_offstreet.setCompoundDrawables(null, null, null, null)
                    checkStatus.stop()
                    Toast.makeText(context, response.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }


    companion object {
        private val MIN_CLICK_INTERVAL: Long = 1000
    }
}
