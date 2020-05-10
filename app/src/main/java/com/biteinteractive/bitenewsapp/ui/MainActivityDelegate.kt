package com.biteinteractive.bitenewsapp.ui

import androidx.appcompat.widget.Toolbar


interface MainActivityDelegate {
    fun setupToolbar(toolbar: Toolbar, titleResId: Int, backEnabled: Boolean)
}