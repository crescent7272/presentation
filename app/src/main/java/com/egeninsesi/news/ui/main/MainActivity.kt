package com.egeninsesi.news.ui.main

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.egeninsesi.news.R
import com.egeninsesi.news.data.model.Category
import dagger.hilt.android.AndroidEntryPoint
import com.egeninsesi.news.databinding.ActivityMainBinding
import com.egeninsesi.news.ui.authors.AuthorsFragment
import com.egeninsesi.news.ui.contact.ContactFragment
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private var categories: ArrayList<Category> = ArrayList()
    private var authorsFragment: AuthorsFragment? = null
    private var contactFragment: ContactFragment? = null
    private val viewModel by viewModels<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        categories.add(Category(1,"Manşetler","mansetler"))

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        Timber.plant(Timber.DebugTree())
        Timber.d("onCreate ")

        val bottomNavigationView = binding.bottomNavigation
        bottomNavigationView.setOnItemSelectedListener { item ->
            val transaction = supportFragmentManager.beginTransaction()
            val currentFragment = supportFragmentManager.primaryNavigationFragment

            when (item.itemId) {
                R.id.navigation_home -> {
                    currentFragment?.let { transaction.hide(it) }
                    binding.fragmentContainer.visibility = View.GONE
                    true
                }

                R.id.navigation_authors -> {
                    currentFragment?.let { transaction.hide(it) }
                    val fragment = authorsFragment ?: AuthorsFragment().also { authorsFragment = it }
                    if (fragment.isAdded) {
                        transaction.show(fragment)
                    } else {
                        transaction.add(R.id.fragment_container, fragment)
                    }
                    binding.fragmentContainer.visibility = View.VISIBLE
                    transaction.setPrimaryNavigationFragment(fragment)
                    transaction.commitAllowingStateLoss()
                    true
                }

                R.id.navigation_contact -> {
                    currentFragment?.let { transaction.hide(it) }
                    val fragment = contactFragment ?: ContactFragment().also { contactFragment = it }
                    if (fragment.isAdded) {
                        transaction.show(fragment)
                    } else {
                        transaction.add(R.id.fragment_container, fragment)
                    }
                    binding.fragmentContainer.visibility = View.VISIBLE
                    transaction.setPrimaryNavigationFragment(fragment)
                    transaction.commitAllowingStateLoss()
                    true
                }

                else -> false
            }
        }


        val tabLayoutViewPager = binding.tabLayoutViewPager
        val tabLayout = binding.tabLayout

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.state
                    .flowWithLifecycle(lifecycle)
                    .collect { state ->
                        Timber.d("retrieve data = ${viewModel.getRetrievedData()}")

                        if (!viewModel.getRetrievedData()) {

                            if (state.categories != null) {
                                viewModel.setRetrievedData(true)

                                categories.addAll(state.categories!!)
                            }

                            tabLayoutViewPager.adapter = CategoryPagerAdapter(
                                this@MainActivity,
                                categories
                            )
                            tabLayoutViewPager.setOffscreenPageLimit(categories.size)
                            TabLayoutMediator(tabLayout, tabLayoutViewPager) { tab, position ->
                                Timber.d("categories is NOT null")
                                tab.text = categories[position].title
                            }.attach()
                        }
                    }
            }
        }

    }
}

