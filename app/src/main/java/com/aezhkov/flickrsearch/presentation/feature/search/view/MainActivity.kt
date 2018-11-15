package com.aezhkov.flickrsearch.presentation.feature.search.view

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.PopupWindow
import android.widget.Toast
import com.aezhkov.flickrsearch.FlickrSearchApp
import com.aezhkov.flickrsearch.R
import com.aezhkov.flickrsearch.domain.model.search.PhotoItemModel
import com.aezhkov.flickrsearch.presentation.base.adapter.BindAdapter
import com.aezhkov.flickrsearch.presentation.base.extension.addTextChange
import com.aezhkov.flickrsearch.presentation.base.extension.makeGone
import com.aezhkov.flickrsearch.presentation.base.extension.makeVisible
import com.aezhkov.flickrsearch.presentation.base.view.PopupView
import com.aezhkov.flickrsearch.presentation.base.view.addItemClickListener
import com.aezhkov.flickrsearch.presentation.feature.details.view.DetailsActivity
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
    private val photoGridListView by bind<RecyclerView>(R.id.search_photo_list)
    private val textEditView by bind<EditText>(R.id.search_photo_text)
    private val progressView by bind<View>(R.id.search_photo_progress)

    private var items: List<PhotoItemModel>? = null

    private val popupWindow by lazy {
        PopupWindow(this).apply {
            width = textEditView.width
            height = 300
            contentView = popupView
            setBackgroundDrawable(ContextCompat.getDrawable(this@MainActivity, R.drawable.gray_shape))
        }
    }
    val popupView: PopupView by lazy {
        LayoutInflater.from(this@MainActivity).inflate(R.layout.popup_view, null) as PopupView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val app = application as FlickrSearchApp
        app.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        photoGridListView.adapter = photosAdapter
        photoGridListView.layoutManager = GridLayoutManager(this, 3)

        photoGridListView.addItemClickListener { _, position ->
            items?.let {
                presenter.itemClick(it[position])
            }
        }
        textEditView.setOnEditorActionListener { _, actionId, _ ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    presenter.textInputted(textEditView.text.toString())
                    textEditView.clearFocus()
                    true
                }
                else -> false
            }
        }
        textEditView.addTextChange { text ->
            if (text.isEmpty()) {
                popupWindow.showAsDropDown(textEditView, 0, 0)
            } else {
                popupWindow.dismiss()
            }
        }
        textEditView.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                popupWindow.showAsDropDown(textEditView, 0, 0)
            }
        }
        popupView.setOnItemClickListener { item ->
            presenter.suggestSelected(item)
            popupWindow.dismiss()
        }
    }

    override fun updateItemsList(items: List<PhotoItemModel>) {
        this.items = items
        photosAdapter.setItems(items)
        photosAdapter.notifyDataSetChanged()
    }

    override fun showError(throwable: Throwable) {
        Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()
    }

    override fun showSuggest(items: List<String>) {
        textEditView.post {
            popupWindow.showAsDropDown(textEditView, 0, 0)
            popupView.bind(items)
        }
    }

    override fun showProgress() {
        progressView.makeVisible()
        photoGridListView.makeGone()
    }

    override fun hideProgress() {
        progressView.makeGone()
        photoGridListView.makeVisible()
    }

    override fun updateSuggest(list: List<String>) {
        popupView.bind(list)
    }

    override fun openDetails(model: PhotoItemModel) {
        DetailsActivity.start(this, model)
    }
}
