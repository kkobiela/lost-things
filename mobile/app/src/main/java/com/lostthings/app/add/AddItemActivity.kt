package com.lostthings.app.add


import android.app.DatePickerDialog
import android.os.Bundle
import com.lostthings.R
import com.lostthings.app.base.BaseActivity
import kotlinx.android.synthetic.main.activity_add_item.*
import java.text.SimpleDateFormat
import java.util.*

class AddItemActivity : BaseActivity() {

    private val calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)
        title = "Dodaj znalezioną rzecz"
        setupDatePicker()
    }

    private fun setupDatePicker() {
        foundDateTv.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) return@setOnFocusChangeListener
            val listener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateTv()
            }
            DatePickerDialog(
                this, listener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }

    private fun updateDateTv() {
        val format = "dd.MM.yyyy"
        val formatter = SimpleDateFormat(format, Locale.getDefault())
        foundDateTv.setText(formatter.format(calendar.time))
    }
}
