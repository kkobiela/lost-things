package com.lostthings.app.items

import android.os.Bundle
import android.util.DisplayMetrics
import androidx.recyclerview.widget.GridLayoutManager
import com.lostthings.R
import com.lostthings.app.base.BaseActivity
import kotlinx.android.synthetic.main.activity_items.*

class ItemsActivity : BaseActivity() {

    private lateinit var adapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items)
        setupItemsRv()
        repository.getItems({ adapter.setData(it) }, { it.toString() })
    }

    private fun setupItemsRv() {
        adapter = ItemAdapter {}
        itemsItemsRv.layoutManager = GridLayoutManager(this, calculateNumberOfColumns())
        itemsItemsRv.adapter = adapter
    }

    private fun calculateNumberOfColumns(): Int {
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.widthPixels / COLUMN_WIDTH_DIVIDER
    }

    companion object {
        private const val COLUMN_WIDTH_DIVIDER = 500
    }
}
