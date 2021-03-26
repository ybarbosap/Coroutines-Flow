package com.barbosa.yuri.mobile2you.features.moviedetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.barbosa.yuri.mobile2you.R
import com.barbosa.yuri.mobile2you.databinding.SimilarItemBinding
import com.barbosa.yuri.mobile2you.models.Result
import com.squareup.picasso.Picasso


fun String.releaseDateFormat(): String {
    val split = this.split("-")
    return split[0]
}
class MovieAdapter(
    val results: List<Result>,
) : RecyclerView.Adapter<MovieAdapter.VH>() {
    private lateinit var binding: SimilarItemBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        binding = SimilarItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(results[position])
    }

    override fun getItemCount(): Int = results.size

    inner class VH(itemBinding: SimilarItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        private var imageCover = itemBinding.similarCover
        private var title = itemBinding.similarTitle
        private var subtitle = itemBinding.similarSubtitle
        fun bind(result: Result) {
            Picasso.get().load("https://image.tmdb.org/t/p/w500/${result.posterPath}")
                .into(imageCover)
            title.text = result.title.toString()
            /*
            var genre = ""
            result.genres?.let {
                for (g in it) {
                    genre += "${g.name},"
                }
            }
            */
            subtitle.text = "${result.releaseDate.releaseDateFormat()}"
        }
    }
}