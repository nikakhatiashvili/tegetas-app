package com.example.myapplication.presentation.home.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.myapplication.domain.model.Movie

class DiffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}