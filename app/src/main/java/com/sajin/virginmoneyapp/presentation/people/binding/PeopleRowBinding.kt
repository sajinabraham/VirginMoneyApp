package com.sajin.virginmoneyapp.presentation.people.binding

import android.util.Log
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import com.sajin.virginmoneyapp.domain.model.Result
import com.sajin.virginmoneyapp.presentation.main.view.MainFragmentDirections

class PeopleRowBinding {

    companion object {

        @BindingAdapter("onPeopleClickListener")
        @JvmStatic
        fun onPeopleClickListener(peopleRowLayout: ConstraintLayout, result: Result) {
            peopleRowLayout.setOnClickListener {
                try {
                    Log.d("onPeopleClickListener", "CALLED")
                    val action =
                        MainFragmentDirections.actionMainFragmentToProfileDetailsFragment(result)
                    peopleRowLayout.findNavController().navigate(action)

                } catch (e: Exception) {
                    Log.d("onPeopleClickListener", e.toString())
                }
            }
        }
    }
}