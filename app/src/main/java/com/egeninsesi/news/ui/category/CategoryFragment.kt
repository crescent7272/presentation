package com.egeninsesi.news.ui.category

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.recyclerview.widget.GridLayoutManager
import com.egeninsesi.news.R
import com.egeninsesi.news.data.model.Category
import com.egeninsesi.news.data.model.News
import com.egeninsesi.news.databinding.FragmentCategoryBinding
import com.egeninsesi.news.databinding.FragmentMainBinding
import com.egeninsesi.news.ui.details.DetailsActivity
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class CategoryFragment: Fragment(R.layout.fragment_category) {

    private lateinit var binding: FragmentCategoryBinding
    private lateinit var viewModel: CategoryFragmentViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_category, container, false)
        viewModel = ViewModelProvider(this).get(CategoryFragmentViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoryJson = arguments?.getString("category")
        if (categoryJson == null) {
            Timber.e("Category argument is null")
        } else {
            val category = Gson().fromJson(categoryJson, Category::class.java)
            Timber.d("Category: $category")
            category.slug?.let { viewModel.getCategoryNews(it) }
        }
        val adapter = RecyclerViewAdapter(
            object : RecyclerViewAdapter.OnItemClickListener {
                override fun onItemClick(news: News) {
                    val intent = Intent(activity,DetailsActivity::class.java)
                    intent.putExtra("newsdetail",Gson().toJson(news))
                    startActivity(intent)
                }
            })

        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
        }

        viewModel.mutableNewsList.observe(viewLifecycleOwner, Observer { newsList ->
            adapter.submitList(newsList.news)
            binding.recyclerView.adapter = adapter
        })
    }
}