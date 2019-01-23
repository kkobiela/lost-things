package com.lostthings.app.profile

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lostthings.R
import com.lostthings.app.util.extension.inflate
import com.lostthings.domain.Item

class ProfileItemAdapter(private val onItemClick: (View, Item) -> Unit) : RecyclerView.Adapter<ProfileItemViewHolder>() {

    var items = mutableListOf<Item>()
        private set

    fun setData(newItems: List<Item>) {
        with(items) {
            clear()
            addAll(newItems)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileItemViewHolder {
        val view = parent.inflate(R.layout.item_item)
        return ProfileItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProfileItemViewHolder, position: Int) {
        val card = items[position]
        holder.bind(card, onItemClick)
    }

    override fun getItemCount() = items.size
}
