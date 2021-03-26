package com.barbosa.yuri.mobile2you.features.moviedetail.repositories

import org.junit.Assert.assertEquals
import org.junit.Test

class SimilarRepositoryTest {
    private val repository: SimilarRepository = SimilarRepository()

    @Test
    fun `Quando chamar retornar um filme válido`(){
        // movieId 550 retorna o filme Fight Club
        var similar = repository.getSimilar(550)

        assertEquals(similar?.results?.size, 20)
        assertEquals(similar?.results?.get(0)?.title, "Blade Runner")
    }

    @Test
    fun `Quando chamar retornar null`(){
        // movieId 1 não existe na api
        var similar = repository.getSimilar(1)
        assertEquals(similar, null)
    }
}