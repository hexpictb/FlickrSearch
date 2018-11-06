package com.aezhkov.flickrsearch.presentation.feature.search.view

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.aezhkov.flickrsearch.FlickrSearchApp
import com.aezhkov.flickrsearch.R
import com.aezhkov.flickrsearch.domain.model.search.PhotoItemModel
import com.aezhkov.flickrsearch.presentation.base.adapter.BindAdapter
import com.aezhkov.flickrsearch.presentation.feature.search.presenter.SearchPhotoPresenter
import com.aezhkov.flickrsearch.presentation.utils.bind
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), SearchPhotoView {

    @InjectPresenter
    @Inject
    lateinit var presenter: SearchPhotoPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    private val photosAdapter = BindAdapter<PhotoItemModel>(R.layout.photo_item_view)
    private val photoGridList by bind<RecyclerView>(R.id.search_photo_list)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val app = application as FlickrSearchApp
        app.appComponent.inject(this)
        photoGridList.adapter = photosAdapter
        photoGridList.layoutManager = GridLayoutManager(this, 3)
    }

    override fun updateItemsList(items: List<PhotoItemModel>) {
        photosAdapter.setItems(items)
        photosAdapter.notifyDataSetChanged()
    }
}
