package com.aksh.dev.home.natur

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.aksh.dev.home.natur.data.Item
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_view_item.*


class ViewItem : AppCompatActivity() {
    private lateinit var item: Item
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_item)
        setSupportActionBar(toolbar)
        supportActionBar?.title = ""

        if (intent.extras != null) {
            val content = intent.extras.getStringArray("contentInfo")
            item = Item(content[0].toInt(),
                    content[1],
                    content[2],
                    content[3],
                    content[4]
            )
        }

        viewItemTitle.text = item.name
        viewItemSubtitle.text = item.category
        viewItemDescription.text = item.description

        val requestOptions = RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)

        Glide.with(this).load(item.photoUrl)
                .apply(requestOptions)
                .into(app_bar_image)

        val list = listOf("http://hd.wallpaperswide.com/thumbs/high_tech_earth-t2.jpg",
                "https://images.pexels.com/photos/34950/pexels-photo.jpg?auto=compress&cs=tinysrgb&h=350",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRZjVLtJDgMTOExfMHsTZuT4G5cAmaRT0N0vnoVbblrTTKkwSOb",
                "http://hd.wallpaperswide.com/thumbs/spider_man_selfie-t2.jpg"
        ).shuffled()

        val listAdapter = MoreImageListAdapter(this)
        listAdapter.allImages = list

        moreImages.apply {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(this@ViewItem, LinearLayoutManager.HORIZONTAL, false)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.close, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.hideInfo -> onBackPressed()
        }
        return true
    }
}