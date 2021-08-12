package com.example.chucknorrisjokes.random.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chucknorrisjokes.random.domain.model.Joke
import com.example.chucknorrisjokes.random.domain.usecase.GetRandomJokes
import dagger.hilt.android.lifecycle.HiltViewModel
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

    init {
        viewModelScope.launch {
            try {
                val result = getRandomJokes()
                _state.emit(ViewState.Success(result))
            } catch (e: Exception) {
                _state.emit(ViewState.Error)
            }
        }
    }
}

sealed class ViewState {
    object Loading : ViewState()
    object Error : ViewState()
    data class Success(
        val jokes: List<Joke>
    ) : ViewState()
}
