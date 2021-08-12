package com.example.chucknorrisjokes.random.data.network

import com.example.chucknorrisjokes.random.data.network.dto.JokesDtoMapper
import com.example.chucknorrisjokes.random.domain.model.Joke
import javax.inject.Inject

class RandomJokesNetworkDataSource @Inject constructor(
    private val restClient: RandomJokesRestClient,
    private val mapper: JokesDtoMapper
) {
    suspend fun getJokes(num: Int): List<Joke> {
        return mapper.map(restClient.getJokes(num))
    }
}
