package com.caneru.news.base.models

data class NewsDetail(
    val rssDataID: String,
    val title: String,
    val description: String,
    val imageUrl: String,
    val link: String,
    val newsChannelName: String,
    val channelCategoryName: String
)
