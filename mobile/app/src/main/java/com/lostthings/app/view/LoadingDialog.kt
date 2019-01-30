package com.lostthings.app.view

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.lostthings.R

class LoadingDialog(context: Context) : Dialog(context) {

    init {
        window.setBackgroundDrawableResource(android.R.color.transparent)
        setCancelable(false)
        setCanceledOnTouchOutside(false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.loading_dialog)
    }
}