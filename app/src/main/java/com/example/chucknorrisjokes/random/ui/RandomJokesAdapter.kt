package com.example.chucknorrisjokes.random.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chucknorrisjokes.databinding.JokeBinding
import com.example.chucknorrisjokes.random.domain.model.Joke

class RandomJokesAdapter(
    private val jokes: List<Joke>
) : RecyclerView.Adapter<RandomJokesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = JokeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val joke = jokes[position]
        holder.bind(joke)
    }

    override fun getItemId(position: Int) = jokes[position].id

    override fun getItemCount() = jokes.size

    class ViewHolder(private val binding: JokeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(joke: Joke) {
            with(binding) {
                jokeTextView.text = joke.joke
            }
        }
    }
}
