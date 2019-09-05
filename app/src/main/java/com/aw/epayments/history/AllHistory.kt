package com.aw.epayments.history


import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aw.epayments.Adapters.ContactsAdapter
import com.aw.epayments.R
import com.aw.epayments.api.Api
import com.aw.epayments.models.Response.Payment
import com.aw.epayments.models.Response.PaymentsResponse

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
@SuppressLint("StaticFieldLeak")
internal lateinit var  root:View
@SuppressLint("StaticFieldLeak")
internal lateinit var  rvContacts:RecyclerView
@SuppressLint("StaticFieldLeak")
lateinit var adapter:ContactsAdapter
/**
 * A simple [Fragment] subclass.
 *
 */
class AllHistory : Fragment() {
   /* override fun onReciptItemClick(view: View, position: Int, uniqueNumber: String) {
        Toast.makeText(context,"jjdd",Toast.LENGTH_LONG).show()
    }*/

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.history_all, container, false)
        PullTransactions()
        return root
    }

    private fun PullTransactions() {

        Api.getVolley(context, Api.POST, Api.GetCustomerPayments, "{\n" +
                "  \"customer_search_criteria\": 0,\n" +
                "  \"search_value\": \"0727292911\",\n" +
                "  \"records_count\": 5\n" +
                "}", object : Api.VolleyCallback {
            override fun onSuccess(result: String) {
                context?.let { Api.save(it, "TransactionsPayments", result) }
                LoadTransactions(result)
            }
        })

    }

    private fun LoadTransactions(response: String) {

        val transactions = Api.mGson.fromJson(response, PaymentsResponse::class.java)
        if (transactions.statusCode == 200) {

            InflateViews(transactions.payments)

        }
    }

    private fun InflateViews(transActions: List<Payment>) {

      rvContacts = root.findViewById<RecyclerView>(R.id.transactions)

        // Create adapter passing in the sample user data
        adapter = context?.let { ContactsAdapter(transActions,context) }!!

        rvContacts.itemAnimator = DefaultItemAnimator()
        rvContacts.adapter = adapter
        rvContacts.setOnClickListener(View.OnClickListener {

        })
        // Set layout manager to position the items
        rvContacts.layoutManager = LinearLayoutManager(context)

    }


    }
