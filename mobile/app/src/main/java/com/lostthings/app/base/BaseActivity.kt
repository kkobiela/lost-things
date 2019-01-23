package com.lostthings.app.base

import androidx.appcompat.app.AppCompatActivity
import com.lostthings.data.repository.ItemRepository
import org.koin.android.ext.android.inject

abstract class BaseActivity : AppCompatActivity() {

    protected val repository: ItemRepository by inject()

    override fun onStop() {
        super.onStop()
        repository.disposables.clear()
    }
}
