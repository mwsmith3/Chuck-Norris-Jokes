package com.example.chucknorrisjokes.random.domain.usecase

import com.example.chucknorrisjokes.random.data.RandomJokesRepository
import com.example.chucknorrisjokes.random.domain.model.Joke
import javax.inject.Inject

class GetRandomJokes @Inject constructor(
    private val randomJokesRepository: RandomJokesRepository,
    private val numberOfJokesToRetrieve: NumberOfJokesToRetrieve
) {
    suspend operator fun invoke(): List<Joke> {
        val numJokes = numberOfJokesToRetrieve()
        return randomJokesRepository.getJokes(numJokes)
    }
}
