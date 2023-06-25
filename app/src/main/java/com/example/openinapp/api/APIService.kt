package com.example.openinapp.api

import com.example.openinapp.models.ApiResponse
import retrofit2.Response
import retrofit2.http.GET

interface APIService {

    @GET("/api/v1/dashboardNew")
    suspend fun getData() : Response<ApiResponse>
}