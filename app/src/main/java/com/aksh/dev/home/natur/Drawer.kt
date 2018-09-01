package com.aksh.dev.home.natur

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aksh.dev.home.natur.ui.home.Home
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.drawer_bottom_sheet.*
import kotlinx.android.synthetic.main.drawer_bottom_sheet.view.*
import kotlinx.android.synthetic.main.profile_sheet_layout.*
import kotlinx.android.synthetic.main.profile_sheet_layout.view.*

class Drawer : BottomSheetDialogFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.drawer_bottom_sheet, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Glide.with(view).load("https://i1.wp.com/www.winhelponline.com/blog/wp-content/uploads/2017/12/user.png?fit=256%2C256&quality=100&ssl=1")
                .apply(RequestOptions().circleCrop())
                .into(userProfilePic)

        navigationDrawer.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    startActivity(Intent(this.context, Home::class.java))
                    dismiss()
                }

                R.id.categories -> {
                    val categoriesBottomSheet = CategoriesBottomSheet.newInstance()
                    categoriesBottomSheet.show(fragmentManager, "categories_bottom_sheet")
                }

                R.id.sellOnNatur -> {
                    val sellerActivity = Intent(this.context, Seller::class.java)
                    startActivity(sellerActivity)
                    dismiss()
                }
            }
            return@setNavigationItemSelectedListener true
        }

        navigationDrawer.setCheckedItem(R.id.home)

        view.userLogIn.setOnClickListener {
            val loginBottomSheet = LoginBottomSheet.newInstance()
            loginBottomSheet.show(fragmentManager, "login_bottom_sheet")
        }

        view.appVersion.text = "Version \u2022 ${BuildConfig.VERSION_NAME}"
    }


    companion object {
        fun newInstance() = Drawer()
    }
}
