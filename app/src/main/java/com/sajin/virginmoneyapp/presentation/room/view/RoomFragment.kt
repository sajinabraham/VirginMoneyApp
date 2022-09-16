package com.sajin.virginmoneyapp.presentation.room.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.sajin.virginmoneyapp.R
import com.sajin.virginmoneyapp.databinding.FragmentRoomBinding
import com.sajin.virginmoneyapp.presentation.room.adapter.RoomAdapter
import com.sajin.virginmoneyapp.presentation.room.viewmodel.RoomViewModel
import com.sajin.virginmoneyapp.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RoomFragment : Fragment(R.layout.fragment_room) {

    private lateinit var binding: FragmentRoomBinding
    private val viewModel: RoomViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRoomBinding.bind(view)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.apply {
            rvRooms.layoutManager = LinearLayoutManager(requireContext())
        }
        requestApiData()
    }

    private fun requestApiData() {

        binding.srlID.apply {
            setOnRefreshListener {
                isRefreshing = true
                viewModel.getRooms()
            }

        }
        viewModel.getRooms()
        viewModel.roomResponse.asLiveData().observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {

                    response.data?.let {
                        val adapter = RoomAdapter(it) {}
                        binding.rvRooms.adapter = adapter
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