package com.example.myapplication.presentation.detail.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.myapplication.domain.model.Genres

class DetailDiffCallback : DiffUtil.ItemCallback<Genres>() {
    override fun areItemsTheSame(oldItem: Genres, newItem: Genres): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Genres, newItem: Genres): Boolean {
        return oldItem == newItem
    }
}
