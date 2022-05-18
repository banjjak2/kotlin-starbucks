package com.codesquad.kotlin_starbucks.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.codesquad.kotlin_starbucks.databinding.CircleItemBinding
import com.codesquad.kotlin_starbucks.databinding.RectangleItemBinding
import com.codesquad.kotlin_starbucks.home.data.HomeItem

private const val RECOMMEND = 1
private const val EVENT = 2

class ItemListAdapter : ListAdapter<HomeItem, RecyclerView.ViewHolder>(diffUtil) {

    class RecommendViewHolder(private val binding: CircleItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(recommendItem: HomeItem.RecommendItem) {
            binding.recommendItem = recommendItem
        }
    }

    class EventViewHolder(private val binding: RectangleItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(homeEvent: HomeItem.HomeEvent) {
            binding.event = homeEvent
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            RECOMMEND -> {
                RecommendViewHolder(
                    CircleItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            else -> {
                EventViewHolder(
                    RectangleItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is RecommendViewHolder -> holder.bind(getItem(position) as HomeItem.RecommendItem)
            is EventViewHolder -> holder.bind(getItem(position) as HomeItem.HomeEvent)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (currentList[position]) {
            is HomeItem.RecommendItem -> RECOMMEND
            is HomeItem.HomeEvent -> EVENT
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<HomeItem>() {
            override fun areItemsTheSame(oldItem: HomeItem, newItem: HomeItem): Boolean {
                return when (oldItem) {
                    is HomeItem.HomeEvent -> oldItem.imagePath == (newItem as? HomeItem.HomeEvent)?.imagePath
                    is HomeItem.RecommendItem -> oldItem.imagePath == (newItem as? HomeItem.RecommendItem)?.imagePath
                }
            }

            override fun areContentsTheSame(oldItem: HomeItem, newItem: HomeItem): Boolean {
                return when (oldItem) {
                    is HomeItem.HomeEvent -> oldItem == (newItem as? HomeItem.HomeEvent)
                    is HomeItem.RecommendItem -> oldItem == (newItem as? HomeItem.RecommendItem)
                }
            }
        }
    }
}