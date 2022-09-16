package com.sajin.virginmoneyapp.presentation.people.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sajin.virginmoneyapp.domain.model.PeopleModel
import com.sajin.virginmoneyapp.domain.repository.Repository
import com.sajin.virginmoneyapp.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(val repository: Repository) : ViewModel() {

    private var _peopleResponse: MutableStateFlow<NetworkResult<PeopleModel>> =
        MutableStateFlow(NetworkResult.Loading())

    val peopleResponse: StateFlow<NetworkResult<PeopleModel>> get() = _peopleResponse

    var myIndex: Int = 0

    fun getPeople(people: Boolean) = viewModelScope.launch {
        if (people) {
            getPeopleCall()
        }

    }

    private suspend fun getPeopleCall() {
        try {
            val response = repository.getPeople()
            _peopleResponse.value = handlePeopleResponse(response)
        } catch (e: Exception) {
            _peopleResponse.value = NetworkResult.Error("People not found.")
        }
    }

    private fun handlePeopleResponse(response: Response<PeopleModel>): NetworkResult<PeopleModel> {

        return when {
            response.message().toString().contains("timeout") -> {
                NetworkResult.Error("Timeout")
            }
            response.body()!!.isEmpty() -> {
                NetworkResult.Error("People not found.")
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