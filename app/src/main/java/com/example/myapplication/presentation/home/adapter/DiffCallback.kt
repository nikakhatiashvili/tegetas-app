package com.example.myapplication.presentation.home.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.myapplication.domain.model.Movies

class DiffCallback : DiffUtil.ItemCallback<Movies>() {
    override fun areItemsTheSame(oldItem: Movies, newItem: Movies): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movies, newItem: Movies): Boolean {
        return oldItem == newItem
    }
}