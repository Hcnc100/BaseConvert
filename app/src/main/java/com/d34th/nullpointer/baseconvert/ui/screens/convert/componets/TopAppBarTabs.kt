package com.d34th.nullpointer.baseconvert.ui.screens.convert.componets

import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.background
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.material.Text
import com.d34th.nullpointer.baseconvert.R


@Composable
fun Tabs(
    pagerState: PagerState,
    actionChangePage: (Int) -> Unit
) {
    val list = listOf(
        stringResource(R.string.tab_basic_bases),
        stringResource(R.string.tab_all_bases)
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.primary)
    ) {
        list.forEachIndexed { index, title ->
            val selected = pagerState.currentPage == index
            Tab(
                modifier = Modifier
                    .weight(1f)
                    .background(
                        if (selected) MaterialTheme.colors.primaryVariant
                        else MaterialTheme.colors.primary
                    ),
                text = {
                    Text(
                        text = title,
                        color = if (selected) Color.White else Color.LightGray
                    )
                },
                selected = selected,
                onClick = { actionChangePage(index) }
            )
        }
    }
}
