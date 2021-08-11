package com.example.jokes.chucknorris.data.network

import com.example.jokes.chucknorris.data.network.dto.ChuckNorrisJokesDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ChuckNorrisJokesRestClient {

    @GET("/jokes/random/{num}")
    suspend fun getJokes(@Path("num") num: Int): ChuckNorrisJokesDto
}
