package com.codesquad.kotlin_starbucks.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.codesquad.kotlin_starbucks.R
import com.codesquad.kotlin_starbucks.databinding.EventItemBinding
import com.codesquad.kotlin_starbucks.databinding.HomeEventItemBinding
import com.codesquad.kotlin_starbucks.home.data.HomeEventItem

class HomeEventsAdapter(private val isMainEvent: Boolean) :
    ListAdapter<HomeEventItem, RecyclerView.ViewHolder>(diffUtil) {

    class HomeEventItemViewHolder(private val binding: HomeEventItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(homeEventItem: HomeEventItem) {
            binding.event = homeEventItem
        }
    }

    class ListEventItemViewHolder(private val binding: EventItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(homeEventItem: HomeEventItem) {
            binding.event = homeEventItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (isMainEvent) {
            HomeEventItemViewHolder(
                HomeEventItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        } else {
            ListEventItemViewHolder(
                EventItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HomeEventItemViewHolder -> holder.bind(getItem(position))
            is ListEventItemViewHolder -> holder.bind(getItem(position))
        }
    }
}

private val diffUtil = object : DiffUtil.ItemCallback<HomeEventItem>() {
    override fun areItemsTheSame(oldItemHome: HomeEventItem, newItemHome: HomeEventItem): Boolean {
        return oldItemHome.imageUrl == newItemHome.imageUrl
    }

    override fun areContentsTheSame(
        oldItemHome: HomeEventItem,
        newItemHome: HomeEventItem
    ): Boolean {
        return oldItemHome == newItemHome
    }

}