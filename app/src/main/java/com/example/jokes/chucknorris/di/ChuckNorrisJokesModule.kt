package com.example.jokes.chucknorris.di

import com.example.jokes.chucknorris.data.network.ChuckNorrisJokesRestClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import retrofit2.Retrofit

@Module
@InstallIn(ActivityRetainedComponent::class)
class ChuckNorrisJokesModule {
    @Provides
    fun provideRandomJokesService(retrofit: Retrofit): ChuckNorrisJokesRestClient {
        return retrofit.create(ChuckNorrisJokesRestClient::class.java)
    }
}
