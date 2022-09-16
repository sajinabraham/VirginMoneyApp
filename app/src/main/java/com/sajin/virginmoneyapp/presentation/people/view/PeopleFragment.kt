package com.sajin.virginmoneyapp.presentation.people.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.sajin.virginmoneyapp.R
import com.sajin.virginmoneyapp.databinding.FragmentPeopleBinding
import com.sajin.virginmoneyapp.presentation.people.viewmodel.PeopleViewModel
import com.sajin.virginmoneyapp.presentation.people.adapter.PeopleAdapter
import com.sajin.virginmoneyapp.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PeopleFragment : Fragment(R.layout.fragment_people) {
    private lateinit var binding: FragmentPeopleBinding
    private val viewModel: PeopleViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPeopleBinding.bind(view)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.rvPeople.layoutManager = LinearLayoutManager(requireContext())
        requestApiData()
    }

    private fun requestApiData() {

        binding.srlID.apply {
            setOnRefreshListener {
                isRefreshing = true
                viewModel.getPeople(true)
            }

        }

        if (viewModel.myIndex == 0) {
            viewModel.getPeople(true)
            viewModel.myIndex++
        }
        viewModel.peopleResponse.asLiveData().observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {

                    response.data?.let {
                        val adapter = PeopleAdapter(it) {}
                        binding.rvPeople.adapter = adapter
                    }
                    binding.srlID.isRefreshing = false
                }
                is NetworkResult.Error -> {
                    binding.srlID.isRefreshing = false
                    Toast.makeText(
                        requireContext(),
                        response.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is NetworkResult.Loading -> {
                    binding.srlID.isRefreshing = true
                }
            }
        }
    }
}