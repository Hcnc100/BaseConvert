package com.d34th.nullpointer.baseconvert.ui.screens.convert

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.d34th.nullpointer.baseconvert.models.WorkConvert
import com.d34th.nullpointer.baseconvert.presentation.ConvertViewModel
import com.d34th.nullpointer.baseconvert.ui.screens.convert.componets.ListBaseConvert
import com.d34th.nullpointer.baseconvert.ui.screens.convert.componets.TopAppBarTabs
import com.d34th.nullpointer.baseconvert.ui.states.ConvertScreenState
import com.d34th.nullpointer.baseconvert.ui.states.rememberConvertScreenState
import com.google.accompanist.pager.*

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ConvertScreen(
    convertScreenState: ConvertScreenState = rememberConvertScreenState(),
    convertViewModel: ConvertViewModel = hiltViewModel()
) {
    ConvertScreen(
        listBasicBase = convertViewModel.basicBase,
        pagerState = convertScreenState.pagerState,
        actionChangePage = convertScreenState::changePage,
        listAllBase = convertViewModel.listBaseConvert,
        triggerBaseConvert = convertViewModel::triggerConvert
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ConvertScreen(
    pagerState: PagerState,
    actionChangePage: (Int) -> Unit,
    listAllBase: List<WorkConvert>,
    listBasicBase: List<WorkConvert>,
    triggerBaseConvert: (String, WorkConvert) -> Unit
) {
    Scaffold(
        topBar = { TopAppBarTabs(pagerState = pagerState, actionChangePage = actionChangePage) }
    ) {
        HorizontalPager(state = pagerState, count = 2) { page ->
            when (page) {
                0 -> ListBaseConvert(
                    listBase = listBasicBase,
                    modifier = Modifier.padding(it),
                    triggerBaseConvert = triggerBaseConvert
                )
                1 -> ListBaseConvert(
                    listBase = listAllBase,
                    modifier = Modifier.padding(it),
                    triggerBaseConvert = triggerBaseConvert
                )
            }
        }
    }
}
