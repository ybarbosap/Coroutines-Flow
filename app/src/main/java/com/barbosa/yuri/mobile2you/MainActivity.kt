package com.barbosa.yuri.mobile2you

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.barbosa.yuri.mobile2you.databinding.ActivityMainBinding
import com.barbosa.yuri.mobile2you.features.moviedetail.adapter.MovieAdapter
import com.barbosa.yuri.mobile2you.features.moviedetail.presentation.MovieDetailPresenter
import com.barbosa.yuri.mobile2you.features.moviedetail.repositories.MovieRepository
import com.barbosa.yuri.mobile2you.features.moviedetail.repositories.SimilarRepository
import com.barbosa.yuri.mobile2you.models.Response
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity(), MovieDetailPresenter.Companion.MoviePresenterContract {
    private lateinit var presenter: MovieDetailPresenter
    private lateinit var binding: ActivityMainBinding
    private var isLiked: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvMovieDetail.layoutManager = LinearLayoutManager(this)
        binding.rvMovieDetail.adapter = MovieAdapter(mutableListOf())

        binding.favoriteBtn.setOnClickListener {
            changeFavoriteBtn()
        }

        presenter = MovieDetailPresenter(
            SimilarRepository(),
            MovieRepository(),
            this
        )
    }

    private fun changeFavoriteBtn() {
        val resource = if (isLiked) {
            R.drawable.ic_baseline_favorite_border_24
        } else {
            R.drawable.ic_baseline_favorite_24
        }
        isLiked = !isLiked
        binding.favoriteBtn.setImageResource(resource)
        Log.i("btn", "trocou $resource")
    }

    override fun onResume() {
        super.onResume()
        presenter.getMovie(550)
    }

    override fun onSuccess(response: Response) {
        Picasso.get().load("https://image.tmdb.org/t/p/w500/${response.movie.posterPath}")
            .into(binding.movieCover)
        binding.rvMovieDetail.adapter = MovieAdapter(response.similarMovie.results)
        binding.voteCount.text = "${response.movie.voteCount.toString()} Likes"
        binding.popularityCount.text = "${response.movie.popularity} views"
        binding.movieTitle.text = response.movie.title
    }

    override fun onFailure() {
        Log.i("presenter", "Falha")
    }

    override fun onComplete() {
        print("jsadfasdfs")
    }
}