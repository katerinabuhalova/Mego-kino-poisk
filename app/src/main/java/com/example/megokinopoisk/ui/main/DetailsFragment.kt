package com.example.megokinopoisk.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.megokinopoisk.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        binding.tittleView.apply {
            text = arguments?.getString("title")
        }
        return binding.root
    }

    companion object {

        const val BUNDLE_EXTRA = ""

        fun newInstance(bundle: Bundle): DetailsFragment {
            val fragment = DetailsFragment().apply {
                arguments = bundle
            }
            return fragment
        }
    }
}