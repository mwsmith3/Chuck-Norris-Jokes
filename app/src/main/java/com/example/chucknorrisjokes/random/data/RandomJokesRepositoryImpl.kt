package com.example.chucknorrisjokes.random.data

import com.example.chucknorrisjokes.random.data.network.RandomJokesNetworkDataSource
import com.example.chucknorrisjokes.random.domain.model.Joke
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RandomJokesRepositoryImpl @Inject constructor(
    private val networkDataSource: RandomJokesNetworkDataSource
) : RandomJokesRepository {

    override suspend fun getJokes(num: Int): List<Joke> {
        return withContext(Dispatchers.IO) {
            delay(1000) // so you can see the pagination effect :)
            networkDataSource.getJokes(num)
        }
    }
}
