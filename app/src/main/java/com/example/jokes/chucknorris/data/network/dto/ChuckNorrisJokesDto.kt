package com.example.jokes.chucknorris.data.network.dto

import com.google.gson.annotations.SerializedName

data class ChuckNorrisJokesDto(
    @SerializedName("value")
    val jokes: List<ChuckNorrisJokeDto>? = emptyList()
) {
    data class ChuckNorrisJokeDto(
        @SerializedName("id")
        val id: Long,

        @SerializedName("joke")
        val joke: String
    )
}
