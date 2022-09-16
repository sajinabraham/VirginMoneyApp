package com.sajin.virginmoneyapp.di

import com.google.gson.Gson
import com.sajin.virginmoneyapp.data.remote.APIDetails
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkModuleTest {
    lateinit var mockWebServer: MockWebServer
    lateinit var apiService: APIDetails
    lateinit var gson: Gson

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        gson = Gson()
        mockWebServer = MockWebServer()
        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(APIDetails::class.java)
    }

    @Test
    fun `get all people api test`() {
        runBlocking {
            val mockResponse = MockResponse()
            mockWebServer.enqueue(mockResponse.setBody("[]"))
            val response = apiService.getPeople()
            val request = mockWebServer.takeRequest()
            assertEquals("/people", request.path)
            assertEquals(true, response.body()?.isEmpty() == true)
        }
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}