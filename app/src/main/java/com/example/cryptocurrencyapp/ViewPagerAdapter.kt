package com.example.cryptocurrencyapp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity){
    override fun getItemCount(): Int {
        return  5
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ExchangeTabFragment()
            1 -> ExchangeTabFragment()
            2 -> ExchangeTabFragment()
            3 -> ExchangeTabFragment()
            4 -> ExchangeTabFragment()
            else -> throw IllegalArgumentException("Invalid position")
        }
    }

}