package com.example.chucknorrisjokes.random.di

import com.example.chucknorrisjokes.random.data.network.RandomJokesRestClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import retrofit2.Retrofit

@Module
@InstallIn(ActivityRetainedComponent::class)
class RandomJokesModule {
    @Provides
    fun provideRandomJokesService(retrofit: Retrofit): RandomJokesRestClient {
        return retrofit.create(RandomJokesRestClient::class.java)
    }
}
