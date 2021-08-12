package com.example.chucknorrisjokes.random.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.chucknorrisjokes.databinding.ActivityRandomJokesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class RandomJokesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRandomJokesBinding

    private val viewModel: RandomJokesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRandomJokesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecycler()

        lifecycleScope.launchWhenResumed {
            viewModel.state.collect {
                renderState(it)
            }
        }
    }

    private fun setupRecycler() {
        with(binding.recycler) {
            val adapter = RandomJokesAdapter()
            adapter.registerAdapterDataObserver(JokeDataObserver(this))
            this.adapter = adapter
            addItemDecoration(
                DividerItemDecoration(
                    this@RandomJokesActivity,
                    RecyclerView.VERTICAL
                )
            )
            addOnScrollListener(AllItemsShowScrollListener { viewModel.loadMore() })
        }
    }

    private fun renderState(state: ViewState) {
        with(binding) {
            recycler.isVisible = state is ViewState.Success
            error.isVisible = state is ViewState.Error
            loading.isVisible = state is ViewState.Loading

            if (state is ViewState.Success) {
                (recycler.adapter as RandomJokesAdapter).updateJokes(state)
            }
        }
    }
}
