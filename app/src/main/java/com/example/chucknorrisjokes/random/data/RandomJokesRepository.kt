package com.example.chucknorrisjokes.random.data

import com.example.chucknorrisjokes.random.domain.model.Joke

interface RandomJokesRepository {
    suspend fun getJokes(num: Int): List<Joke>
}
