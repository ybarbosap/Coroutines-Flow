package com.barbosa.yuri.mobile2you.features.moviedetail.presentation

import com.barbosa.yuri.mobile2you.features.moviedetail.presentation.MovieDetailPresenter.Companion.MoviePresenterContract
import com.barbosa.yuri.mobile2you.features.moviedetail.repositories.MovieRepository
import com.barbosa.yuri.mobile2you.features.moviedetail.repositories.SimilarRepository
import com.barbosa.yuri.mobile2you.models.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals

class MovieDetailPresenterTest : MoviePresenterContract {

    private val presenter: MovieDetailPresenter = MovieDetailPresenter(
        SimilarRepository(),
        MovieRepository(),
        this
    )
    private lateinit var response: Response
    private val mainThreadSurrogate = newSingleThreadContext("UI tread")

    @Before
    fun before() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @Test
    fun `Quando chamar`() {
        runBlocking {
            launch(Dispatchers.Main) {
                presenter.getMovie(550)
                response
            }
        }

    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }


    fun t(){
        assertEquals(20, response.similarMovie.results.size)
    }

    override fun onSuccess(r: Response) {
        response = r
    }

    override fun onFailure() {
        TODO("Not yet implemented")
    }

    override fun onComplete() {
        TODO("Not yet implemented")
    }
}