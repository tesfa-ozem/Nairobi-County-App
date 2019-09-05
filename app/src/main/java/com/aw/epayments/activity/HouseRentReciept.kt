package com.aw.epayments.activity


import android.annotation.SuppressLint

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.aw.epayments.R
import com.aw.epayments.models.NewApiResponse.ReciptBase
import com.google.gson.Gson
import kotlinx.android.synthetic.main.reciept_view.*


/**
 * A simple [Fragment] subclass.
 */
@SuppressLint("Registered")
class HouseRentReciept : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.reciept_view)
        val intent = intent
        val name = intent.getStringExtra("name")

        val gson = Gson()

        val data = gson.fromJson(name, ReciptBase::class.java)
        receipt_date.text = data.response_data.receiptInfo.receiptDate
        receipt_number_plate.text= data.response_data.billInfo.feeDescription
        receipt_street.text = data.response_data.billInfo.customer
        var recieptTitle = findViewById<TextView>(R.id.title)
        recieptTitle.text = data.response_data.billInfo.description
        receipt_unique_code.text = data.response_data.billInfo.billNo
        receipt_total.text = data.response_data.billInfo.detailAmount.toString()




    }


}
