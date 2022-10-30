package com.d34th.nullpointer.baseconvert.ui.screens.convert

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.d34th.nullpointer.baseconvert.ui.screens.convert.componets.ListBaseConvert
import com.d34th.nullpointer.baseconvert.ui.screens.convert.componets.TopAppBarTabs
import com.d34th.nullpointer.baseconvert.ui.states.ConvertScreenState
import com.d34th.nullpointer.baseconvert.ui.states.rememberConvertScreenState
import com.google.accompanist.pager.*

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ConvertScreen(
    convertScreenState: ConvertScreenState = rememberConvertScreenState()
) {
    ConvertScreen(
        pagerState = convertScreenState.pagerState,
        actionChangePage = convertScreenState::changePage
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ConvertScreen(
    pagerState: PagerState,
    actionChangePage: (Int) -> Unit
) {
    Scaffold(
        topBar = { TopAppBarTabs(pagerState = pagerState, actionChangePage = actionChangePage) }
    ) {
        HorizontalPager(state = pagerState, count = 2) { page ->
            when (page) {
                0 -> ListBaseConvert(
                    listBase = listOf(2, 8, 10, 16),
                    modifier = Modifier.padding(it)
                )
                1 -> ListBaseConvert(
                    listBase = (2..16).toList(),
                    modifier = Modifier.padding(it)
                )
            }
        }
    }
}


@Composable
fun TabContentScreen(data: String, modifier: Modifier = Modifier) {
    // on below line we are creating a column
    Column(
        // in this column we are specifying modifier
        // and aligning it center of the screen on below lines.
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // in this column we are specifying the text
        Text(
            // on below line we are specifying the text message
            text = data,

            // on below line we are specifying the text style.
            style = MaterialTheme.typography.h5,


            // on below line we are specifying the font weight
            fontWeight = FontWeight.Bold,

            //on below line we are specifying the text alignment.
            textAlign = TextAlign.Center
        )
    }
}