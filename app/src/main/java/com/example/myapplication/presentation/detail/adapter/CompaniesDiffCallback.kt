package com.example.myapplication.presentation.detail.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.myapplication.domain.model.Genres
import com.example.myapplication.domain.model.ProductionCompanies

class CompaniesDiffCallback : DiffUtil.ItemCallback<ProductionCompanies>() {
    override fun areItemsTheSame(oldItem: ProductionCompanies, newItem: ProductionCompanies): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ProductionCompanies, newItem: ProductionCompanies): Boolean {
        return oldItem == newItem
    }
}

