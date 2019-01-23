package com.lostthings.app.items

import android.os.Bundle
import com.lostthings.R
import com.lostthings.app.base.BaseActivity
import kotlinx.android.synthetic.main.activity_items.*

class ItemsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items)
        repository.getItems({ items.text = it.component1().name }, { it.toString() })
    }
}
