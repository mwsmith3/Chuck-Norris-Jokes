package com.example.chucknorrisjokes.random.domain.usecase

import javax.inject.Inject
import kotlin.random.Random
import kotlin.random.nextInt

class NumberOfJokesToRetrieve @Inject constructor() {
    operator fun invoke() = Random.nextInt(MIN..MAX)

    companion object {
        const val MIN = 8
        const val MAX = 21
    }
}
