package com.lostthings.app.profile

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import androidx.recyclerview.widget.GridLayoutManager
import com.lostthings.R
import com.lostthings.app.add.AddItemActivity
import com.lostthings.app.base.BaseActivity
import com.lostthings.app.items.ItemsActivity
import com.lostthings.domain.Item
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : BaseActivity() {

    private lateinit var adapter: ProfileItemAdapter
    private lateinit var items: List<Item>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        loadingDialog.show()
        getItems()
        setupItemsRv()
        setupBottomNavigation()
        setupProfile()
    }

    private fun getItems() {
        repository.getItems({
            items = it.filter { it.userId == repository.profileEmail }.reversed()
            adapter.setData(items)
            loadingDialog.dismiss()
        }, {
            loadingDialog.dismiss()
        })
    }

    private fun setupItemsRv() {
        adapter = ProfileItemAdapter { _, item ->
            repository.returnItem(item) { getItems() }
        }
        profileItemsRv.layoutManager = GridLayoutManager(this, calculateNumberOfColumns())
        profileItemsRv.adapter = adapter
    }

    private fun calculateNumberOfColumns(): Int {
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.widthPixels / COLUMN_WIDTH_DIVIDER
    }

    private fun setupProfile() {
        Picasso.get()
            .load(repository.profilePhotoUrl)
            .into(object : Target {
                override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                    val drawable = RoundedBitmapDrawableFactory.create(resources, bitmap)
                    drawable.isCircular = true
                    profilePhotoIv.setImageDrawable(drawable)
                }

                override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {}

                override fun onPrepareLoad(placeHolderDrawable: Drawable?) {}
            })
        profileNameTv.text = repository.profileName
    }

    private fun setupBottomNavigation() {
        bottomNavigation.selectedItemId = R.id.bottom_navigation_profile
        bottomNavigation.setOnNavigationItemSelectedListener {
            return@setOnNavigationItemSelectedListener when (it.itemId) {
                R.id.bottom_navigation_items -> {
                    startActivityWithoutStack(Intent(this, ItemsActivity::class.java))
                    false
                }
                R.id.bottom_navigation_add -> {
                    startActivityWithoutStack(Intent(this, AddItemActivity::class.java))
                    false
                }
                R.id.bottom_navigation_profile -> false
                else -> false
            }
        }
    }

    companion object {
        private const val COLUMN_WIDTH_DIVIDER = 500
    }
}
