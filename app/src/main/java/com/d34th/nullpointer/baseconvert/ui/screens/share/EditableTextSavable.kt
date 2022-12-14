package com.d34th.nullpointer.baseconvert.ui.screens.share

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.d34th.nullpointer.baseconvert.R
import com.d34th.nullpointer.baseconvert.models.WorkConvert

@Composable
fun EditableBaseSavable(
    workConvert: WorkConvert,
    modifier: Modifier = Modifier,
    triggerBaseConvert: (String, WorkConvert) -> Unit
) {

    Column(
        horizontalAlignment = Alignment.End
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .height(IntrinsicSize.Min),
        ) {
            Text(
                text = stringResource(R.string.text_base, workConvert.base),
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .clip(RoundedCornerShape(topStart = 15.dp, bottomStart = 15.dp))
                    .background(MaterialTheme.colors.primary)
                    .width(70.dp)
                    .fillMaxHeight()
                    .wrapContentHeight(),
            )

            OutlinedTextField(
                singleLine = true,
                maxLines = 1,
                isError = workConvert.hasError,
                value = workConvert.currentValue,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { triggerBaseConvert(it, workConvert) },
                shape = RoundedCornerShape(bottomEnd = 15.dp, topEnd = 15.dp),
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.End),
                keyboardOptions = KeyboardOptions(
                    autoCorrect = false,
                    capitalization = KeyboardCapitalization.None,
                    keyboardType = KeyboardType.Ascii
                ),
                label = { Text(stringResource(R.string.text_base, workConvert.base)) },
                placeholder = { Text(stringResource(R.string.place_holder_input_number)) },
            )
        }

        Text(
            text = if (workConvert.hasError) stringResource(id = workConvert.errorValue) else "",
            style = MaterialTheme.typography.caption,
            color = if (workConvert.hasError) MaterialTheme.colors.error else Color.Unspecified
        )

    }


}