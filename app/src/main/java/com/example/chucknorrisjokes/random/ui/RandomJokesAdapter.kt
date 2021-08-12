package com.example.chucknorrisjokes.random.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chucknorrisjokes.R
import com.example.chucknorrisjokes.databinding.JokeBinding
import com.example.chucknorrisjokes.databinding.LoadingBinding
import com.example.chucknorrisjokes.random.domain.model.Joke

class RandomJokesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var state = ViewState.Success(emptyList())

    override fun getItemViewType(position: Int): Int {
        return if (position == state.jokes.size) {
            LOADING_VIEW_TYPE
        } else {
            JOKE_VIEW_TYPE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            LOADING_VIEW_TYPE -> {
                val binding =
                    LoadingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                LoadingViewHolder(binding)
            }
            else -> {
                val binding =
                    JokeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                JokeViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewType = getItemViewType(position)
        if (viewType == JOKE_VIEW_TYPE) {
            val joke = state.jokes[position]
            (holder as JokeViewHolder).bind(position, joke)
        }
    }

    override fun getItemCount(): Int {
        val numJokes = state.jokes.size
        val numExtraViewsForLoading = if (state.loadingMore) {
            1
        } else {
            0
        }
        return numJokes + numExtraViewsForLoading
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateJokes(newState: ViewState.Success) {
        state = newState
        notifyDataSetChanged()
    }

    class LoadingViewHolder(binding: LoadingBinding) : RecyclerView.ViewHolder(binding.root)

    class JokeViewHolder(private val binding: JokeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int, joke: Joke) {
            with(binding) {
                jokeNumberTextView.text = getJokeNumberString(position)
                jokeTextView.text = joke.joke
            }
        }

        private fun getJokeNumberString(position: Int) =
            binding.root.context.getString(R.string.joke_number, position + 1)
    }

    companion object {
        const val JOKE_VIEW_TYPE = 0
        const val LOADING_VIEW_TYPE = 1
    }
}
