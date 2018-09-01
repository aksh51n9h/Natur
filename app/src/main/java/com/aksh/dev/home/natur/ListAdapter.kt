package com.aksh.dev.home.natur

import android.content.Context
import android.content.Intent
import android.util.Pair
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aksh.dev.home.natur.data.Item
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.list_view_item.view.*

class ListAdapter(private val context: Context) : RecyclerView.Adapter<ListAdapter.ListItemViewHolder>(){
    var gridLayout: Boolean = true
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var allItems: List<Item>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ListItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val contentHolder: LinearLayout = view.contentHolder
        val actionsHolder: LinearLayout = view.actionsHolder
        val image: ImageView = view.itemImage
        val title: TextView = view.title
        val subtitle: TextView = view.subtitle
        val description: TextView = view.description
        val add: MaterialButton = view.add
        val share: MaterialButton = view.share
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        return ListItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_view_item, parent, false))
    }

    override fun getItemCount(): Int = allItems?.size ?: 0

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        val cItem = allItems?.get(position) ?: Item()
        holder.title.text = cItem.name
        holder.subtitle.text = cItem.category
        holder.description.text = cItem.description
        Glide.with(context).load(cItem.photoUrl).into(holder.image)

        with(gridLayout) {
            val visible = if (this) View.GONE else View.VISIBLE
            holder.description.visibility = visible
            holder.add.visibility = visible
            holder.share.visibility = visible
        }

    }

    fun getItem(position: Int): Item = allItems?.get(position) ?: Item()
}