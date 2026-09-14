package com.d34th.nullpointer.baseconvert.ui.screens.convert

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.d34th.nullpointer.baseconvert.models.WorkConvert
import com.d34th.nullpointer.baseconvert.presentation.ConvertViewModel
import com.d34th.nullpointer.baseconvert.ui.screens.convert.componets.ListBaseConvert
import com.d34th.nullpointer.baseconvert.ui.screens.convert.componets.Tabs
import com.d34th.nullpointer.baseconvert.ui.states.ConvertScreenState
import com.d34th.nullpointer.baseconvert.ui.states.rememberConvertScreenState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph

@RootNavGraph(start = true)
@Destination
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

@Composable
fun ConvertScreen(
    pagerState: PagerState,
    listAllBase: List<WorkConvert>,
    listBasicBase: List<WorkConvert>,
    actionChangePage: (Int) -> Unit,
    triggerBaseConvert: (String, WorkConvert) -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Tabs(pagerState = pagerState, actionChangePage = actionChangePage)
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            when (page) {
                0 -> ListBaseConvert(
                    listBase = listBasicBase,
                    triggerBaseConvert = triggerBaseConvert
                )
                1 -> ListBaseConvert(
                    listBase = listAllBase,
                    triggerBaseConvert = triggerBaseConvert
                )
            }
        }
    }
}
