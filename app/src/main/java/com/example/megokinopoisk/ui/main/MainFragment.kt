package com.example.megokinopoisk.ui.main

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.megokinopoisk.R
import com.example.megokinopoisk.data.DataSource
import com.example.megokinopoisk.data.FilmDetailsDTO
import com.example.megokinopoisk.databinding.MainFragmentBinding
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.main_fragment.*
import layout.PagerAdapter

class MainFragment : Fragment() {

    private var _binding: MainFragmentBinding? = null

    private val binding get() = _binding!!

    private val onLoadListener: DataSource.FilmLoaderListener =
            object : DataSource.FilmLoaderListener {

                override fun onLoaded(weatherDTO: FilmDetailsDTO) {
                    //displayFilm(weatherDTO)
                }

                override fun onFailed(throwable: Throwable) {
                    //Обработка ошибки
                }
            }

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)

//        var dataSource = DataSource().also {
//            it.loadData()
//        }
        val view = binding.root

        val toolbar = binding.toolbar
        var currentActivity = (activity as AppCompatActivity?)!!.apply {
            setSupportActionBar(toolbar)
        }

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
        val words = arrayListOf("Films", "Animations", "TV Series")
        viewPager.adapter = PagerAdapter(currentActivity, words)

        TabLayoutMediator(tabLayout, viewPager) { tab, position -> tab.text = returnName(position) }.attach()
        return view
    }

    private fun returnName(position: Int): String {
        var names = arrayOf(getString(R.string.films), this.getString(R.string.animations), this.getString(R.string.tv_series))
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


//    private fun displayFilm(filmDetailsDTO: FilmDetailsDTO) {
//        with(binding) {
//            mainView.visibility = View.VISIBLE
//            loadingLayout.visibility = View.GONE
//            val city = weatherBundle.city
//            cityName.text = city.city
//            cityCoordinates.text = String.format(
//                    getString(R.string.city_coordinates),
//                    city.lat.toString(),
//                    city.lon.toString()
//            )
//            weatherCondition.text = weatherDTO.fact?.condition
//            temperatureValue.text = weatherDTO.fact?.temp.toString()
//            feelsLikeValue.text = weatherDTO.fact?.feels_like.toString()
//        }
//    }
}