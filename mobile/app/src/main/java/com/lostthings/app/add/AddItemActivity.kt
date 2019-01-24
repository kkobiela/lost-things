package com.lostthings.app.add


import android.Manifest
import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Base64
import android.widget.Toast
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import com.lostthings.R
import com.lostthings.app.base.BaseActivity
import com.lostthings.domain.ItemToAdd
import kotlinx.android.synthetic.main.activity_add_item.*
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.*


class AddItemActivity : BaseActivity() {

    private val calendar = Calendar.getInstance()
    private var base64Photo: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)
        title = "Dodaj znalezioną rzecz"
        setupDatePicker()
        setupAddItem()
        setupAddPhoto()
    }

    private fun setupAddPhoto() {
        addPhotoFab.setOnClickListener {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(
                    arrayOf(Manifest.permission.CAMERA),
                    CAMERA_PERMISSION_CODE
                )
            } else {
                val cameraIntent = Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val cameraIntent = Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE)
            } else {
                Toast.makeText(this, "Nie dodasz zdjęcia :c", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val photo = data?.extras?.get("data") as Bitmap
            base64Photo = bitmapToBase64String(photo)
            val drawable = RoundedBitmapDrawableFactory.create(resources, photo)
            drawable.isCircular = true
            photoIv.setImageDrawable(drawable)
        }
    }

    private fun setupAddItem() {
        addFab.setOnClickListener {
            if (contactTv.text.isEmpty()
                || descriptionTv.text.isEmpty()
                || nameTv.text.isEmpty()
                || locationTv.text.isEmpty()
                || photoIv.drawable == null
            ) {
                Toast.makeText(this, "Uzupełnij wszystkie pola i dodaj zdjęcie!", Toast.LENGTH_LONG).show()
            } else {
                val itemToAdd = ItemToAdd(
                    contactTv.text.toString(),
                    descriptionTv.text.toString(),
                    locationTv.text.toString(),
                    nameTv.text.toString(),
                    base64Photo ?: "null",
                    repository.profileEmail
                )
                repository.addItem(
                    itemToAdd, { finish() }, {
                        Toast.makeText(this, "Coś poszło nie tak, spróbuj ponownie", Toast.LENGTH_LONG).show()
                    })
            }
        }
    }

    fun drawableToBitmap(drawable: Drawable): Bitmap {
        val bitmap = if (drawable.intrinsicWidth <= 0 || drawable.intrinsicHeight <= 0) {
            Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888) // Single color bitmap will be created of 1x1 pixel
        } else {
            Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
        }
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }

    fun drawableToBase64String(drawable: Drawable): String {
        val bitmap = drawableToBitmap(drawable)
        return bitmapToBase64String(bitmap)
    }

    fun bitmapToByteArray(bitmap: Bitmap): ByteArray {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        return outputStream.toByteArray()
    }

    fun bitmapToBase64String(bitmap: Bitmap): String {
        val bytes = bitmapToByteArray(bitmap)
        return Base64.encodeToString(bytes, Base64.DEFAULT)
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

    companion object {
        private const val CAMERA_PERMISSION_CODE = 123
        private const val CAMERA_REQUEST_CODE = 321
    }
}
