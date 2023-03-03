package com.uthus.alebeer.presentation.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.uthus.alebeer.presentation.beer.BeerFragment
import com.uthus.alebeer.presentation.beer.FavoriteFragment

const val ARG_OBJECT = "object"

class AleBeerViewPagerAdapter(
    fragment: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragment, lifecycle) {

    companion object {
        private const val BEER_FRAGMENT_POS = 0
        private const val FAVORITE_FRAGMENT_POS = 1
    }

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return if(position == BEER_FRAGMENT_POS) {
            BeerFragment()
        } else {
            FavoriteFragment()
        }
    }


}