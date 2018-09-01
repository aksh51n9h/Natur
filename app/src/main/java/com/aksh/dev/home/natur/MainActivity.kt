package com.aksh.dev.home.natur

import android.content.Intent
import android.os.Bundle
import android.util.Pair
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.aksh.dev.home.natur.data.Item
import com.google.android.material.bottomappbar.BottomAppBar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_view_item.view.*


class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private val linearLayoutManager = LinearLayoutManager(this)
    private val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
    private val listAdapter = ListAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = bottomAppBar as BottomAppBar
        setSupportActionBar(toolbar)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        viewModel.allItems.observe(this, Observer<List<Item>> {
            listAdapter.allItems = it
        })

        viewModel.getData()

        val touchListener = RecyclerTouchListener(this, allItemsContainer, object : RecyclerviewClickListener {
            override fun onClick(view: View, position: Int) {
                view.itemImage.setOnClickListener {
                    showMoreDetails(position, view)
                }
            }

            override fun onLongClick(view: View, position: Int) {}
        })

        allItemsContainer.apply {
            layoutManager = staggeredGridLayoutManager
            adapter = listAdapter
            addOnItemTouchListener(touchListener)
        }

        fab.setOnClickListener {

        }
    }

    private fun changeViewLayout() {
        viewModel.gridLayout = !viewModel.gridLayout
        listAdapter.gridLayout = viewModel.gridLayout

        allItemsContainer.apply {
            layoutManager = if (viewModel.gridLayout) staggeredGridLayoutManager else linearLayoutManager
        }
    }

    private fun showMoreDetails(position: Int, view: View) {
        val item = listAdapter.getItem(position)
        val sharedTitle = Pair<View, String>(view.title, "shared_title")
        val sharedSubtitle = Pair<View, String>(view.subtitle, "shared_subtitle")
        val sharedImage = Pair<View, String>(view.itemImage, "shared_image")
        val sharedDescription = Pair<View, String>(view.description, "shared_description")
        val viewItem = Intent(this, ViewItem::class.java)
        viewItem.putExtra("contentInfo", item.toArray())

        with(viewModel.gridLayout) {
            if (!this) {
                val options = android.app.ActivityOptions.makeSceneTransitionAnimation(this@MainActivity,
                        sharedSubtitle, sharedTitle, sharedImage, sharedDescription
                )
                startActivity(viewItem, options.toBundle())
            } else {
                val options = android.app.ActivityOptions.makeSceneTransitionAnimation(this@MainActivity,
                        sharedSubtitle, sharedTitle
                )
                startActivity(viewItem, options.toBundle())
            }
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        invalidateOptionsMenu()
        with(viewModel.gridLayout) {
            menu?.findItem(R.id.gridViewToggle)?.isVisible = !this
            menu?.findItem(R.id.listViewToggle)?.isVisible = this
        }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.gridViewToggle, R.id.listViewToggle -> changeViewLayout()
        }
        return true
    }
}
