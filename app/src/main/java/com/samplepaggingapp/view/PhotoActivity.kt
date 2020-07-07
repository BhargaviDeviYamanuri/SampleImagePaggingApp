package com.samplepaggingapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.samplepaggingapp.R

class PhotoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
    }
}