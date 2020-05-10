package com.biteinteractive.bitenewsapp.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ArticleItem(
    @SerializedName("section") val section: String? = null,
    @SerializedName("subsection") val subsection: String? = null,
    @SerializedName("title") val title: String,
    @SerializedName("abstract") val abstract: String? = null,
    @SerializedName("multimedia") val multimedia: List<Multimedia>? = null,
    @SerializedName("short_url") val short_url: String? = null
) : Serializable {
    fun getThumbnail(): String? {
        return multimedia.let {
            it?.get(0)
        }?.url
    }
}