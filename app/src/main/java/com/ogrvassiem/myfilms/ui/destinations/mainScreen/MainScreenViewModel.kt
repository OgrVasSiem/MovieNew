package com.ogrvassiem.myfilms.ui.destinations.mainScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ogrvassiem.myfilms.aplication.rest.TopicsRepository
import com.ogrvassiem.myfilms.aplication.rest.TopicsResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val repository: TopicsRepository
) : ViewModel() {
    private val _categoryInfo = MutableStateFlow<List<TopicsResponse.Topic>?>(null)
    val categoryInfo: StateFlow<List<TopicsResponse.Topic>?> = _categoryInfo.asStateFlow()

    init {
        getMovies()
    }

    private fun getMovies(){
        viewModelScope.launch {
            val result = repository.getTopics()
            if (result.isSuccess) {
                _categoryInfo.value = result.getOrNull()?.topics
                Log.d("MainViewModel", "${_categoryInfo.value}")

            } else {
                _categoryInfo.value = null
                Log.d("MainViewModel", "Error loading topics: ${result.exceptionOrNull()?.message}")
            }
        }
    }

}