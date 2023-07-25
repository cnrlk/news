package com.caneru.news.base.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NewsBrief(
    @PrimaryKey val rssDataID: String,
    val title: String,
    val imageUrl: String,
    val pubDate: String
)