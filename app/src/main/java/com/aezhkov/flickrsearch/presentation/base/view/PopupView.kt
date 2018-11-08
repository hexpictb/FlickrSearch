package com.aezhkov.flickrsearch.presentation.base.view

import android.content.Context
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.widget.FrameLayout
import com.aezhkov.flickrsearch.R
import com.aezhkov.flickrsearch.presentation.base.adapter.BindAdapter
import com.aezhkov.flickrsearch.presentation.base.adapter.BindableView
import com.aezhkov.flickrsearch.presentation.utils.bind

class PopupView
@JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0)
    : FrameLayout(context, attrs, defStyle), BindableView<List<String>> {

    private val popupListView by bind<RecyclerView>(R.id.popup_view_list)

    private val popupAdapter = BindAdapter<String>(R.layout.popup_item_view)
    private var items: List<String>? = null

    override fun onFinishInflate() {
        super.onFinishInflate()

        popupListView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        popupListView.adapter = popupAdapter
        popupListView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }

    override fun bind(model: List<String>) {
        items = model
        popupAdapter.setItems(model)
        popupAdapter.notifyDataSetChanged()
    }

    fun setOnItemClickListener(block: (String) -> Unit) {
        popupListView.addItemClickListener { _, position -> items?.let { block(it[position]) } }
    }
}