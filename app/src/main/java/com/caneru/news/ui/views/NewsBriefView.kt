package com.caneru.news.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.caneru.news.base.models.NewsBrief
import com.caneru.news.ui.utils.DateFormatter

@Composable
fun NewsBriefView(
    modifier: Modifier = Modifier,
    data: NewsBrief
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
            Text(modifier = Modifier.fillMaxWidth(), text = data.title)
            Image(
                painter = rememberAsyncImagePainter(model = data.imageUrl),
                contentDescription = "News Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(256.dp)
            )
            Text(modifier = Modifier.fillMaxWidth(), text = DateFormatter.format(data.pubDate), textAlign = TextAlign.End)
        }
    }
}

@Preview
@Composable
fun PreviewNewsBriefView() {
    NewsBriefView(
        data = NewsBrief(
            "8943C49B-AFCE-49EB-A8B0-E6245A05CD2A",
            "Okul katliamı faili yüzünden Trans bireyler hedefte Mustafa Kemal Erdemol yazdı... @mkerdemol",
            "https://static.bundle.app/news/pc-214499b38a5d23855f7d47926d2d15be.jpg",
            "2023-03-30T02:20:23.320"
        )
    )
}