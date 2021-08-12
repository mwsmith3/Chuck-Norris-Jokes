package com.example.chucknorrisjokes.random.domain.usecase

import com.example.chucknorrisjokes.random.data.RandomJokesRepository
import com.example.chucknorrisjokes.random.domain.model.Joke
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Test

class GetRandomJokesTest {

    private val repository: RandomJokesRepository = mockk()
    private val numberOfJokesToRetrieve: NumberOfJokesToRetrieve = mockk()

    @After
    fun tearDown() {
        confirmVerified(repository)
        confirmVerified(numberOfJokesToRetrieve)
    }

    @Test
    fun `when GetRandomJokes then jokes retrieved from repository`(): Unit = runBlocking {
        val numJokes = 8
        every { numberOfJokesToRetrieve.invoke() } returns numJokes
        coEvery { repository.getJokes(numJokes) } returns JOKES

        val getRandomJokes = GetRandomJokes(repository, numberOfJokesToRetrieve)
        val expected = JOKES

        val actual = getRandomJokes()

        assertThat(actual).isEqualTo(expected)
        verify { numberOfJokesToRetrieve() }
        coVerify { repository.getJokes(numJokes) }
    }

    companion object {
        val JOKES = List(4) {
            Joke(it.toLong(), "joke $it")
        }
    }
}
