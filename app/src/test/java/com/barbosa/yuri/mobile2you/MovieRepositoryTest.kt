package com.barbosa.yuri.mobile2you

import com.barbosa.yuri.mobile2you.datasource.repositories.MovieRepository
import org.junit.Assert.assertEquals
import org.junit.Test


class MovieRepositoryTest {
    private val repository: MovieRepository = MovieRepository()

    @Test
    fun `Quando chamar retornar um filme válido`() {
        // movieId 550 retorna o filme Fight Club
        var movie = repository.getMovie(550)
        assertEquals(movie?.title, "Fight Club")
    }

    @Test
    fun `Quando chamar retornar null em getMovie`() {
        // movieId 1 não existe na api
        var movie = repository.getMovie(1)
        assertEquals(movie, null)
    }

    @Test
    fun `Quando chamar retornar um filme similar válido`() {
        // movieId 550 retorna o filme Fight Club
        var similar = repository.getSimilarMovies(550)

        assertEquals(similar?.results?.size, 20)
        assertEquals(similar?.results?.get(0)?.title, "Blade Runner")
    }

    @Test
    fun `Quando chamar retornar null em getSimilarMovies`() {
        // movieId 1 não existe na api
        var similar = repository.getSimilarMovies(1)
        assertEquals(similar, null)
    }
}