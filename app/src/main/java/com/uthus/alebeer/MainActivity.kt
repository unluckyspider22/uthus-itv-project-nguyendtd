package com.uthus.alebeer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import com.uthus.alebeer.databinding.ActivityMainBinding
import com.uthus.alebeer.presentation.adapter.AleBeerViewPagerAdapter

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
            tab.setText(getTabName()[pos])
        }.attach()
    }

    private fun getTabName() : List<Int> {
        return listOf(R.string.beer_tab_name,R.string.favorite_tab_name)
    }

}