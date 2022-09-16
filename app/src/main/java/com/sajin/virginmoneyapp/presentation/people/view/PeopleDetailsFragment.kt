package com.sajin.virginmoneyapp.presentation.people.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import com.sajin.virginmoneyapp.R
import com.sajin.virginmoneyapp.databinding.FragmentPeopleDetailsBinding
import com.sajin.virginmoneyapp.utils.DateUtils

class PeopleDetailsFragment : Fragment(R.layout.fragment_people_details) {
    private lateinit var binding: FragmentPeopleDetailsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentPeopleDetailsBinding.bind(view)
        setProfile()
    }

    private fun setProfile() {
        val args: PeopleDetailsFragmentArgs by navArgs()
        binding.apply {
            itemImage.profileCIV.load(args.peopleModel!!.avatar)
            (args.peopleModel!!.firstName + args.peopleModel!!.lastName).also {
                buildString {
                    append(" ")
                }
            }
            itemImage.tvJobtitle.text = args.peopleModel!!.jobtitle
            itemEmail.tvEmail.text = args.peopleModel!!.email

            ("My Favorite Colour: " + args.peopleModel!!.favouriteColor).also {
                itemImage.tvFavColor.text = it
            }
            ("Profile Created at: " +
                    DateUtils.getFormattedDate(
                        args.peopleModel!!.getDateTimeObj()!!,
                        DateUtils.DIS_DATE_FORMAT_PATTERN1
                    )).also { itemDate.tvDate.text = it }
        }


    }
}