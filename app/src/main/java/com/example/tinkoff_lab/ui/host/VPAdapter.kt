package com.example.tinkoff_lab.ui.host

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.tinkoff_lab.ui.hot.HotContentFragment
import com.example.tinkoff_lab.ui.latest.LatestContentFragment
import com.example.tinkoff_lab.ui.top.TopContentFragment

/** Adapter for displaying fragments inside DataHostFragment */
class VPAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val screensCreators = mutableListOf(
        { HotContentFragment() },
        { LatestContentFragment() },
        { TopContentFragment() }
    )

    override fun getItemCount(): Int = screensCreators.size

    override fun createFragment(position: Int) = screensCreators[position]()
}