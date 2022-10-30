package com.d34th.nullpointer.baseconvert.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.d34th.nullpointer.baseconvert.models.WorkConvert

@Composable
fun EditableTextSavable(
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    singleLine: Boolean = false,
    workConvert: WorkConvert,
    shape: Shape = MaterialTheme.shapes.small,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    triggerBaseConvert: (String, WorkConvert) -> Unit
) {

    val valueProperty = workConvert.propertyBase

    Surface(
        modifier = modifier.height(80.dp),
    ) {

        Column {
            OutlinedTextField(
                shape = shape,
                enabled = isEnabled,
                singleLine = singleLine,
                isError = valueProperty.hasError,
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                value = valueProperty.currentValue,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.8f),
                visualTransformation = visualTransformation,
                onValueChange = { triggerBaseConvert(it, workConvert) },
                label = { Text(stringResource(id = valueProperty.label, valueProperty.base)) },
                placeholder = { Text(stringResource(id = valueProperty.hint, valueProperty.base)) },
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.End)

            )
            Row {
                Text(
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.weight(.9f),
                    text = if (valueProperty.hasError) stringResource(id = valueProperty.errorValue) else ""
                )
                Text(
                    text = valueProperty.countLength,
                    style = MaterialTheme.typography.caption,
                    color = if (valueProperty.hasError) MaterialTheme.colors.error else Color.Unspecified
                )
            }
        }
    }

}