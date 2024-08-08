package com.egeninsesi.news.ui.category

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.egeninsesi.news.R
import com.egeninsesi.news.data.model.News
import com.egeninsesi.news.databinding.FragmentMainBinding
import com.egeninsesi.news.ui.details.DetailsActivity
import com.egeninsesi.news.utils.ImageViewPagerAdapter
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private var headlines: ArrayList<News> = ArrayList()
    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel: MainFragmentViewModel
    private lateinit var innerViewPager: ViewPager2
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        viewModel = ViewModelProvider(this).get(MainFragmentViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        innerViewPager = binding.headlinesViewpager
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

        viewModel.mutableNewsList.observe(viewLifecycleOwner) { newsList ->
            Timber.d("mutableNewsList =${newsList.news.size}")
            Timber.d("mutableNewsList headlines=${newsList.headlines.size}")
            adapter.submitList(newsList.news)
            binding.recyclerView.adapter = adapter
            headlines = newsList.headlines
            setUpViewPager()
        }
    }

    private fun setUpViewPager() {

        Timber.d("setUpViewPager")
        Timber.d("headlines = "+ headlines.size)

        val adapter = ImageViewPagerAdapter(object : ImageViewPagerAdapter.OnItemClickListener {
            override fun onItemClick(news: News) {
                val intent = Intent(activity,DetailsActivity::class.java)
                intent.putExtra("newsdetail",Gson().toJson(news))
                startActivity(intent)
            }
        })
        adapter.submitList(headlines)
        binding.headlinesViewpager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        val currentPageIndex = 1
        binding.headlinesViewpager.currentItem = currentPageIndex

        binding.headlinesViewpager.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {
            }
        )
        binding.headlinesViewpager.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.headlinesViewpager.unregisterOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {}
        )
    }
}