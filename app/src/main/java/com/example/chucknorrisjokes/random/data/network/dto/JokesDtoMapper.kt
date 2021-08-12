package com.example.chucknorrisjokes.random.data.network.dto

import com.example.chucknorrisjokes.random.domain.model.Joke
import javax.inject.Inject

class JokesDtoMapper @Inject constructor() {

    fun map(dto: JokesDto): List<Joke> {
        return dto.jokes?.map {
            Joke(
                it.id,
                it.joke
            )
        } ?: emptyList()
    }
}
