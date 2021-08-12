package com.example.chucknorrisjokes.random.di

import com.example.chucknorrisjokes.random.data.FakeRandomJokesRepository
import com.example.chucknorrisjokes.random.data.RandomJokesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RandomJokesModule::class]
)
abstract class FakeRandomJokesModule {

    @Singleton
    @Binds
    abstract fun bindFakeRandomJokesRepository(fake: FakeRandomJokesRepository): RandomJokesRepository
}
