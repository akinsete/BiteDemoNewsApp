package com.biteinteractive.bitenewsapp.ui

import androidx.appcompat.widget.Toolbar


interface MainActivityDelegate {
    fun setupToolbar(toolbar: Toolbar, titleResId: Int?, titleString: String?, backEnabled: Boolean)
}