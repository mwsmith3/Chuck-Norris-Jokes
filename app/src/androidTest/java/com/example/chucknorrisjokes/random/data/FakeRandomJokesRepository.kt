package com.example.chucknorrisjokes.random.data

import com.example.chucknorrisjokes.random.domain.model.Joke
import javax.inject.Inject

class FakeRandomJokesRepository @Inject constructor() : RandomJokesRepository {

    var response: () -> List<Joke> = { emptyList() }

    override suspend fun getJokes(num: Int): List<Joke> {
        return response()
    }
}
