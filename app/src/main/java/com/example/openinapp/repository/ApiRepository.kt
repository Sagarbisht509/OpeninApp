package com.example.openinapp.repository

import androidx.lifecycle.MutableLiveData
import com.example.openinapp.R
import com.example.openinapp.api.APIService
import com.example.openinapp.models.ApiResponse
import com.example.openinapp.models.Item
import com.example.openinapp.utils.NetworkResult
import org.json.JSONObject
import javax.inject.Inject

class ApiRepository @Inject constructor(private val apiService: APIService) {

    private var _apiResponseLiveData = MutableLiveData<NetworkResult<ApiResponse>>()
    val apiResponseLiveData get() = _apiResponseLiveData

    suspend fun getData() {
        _apiResponseLiveData.postValue(NetworkResult.Loading())
        val response = apiService.getData()

        if(response.isSuccessful && response.body() != null) {
            //storeData(response.body()!!)
            _apiResponseLiveData.postValue(NetworkResult.Success(response.body()!!))
        }
        else if(response.body() != null) {
            val errorObject = JSONObject(response.errorBody()!!.charStream().readText())
            _apiResponseLiveData.postValue(NetworkResult.Error(errorObject.toString()))
        }
        else {
            _apiResponseLiveData.postValue(NetworkResult.Error("Error"))
        }
    }


}