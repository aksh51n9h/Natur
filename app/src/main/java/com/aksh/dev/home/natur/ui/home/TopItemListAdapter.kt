package com.aksh.dev.home.natur.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aksh.dev.home.natur.R
import com.aksh.dev.home.natur.data.Item
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.image_item.view.*

class TopItemListAdapter(private val context: Context) : RecyclerView.Adapter<TopItemListAdapter.NewItemViewHolder>() {
    var topItems: List<Item>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewItemViewHolder {
        return NewItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.image_item, parent, false))
    }

    override fun getItemCount() = topItems?.size ?: 0

    override fun onBindViewHolder(holder: TopItemListAdapter.NewItemViewHolder, position: Int) {
        val cItem = topItems?.get(position) ?: Item()

        Glide.with(context).load(cItem.photoUrl).into(holder.image)
        holder.itemDesc.text = cItem.name
    }

    inner class NewItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image = view.imageHolder
        val itemDesc = view.itemName
    }
}