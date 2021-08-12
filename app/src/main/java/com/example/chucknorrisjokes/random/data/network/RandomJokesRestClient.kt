package com.example.chucknorrisjokes.random.data.network

import com.example.chucknorrisjokes.random.data.network.dto.JokesDto
import retrofit2.http.GET
import retrofit2.http.Path

interface RandomJokesRestClient {

    @GET("/jokes/random/{num}")
    suspend fun getJokes(@Path("num") num: Int): JokesDto
}
