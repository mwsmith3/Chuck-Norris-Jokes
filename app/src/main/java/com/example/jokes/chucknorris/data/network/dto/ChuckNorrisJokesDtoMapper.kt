package com.example.jokes.chucknorris.data.network.dto

import com.example.jokes.chucknorris.domain.model.ChuckNorrisJoke
import javax.inject.Inject

class ChuckNorrisJokesDtoMapper @Inject constructor() {

    fun map(dto: ChuckNorrisJokesDto): List<ChuckNorrisJoke> {
        return dto.jokes?.map {
            ChuckNorrisJoke(
                it.id,
                it.joke
            )
        } ?: emptyList()
    }
}
