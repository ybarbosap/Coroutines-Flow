package com.barbosa.yuri.mobile2you.features.moviedetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.barbosa.yuri.mobile2you.R
import com.barbosa.yuri.mobile2you.databinding.SimilarItemBinding
import com.barbosa.yuri.mobile2you.datasource.TheMovieDbApiHelper
import com.barbosa.yuri.mobile2you.models.Result
import com.squareup.picasso.Picasso


fun String.releaseDateFormat(): String {
    val split = this.split("-")
    return split[0]
}
class MovieAdapter(
    private val results: List<Result>,
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
        private var ctx = itemView.context
        fun bind(result: Result) {
            Picasso.get().load("${TheMovieDbApiHelper.IMAGE_PATH}${result.posterPath}")
                .into(imageCover)
            title.text = result.title
            subtitle.text = ctx.getString(R.string.similar_subtitle, result.releaseDate.releaseDateFormat())
        }
    }
}