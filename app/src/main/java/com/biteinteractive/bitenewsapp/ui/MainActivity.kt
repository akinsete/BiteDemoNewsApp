package com.biteinteractive.bitenewsapp.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import com.biteinteractive.bitenewsapp.R

class MainActivity : AppCompatActivity(), MainActivityDelegate {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun setupToolbar(
        toolbar: Toolbar,
        titleResId: Int?,
        titleString: String?,
        backEnabled: Boolean
    ) {
        setSupportActionBar(toolbar)
        supportActionBar?.title = (titleResId?.let { getString(it) } ?: titleString)
        supportActionBar?.setDisplayHomeAsUpEnabled(backEnabled)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> findNavController(item.itemId).popBackStack()
        }
        return super.onOptionsItemSelected(item)
    }
}
