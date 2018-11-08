package com.aezhkov.flickrsearch.presentation.feature.search.view

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.PopupWindow
import android.widget.Toast
import com.aezhkov.flickrsearch.FlickrSearchApp
import com.aezhkov.flickrsearch.R
import com.aezhkov.flickrsearch.domain.model.search.PhotoItemModel
import com.aezhkov.flickrsearch.presentation.base.adapter.BindAdapter
import com.aezhkov.flickrsearch.presentation.base.view.PopupView
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
    private val textEditView by bind<EditText>(R.id.search_photo_text)
    private val popupWindow by lazy {
        PopupWindow(this).apply {
            width = textEditView.width
            height = 300
            contentView = LayoutInflater.from(this@MainActivity).inflate(R.layout.popup_view, null).also {
                popupView = it as PopupView
                popupView.setOnItemClickListener { item ->
                    presenter.suggestSelected(item)
                    dismiss()
                }
            }
            setBackgroundDrawable(ContextCompat.getDrawable(this@MainActivity, R.drawable.gray_shape))
        }
    }
    lateinit var popupView: PopupView

    override fun onCreate(savedInstanceState: Bundle?) {
        val app = application as FlickrSearchApp
        app.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        photoGridList.adapter = photosAdapter
        photoGridList.layoutManager = GridLayoutManager(this, 3)

        textEditView.setOnEditorActionListener { _, actionId, _ ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    presenter.textInputted(textEditView.text.toString())
                    textEditView.text.clear()
                    true
                }
                else -> false
            }
        }
        textEditView.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                popupWindow.showAsDropDown(textEditView, 0, 0)
            } else {
                popupWindow.dismiss()
            }
        }
    }

    override fun updateItemsList(items: List<PhotoItemModel>) {
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
}
