package com.example.chucknorrisjokes.random.ui

import androidx.recyclerview.widget.RecyclerView

class JokeDataObserver(
    private val recycler: RecyclerView
) :
    RecyclerView.AdapterDataObserver() {

    override fun onChanged() {
        super.onChanged()
        val adapter = recycler.adapter ?: return
        val lastPosition = adapter.itemCount - 1
        val lastItemViewType = adapter.getItemViewType(lastPosition)

        if (lastItemViewType == RandomJokesAdapter.LOADING_VIEW_TYPE) {
            recycler.smoothScrollToPosition(lastPosition)
        }
    }
}
