package com.example.megokinopoisk.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.megokinopoisk.R
import com.example.megokinopoisk.databinding.MainFragmentBinding
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.main_fragment.*
import layout.PagerAdapter
import java.text.FieldPosition

class MainFragment : Fragment() {

    private var _binding: MainFragmentBinding? = null

    private val binding get() = _binding!!

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        val view = binding.root

        val toolbar = binding.toolbar
        var currentActivity = (activity as AppCompatActivity?)!!
        currentActivity.setSupportActionBar(toolbar)

        var drawer = binding.drawerLayout
        var toggle = ActionBarDrawerToggle(
                currentActivity,
                drawer,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        )
        drawer.addDrawerListener(toggle)
        toggle.isDrawerIndicatorEnabled = true
        toggle.syncState()

        var tabLayout = binding.tabLayout
        var viewPager = binding.pager
        //tabLayout.setupWithViewPager(viewPager)
        val words = arrayListOf("Films", "Animations", "TV Series")
        viewPager.adapter = PagerAdapter(currentActivity, words)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = returnName(position)
        }.attach()

        return view
    }

    private fun returnName(position: Int): String {
        var names: Array<String> = arrayOf(getString(R.string.films), getString(R.string.animations), getString(R.string.tv_series))
        return names[position]
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}