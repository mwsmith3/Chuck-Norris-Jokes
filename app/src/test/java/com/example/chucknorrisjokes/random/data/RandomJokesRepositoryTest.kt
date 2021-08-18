package com.example.chucknorrisjokes.random.data

import com.example.chucknorrisjokes.random.data.network.RandomJokesNetworkDataSource
import com.example.chucknorrisjokes.random.domain.model.Joke
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.withContext
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Test

@ExperimentalCoroutinesApi
class RandomJokesRepositoryTest {

    private val testDispatcher = TestCoroutineDispatcher()

    private val networkDataSource: RandomJokesNetworkDataSource = mockk()

    @After
    fun tearDown() {
        confirmVerified(networkDataSource)
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `when getJokes then repository returns response from network`(): Unit = runBlocking {
        withContext(testDispatcher) {
            val numberOfJokes = 4
            coEvery { networkDataSource.getJokes(numberOfJokes) } returns JOKES

            val repository = RandomJokesRepositoryImpl(networkDataSource)

            val actual = repository.getJokes(numberOfJokes)
            coVerify { networkDataSource.getJokes(numberOfJokes) }

            assertThat(actual).isEqualTo(JOKES)
        }
    }

    companion object {
        val JOKES = List(4) {
            Joke(it.toLong(), "joke $it")
        }
    }
}
