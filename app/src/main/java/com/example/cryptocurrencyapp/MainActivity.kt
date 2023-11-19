package com.example.cryptocurrencyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.cryptocurrencyapp.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewPager = binding.viewPager
        viewPager.adapter = ViewPagerAdapter(this@MainActivity)

        TabLayoutMediator(binding.tabLayout,viewPager ) { tab, position ->
            /*tab.text = when (position) {
                0 -> getString(R.string.st_eShop)
                1 -> getString(R.string.st_exchange)
                2 -> ""
                3 -> getString(R.string.st_launchpads)
                4 -> getString(R.string.st_wallet)
                else -> throw IllegalArgumentException("Invalid position")
            }*/

            val customView = layoutInflater.inflate(R.layout.custom_layout_file, null)
            val tabText: TextView = customView.findViewById(R.id.tabText)
            val tabIcon: ImageView = customView.findViewById(R.id.tabIcon)
            val tabImage: ImageView = customView.findViewById(R.id.tabImage)

            // Set text and icon for each tab
            tabText.text = when (position) {
                0 -> getString(R.string.st_eShop)
                1 -> getString(R.string.st_exchange)
                2 -> ""
                3 -> getString(R.string.st_launchpads)
                4 -> getString(R.string.st_wallet)
                else -> throw IllegalArgumentException("Invalid position")
            }

            // Set icon based on position or use custom drawables
            when (position) {
                0 -> tabIcon.setImageResource(R.drawable.ic_eshop_icon)
                1 -> tabIcon.setImageResource(R.drawable.ic_exchange_icon)
                2 -> {
                    tabImage.visibility = View.VISIBLE
                    tabText.visibility = View.GONE
                    tabImage.scaleType = ImageView.ScaleType.FIT_XY
                    tabImage.setImageResource(R.drawable.ic_globe_tab_icon)
                }
                3 -> tabIcon.setImageResource(R.drawable.ic_launchpad_icon)
                4 -> tabIcon.setImageResource(R.drawable.ic_wallet_icon)
            }

            // Set custom view for the tab
            tab.customView = customView
        }.attach()
    }
}