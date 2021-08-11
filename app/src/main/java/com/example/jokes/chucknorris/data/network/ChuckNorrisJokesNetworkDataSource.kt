package com.example.jokes.chucknorris.data.network

import com.example.jokes.chucknorris.data.network.dto.ChuckNorrisJokesDtoMapper
import com.example.jokes.chucknorris.domain.model.ChuckNorrisJoke
import javax.inject.Inject

class ChuckNorrisJokesNetworkDataSource @Inject constructor(
    private val restClient: ChuckNorrisJokesRestClient,
    private val mapper: ChuckNorrisJokesDtoMapper
) {
    suspend fun getJokes(num: Int): List<ChuckNorrisJoke> {
        return mapper.map(restClient.getJokes(num))
    }
}
