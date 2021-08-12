package com.example.chucknorrisjokes.random.ui

import androidx.lifecycle.ViewModel
import com.example.chucknorrisjokes.random.domain.model.Joke

class ChuckNorrisJokesViewModel : ViewModel() {
}

sealed class ViewState {
    object Loading : ViewState()
    object Error : ViewState()
    data class Success(
        val jokes: List<Joke>
    ) : ViewState()
}
