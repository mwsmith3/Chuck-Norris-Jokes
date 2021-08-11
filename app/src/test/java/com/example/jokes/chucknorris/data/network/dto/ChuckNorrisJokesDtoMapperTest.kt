package com.example.jokes.chucknorris.data.network.dto

import com.example.jokes.chucknorris.domain.model.ChuckNorrisJoke
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class ChuckNorrisJokesDtoMapperTest {

    private val mapper = ChuckNorrisJokesDtoMapper()

    @Test
    fun `when null jokes then mapper returns empty list`() {
        val dto = ChuckNorrisJokesDto(null)

        val actual = mapper.map(dto)
        val expected = emptyList<ChuckNorrisJoke>()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `when dto has jokes then mapper returns jokes`() {
        val actual = mapper.map(JOKES_DTO)
        val expected = JOKES

        assertThat(actual).isEqualTo(expected)
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
