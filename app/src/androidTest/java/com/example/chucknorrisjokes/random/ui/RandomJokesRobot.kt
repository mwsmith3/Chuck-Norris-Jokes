package com.example.chucknorrisjokes.random.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.chucknorrisjokes.R

class RandomJokesRobot(
    run: RandomJokesRobot.() -> Unit
) {
    init {
        run()
    }

    fun checkErrorShown() {
        onView(withId(R.id.error))
            .check(
                ViewAssertions.matches(
                    ViewMatchers.isDisplayed()
                )
            )
    }

    fun checkRecyclerViewShown() {
        onView(withId(R.id.recycler))
            .check(
                ViewAssertions.matches(
                    ViewMatchers.isDisplayed()
                )
            )
    }
}
