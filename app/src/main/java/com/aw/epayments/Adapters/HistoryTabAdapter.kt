package com.aw.epayments.Adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.aw.epayments.history.AllHistory
import com.aw.epayments.history.ParkingHistory

class HistoryTabAdapter( fm: FragmentManager): FragmentPagerAdapter(fm)  {
    override fun getItem(position: Int): Fragment? {
        return when (position) {
            0 -> {
                //  val homeFragment: HomeFragment = HomeFragment()
                AllHistory()
            }

            else -> ParkingHistory()
        }
    }

    // this counts total number of tabs
    override fun getCount(): Int {
        return 2
    }


}