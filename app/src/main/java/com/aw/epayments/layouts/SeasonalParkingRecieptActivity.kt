package com.aw.epayments.layouts

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.aw.epayments.R
import com.aw.epayments.api.Const

import kotlinx.android.synthetic.main.activity_seasonal_parking_reciept.*

class SeasonalParkingRecieptActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seasonal_parking_reciept)
        var response = Const.getInstance().seasonalParkingRecipt
        stream_title.text = "Parking Payment"
        receipt_date.text = response.responseData.datePaid
        receipt_payment_mode.text = response.responseData.paymentMode
        receipt_unique_code.text = response.responseData.receiptNumber
        receipt_total.text = response.responseData.amount.toString()


    }
    /*internal MyAdapter : RecyclerView.Adapter<MyAdapter.ViewHolder>() {}*/
}
