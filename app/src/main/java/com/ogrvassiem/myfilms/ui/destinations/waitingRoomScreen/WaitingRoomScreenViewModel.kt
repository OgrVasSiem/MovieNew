package com.ogrvassiem.myfilms.ui.destinations.waitingRoomScreen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ogrvassiem.myfilms.aplication.rest.TopicsRepository
import com.ogrvassiem.myfilms.aplication.rest.TopicsResponse
import com.ogrvassiem.myfilms.navArgs
import com.ogrvassiem.myfilms.ui.destinations.navArgs.CategoriesNavArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WaitingRoomScreenViewModel @Inject constructor(
    private val repository: TopicsRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _categoryInfo = MutableStateFlow<List<TopicsResponse.Topic>?>(null)
    val categoryInfo: StateFlow<List<TopicsResponse.Topic>?> = _categoryInfo.asStateFlow()

    private val _password = MutableStateFlow<String?>(null)
    val password: StateFlow<String?> = _password.asStateFlow()

    val categories: List<String> = savedStateHandle.navArgs<CategoriesNavArgs>().categories

    init {
        viewModelScope.launch {
            val result = repository.getTopics()
            if (result.isSuccess) {
                val topics = result.getOrNull()?.topics ?: emptyList()
                filterTopicsByCategories(topics)
            } else {

            }
        }

        viewModelScope.launch {
            generateAndSavePassword()
        }
    }

    private fun filterTopicsByCategories(topics: List<TopicsResponse.Topic>){
        val filteredTopics = topics.filter { it.nameCategory in categories }
        _categoryInfo.value = filteredTopics
    }

    fun generateAndSavePassword() {
        val chars = ('A'..'Z') + ('0'..'9')
        val newPassword = (1..5)
            .map { chars.random() }
            .joinToString("")
        _password.value = newPassword
    }
}