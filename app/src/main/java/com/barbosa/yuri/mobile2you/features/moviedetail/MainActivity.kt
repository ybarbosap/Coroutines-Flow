package com.barbosa.yuri.mobile2you.features.moviedetail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.barbosa.yuri.mobile2you.R
import com.barbosa.yuri.mobile2you.databinding.ActivityMainBinding
import com.barbosa.yuri.mobile2you.datasource.RetrofitClient
import com.barbosa.yuri.mobile2you.datasource.repositories.MovieRepository
import com.barbosa.yuri.mobile2you.models.Movie
import com.barbosa.yuri.mobile2you.models.SimilarMovies
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity(), MovieDetailContract.View {
    private lateinit var presenter: MovieDetailPresenter
    private lateinit var binding: ActivityMainBinding
    private var isLiked: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.similarMoviesRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.similarMoviesRecyclerView.adapter = MovieAdapter(mutableListOf())

        binding.favoriteButton.setOnClickListener { toggleFavorite() }

        presenter = MovieDetailPresenter(MovieRepository(), this)

        presenter.getMovieInfo(299536)
    }

    //TODO: Salvar no sharedPreferences
    private fun toggleFavorite() {
        val resource = if (isLiked) {
            R.drawable.ic_baseline_favorite_border_24
        } else {
            R.drawable.ic_baseline_favorite_24
        }
        isLiked = !isLiked
        binding.favoriteButton.setImageResource(resource)
    }

    private fun showIcons() {
        binding.favoriteButton.visibility = View.VISIBLE
        binding.popularityIcon.visibility = View.VISIBLE
        binding.imageLikes.visibility = View.VISIBLE
        binding.backButton.visibility = View.VISIBLE
    }

    override fun displayMovieDetails(similarMovies: SimilarMovies, movie: Movie) {
        Picasso.get()
            .load("${RetrofitClient.IMAGE_PATH}${movie.posterPath}")
            .into(binding.movieCoverImage)

        binding.similarMoviesRecyclerView.adapter =
            MovieAdapter(similarMovies.results)
        binding.voteCount.text = getString(R.string.likes, movie.voteCount)
        binding.popularityCount.text =
            getString(R.string.popularity, movie.popularity.toString())
        binding.movieTitle.text = movie.title
        binding.progress.visibility = View.GONE
        showIcons()
    }

    override fun onFailure() {
        Toast.makeText(this, getString(R.string.error_message), Toast.LENGTH_LONG).show()
        binding.progress.visibility = View.GONE
    }

}