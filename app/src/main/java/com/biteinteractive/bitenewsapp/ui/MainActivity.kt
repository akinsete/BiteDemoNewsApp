package com.biteinteractive.bitenewsapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.biteinteractive.bitenewsapp.R

class MainActivity : AppCompatActivity(), MainActivityDelegate {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun setupToolbar(toolbar: Toolbar, titleResId: Int, backEnabled: Boolean) {
        setSupportActionBar(toolbar)
        supportActionBar?.setTitle(titleResId)
        supportActionBar?.setDisplayHomeAsUpEnabled(backEnabled)
    }

}
