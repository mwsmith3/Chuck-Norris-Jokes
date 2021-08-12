package com.example.chucknorrisjokes.random.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chucknorrisjokes.random.domain.model.Joke
import com.example.chucknorrisjokes.random.domain.usecase.GetRandomJokes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RandomJokesViewModel @Inject constructor(
    private val getRandomJokes: GetRandomJokes
) : ViewModel() {

    private val _state = MutableStateFlow<ViewState>(ViewState.Loading)
    val state: StateFlow<ViewState> = _state

    private var loadMoreJob: Job? = null

    init {
        viewModelScope.launch {
            try {
                val result = getRandomJokes()
                _state.emit(ViewState.Success(result))
            } catch (e: Exception) {
                Log.d("RandomJokesViewModel", "exception: ${e.message}")
                _state.emit(ViewState.Error)
            }
        }
    }

    fun loadMore() {
        val currentState = _state.value
        if (currentState !is ViewState.Success) return
        loadMoreJob?.cancel()
        loadMoreJob = viewModelScope.launch {
            _state.emit(currentState.copy(loadingMore = true))
            try {
                val newJokes = getRandomJokes()
                val newState =
                    currentState.copy(loadingMore = false, jokes = currentState.jokes + newJokes)
                _state.emit(newState)
            } catch (e: Exception) {
                Log.d("RandomJokesViewModel", "exception when loading more: ${e.message}")
            }
        }
    }
}

sealed class ViewState {
    object Loading : ViewState()
    object Error : ViewState()
    data class Success(
        val jokes: List<Joke>,
        val loadingMore: Boolean = false
    ) : ViewState()
}
