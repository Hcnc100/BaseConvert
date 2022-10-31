package com.d34th.nullpointer.baseconvert.ui.states

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import com.d34th.nullpointer.baseconvert.R
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

    fun copyValue(value: String) {
        if (value.isNotEmpty()) {
            val clipboard: ClipboardManager =
                context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("Copied Text", value)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(
                context,
                context.getString(R.string.message_text_copied),
                Toast.LENGTH_SHORT
            ).show()
        } else {
            Toast.makeText(context, context.getString(R.string.empty_text_copy), Toast.LENGTH_SHORT)
                .show()
        }

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