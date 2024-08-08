package com.egeninsesi.news.ui.category

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.egeninsesi.news.R
import com.egeninsesi.news.data.model.News
import com.egeninsesi.news.databinding.ItemNewsBinding
import com.egeninsesi.news.utils.BindableAdapter
import timber.log.Timber


class RecyclerViewAdapter(
    private val itemClickListener: OnItemClickListener
):  ListAdapter<News, RecyclerViewAdapter.ViewHolder>(NewsDiffCallback()), BindableAdapter<News> {
    interface OnItemClickListener { fun onItemClick(news: News)}
    class NewsDiffCallback : DiffUtil.ItemCallback<News>() {
        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem == newItem
        }
    }
    override fun setData(items: List<News>) {
        submitList(items)
    }
    inner class ViewHolder(private val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: News) {
            binding.news = item
            binding.root.setOnClickListener {
                Timber.d("itemView.setOnClickListener")
                itemClickListener.onItemClick(item)
            }
            binding.executePendingBindings()
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemNewsBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_news, parent, false
        )
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}