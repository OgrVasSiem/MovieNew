package com.ogrvassiem.myfilms.ui.destinations.gameScreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
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
class GameScreenViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: TopicsRepository
) : ViewModel() {
    private val categories: List<String> = savedStateHandle.navArgs<CategoriesNavArgs>().categories

    private val _films = MutableStateFlow<List<TopicsResponse.Film>>(emptyList())
    val films: StateFlow<List<TopicsResponse.Film>> = _films.asStateFlow()

    private val _swipedRightFilms = MutableStateFlow<List<TopicsResponse.Film>>(emptyList())
    val swipedRightFilms: StateFlow<List<TopicsResponse.Film>> = _swipedRightFilms.asStateFlow()

    private val _displayedFilms = MutableStateFlow<List<TopicsResponse.Film>>(emptyList())
    val displayedFilms: StateFlow<List<TopicsResponse.Film>> = _displayedFilms.asStateFlow()

    private val _offsets = MutableStateFlow<List<MutableState<Dp>>>(emptyList())
    val offsets: StateFlow<List<MutableState<Dp>>> = _offsets.asStateFlow()

    init {
        viewModelScope.launch {
            val result = repository.getTopics()
            if (result.isSuccess) {
                val topics = result.getOrNull()?.topics ?: emptyList()
                getFilmsByCategories(topics)
            } else {
            }
        }
    }

    private fun getFilmsByCategories(topics: List<TopicsResponse.Topic>) {
        val films = topics.filter { it.nameCategory in categories }
            .flatMap { it.content }
            .flatMap { it.values }
            .flatten()
        _films.value = films
        _displayedFilms.value = films.take(2)
        updateOffsets(2)
    }

    fun onSwipeLeft() {
        viewModelScope.launch {
            val currentFilms = _displayedFilms.value.toMutableList()

            if (currentFilms.isNotEmpty()) {
                val removedFilm = currentFilms.removeAt(0)

                _films.value = _films.value.filter { it.filmName != removedFilm.filmName }
                getNextFilm()?.let { currentFilms.add(it) }
                _displayedFilms.value = currentFilms
                updateOffsets(currentFilms.size)
            }
        }
    }

    fun onSwipeRight() {
        viewModelScope.launch {
            val currentFilms = _displayedFilms.value.toMutableList()

            if (currentFilms.isNotEmpty()) {
                val removedFilm = currentFilms.removeAt(0)

                _swipedRightFilms.value = _swipedRightFilms.value + removedFilm
                _films.value = _films.value.filter { it.filmName != removedFilm.filmName }
                getNextFilm()?.let { currentFilms.add(it) }
                _displayedFilms.value = currentFilms
                updateOffsets(currentFilms.size)
            }
        }
    }

    private fun getNextFilm(): TopicsResponse.Film? {
        val displayedFilmNames = _displayedFilms.value.map { it.filmName }

        return _films.value.firstOrNull { it.filmName !in displayedFilmNames }
    }

    private fun updateOffsets(size: Int) {
        val baseOffsets = listOf(0.dp, 40.dp)
        _offsets.value = List(size) { i -> mutableStateOf(baseOffsets.getOrElse(i) { 40.dp }) }
    }
}
