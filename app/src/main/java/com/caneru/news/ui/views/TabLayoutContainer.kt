package com.caneru.news.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

@Composable
fun TabLayoutContainer(
    modifier: Modifier = Modifier,
    tabTitles: List<String>,
    elevation: Dp = 0.dp,
    selectedTab: Int = 0,
    content: @Composable (String) -> Unit
) {
    var selectedTabIndex by rememberSaveable { mutableIntStateOf(selectedTab) }

    Column(
        modifier = Modifier.wrapContentSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier.wrapContentWidth(),
            elevation = elevation,
            shape = RectangleShape
        ) {
            TabRow(
                modifier = modifier.padding(2.dp),
                selectedTabIndex = selectedTabIndex,
                backgroundColor = Color.White,
                divider = {},
                indicator = { tabs ->
                    Box(
                        Modifier
                            .tabIndicatorOffset(tabs[selectedTabIndex])
                            .fillMaxSize()
                            .background(
                                color = Color.Gray,
                                shape = RoundedCornerShape(4.dp)
                            )
                            .zIndex(-1f),
                    )
                }
            ) {
                tabTitles.forEachIndexed { index, text ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        selectedContentColor = Color.DarkGray,
                        unselectedContentColor = Color.Gray,
                        modifier = Modifier.height(IntrinsicSize.Min)
                    ) {
                        Box(modifier = Modifier.wrapContentSize()) {
                            Text(
                                text = text,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .align(
                                        Alignment.Center
                                    )
                            )
                            if (index < tabTitles.size - 1) {
                                Divider(
                                    color = Color.Gray, modifier = Modifier
                                        .fillMaxHeight()
                                        .width(Dp.Hairline)
                                        .align(Alignment.CenterEnd)
                                )
                            }
                        }
                    }
                }

            }
        }
        content(tabTitles[selectedTabIndex])
    }
}