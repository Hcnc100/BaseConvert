package com.d34th.nullpointer.baseconvert.ui.screens.convert.componets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset


@OptIn(ExperimentalPagerApi::class)
@Composable
fun TopAppBarTabs(
    pagerState: PagerState,
    modifier: Modifier = Modifier,
    actionChangePage: (Int) -> Unit
) {
    Surface(
        modifier = modifier,
        contentColor = Color.White,
        color = MaterialTheme.colors.primary
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Convert Base",
                modifier = Modifier.padding(vertical = 10.dp),
                style = MaterialTheme.typography.h6
            )
            Tabs(pagerState = pagerState, actionChangePage = actionChangePage)
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun Tabs(
    pagerState: PagerState,
    actionChangePage: (Int) -> Unit
) {
    val list = listOf(
        "Basic Base",
        "All Base"
    )
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        contentColor = Color.White,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                height = 2.dp,
                color = Color.White
            )
        }
    ) {
        list.forEachIndexed { index, title ->
            Tab(
                text = {
                    Text(
                        text = title,
                        color = if (pagerState.currentPage == index) Color.White else Color.LightGray
                    )
                },
                selected = pagerState.currentPage == index,
                onClick = { actionChangePage(index) }
            )
        }
    }
}
