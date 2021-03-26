package com.barbosa.yuri.mobile2you.features.moviedetail.repositories

import org.junit.Assert.assertEquals
import org.junit.Test


class MovieRepositoryTest {
    private val repository: MovieRepository = MovieRepository()

    @Test
    fun `Quando chamar retornar um filme válido`(){
        // movieId 550 retorna o filme Fight Club
        var movie = repository.getMovie(550)
        assertEquals(movie?.title, "Fight Club")
    }

    @Test
    fun `Quando chamar retornar null`(){
        // movieId 1 não existe na api
        var movie = repository.getMovie(1)
        assertEquals(movie, null)
    }
}