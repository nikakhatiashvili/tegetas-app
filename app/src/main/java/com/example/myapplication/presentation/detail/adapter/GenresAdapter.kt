package com.example.myapplication.presentation.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ListAdapter

import com.example.myapplication.databinding.GenresItemBinding
import com.example.myapplication.domain.model.Genres
import com.example.myapplication.presentation.home.adapter.DiffCallback

class GenresAdapter : ListAdapter<Genres, RecyclerView.ViewHolder>(DetailDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = GenresItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GenresItemViewHolder(binding)
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        (holder as GenresItemViewHolder).bind(item)
    }
}
