package com.barbosa.yuri.mobile2you.features.moviedetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.barbosa.yuri.mobile2you.R
import com.barbosa.yuri.mobile2you.databinding.ActivityMainBinding
import com.barbosa.yuri.mobile2you.datasource.RetrofitClient
import com.barbosa.yuri.mobile2you.datasource.repositories.MovieRepository
import com.barbosa.yuri.mobile2you.features.moviedetail.MovieActivity.Companion.movieExtra
import com.barbosa.yuri.mobile2you.datasource.models.Movie
import com.barbosa.yuri.mobile2you.datasource.models.SimilarMovies
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MovieActivity : AppCompatActivity() {

    private var isLiked: Boolean = true
    private var movieID = 299536
    private var job: Job? = null
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MovieAdapter
    private val viewModel by lazy {
        ViewModelProvider(
            this,
            MovieDetailViewModel.Factory(MovieRepository())
        ).get(MovieDetailViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initArgs()
        initAdapter()
        setView()
        setListeners()
        setObservables()
    }

    override fun onDestroy() {
        job?.cancel()
        super.onDestroy()
    }

    private fun initArgs() {
        intent.getIntExtra(movieExtra, -1).let {
            if (it != -1) movieID = it
        }
    }

    private fun initAdapter() {
        adapter = MovieAdapter {
            startActivity(createMainIntent(it))
        }
    }

    private fun setView() = binding.run {
        similarMoviesRecyclerView.layoutManager = LinearLayoutManager(this@MovieActivity)
        similarMoviesRecyclerView.adapter = adapter
    }

    private fun setListeners() {
        binding.favoriteButton.setOnClickListener { toggleFavorite() }
    }

    private fun setObservables() {
        job = lifecycleScope.launch {
            viewModel.state.collect { state ->
                when(state){
                    is MovieDetailState.Error -> onFailure(state.message)
                    MovieDetailState.Initial -> {
                        viewModel.handle(MovieDetailIntent.InitArgs(movieID))
                        viewModel.handle(MovieDetailIntent.GetMovie)
                    }
                    is MovieDetailState.Loading -> displayLoading(state.isLoading)
                    is MovieDetailState.ShowMovie -> {
                        viewModel.handle(MovieDetailIntent.GetSimilar)
                        displayMovieDetails(state.movie)
                    }
                    is MovieDetailState.ShowSimilar -> displaySimilar(state.similarMovies)
                }
            }
        }

    }

    private fun toggleFavorite() {
        isLiked = !isLiked
        binding.favoriteButton.setImageResource(if (isLiked) {
            R.drawable.ic_baseline_favorite_border_24
        } else {
            R.drawable.ic_baseline_favorite_24
        })
    }

    private fun showIcons() = binding.run {
        favoriteButton.visibility = View.VISIBLE
        popularityIcon.visibility = View.VISIBLE
        imageLikes.visibility = View.VISIBLE
        backButton.visibility = View.VISIBLE
    }

    private fun displayLoading(isLoading: Boolean) {
        binding.mainFlipper.displayedChild = if(isLoading) progress else layout
    }

    private fun displayMovieDetails(movie: Movie) = binding.run {
        Picasso.get()
            .load("${RetrofitClient.IMAGE_PATH}${movie.posterPath}")
            .into(binding.movieCoverImage)

        voteCount.text = getString(R.string.likes, movie.voteCount)
        popularityCount.text = getString(R.string.popularity, movie.popularity.toString())
        movieTitle.text = movie.title
        showIcons()
    }

    private fun displaySimilar(similarMovies: SimilarMovies) {
        adapter.movies = similarMovies.results
    }

    private fun onFailure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        binding.progress.visibility = View.GONE
    }

    companion object {
        const val movieExtra = "MOVIE_ID"
        private const val layout = 0
        private const val progress = 1
    }
}

fun Context.createMainIntent(movieID: Int): Intent {
    return Intent(this, MovieActivity::class.java).apply {
        putExtra(movieExtra, movieID)
    }
}