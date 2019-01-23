package com.lostthings.app.details

import android.os.Bundle
import com.lostthings.R
import com.lostthings.app.base.BaseActivity
import com.lostthings.domain.Item
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val item = intent.extras["item"] as Item
        title = item.name
        Picasso.get()
            .load(item.thumbnail)
            .placeholder(R.drawable.ic_item_placeholder)
            .into(detailsItemIv)
    }
}
