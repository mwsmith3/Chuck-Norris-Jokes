package com.example.chucknorrisjokes.random.data.network.dto

import com.example.chucknorrisjokes.random.domain.model.Joke
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class JokesDtoMapperTest {

    private val mapper = JokesDtoMapper()

    @Test
    fun `when null jokes then mapper returns empty list`() {
        val dto = JokesDto(null)

        val actual = mapper.map(dto)
        val expected = emptyList<Joke>()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `when dto has jokes then mapper returns jokes`() {
        val actual = mapper.map(JOKES_DTO)
        val expected = JOKES

        assertThat(actual).isEqualTo(expected)
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
