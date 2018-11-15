package com.aezhkov.flickrsearch.presentation.feature.search.view.items

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.ImageView
import com.aezhkov.flickrsearch.R
import com.aezhkov.flickrsearch.domain.model.search.PhotoItemModel
import com.aezhkov.flickrsearch.presentation.base.adapter.BindableView
import com.aezhkov.flickrsearch.presentation.utils.bind
import com.bumptech.glide.Glide

class PhotoItemView
@JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0)
    : FrameLayout(context, attrs, defStyle), BindableView<PhotoItemModel> {

    private val photoImageView by bind<ImageView>(R.id.photo_item_image)

    override fun bind(model: PhotoItemModel) {
        Glide.with(this).load(model.photoUrlSmall).into(photoImageView)
    }
}