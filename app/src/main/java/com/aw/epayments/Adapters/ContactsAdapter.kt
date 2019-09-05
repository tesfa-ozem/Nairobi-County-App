package com.aw.epayments.Adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup

import com.aw.epayments.models.Response.Payment

class ContactsAdapter (internal var _payment: List<Payment>,
                       val context: Context?)  : RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ContactsAdapter.ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(p0: ContactsAdapter.ViewHolder, p1: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

//    lateinit var tsAmount: TextView
//    lateinit var tsStream: TextView
//    lateinit var tsDescription: TextView
//    internal var unqCode = ""
//    private var _mClickListener: ItemClickListener? = null

    /*override fun onBindViewHolder(holder: ViewHolder, position: Int)= holder.bind(PaymentsResponse,listener)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.transaction, parent, false))

    }

    override fun getItemCount(): Int {
        return PaymentsResponse.size
    }

    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Payment, listener: (Payment) -> Unit) = with(itemView) {
           var tsStream = itemView.findViewById<TextView>(R.id.order_id)
            var tsAmount = itemView.findViewById<TextView>(R.id.transaction_amount)
             //tsStream = (TextView) itemView.findViewById(R.id.trsDate);
            var tsDescription = itemView.findViewById<TextView>(R.id.transaction_description)
            setOnClickListener { listener(item) }
        }


    }*/
//    fun setClickListener(itemClickListener: ItemClickListener) {
//        this._mClickListener = itemClickListener
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsAdapter.ViewHolder {
//        val context = parent.context
//        val inflater = LayoutInflater.from(context)
//        val contactView = inflater.inflate(R.layout.transaction, parent, false)
//
//        return ViewHolder(contactView)
//    }
//
//    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
//        val payments = _payment[i]
//
//        tsStream.text = "Parking"
//
//        tsAmount.text = String.format("KSH %s", payments.amount.toString())
//        tsDescription.text = String.format("Ref: %s", payments.accountRef)
//        unqCode = payments.accountRef
//
//        /**/
//
//
//    }
//
//    override fun getItemCount(): Int {
//        return _payment.size
//    }
//
//    fun getItem(id: Int): Payment {
//        return _payment[id]
//    }
//
//    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
//
//
//
//
//        init {
//            tsStream = itemView.findViewById(R.id.order_id)
//            tsAmount = itemView.findViewById(R.id.transaction_amount)
//            //tsStream = (TextView) itemView.findViewById(R.id.trsDate);
//            tsDescription = itemView.findViewById(R.id.transaction_description)
//            itemView.setOnClickListener(this)
//
//        }
//
//        override fun onClick(v: View) {
//            if (_mClickListener != null) {
//                _mClickListener!!.onReciptItemClick(v, adapterPosition, getItem(adapterPosition).accountRef)
//
//            }
//        }
//    }
//
//    interface ItemClickListener {
//        fun onReciptItemClick(view: View, position: Int, uniqueNumber: String)
//    }








}
