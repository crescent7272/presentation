package com.egeninsesi.news.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.egeninsesi.news.R

object BindingAdapters {
    @BindingAdapter("imageUrl","isHeadline")
    @JvmStatic
    fun loadImage(view: ImageView, imageUrl: String, isHeadline: Boolean = false) {

        val fullUrl = "http://cms.egeninsesi.com$imageUrl"

        if(isHeadline) {
            Glide.with(view.context)
                .load(fullUrl)
                .error(R.drawable.ic_baseline_error_outline_24)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(view)
        } else {
            val displayMetrics = view.context.resources.displayMetrics
            val screenWidth = displayMetrics.widthPixels
            val itemWidth = screenWidth / 2

            val layoutParams = view.layoutParams
            layoutParams.width = itemWidth
            view.layoutParams = layoutParams

            Glide.with(view.context)
                .load(fullUrl)
                .into(view)
        }
    }

    @BindingAdapter("headlinesUrl")
    @JvmStatic
    fun loadHeadlinesImage(view: ImageView, headlinesUrl: String) {

        val fullUrl = "http://cms.egeninsesi.com$headlinesUrl"

    }

    @BindingAdapter("items")
    @JvmStatic
    fun <T> setRecyclerViewProperties(recyclerView: RecyclerView, items: List<T>?) {
        if (recyclerView.adapter is BindableAdapter<*>) {
            items?.let {
                (recyclerView.adapter as BindableAdapter<T>).setData(it)
            }
        }
    }
}


interface BindableAdapter<T> {
    fun setData(items: List<T>)
}
