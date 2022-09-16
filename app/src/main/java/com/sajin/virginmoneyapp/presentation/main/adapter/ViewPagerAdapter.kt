package com.sajin.virginmoneyapp.presentation.main.adapter

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sajin.virginmoneyapp.presentation.people.view.PeopleFragment
import com.sajin.virginmoneyapp.presentation.room.view.RoomFragment

private const val NUM_TABS = 2

open class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int) = when (position) {
        0 -> PeopleFragment()
        else -> RoomFragment()
    }
}