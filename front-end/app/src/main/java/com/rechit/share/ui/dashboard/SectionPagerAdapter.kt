package com.rechit.share.ui.dashboard

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionPagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = PenangananFragment()
            1 -> fragment = PemulihanFragment()
            2 -> fragment = PerlindunganFragment()
        }
        return fragment as Fragment
    }
}