package com.kotlin.course.conceptosbasicos

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.kotlin.course.conceptosbasicos.MediaItem.Type.*
import kotlinx.android.synthetic.main.item_recycler.view.*
import kotlin.properties.Delegates

class MediaAdapter(items: List<MediaItem> = emptyList(), val onItemSelected: OnMediaItemSelected)
    : RecyclerView.Adapter<MediaAdapter.ViewHolder>() {

    var items: List<MediaItem> by Delegates.observable(items) {
        _, _, _ -> notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.item_recycler))
    }

    override fun onBindViewHolder(h: ViewHolder, pos: Int) {
        with(items[pos]) {
            h.bind(this)
            h.itemView.setOnClickListener { onItemSelected(this)}
        }
    }

    override fun getItemCount() = items.size

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        fun bind(item: MediaItem) {
            with(itemView) {
                with(item) {
                    txvMediaTitle.text = title
                    imvMediaImage.loadUrl(thumbUrl)

                    imvPlay.visibility = when (type) {
                        PHOTO -> View.GONE
                        VIDEO -> View.VISIBLE
                    }
                }
            }
        }
    }
}