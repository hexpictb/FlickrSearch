package com.aezhkov.flickrsearch.presentation.base.view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View

private typealias ClickListener = (view: View, position: Int) -> Unit

class RecyclerItemClickListener(
    context: Context,
    recyclerView: RecyclerView,
    private val itemClickListener: ClickListener? = null,
    private val itemLongClickListener: ClickListener? = null
) : RecyclerView.OnItemTouchListener {

    private var detector: GestureDetector

    init {
        detector = GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
            override fun onSingleTapUp(e: MotionEvent): Boolean {
                return true
            }

            override fun onLongPress(e: MotionEvent) {
                val child = recyclerView.findChildViewUnder(e.x, e.y)
                if (child != null) {
                    itemLongClickListener?.invoke(child, recyclerView.getChildAdapterPosition(child))
                }
            }
        })
    }

    override fun onInterceptTouchEvent(view: RecyclerView, e: MotionEvent): Boolean {
        val childView = view.findChildViewUnder(e.x, e.y)
        if (childView != null && detector.onTouchEvent(e)) {
            itemClickListener?.invoke(childView, view.getChildAdapterPosition(childView))
            return true
        }
        return false
    }

    override fun onTouchEvent(view: RecyclerView, motionEvent: MotionEvent) {}

    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}
}

fun RecyclerView.addItemClickListener(block: (view: View, position: Int) -> Unit) {
    val listener = RecyclerItemClickListener(context, this, itemClickListener = block)
    addOnItemTouchListener(listener)
}

