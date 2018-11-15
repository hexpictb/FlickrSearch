package com.aezhkov.flickrsearch.presentation.base.extension

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText

fun View.makeVisible() {
    visibility = View.VISIBLE
}

fun View.makeGone() {
    visibility = View.GONE
}

fun EditText.addTextChange(block: (CharSequence) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {

        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            block(s)
        }

    })
}