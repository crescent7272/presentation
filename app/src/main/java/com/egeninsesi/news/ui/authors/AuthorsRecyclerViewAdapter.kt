package com.egeninsesi.news.ui.authors

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.egeninsesi.news.R
import com.egeninsesi.news.data.model.Author
import com.egeninsesi.news.databinding.AuthorBinding
import com.egeninsesi.news.utils.BindableAdapter

class AuthorsRecyclerViewAdapter(
    private val itemClickListener: OnItemClickListener
):  ListAdapter<Author, AuthorsRecyclerViewAdapter.ViewHolder>(NewsDiffCallback()),BindableAdapter<Author> {
    class NewsDiffCallback : DiffUtil.ItemCallback<Author>() {
        override fun areItemsTheSame(oldItem: Author, newItem: Author): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: Author, newItem: Author): Boolean {
            return oldItem == newItem
        }
    }
    override fun setData(items: List<Author>) {
        submitList(items)
    }
    interface OnItemClickListener { fun onItemClick(author: Author)}

    inner class ViewHolder(private val binding: AuthorBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(author: Author) {
            binding.author = author
            binding.authorName.text = "${author.firstName?.trim()} ${author.lastName?.trim()}"
            val imageUrl = "http://cms.egeninsesi.com" + author.imagePath

            Glide.with(binding.root.context)
                .load(imageUrl)
                .into(binding.imageView)

            binding.root.setOnClickListener {
                itemClickListener.onItemClick(author)
            }
            binding.executePendingBindings()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: AuthorBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.author, parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
