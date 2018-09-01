package com.aksh.dev.home.natur

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.seller_form.*

class Seller : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.seller_form)
        supportActionBar?.title = "New Seller"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val cities = resources.getStringArray(R.array.cities)
        ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cities).also { adapter ->
            sellerCity.setAdapter(adapter)
        }

        val states = resources.getStringArray(R.array.states)
        ArrayAdapter<CharSequence>(this, android.R.layout.simple_list_item_1, states).also { adapter ->
            sellerState.setAdapter(adapter)
        }

    }

    private fun isCorrectLength(text: String) = text.length > 10

    private fun isCorrectEmail(email: String) = email.contains('@')

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.create_seller, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()

            R.id.createSeller -> {
                val fullName = sellerFullName.text.toString()
                val phoneNumber = sellerPhoneNumber.text.toString()
                val address = sellerAddress.text.toString()
                val city = sellerCity.text.toString()
//                val state = sellerState.
                val pincode = sellerPincode.text.toString()
                val email = sellerEmail.text.toString()

                if (!isCorrectLength(phoneNumber)) {
                    sellerPhoneNumber.error = "10 digits required"
                }

                if (!isCorrectEmail(email)) {
                    sellerEmail.error = "Wrong email"
                }
            }
        }
        return true
    }
}
