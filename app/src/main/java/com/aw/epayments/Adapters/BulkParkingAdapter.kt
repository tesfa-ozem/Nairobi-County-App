package com.aw.epayments.Adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView

import com.aw.epayments.R
import com.aw.epayments.models.Response.EntriesResponse

class BulkParkingAdapter(// The items to display in your RecyclerView
        private val items: List<EntriesResponse>, private val context: Context) : RecyclerView.Adapter<BulkParkingAdapter.ViewHolder>() {
    private val _mInflater: LayoutInflater
    // Allows to remember the last item shown on screen
    private var lastPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BulkParkingAdapter.ViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.custom_item_layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.text.text = items[position].vehicleRegNo
        holder.parking_charge.text = """KSH ${items[position].parkingCost}"""

        // Here you apply the animation when the view is bound
        setAnimation(holder.itemView, position)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    /**
     * Here is the key method to apply the animation
     */
    private fun setAnimation(viewToAnimate: View, position: Int) {
        // If the bound view wasn't previously displayed on screen, it's animated
        /*if (position > lastPosition) {
            val animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left)
            viewToAnimate.startAnimation(animation)
            lastPosition = position
        }*/

    }

    init {
        this._mInflater = LayoutInflater.from(context)

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var text: TextView
        internal var parking_charge: TextView
        // You need to retrieve the container (ie the root ViewGroup from your custom_item_layout)
        // It's the view that will be animated
        internal var container: FrameLayout

        init {
            container = itemView.findViewById<View>(R.id.item_layout_container) as FrameLayout
            text = itemView.findViewById<View>(R.id.item_layout_text) as TextView
            parking_charge = itemView.findViewById<View>(R.id.parking_charge) as TextView
        }
    }
}
