package com.biteinteractive.bitenewsapp.util

import android.widget.ImageView
import com.biteinteractive.bitenewsapp.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadImage(uri: String) {
    val options = RequestOptions()
        .error(R.mipmap.ic_launcher)
    Glide.with(this.context)
        .setDefaultRequestOptions(options)
        .load(uri)
        .into(this)
}