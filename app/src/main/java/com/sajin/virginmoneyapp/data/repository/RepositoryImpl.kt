package com.sajin.virginmoneyapp.data.repository

import com.sajin.virginmoneyapp.data.remote.APIDetails
import com.sajin.virginmoneyapp.domain.model.PeopleModel
import com.sajin.virginmoneyapp.domain.model.RoomsModel
import com.sajin.virginmoneyapp.domain.repository.Repository
import retrofit2.Response
import javax.inject.Inject

class RepositoryImpl @Inject constructor(val apiDetails: APIDetails) : Repository {
    override suspend fun getPeople(): Response<PeopleModel> = apiDetails.getPeople()

    override suspend fun getRooms(): Response<RoomsModel> = apiDetails.getRooms()
}