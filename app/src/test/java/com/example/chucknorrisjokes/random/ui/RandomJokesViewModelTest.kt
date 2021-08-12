package com.example.chucknorrisjokes.random.ui

import com.example.chucknorrisjokes.random.domain.model.Joke
import com.example.chucknorrisjokes.random.domain.usecase.GetRandomJokes
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Test

class RandomJokesViewModelTest {

    private val getRandomJokes: GetRandomJokes = mockk()

    @After
    fun tearDown() {
        confirmVerified(getRandomJokes)
    }

    @Test
    fun `when getRandomJokes throws exception, then state is Error`(): Unit = runBlocking {
        coEvery { getRandomJokes.invoke() } throws (Exception())

        val viewModel = RandomJokesViewModel(getRandomJokes)

        val actual = viewModel.state.value
        val expected = ViewState.Error

        assertThat(actual).isEqualTo(expected)
        coVerify { getRandomJokes.invoke() }
    }

    @Test
    fun `when getRandomJokes returns result, then state is Success`(): Unit = runBlocking {
        coEvery { getRandomJokes.invoke() } returns JOKES

        val viewModel = RandomJokesViewModel(getRandomJokes)

        val actual = viewModel.state.value
        val expected = ViewState.Success(JOKES)

        assertThat(actual).isEqualTo(expected)
        coVerify { getRandomJokes.invoke() }
    }

    companion object {
        val JOKES = List(4) {
            Joke(it.toLong(), "joke $it")
        }
    }
}
