package com.example.myapplication.presentation.detail.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.common.Constants
import com.example.myapplication.databinding.CompaniesItemBinding
import com.example.myapplication.databinding.GenresItemBinding
import com.example.myapplication.domain.model.Genres
import com.example.myapplication.domain.model.ProductionCompanies

class CompaniesItemViewHolder(
    private val binding: CompaniesItemBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(productionCompanies: ProductionCompanies) {
        binding.name.text = productionCompanies.name
        Glide.with(binding.root.context).load(Constants.IMAGE_PATH.plus(productionCompanies.logo_path))
            .into(binding.poster)
    }
}

