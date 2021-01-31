package com.example.tinkoff_lab.ui.host

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.tinkoff_lab.R
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_data_host.*

/**
 * Host fragment for ViewPager with content
 */
class DataHostFragment : Fragment(R.layout.fragment_data_host) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(fragment_host_container) {
            adapter = VPAdapter(this@DataHostFragment)
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            registerOnPageChangeCallback(SimpleTabsSwitcher(fragment_host_tab_layout))
        }
    }

    // TODO: Поддержать клик на табы с послеующим переключением фрагментов в VP
    private class SimpleTabsSwitcher(private val tabLayout: TabLayout) :
        ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            val tabByPosition = tabLayout.getTabAt(position)
            tabLayout.selectTab(tabByPosition)
        }
    }
}