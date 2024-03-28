package com.example.myapplication.presentation.home.adapter

import android.util.Log.d
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.common.Constants
import com.example.myapplication.databinding.MovieItemBinding
import com.example.myapplication.domain.model.Movies


class ItemViewHolder(
    private val binding: MovieItemBinding,
    private val clickListener: (id: Int) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: Movies) {
        binding.originalName.text = movie.original_title.toString()
        Glide.with(binding.root.context).load(Constants.IMAGE_PATH.plus(movie.poster_path))
            .into(binding.poster)
        binding.overview.text = movie.overview
        binding.voteAverage.text =
            binding.root.context.getString(R.string.vote_average, movie.vote_average.toString())
        binding.poster.setOnClickListener {
            clickListener.invoke(movie.id)
        }
    }
}