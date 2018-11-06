package com.aezhkov.flickrsearch.presentation.base.adapter

interface BindableView<T> {

    fun bind(model: T)
}