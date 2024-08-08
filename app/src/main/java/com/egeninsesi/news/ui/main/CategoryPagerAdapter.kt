package com.egeninsesi.news.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.egeninsesi.news.data.model.Category
import com.egeninsesi.news.ui.category.CategoryFragment
import com.egeninsesi.news.ui.category.MainFragment
import com.google.gson.Gson

class CategoryPagerAdapter(activity: FragmentActivity, private val categories: ArrayList<Category>) : FragmentStateAdapter(activity) {


    override fun getItemCount(): Int {
        return categories.size
    }

    override fun createFragment(position: Int): Fragment {

        val fragment: Fragment = if(position == 0) {
            MainFragment()
        } else {
            CategoryFragment()
        }

        fragment.arguments = Bundle().apply {
            putString("category", Gson().toJson(categories[position]))
        }

        return fragment
    }
}