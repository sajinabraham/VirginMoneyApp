package com.sajin.virginmoneyapp.presentation.main.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.sajin.virginmoneyapp.R
import com.sajin.virginmoneyapp.databinding.FragmentMainBinding
import com.sajin.virginmoneyapp.presentation.main.adapter.ViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var binding: FragmentMainBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMainBinding.bind(view)

        val tabLayout = binding.tabLayout
        val viewPager = binding.pager
        val adapter = ViewPagerAdapter(childFragmentManager, lifecycle)

        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "People"
                }
                1 -> {
                    tab.text = "Rooms"
                }
            }
        }.attach()
    }
}