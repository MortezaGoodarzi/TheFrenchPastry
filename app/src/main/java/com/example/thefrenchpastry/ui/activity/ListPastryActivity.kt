package com.example.thefrenchpastry.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.thefrenchpastry.R

class ListPastryActivity : AppCompatActivity() {

    companion object {
        const val ID = "ID"
        const val TYPE = "TYPE"
        const val NEW = "NEW"
        const val SPECIAL_OFFER = "SPECIAL_OFFER"
        const val RATE = "RATE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_pastry)
    }

}