package com.lostthings.app.items

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.lostthings.R
import com.lostthings.domain.Item
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_item.view.*

class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(item: Item, onItemClick: (View, Item) -> Unit) {
        with(view) {
            loadImage(item)
            itemItemNameTv.text = item.name
            setOnClickListener { onItemClick(view.itemItemPhotoIv, item) }
        }
    }

    private fun View.loadImage(item: Item) {
        Picasso.get()
            .load(item.thumbnail)
            .placeholder(R.drawable.ic_item_placeholder)
            .into(itemItemPhotoIv)
    }
}
