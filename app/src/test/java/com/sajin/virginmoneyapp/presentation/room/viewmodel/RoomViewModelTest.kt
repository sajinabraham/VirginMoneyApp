package com.sajin.virginmoneyapp.presentation.room.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import com.sajin.virginmoneyapp.domain.model.PeopleModel
import com.sajin.virginmoneyapp.domain.model.Result
import com.sajin.virginmoneyapp.domain.model.RoomsDataModel
import com.sajin.virginmoneyapp.domain.model.RoomsModel
import com.sajin.virginmoneyapp.domain.repository.Repository
import com.sajin.virginmoneyapp.presentation.people.viewmodel.PeopleViewModel
import com.sajin.virginmoneyapp.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner.Silent::class)
class RoomViewModelTest {
    val dispatcher = StandardTestDispatcher()

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var repository: Repository
    private lateinit var viewModel: RoomViewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(dispatcher)
        viewModel = RoomViewModel(repository)
    }
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `Room data from response when Success Then result is Empty`()= runBlocking{
        val response = RoomsModel()
        whenever(repository.getRooms()).thenReturn(Response.success(response))

        viewModel.getRooms()

        viewModel.roomResponse.asLiveData().observeForever{
            assertEquals((it as NetworkResult.Success).data,response)
        }

    }

    @Test
    fun `Room data from response when error then return Error`()= runBlocking{
        whenever(repository.getRooms()).thenReturn(Response.error(404,"Error ".toResponseBody()))
        viewModel.getRooms()
        viewModel.roomResponse.asLiveData().observeForever{
            assertEquals((it as NetworkResult.Error).message,"")
        }

    }

    @Test
    fun `Given Room data from response When success Then return all data`()= runBlocking{
        val rooms = arrayListOf<RoomsDataModel>(
            RoomsDataModel(createdAt = "", id = "", isOccupied = true, maxOccupancy = 23))

        Mockito.`when`(repository.getRooms()).thenReturn(Response.success(rooms) as Response<RoomsModel>)
        viewModel.getRooms()
        viewModel.roomResponse.asLiveData().observeForever{
            assertEquals((it as NetworkResult.Success).data,rooms)
        }

    }
}