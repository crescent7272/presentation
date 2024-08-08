package com.egeninsesi.news.ui.authors

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.egeninsesi.news.R
import com.egeninsesi.news.data.model.Author
import com.egeninsesi.news.databinding.FragmentAuthorsBinding
import com.egeninsesi.news.ui.articles.ArticlesActivity
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
@AndroidEntryPoint
class AuthorsFragment: Fragment(R.layout.fragment_authors) {

    private lateinit var binding: FragmentAuthorsBinding
    private lateinit var viewModel: AuthorsViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_authors, container, false)
        viewModel = ViewModelProvider(this).get(AuthorsViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = AuthorsRecyclerViewAdapter(object : AuthorsRecyclerViewAdapter.OnItemClickListener {
            override fun onItemClick(author: Author) {
                Timber.d("author item")
                val intent = Intent(activity,ArticlesActivity::class.java)
                intent.putExtra("author",Gson().toJson(author))
                startActivity(intent)
            }
        })

        viewModel.mutableAuthorList.observe(viewLifecycleOwner) { list ->
            Timber.d("authors list =${list.size}")
            adapter.submitList(list)
            binding.recyclerView.adapter = adapter
        }

        viewModel.mutableError.observe(viewLifecycleOwner) { error ->
            Timber.d("authors list =${error}")
            Toast.makeText(context, error,Toast.LENGTH_LONG).show()
        }
    }
}