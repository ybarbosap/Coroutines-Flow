package com.barbosa.yuri.mobile2you.features.moviedetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.barbosa.yuri.mobile2you.R
import com.barbosa.yuri.mobile2you.databinding.SimilarItemBinding
import com.barbosa.yuri.mobile2you.datasource.RetrofitClient
import com.barbosa.yuri.mobile2you.models.Movie
import com.barbosa.yuri.mobile2you.utils.releaseDateFormat
import com.squareup.picasso.Picasso


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
        fun bind(movie: Movie) {
            Picasso.get().load("${RetrofitClient.IMAGE_PATH}${movie.posterPath}")
                .into(imageCover)
            title.text = movie.title
            subtitle.text = movie.releaseDate.releaseDateFormat()
            movie.genres?.let {
                val actual = subtitle.text
                subtitle.text =
                    itemView.context.getString(
                        R.string.genres,
                        actual,
                        it.joinToString(","),
                    )
            }
        }
    }
}