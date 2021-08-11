package com.example.jokes.chucknorris.data.network.dto

import com.example.jokes.chucknorris.data.network.ChuckNorrisJokesNetworkDataSource
import com.example.jokes.chucknorris.data.network.ChuckNorrisJokesRestClient
import com.example.jokes.chucknorris.domain.model.ChuckNorrisJoke
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.withContext
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Test

@ExperimentalCoroutinesApi
class ChuckNorrisJokesNetworkDataSourceTest {

    private val testDispatcher = TestCoroutineDispatcher()

    private val restClient: ChuckNorrisJokesRestClient = mockk()
    private val mapper: ChuckNorrisJokesDtoMapper = mockk()

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

                val networkDataSource = ChuckNorrisJokesNetworkDataSource(restClient, mapper)
                val actual = networkDataSource.getJokes(numberOfJokes)

                coVerify { restClient.getJokes(numberOfJokes) }
                verify { mapper.map(JOKES_DTO) }

                assertThat(actual).isEqualTo(JOKES)
            }
        }

    companion object {
        val JOKES_DTO = ChuckNorrisJokesDto(
            List(4) {
                ChuckNorrisJokesDto.ChuckNorrisJokeDto(it.toLong(), "joke $it")
            }
        )
        val JOKES = List(4) {
            ChuckNorrisJoke(it.toLong(), "joke $it")
        }
    }
}