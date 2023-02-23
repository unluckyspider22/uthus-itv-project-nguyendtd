package com.uthus.alebeer.ui.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.uthus.alebeer.ui.beer.BeerFragment

const val ARG_OBJECT = "object"

class AleBeerViewPagerAdapter(
    fragment: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragment,lifecycle) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        val fragment = BeerFragment()
        fragment.arguments = Bundle().apply {
            // Our object is just an integer :-P
            putInt(ARG_OBJECT, position + 1)
        }
        return fragment
    }


}