package com.d34th.nullpointer.baseconvert.ui.screens.explanation

import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/** Renders the lightweight formula notation used by the explanation cards. */
@Composable
fun FormulaText(formula: String, modifier: Modifier = Modifier) {
    val annotated = buildAnnotatedString {
        var index = 0
        while (index < formula.length) {
            when (formula[index]) {
                '₍' -> {
                    val end = formula.indexOf('₎', index + 1)
                    if (end >= 0) {
                        withStyle(SpanStyle(fontSize = 12.sp, baselineShift = BaselineShift.Subscript, fontWeight = FontWeight.Bold)) {
                            append(formula.substring(index + 1, end))
                        }
                        index = end + 1
                    } else {
                        append(formula[index++])
                    }
                }
                '⁽' -> {
                    val end = formula.indexOf('⁾', index + 1)
                    if (end >= 0) {
                        withStyle(SpanStyle(fontSize = 12.sp, baselineShift = BaselineShift.Superscript)) {
                            append(formula.substring(index + 1, end))
                        }
                        index = end + 1
                    } else {
                        append(formula[index++])
                    }
                }
                '⟦' -> {
                    val end = formula.indexOf('⟧', index + 1)
                    if (end >= 0) {
                        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                            append(formula.substring(index + 1, end))
                        }
                        index = end + 1
                    } else {
                        append(formula[index++])
                    }
                }
                else -> append(formula[index++])
            }
        }
    }
    BasicText(
        annotated,
        modifier = modifier.background(Color.LightGray.copy(alpha = .35f)).padding(12.dp),
        style = androidx.compose.ui.text.TextStyle(fontWeight = FontWeight.Normal)
    )
}
