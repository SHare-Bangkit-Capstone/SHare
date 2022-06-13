package com.rechit.share.ui.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayoutMediator
import com.rechit.share.R
import com.rechit.share.databinding.FragmentDashboardBinding
import com.rechit.share.data.Psikolog

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private val list = ArrayList<Psikolog>()

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2,
            R.string.tab_text_3
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)

        val sectionsPagerAdapter = SectionPagerAdapter(this)

        binding.viewPager.adapter = sectionsPagerAdapter
        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        binding.rvPsikolog.setHasFixedSize(true)

        list.addAll(listPsikolog)
        showRecyclerlist()

        // Inflate the layout for this fragment
        val view = binding.root
        return view
    }


    private val listPsikolog: ArrayList<Psikolog>
        get() {
            val dataName = resources.getStringArray(R.array.data_name)
            val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
            val listPsikolog = ArrayList<Psikolog>()
            for (i in dataName.indices) {
                val psikolog = Psikolog(dataName[i], dataPhoto.getResourceId(i, -1))
                listPsikolog.add(psikolog)
            }
            return listPsikolog
        }

    private fun showRecyclerlist() {
        binding.rvPsikolog.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val listPsikologAdapter = ListPsikologAdapter(list)
        binding.rvPsikolog.adapter = listPsikologAdapter
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}