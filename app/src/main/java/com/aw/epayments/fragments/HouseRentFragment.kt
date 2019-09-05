package com.aw.epayments.fragments

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.design.widget.BottomSheetDialogFragment
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast

import com.aw.epayments.R
import com.aw.epayments.api.Api
import com.aw.epayments.models.NewApiResponse.Estates_Base
import com.aw.epayments.models.NewApiResponse.HouseDetails_data
import com.aw.epayments.models.NewApiResponse.HouseNumberBase
import com.aw.epayments.models.NewApiResponse.HouseNumber_data
import com.aw.epayments.models.Response.HouseResult
import com.aw.epayments.models.Response.HouseTypeResponse
import com.aw.epayments.utility.Formaters
import com.github.ybq.android.spinkit.style.FadingCircle
import com.google.gson.Gson
import com.toptoche.searchablespinnerlibrary.SearchableSpinner
import kotlinx.android.synthetic.main.fragment_house_rent.*
import kotlinx.android.synthetic.main.fragment_house_rent.view.*
import kotlinx.android.synthetic.main.fragment_house_rent.view.back_action
import kotlinx.android.synthetic.main.fragment_land_rate.view.*
import kotlinx.android.synthetic.main.house_rent_details.*
import kotlinx.android.synthetic.main.house_rent_details.view.*
import kotlinx.android.synthetic.main.house_rent_details.view.current_balance

import java.util.ArrayList
import java.util.HashMap


class HouseRentFragment : BottomSheetDialogFragment() {

    internal lateinit var view: View

    internal lateinit var cancel_action: ImageView
    internal lateinit var back_action: ImageView
    private var spinner: SearchableSpinner? = null
    private var spinnerType: SearchableSpinner? = null
    private var spinerHseNumber: SearchableSpinner? = null
    private var adapters: ArrayAdapter<String>? = null
    private var adaptersType: ArrayAdapter<String>? = null
    private var adapter_hse_number: ArrayAdapter<String>? = null
    private val arrayList = ArrayList<String>()
    private val arrayListType = ArrayList<String>()
    private val arrayListhseNumber = ArrayList<String>()
    internal var EstateCategories = HashMap<String, Int>()
    internal var HouseTypes = HashMap<String, Int>()
    internal var Upn = HashMap<String, String>()
    internal var Estate_id: Int = 0
    internal var houseNumber: String = ""
    internal var Un: String? = ""
    internal var phoneNumber: EditText? = null
    internal var amount: String? = null
    internal lateinit var Submit: Button
    internal lateinit var pay_house_rent: Button

    internal var hseTypeId: Int = 0
    internal var rentAmount: String = ""

    internal var rent_balance = HashMap<String, Double>()
    internal var tenancyId = HashMap<String,String>()
    private var tenantId: Int? = 0
    internal var rentIfo = HashMap<String, HouseNumber_data>()
    internal var result: HouseNumber_data? = null
    private lateinit var progess: FadingCircle
    private lateinit var loading_content: ProgressBar


    @SuppressLint("CommitTransaction")
    @RequiresApi(Build.VERSION_CODES.CUPCAKE)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)
        view = inflater.inflate(R.layout.fragment_house_rent, container, false)
        Submit = view.check_rent_status

        pay_house_rent = view.pay_house_rent
        back_action = view.back_action
        loading_content = view.loading_content

        progess = FadingCircle()
        Submit.setTextColor(Color.WHITE)
        cancel_action = view.findViewById(R.id.cancel_action)
        /*spinner Estate*/
        spinner = view.findViewById(R.id.spinnerEstate)
        spinner!!.setTitle("Select Estate")
        adapters = ArrayAdapter(context!!.applicationContext, R.layout.simple_spinner_dropdown_item, arrayList)
        spinner!!.adapter = adapters
        adapters!!.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        spinner!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                try {
                    val x = parent.getItemAtPosition(position).toString()
                    Estate_id = EstateCategories[x]!!
                    GetHouseTypes(Estate_id)

                    if (Api.getValue(context!!, "GetHouseTypes")!!.matches("".toRegex())) {

                    } else {
                        LoadHouseTypes(Api.getValue(context!!, "GetHouseTypes"))
                    }
                } catch (ex: Exception) {
                    Log.e("", ex.toString())
                }

            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }


        /*Spinner house type*/
        spinnerType = view.findViewById(R.id.spinner_hse_type)
        adaptersType = ArrayAdapter(context!!.applicationContext, R.layout.simple_spinner_dropdown_item, arrayListType)

        adaptersType!!.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        spinnerType!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                try {
                    val x = parent.getItemAtPosition(position).toString()
                    hseTypeId = HouseTypes[x]!!

                    GetHouse(hseTypeId, Estate_id)
                } catch (ex: Exception) {
                    Log.e("", ex.toString())
                }


            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }

        spinerHseNumber = view.findViewById(R.id.spinnerNumber)
        adapter_hse_number = ArrayAdapter(context!!.applicationContext, R.layout.simple_spinner_dropdown_item, arrayListhseNumber)
        adapter_hse_number!!.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        spinerHseNumber!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                try {
                    val x = parent.getItemAtPosition(position).toString()
                    houseNumber = x
                    Un = Upn[x]

                    result = rentIfo[x]
                    Submit.setBackgroundResource(R.drawable.background_shape_preset_button__pressed)
                    Submit.setTextColor(Color.WHITE)
                } catch (ex: Exception) {

                }

            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                Submit.setBackgroundResource(R.drawable.button_shape)
                Submit.setTextColor(Color.BLACK)
            }
        }


        GetEstates()
        /*if (Api.getValue(context!!, "GetEstates")!!.matches("".toRegex())) {

            Log.i("GetEstates epty",Api.getValue(context!!, "GetEstates"))
        } else {
            Log.i("GetEstates eyy",Api.getValue(context!!, "GetEstates"))
            LoadEstates(Api.getValue(context!!, "GetEstates"))
        }*/


        Submit.setOnClickListener {
            if ( Un != null && result != null) {
                checkStatus(Gson().toJson(result))
                rlHouseRent.visibility = View.VISIBLE
                houseRentform.visibility = View.GONE
                back_action.visibility = View.VISIBLE


            } else {

                Toast.makeText(context, "Check Inputs Are Not Blank", Toast.LENGTH_LONG).show()
            }
        }
        cancel_action.setOnClickListener { dismiss() }
        back_action.setOnClickListener {
            back_action.visibility = View.GONE
            rlHouseRent.visibility = View.GONE
            houseRentform.visibility = View.VISIBLE
        }

        pay_house_rent.setOnClickListener {
            rlHouseRent.visibility = View.GONE
            houseRentform.visibility = View.VISIBLE
            val payment = PaymentFragment()
            val fm = activity!!.supportFragmentManager
            val bundle = Bundle()
            bundle.putString("amount", amount!!)
            bundle.putString("Unh", Un)
            bundle.putString("house_number",houseNumber)
            bundle.putString("stream", "houseRent")

            bundle.putInt("estateId", Estate_id)
            bundle.putInt("houseTypeId", hseTypeId)
            payment.arguments = bundle
            payment.show(fm.beginTransaction(), payment.tag)
            payment.isCancelable = false
        }
        dialog.window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        return view

    }

    fun GetEstates() {
        Api.getVolley(context, Api.GET, Api.Get_Estates,  object : Api.VolleyCallback {
            override fun onSuccess(result: String) {
                //context?.let { Api.save(it, "GetEstates", result) }
                LoadEstates(result)

            }

        }, apiUrl = Api.HouseRentUrl,form_data =null)


    }

    private fun LoadEstates(result: String?) {
        val gson = Gson()
        val estates = gson.fromJson(result, Estates_Base::class.java)
        if (estates.status_code == 200) {
            for (x in estates.response_data) {
                arrayList.add(x.estateDescription)
                EstateCategories[x.estateDescription] = x.estateID

            }
            spinner!!.adapter = adapters
        } else {

        }
    }

    fun GetHouseTypes(estateId: Int) {
        val params = HashMap<String, String>()
        params["EstateID"] = estateId.toString()
        progess.start()
        Api.getVolley(context, Api.POST, Api.Get_House_type,  object : Api.VolleyCallback {
            override fun onSuccess(result: String) {

                LoadHouseTypes(result)

            }
        }, apiUrl = Api.HouseRentUrl,form_data = params)

    }

    private fun LoadHouseTypes(result: String?) {
        try {
            context?.let { Api.save(it, "GetHouseTypes", result!!) }
            val houseTypeResponse = Api.mGson.fromJson(result, HouseTypeResponse::class.java)
            if (houseTypeResponse.statusCode == 200) {
                arrayListType.clear()
                HouseTypes.clear()
                for (x in houseTypeResponse.resultData) {

                    arrayListType.add(x.housetype)
                    HouseTypes[x.housetype] = x.houseTypeID

                }
                spinnerType!!.adapter = adaptersType
            }
        } catch (e: Exception) {
            Log.e("", e.localizedMessage)
        }

    }

    fun GetHouse(hseTypeId: Int, estateId: Int) {
        try {
            val params = HashMap<String, String>()
            params["EstateID"] = estateId.toString()
            params["HouseTypeId"] = hseTypeId.toString()
            arrayListhseNumber.clear()
            loading_content.visibility = View.VISIBLE
            Api.getVolley(context, Api.POST, Api.Get_House_Number,  object : Api.VolleyCallback {
                override fun onSuccess(result: String) {
                    loading_content.visibility = View.GONE
                    LoadHouses(result)

                }
            },
                    apiUrl = Api.HouseRentUrl,form_data = params)
        } catch (e: Exception) {
            Log.e("", e.localizedMessage)
        }


    }

    private fun LoadHouses(result: String) {
        var gson = Gson()
        val houseResponse = gson.fromJson(result, HouseNumberBase::class.java)
        if (houseResponse.status_code == 200) {

            Upn.clear()
            rent_balance.clear()
            for (x in houseResponse.response_data) {
                tenancyId[x.houseNumber] = x.houseNumber
                arrayListhseNumber.add(x.houseNumber)
                Upn[x.houseNumber] = x.uHN
                rent_balance[x.houseNumber] = x.currentBalance
                rentIfo[x.houseNumber] = x
            }
            spinerHseNumber!!.adapter = adapter_hse_number
        }
    }

    fun checkStatus(houseDetails: String) {
        try {
            //val CurrentBalance = Formaters.AddThousandSeparetor(result!!.currentBalance)
            var gson = Gson()
            val result = gson.fromJson(houseDetails, HouseDetails_data::class.java)
            if (result != null) {
                view.CustomerSupplierName.text = result.customerSupplierName
                view.physicalAdress.text = result.physicalAddress
                view.unh.text = result.uHN
                view.house_number.text = result.houseNumber
                view.monthly_rent.text = result.standardRent.toString()
                view.rent_arrears.text = if (result.rentArrears > 0.0) result.rentArrears.toString() else "No Arrears"
                view.current_balance.text =  result.currentBalance.toString()
                //view.EamountToPay.text = result.currentBalance.toString()
                Estate_id =result.estateID
                Un = result.uHN
                hseTypeId = result.houseTypeID
                houseNumber = result.houseNumber
                amount = result.currentBalance.toString()

            }
        } catch (ex: Exception) {
            Log.e("", ex.toString())
        }


    }

    override fun onAttachFragment(fragment: Fragment?) {
        if (fragment!!.isAdded)
            return
        super.onAttachFragment(fragment)
    }




}
