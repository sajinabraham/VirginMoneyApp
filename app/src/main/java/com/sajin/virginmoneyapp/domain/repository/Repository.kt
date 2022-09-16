package com.sajin.virginmoneyapp.domain.repository


import com.sajin.virginmoneyapp.domain.model.PeopleModel
import com.sajin.virginmoneyapp.domain.model.RoomsModel
import retrofit2.Response

interface Repository {

    suspend fun getPeople():Response<PeopleModel>
    suspend fun getRooms():Response<RoomsModel>
}