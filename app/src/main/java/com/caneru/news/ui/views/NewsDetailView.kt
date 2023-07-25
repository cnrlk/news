package com.caneru.news.ui.views

import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.rememberAsyncImagePainter
import com.caneru.news.R
import com.caneru.news.base.models.NewsDetail

@Composable
fun NewsDetailView(
    modifier: Modifier,
    data: NewsDetail
) {
    Card(modifier = modifier, elevation = 8.dp) {
        Column(
            modifier = Modifier
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(size = 6.dp)
                )
                .padding(16.dp)
        ) {
            TabLayoutContainer(
                tabTitles = listOf(
                    stringResource(id = R.string.web_view),
                    stringResource(id = R.string.reader_view)
                )
            ) {
                when (it) {
                    stringResource(id = R.string.web_view) -> {
                        MyWebView(modifier = Modifier, link = data.link)
                    }

                    stringResource(id = R.string.reader_view) -> {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = data.title,
                            fontStyle = FontStyle.Normal,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = TextUnit(value = 17f, type = TextUnitType.Sp)
                        )
                        Text(modifier = Modifier.fillMaxWidth(), text = data.description)
                        Image(
                            painter = rememberAsyncImagePainter(model = data.imageUrl),
                            contentDescription = "News Image",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(256.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun MyWebView(
    modifier: Modifier,
    link: String
) {
    Box {
        AndroidView(
            modifier = modifier,
            factory = { context ->
                WebView(context).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    this.webViewClient = object : WebViewClient() {
                        override fun shouldOverrideUrlLoading(
                            view: WebView?,
                            request: WebResourceRequest?
                        ): Boolean {
                            return false
                        }
                    }
                    loadUrl(link)
                }
            }, update = {
                it.loadUrl(link)
            })
    }
}