package com.rechit.share.ui.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rechit.share.databinding.FragmentPenangananBinding

class PenangananFragment : Fragment() {

    private var _binding: FragmentPenangananBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPenangananBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        val view = binding.root
        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}