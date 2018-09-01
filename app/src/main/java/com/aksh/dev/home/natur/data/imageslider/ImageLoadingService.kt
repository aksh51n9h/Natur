package com.aksh.dev.home.natur.data.imageslider

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import ss.com.bannerslider.ImageLoadingService


class ImageLoadingService(var context: Context) : ImageLoadingService {
    override fun loadImage(url: String, imageView: ImageView) {
        Glide.with(context).load(url).apply(RequestOptions().centerCrop()).into(imageView)
    }

    override fun loadImage(resource: Int, imageView: ImageView) {
        Glide.with(context).load(resource).apply(RequestOptions().centerCrop()).into(imageView)
    }

    override fun loadImage(url: String, placeHolder: Int, errorDrawable: Int, imageView: ImageView) {
        Glide.with(context).load(url).apply(RequestOptions().centerCrop()).into(imageView)
    }
}