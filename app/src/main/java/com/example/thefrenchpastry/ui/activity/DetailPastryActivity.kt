package com.example.thefrenchpastry.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.thefrenchpastry.R

class DetailPastryActivity : AppCompatActivity() {
    companion object{
        const val ID = "ID"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_pastry)
    }
}