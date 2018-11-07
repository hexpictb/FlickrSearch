package com.aezhkov.flickrsearch.presentation.base.view

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.TextView
import com.aezhkov.flickrsearch.R
import com.aezhkov.flickrsearch.presentation.base.adapter.BindableView
import com.aezhkov.flickrsearch.presentation.utils.bind

class PopupItemView
@JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0)
    : FrameLayout(context, attrs, defStyle), BindableView<String> {

    private val titleView by bind<TextView>(R.id.popup_item_title)

    override fun bind(model: String) {
        titleView.text = model
    }
}