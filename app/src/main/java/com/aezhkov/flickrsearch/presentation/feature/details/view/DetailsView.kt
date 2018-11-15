package com.aezhkov.flickrsearch.presentation.feature.details.view

import com.arellomobile.mvp.MvpView

interface DetailsView : MvpView {

    fun showImage(url: String)
}