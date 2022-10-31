package com.d34th.nullpointer.baseconvert.ui.screens.convert

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.d34th.nullpointer.baseconvert.models.WorkConvert
import com.d34th.nullpointer.baseconvert.presentation.ConvertViewModel
import com.d34th.nullpointer.baseconvert.ui.screens.convert.componets.ListBaseConvert
import com.d34th.nullpointer.baseconvert.ui.screens.convert.componets.Tabs
import com.d34th.nullpointer.baseconvert.ui.states.ConvertScreenState
import com.d34th.nullpointer.baseconvert.ui.states.rememberConvertScreenState
import com.google.accompanist.pager.*
import com.ramcosta.composedestinations.annotation.Destination

@Destination(start = true)
@OptIn(ExperimentalPagerApi::class)
@Composable
fun ConvertScreen(
    convertViewModel: ConvertViewModel = hiltViewModel(),
    convertScreenState: ConvertScreenState = rememberConvertScreenState()
) {
    ConvertScreen(
        listBasicBase = convertViewModel.basicBase,
        pagerState = convertScreenState.pagerState,
        listAllBase = convertViewModel.listBaseConvert,
        actionChangePage = convertScreenState::changePage,
        triggerBaseConvert = convertViewModel::triggerConvert
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ConvertScreen(
    pagerState: PagerState,
    listAllBase: List<WorkConvert>,
    listBasicBase: List<WorkConvert>,
    actionChangePage: (Int) -> Unit,
    triggerBaseConvert: (String, WorkConvert) -> Unit,
) {
    Scaffold(
        topBar = { Tabs(pagerState = pagerState, actionChangePage = actionChangePage) }
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
