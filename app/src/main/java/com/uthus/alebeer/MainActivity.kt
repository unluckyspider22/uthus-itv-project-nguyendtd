package com.uthus.alebeer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.uthus.alebeer.databinding.ActivityMainBinding
import com.uthus.alebeer.ui.adapter.AleBeerViewPagerAdapter
import com.uthus.alebeer.ui.beer.BeerFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var aleBeerViewPagerAdapter: AleBeerViewPagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViewPager()
        setupTabLayout()
    }

    private fun setupViewPager() = with(binding) {
        aleBeerViewPagerAdapter = AleBeerViewPagerAdapter(supportFragmentManager,lifecycle)
        vp2.adapter = aleBeerViewPagerAdapter
    }

    private fun setupTabLayout() = with(binding) {
        TabLayoutMediator(tabLayout,vp2) { tab, pos ->
            tab.text = "Tab ${(pos + 1)}"
        }.attach()
    }

}