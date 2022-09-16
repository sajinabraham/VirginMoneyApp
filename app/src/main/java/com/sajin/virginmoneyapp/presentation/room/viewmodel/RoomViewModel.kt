package com.sajin.virginmoneyapp.presentation.room.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sajin.virginmoneyapp.domain.model.RoomsModel
import com.sajin.virginmoneyapp.domain.repository.Repository
import com.sajin.virginmoneyapp.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class RoomViewModel @Inject constructor(val repository: Repository) : ViewModel() {

    private var _roomResponse: MutableStateFlow<NetworkResult<RoomsModel>> =
        MutableStateFlow(NetworkResult.Loading())

    val roomResponse: StateFlow<NetworkResult<RoomsModel>> get() = _roomResponse

    fun getRooms() = viewModelScope.launch {
        getRoomCall()
    }

    private suspend fun getRoomCall() {

        try {
            val response = repository.getRooms()
            _roomResponse.value = handleRoomResponse(response)
        } catch (e: Exception) {
            _roomResponse.value = NetworkResult.Error("Rooms not found.")
        }
    }

    private fun handleRoomResponse(response: Response<RoomsModel>): NetworkResult<RoomsModel> {

        return when {
            response.message().toString().contains("timeout") -> {
                NetworkResult.Error("Timeout")
            }
            response.body()!!.isEmpty() -> {
                NetworkResult.Error("Room not found.")
            }
            response.isSuccessful -> {
                val response = response.body()
                NetworkResult.Success(response!!)
            }
            else -> {
                NetworkResult.Error(response.message())
            }
        }
    }
}