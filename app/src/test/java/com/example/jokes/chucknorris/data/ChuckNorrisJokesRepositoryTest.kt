package com.example.jokes.chucknorris.data

import com.example.jokes.chucknorris.data.network.ChuckNorrisJokesNetworkDataSource
import com.example.jokes.chucknorris.domain.model.ChuckNorrisJoke
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
class ChuckNorrisJokesRepositoryTest {

    private val testDispatcher = TestCoroutineDispatcher()

    private val networkDataSource: ChuckNorrisJokesNetworkDataSource = mockk()

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

            val repository = ChuckNorrisJokesRepository(networkDataSource)

            val actual = repository.getJokes(numberOfJokes)
            coVerify { networkDataSource.getJokes(numberOfJokes) }

            assertThat(actual).isEqualTo(JOKES)
        }
    }

    companion object {
        val JOKES = List(4) {
            ChuckNorrisJoke(it.toLong(), "joke $it")
        }
    }
}
