package com.example.chucknorrisjokes.random.data.network.dto

import com.google.gson.annotations.SerializedName

data class JokesDto(
    @SerializedName("value")
    val jokes: List<JokeDto>? = emptyList()
) {
    data class JokeDto(
        @SerializedName("id")
        val id: Long,

        @SerializedName("joke")
        val joke: String
    )
}
