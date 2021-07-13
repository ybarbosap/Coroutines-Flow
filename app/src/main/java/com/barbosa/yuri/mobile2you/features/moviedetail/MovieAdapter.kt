package com.barbosa.yuri.mobile2you.features.moviedetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.barbosa.yuri.mobile2you.R
import com.barbosa.yuri.mobile2you.databinding.SimilarItemBinding
import com.barbosa.yuri.mobile2you.datasource.RetrofitClient
import com.barbosa.yuri.mobile2you.datasource.models.Genre
import com.barbosa.yuri.mobile2you.datasource.models.Movie
import com.barbosa.yuri.mobile2you.utils.releaseDateFormat
import com.squareup.picasso.Picasso


class MovieAdapter(private val onClickMovie: OnClickMovie) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private lateinit var binding: SimilarItemBinding
    var movies = listOf<Movie>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        binding = SimilarItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int = movies.size

    fun interface OnClickMovie {
        fun onClick(movieID: Int)
    }

    inner class MovieViewHolder(itemBinding: SimilarItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        private var imageCover = itemBinding.similarCover
        private var title = itemBinding.similarTitle
        private var subtitle = itemBinding.similarSubtitle

        fun bind(movie: Movie) {
            loadCover(movie.posterPath.orEmpty())
            setListeners(movie.id)
            movie.genres?.let {incrementSubtitle(it)}
            setTitleAndSubtitle(
                title = movie.title,
                subtitle = movie.releaseDate.releaseDateFormat()
            )
        }

        private fun loadCover(posterPath: String) {
            Picasso.get().load("${RetrofitClient.IMAGE_PATH}$posterPath")
                .into(imageCover)
        }

        private fun setTitleAndSubtitle(title: String, subtitle: String) {
            this.title.text = title
            this.subtitle.text = subtitle
        }

        private fun setListeners(movieID: Int) {
            imageCover.setOnClickListener {
                onClickMovie.onClick(movieID)
            }
        }

        private fun incrementSubtitle(genres: List<Genre>){
            val actual = subtitle.text
            subtitle.text =
                itemView.context.getString(
                    R.string.genres,
                    actual,
                    genres.joinToString(","),
                )
        }
    }
}