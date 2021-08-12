package com.example.chucknorrisjokes.random.data

import com.example.chucknorrisjokes.random.data.network.RandomJokesNetworkDataSource
import com.example.chucknorrisjokes.random.domain.model.Joke
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RandomJokesRepository @Inject constructor(
    private val networkDataSource: RandomJokesNetworkDataSource
) {
    suspend fun getJokes(num: Int): List<Joke> {
        return withContext(Dispatchers.IO) {
            networkDataSource.getJokes(num)
        }
    }
}
