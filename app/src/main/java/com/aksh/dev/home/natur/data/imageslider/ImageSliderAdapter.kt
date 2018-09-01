package com.aksh.dev.home.natur.data.imageslider

import com.aksh.dev.home.natur.data.Item
import ss.com.bannerslider.adapters.SliderAdapter
import ss.com.bannerslider.viewholder.ImageSlideViewHolder
import java.util.*


class ImageSliderAdapter(private var allItems: List<Item>) : SliderAdapter() {

    var list = listOf("https://us-east-1.tchyn.io/snopes-production/uploads/2017/06/Dieffenbachia_amoena_poison_fb.jpg?resize=865%2C452",
            "https://atmedia.imgix.net/dc900abc1bca7705f8aa53d22e34463b87bc4641?auto=format&q=45&w=600.0&h=750.0&fit=max&cs=strip",
            "https://cdn.shopify.com/s/files/1/2083/6855/products/IMGP8146.jpg?v=1499122170")

    override fun getItemCount(): Int {
        return 3
    }

    override fun onBindImageSlide(position: Int, viewHolder: ImageSlideViewHolder) {
        viewHolder.bindImageSlide(list[position])
    }

    fun getItem(position: Int): Item = allItems[position]
}