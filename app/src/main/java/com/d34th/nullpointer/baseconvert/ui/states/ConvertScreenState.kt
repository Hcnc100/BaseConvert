package com.d34th.nullpointer.baseconvert.ui.states

import android.content.Context
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
class ConvertScreenState(
    context: Context,
    val pagerState: PagerState,
    scaffoldState: ScaffoldState,
    private val scope: CoroutineScope
) : SimpleScreenState(context, scaffoldState) {

    fun changePage(page: Int) = scope.launch {
        pagerState.animateScrollToPage(page)
    }
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun rememberConvertScreenState(
    context: Context=LocalContext.current,
    scope: CoroutineScope= rememberCoroutineScope(),
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    pagerState: PagerState= rememberPagerState()
)= remember(scope,pagerState) {
    ConvertScreenState(
        scope = scope,
        context = context,
        pagerState = pagerState,
        scaffoldState = scaffoldState
    )
}