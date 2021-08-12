package com.example.chucknorrisjokes.random.ui

import androidx.recyclerview.widget.RecyclerView

class AllItemsShowScrollListener(
    private val onAllJokesShownCallback: () -> Unit
) : RecyclerView.OnScrollListener() {

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        val adapter = recyclerView.adapter ?: return
        val lastPosition = adapter.itemCount - 1
        val lastItemViewType = adapter.getItemViewType(lastPosition)

        if (hasScrolledToLastJoke(recyclerView, newState, lastItemViewType)) {
            onAllJokesShownCallback()
        }
    }

    private fun hasScrolledToLastJoke(
        recyclerView: RecyclerView,
        newState: Int,
        lastItemViewType: Int
    ): Boolean {
        return recyclerView.canScrollVertically(-1) &&
                !recyclerView.canScrollVertically(1) &&
                newState == RecyclerView.SCROLL_STATE_IDLE &&
                lastItemViewType != RandomJokesAdapter.LOADING_VIEW_TYPE
    }
}
