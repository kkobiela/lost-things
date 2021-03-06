package com.lostthings.app.items

import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import com.lostthings.R
import com.lostthings.app.add.AddItemActivity
import com.lostthings.app.base.BaseActivity
import com.lostthings.app.details.DetailsActivity
import com.lostthings.app.profile.ProfileActivity
import com.lostthings.domain.Item
import kotlinx.android.synthetic.main.activity_items.*

class ItemsActivity : BaseActivity() {

    private lateinit var adapter: ItemAdapter
    private lateinit var items: List<Item>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items)
        setupItemsRv()
        loadingDialog.show()
        setupBottomNavigation()
    }

    override fun onStart() {
        super.onStart()
        getItems()
    }

    private fun getItems() {
        Log.d("ItemsActivity", "Getting items")
        repository.getItems({
            Log.d("ItemsActivity", "Getting succeed")
            items = it.filterNot { item -> item.isReturned }.reversed()
            adapter.setData(items)
            loadingDialog.dismiss()
        }, {
            Log.d("ItemsActivity", "Getting failed")
            loadingDialog.dismiss()
        })
    }

    private fun setupBottomNavigation() {
        bottomNavigation.selectedItemId = R.id.bottom_navigation_items
        bottomNavigation.setOnNavigationItemSelectedListener {
            return@setOnNavigationItemSelectedListener when (it.itemId) {
                R.id.bottom_navigation_items -> false
                R.id.bottom_navigation_add -> {
                    startActivityWithoutStack(Intent(this, AddItemActivity::class.java))
                    false
                }
                R.id.bottom_navigation_profile -> {
                    startActivityWithoutStack(Intent(this, ProfileActivity::class.java))
                    false
                }
                else -> false
            }
        }
    }

    private fun setupItemsRv() {
        adapter = ItemAdapter { _, item ->
            val intent = Intent(this, DetailsActivity::class.java).apply { putExtra("item", item) }
            startActivity(intent)
        }
        itemsItemsRv.layoutManager = GridLayoutManager(this, calculateNumberOfColumns())
        itemsItemsRv.adapter = adapter
    }

    private fun calculateNumberOfColumns(): Int {
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.widthPixels / COLUMN_WIDTH_DIVIDER
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(com.lostthings.R.menu.items_menu, menu)
        val myActionMenuItem = menu.findItem(R.id.action_search)
        val searchView = myActionMenuItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (!searchView.isIconified) {
                    searchView.isIconified = true
                }
                myActionMenuItem.collapseActionView()
                return false
            }

            override fun onQueryTextChange(text: String): Boolean {
                adapter.setData(items.filter { it.contains(text) })
                return false
            }
        })
        return true
    }

    companion object {
        private const val COLUMN_WIDTH_DIVIDER = 500
    }
}
