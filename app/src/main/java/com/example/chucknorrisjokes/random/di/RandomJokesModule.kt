package com.example.chucknorrisjokes.random.di

import com.example.chucknorrisjokes.random.data.RandomJokesRepository
import com.example.chucknorrisjokes.random.data.RandomJokesRepositoryImpl
import com.example.chucknorrisjokes.random.data.network.RandomJokesRestClient
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import retrofit2.Retrofit

@Module
@InstallIn(ActivityRetainedComponent::class)
object RandomJokesModule {

    @ActivityRetainedScoped
    @Provides
    fun provideRandomJokesService(retrofit: Retrofit): RandomJokesRestClient {
        return retrofit.create(RandomJokesRestClient::class.java)
    }

    @ActivityRetainedScoped
    @Provides
    fun bindRandomJokesRepository(impl: RandomJokesRepositoryImpl): RandomJokesRepository {
        return impl
    }
}
