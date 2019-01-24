package com.lostthings.app.details

import android.os.Bundle
import android.view.View
import com.lostthings.R
import com.lostthings.app.base.BaseActivity
import com.lostthings.domain.Item
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details.*
import java.text.SimpleDateFormat

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
        detailsName.text = item.name
        detailsDescription.text = item.description
        detailsLocation.text = item.location
        var format = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
        val date = format.parse(item.addDate)
        format = SimpleDateFormat("dd.MM.yyyy hh:mm ")
        detailsDateAdded.text = format.format(date)
        if (item.foundDate.isNullOrBlank()) {
            detailsDateFound.visibility = View.GONE
            detailsDateFoundLabel.visibility = View.GONE
        } else {
            var format = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
            val date = format.parse(item.foundDate)
            format = SimpleDateFormat("dd.MM.yyyy hh:mm ")
            detailsDateFound.text = format.format(date)
        }
        detailsContact.text = item.contact
    }
}
