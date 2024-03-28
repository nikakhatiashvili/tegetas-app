package com.example.myapplication.presentation.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.CompaniesItemBinding
import com.example.myapplication.domain.model.ProductionCompanies
import com.example.myapplication.presentation.home.adapter.ItemViewHolder

class CompaniesAdapter : ListAdapter<ProductionCompanies, RecyclerView.ViewHolder>(CompaniesDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = CompaniesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CompaniesItemViewHolder(binding)
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        (holder as CompaniesItemViewHolder).bind(item)
    }
}

