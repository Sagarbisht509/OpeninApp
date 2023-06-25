package com.example.openinapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.openinapp.repository.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val apiRepository: ApiRepository) : ViewModel() {

    val liveData get() = apiRepository.apiResponseLiveData
    //val status get() = apiRepository.status

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            apiRepository.getData()
        }
    }
}