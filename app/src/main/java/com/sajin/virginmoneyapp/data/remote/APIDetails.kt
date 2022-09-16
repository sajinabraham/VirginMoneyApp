package com.sajin.virginmoneyapp.data.remote


import com.sajin.virginmoneyapp.domain.model.PeopleModel
import com.sajin.virginmoneyapp.domain.model.RoomsModel
import retrofit2.Response
import retrofit2.http.GET

/*
* Created by Sajin Abraham on 16/09/2022 10:29
*/

interface APIDetails {
    @GET("people")
    suspend fun getPeople(): Response<PeopleModel>

    @GET("rooms")
    suspend fun getRooms(): Response<RoomsModel>
}
