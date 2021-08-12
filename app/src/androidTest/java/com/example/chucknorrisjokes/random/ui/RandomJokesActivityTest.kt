package com.example.chucknorrisjokes.random.ui

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.launchActivity
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.chucknorrisjokes.random.data.FakeRandomJokesRepository
import com.example.chucknorrisjokes.random.data.RandomJokesRepository
import com.example.chucknorrisjokes.random.domain.model.Joke
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class RandomJokesActivityTest {

    private lateinit var scenario: ActivityScenario<RandomJokesActivity>

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var repository: RandomJokesRepository
    private val fakeRepository: FakeRandomJokesRepository
        get() = repository as FakeRandomJokesRepository

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @After
    fun cleanUp() {
        scenario.close()
    }

    @Test
    fun whenRepositoryThrowsError_thenErrorShown() {
        fakeRepository.response = { throw Exception() }

        scenario = launchActivity()

        RandomJokesRobot {
            checkErrorShown()
        }
    }

    @Test
    fun whenRepositoryReturnsJokes_thenRecyclerViewShown() {
        fakeRepository.response = { JOKES }

        scenario = launchActivity()

        RandomJokesRobot {
            checkRecyclerViewShown()
        }
    }
    companion object {
        val JOKES = List(4) {
            Joke(it.toLong(), "joke $it")
        }
    }
}
