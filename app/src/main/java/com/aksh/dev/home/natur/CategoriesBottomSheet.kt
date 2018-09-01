package com.aksh.dev.home.natur

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.categories_bottom_sheet.*
import kotlinx.android.synthetic.main.categories_layout.*


class CategoriesBottomSheet : BottomSheetDialogFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.categories_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        categories_view.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.commonPlants -> {
                }
            }
            return@setNavigationItemSelectedListener true
        }

        category_heading.setOnTouchListener(OnTouchListener { _, event ->
            val DRAWABLE_RIGHT = 2

            if (event.action == MotionEvent.ACTION_DOWN) {
                if (event.rawX >= category_heading.right - category_heading.compoundDrawables[DRAWABLE_RIGHT].bounds.width()) {
                    dismiss()
                    return@OnTouchListener true
                }
            }
            return@OnTouchListener false
        })
    }

    companion object {
        fun newInstance() = CategoriesBottomSheet()
    }
}
