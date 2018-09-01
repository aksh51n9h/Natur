package com.aksh.dev.home.natur

import android.view.View

interface RecyclerviewClickListener {
    fun onClick(view: View, position: Int)
    fun onLongClick(view: View, position: Int)
}
