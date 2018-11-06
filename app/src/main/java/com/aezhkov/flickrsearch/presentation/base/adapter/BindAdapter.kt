package com.aezhkov.flickrsearch.presentation.base.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class BindAdapter<T>(
    private val viewResId: Int
) : RecyclerView.Adapter<BindAdapter.BindViewHolder<T>>() {
    private var items: List<T> = emptyList()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): BindViewHolder<T> {
        val view = LayoutInflater.from(viewGroup.context).inflate(viewResId, viewGroup, false)
        return BindViewHolder(view as BindableView<T>)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(viewHolder: BindViewHolder<T>, position: Int) {
        viewHolder.bind(items[position])
    }

    fun setItems(items: List<T>) {
        this.items = ArrayList(items)
    }

    class BindViewHolder<T>(
        val view: BindableView<T>
    ) : RecyclerView.ViewHolder(view as View) {

        fun bind(model: T) {
            view.bind(model)
        }
    }
}