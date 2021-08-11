package com.example.jokes.chucknorris.data

import com.example.jokes.chucknorris.data.network.ChuckNorrisJokesNetworkDataSource
import com.example.jokes.chucknorris.domain.model.ChuckNorrisJoke
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ChuckNorrisJokesRepository @Inject constructor(
    private val networkDataSource: ChuckNorrisJokesNetworkDataSource
) {
    suspend fun getJokes(num: Int): List<ChuckNorrisJoke> {
        return withContext(Dispatchers.IO) {
            networkDataSource.getJokes(num)
        }
    }
}
