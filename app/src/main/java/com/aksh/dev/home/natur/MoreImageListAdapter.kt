package com.aksh.dev.home.natur

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.recycler_view_item.view.*

class MoreImageListAdapter(private val context: Context) : RecyclerView.Adapter<MoreImageListAdapter.ListItemViewHolder>() {
    var allImages: List<String>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ListItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.moreImage
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        return ListItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item, parent, false))
    }

    override fun getItemCount(): Int = allImages?.size ?: 0

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        val cImage = allImages?.get(position) ?: ""
        Glide.with(context)
                .load(cImage)
                .transition(DrawableTransitionOptions.withCrossFade(1000))
                .into(holder.image)
    }

    fun getItem(position: Int): String = allImages?.get(position) ?: ""
}