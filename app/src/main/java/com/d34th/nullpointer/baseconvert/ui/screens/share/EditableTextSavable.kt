package com.d34th.nullpointer.baseconvert.ui.screens.share

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.d34th.nullpointer.baseconvert.R
import com.d34th.nullpointer.baseconvert.models.WorkConvert

@Composable
fun EditableTextSavable(
    modifier: Modifier = Modifier,
    workConvert: WorkConvert,
    triggerBaseConvert: (String, WorkConvert) -> Unit
) {

    val valueProperty = workConvert.propertyBase



    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .height(IntrinsicSize.Min)
            .clip(RoundedCornerShape(15.dp)),
    ) {
        Text(
            text = "Base ${workConvert.base}",
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .background(MaterialTheme.colors.primary)
                .width(70.dp)
                .fillMaxHeight()
                .wrapContentHeight(),
        )
        OutlinedTextField(
            singleLine = true,
            maxLines = 1,
            isError = valueProperty.hasError,
            value = valueProperty.currentValue,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { triggerBaseConvert(it, workConvert) },
            shape = RoundedCornerShape(bottomEnd = 15.dp, topEnd = 15.dp),
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.End),
            trailingIcon = {
                Icon(painter = painterResource(id = R.drawable.ic_copy), contentDescription = null)
            }
        )
    }


}