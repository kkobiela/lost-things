package com.lostthings.app.base

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lostthings.app.view.LoadingDialog
import com.lostthings.data.repository.ItemRepository
import org.koin.android.ext.android.inject

abstract class BaseActivity : AppCompatActivity() {

    protected val repository: ItemRepository by inject()

    val loadingDialog by lazy { LoadingDialog(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    fun startActivityWithoutStack(intent: Intent) {
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
}
