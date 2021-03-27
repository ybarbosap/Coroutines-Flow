package com.barbosa.yuri.mobile2you.features.moviedetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.barbosa.yuri.mobile2you.R
import com.barbosa.yuri.mobile2you.databinding.SimilarItemBinding
import com.barbosa.yuri.mobile2you.datasource.TheMovieDbApiHelper
import com.barbosa.yuri.mobile2you.models.Movie
import com.squareup.picasso.Picasso


fun String.releaseDateFormat(): String {
    val split = this.split("-")
    return split[0]
}

class MovieAdapter(private val movies: List<Movie>) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private lateinit var binding: SimilarItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        binding = SimilarItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int = movies.size

    inner class MovieViewHolder(itemBinding: SimilarItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        private var imageCover = itemBinding.similarCover
        private var title = itemBinding.similarTitle
        private var subtitle = itemBinding.similarSubtitle
        private var ctx = itemView.context
        fun bind(movie: Movie) {
            Picasso.get().load("${TheMovieDbApiHelper.IMAGE_PATH}${movie.posterPath}")
                .into(imageCover)
            title.text = movie.title
            subtitle.text =
                ctx.getString(R.string.similar_subtitle, movie.releaseDate.releaseDateFormat())
        }
    }
}