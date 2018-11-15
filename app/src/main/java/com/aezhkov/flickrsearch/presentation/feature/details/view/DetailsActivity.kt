package com.aezhkov.flickrsearch.presentation.feature.details.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import com.aezhkov.flickrsearch.FlickrSearchApp
import com.aezhkov.flickrsearch.R
import com.aezhkov.flickrsearch.domain.model.search.PhotoItemModel
import com.aezhkov.flickrsearch.presentation.feature.details.presenter.DetailsPresenter
import com.aezhkov.flickrsearch.presentation.utils.bind
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.bumptech.glide.Glide
import javax.inject.Inject

class DetailsActivity : MvpAppCompatActivity(), DetailsView {

    @InjectPresenter
    @Inject
    lateinit var presenter: DetailsPresenter

    @ProvidePresenter
    fun providePresenter(): DetailsPresenter {
        return presenter.apply {
            this.model = intent.getSerializableExtra(MODEL_PARAM) as PhotoItemModel
        }
    }

    private val imageView by bind<ImageView>(R.id.details_activity_image)

    override fun onCreate(savedInstanceState: Bundle?) {
        val app = application as FlickrSearchApp
        app.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details_activity)
    }

    override fun showImage(url: String) {
        Glide.with(this).load(url).into(imageView)
    }

    companion object {
        private const val MODEL_PARAM = "model_param"
        fun start(context: Context, model: PhotoItemModel) {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(MODEL_PARAM, model)
            context.startActivity(intent)
        }
    }
}