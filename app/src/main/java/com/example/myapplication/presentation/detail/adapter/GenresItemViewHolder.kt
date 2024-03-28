package com.example.myapplication.presentation.detail.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.GenresItemBinding
import com.example.myapplication.domain.model.Genres

class GenresItemViewHolder(
    private val binding: GenresItemBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(genre: Genres) {
        binding.genre.text = genre.name
    }
}
