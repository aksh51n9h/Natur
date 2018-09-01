package com.aksh.dev.home.natur.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.aksh.dev.home.natur.MainActivity
import com.aksh.dev.home.natur.Drawer
import com.aksh.dev.home.natur.R
import com.aksh.dev.home.natur.data.Item
import com.aksh.dev.home.natur.data.imageslider.ImageLoadingService
import com.aksh.dev.home.natur.data.imageslider.ImageSliderAdapter
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.image_slider_layout.*
import kotlinx.android.synthetic.main.new_items_layout.*
import kotlinx.android.synthetic.main.top_items_layout.*
import ss.com.bannerslider.Slider

class Home : AppCompatActivity() {
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var imageSliderAdapter: ImageSliderAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(bar)
        /* supportActionBar?.title = "  Natur"
         supportActionBar?.setDisplayUseLogoEnabled(true)
         supportActionBar?.setLogo(getDrawable(R.drawable.ic_black_willow))

         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
             header.setTitleTextColor(getColor(R.color.colorPrimary))
         }*/

        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        bar.setNavigationOnClickListener {
            val profileBottomSheet = Drawer.newInstance()
            profileBottomSheet.show(supportFragmentManager, "profile_bottom_drawer")
        }

        Slider.init(ImageLoadingService(this))

        val newItemListAdapter = NewItemListAdapter(this)
        val topItemListAdapter = TopItemListAdapter(this)

        homeViewModel.newItems.observe(this, Observer<List<Item>> {
            newItemListAdapter.newItems = it
            topItemListAdapter.topItems = it
            imageSliderAdapter = ImageSliderAdapter(it)
            imageSlider.setAdapter(imageSliderAdapter)
        })

        newItemList.apply {
            adapter = newItemListAdapter
            layoutManager = LinearLayoutManager(this@Home, LinearLayoutManager.HORIZONTAL, false)
        }

        topItemList.apply {
            adapter = newItemListAdapter
            layoutManager = LinearLayoutManager(this@Home, LinearLayoutManager.HORIZONTAL, false)
        }

        homeViewModel.getData()

        /*imageSlider.setOnSlideClickListener {
            val item = imageSliderAdapter.getItem(it)
            val intent = Intent(this, ViewItem::class.java)
            intent.putExtra("contentInfo", item.toArray())
            startActivity(intent)
        }*/

        viewAllNewItems.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home, menu)
        return true
    }

    override fun onBackPressed() {
        finishAffinity()
    }
}
