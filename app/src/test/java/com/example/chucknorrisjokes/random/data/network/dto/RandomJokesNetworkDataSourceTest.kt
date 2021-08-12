package com.example.chucknorrisjokes.random.data.network.dto

import com.example.chucknorrisjokes.random.data.network.RandomJokesNetworkDataSource
import com.example.chucknorrisjokes.random.data.network.RandomJokesRestClient
import com.example.chucknorrisjokes.random.domain.model.Joke
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.withContext
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Test

@ExperimentalCoroutinesApi
class RandomJokesNetworkDataSourceTest {

    private val testDispatcher = TestCoroutineDispatcher()

    private val restClient: RandomJokesRestClient = mockk()
    private val mapper: JokesDtoMapper = mockk()

    @After
    fun tearDown() {
        confirmVerified(restClient)
        confirmVerified(mapper)
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `when getJokes, the network data source returns the mapped rest client response`(): Unit =
        runBlocking {
            withContext(testDispatcher) {
                val numberOfJokes = 4
                coEvery { restClient.getJokes(numberOfJokes) } returns JOKES_DTO
                every { mapper.map(JOKES_DTO) } returns JOKES

                val networkDataSource = RandomJokesNetworkDataSource(restClient, mapper)
                val actual = networkDataSource.getJokes(numberOfJokes)

                coVerify { restClient.getJokes(numberOfJokes) }
                verify { mapper.map(JOKES_DTO) }

                assertThat(actual).isEqualTo(JOKES)
            }
        }

    companion object {
        val JOKES_DTO = JokesDto(
            List(4) {
                JokesDto.JokeDto(it.toLong(), "joke $it")
            }
        )
        val JOKES = List(4) {
            Joke(it.toLong(), "joke $it")
        }
    }
}