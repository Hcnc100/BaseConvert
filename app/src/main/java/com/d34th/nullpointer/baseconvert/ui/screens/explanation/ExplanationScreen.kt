package com.d34th.nullpointer.baseconvert.ui.screens.explanation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.AlertDialog
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.platform.LocalContext
import androidx.core.os.ConfigurationCompat
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.hilt.navigation.compose.hiltViewModel
import com.d34th.nullpointer.baseconvert.core.utils.ChangeBase
import com.d34th.nullpointer.baseconvert.R
import com.d34th.nullpointer.baseconvert.presentation.SettingsViewModel
import com.d34th.nullpointer.baseconvert.ui.navigation.LocalRootNavController
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun ExplanationScreen() {
    val rootNavController = LocalRootNavController.current
    val settingsViewModel: SettingsViewModel = hiltViewModel()
    val precision = settingsViewModel.currentPrecision.collectAsState().value.toIntOrNull() ?: 10
    var number by rememberSaveable { mutableStateOf("") }
    var fromBase by rememberSaveable { mutableStateOf(10) }
    var toBase by rememberSaveable { mutableStateOf(2) }
    var result by remember { mutableStateOf("") }
    var error by remember { mutableStateOf<String?>(null) }
    val emptyNumberMessage = stringResource(R.string.explain_empty_number)
    val numberError = if (number.isNotBlank() && !ChangeBase.validate(number, fromBase)) {
        invalidNumberMessage(LocalContext.current, number, fromBase)
    } else null

    LaunchedEffect(number, fromBase, toBase, precision) {
        result = if (number.isNotBlank() && ChangeBase.validate(number, fromBase)) {
            ChangeBase.baseToBase(number, fromBase, toBase, precision)
        } else ""
    }

    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(stringResource(R.string.explain_title), style = MaterialTheme.typography.h5, fontWeight = FontWeight.Bold)
        Text(stringResource(R.string.explain_intro))
        BaseTextField(
            base = fromBase,
            value = number,
            placeholder = stringResource(R.string.place_holder_input_number),
            enabled = true,
            isError = numberError != null,
            errorMessage = numberError,
            onBaseSelected = { fromBase = it },
            onValueChange = { number = it.uppercase(); error = null }
        )
        BaseTextField(
            base = toBase,
            value = result,
            placeholder = stringResource(R.string.explain_result_placeholder),
            enabled = false,
            onBaseSelected = { toBase = it },
            onValueChange = {}
        )
        Text(stringResource(R.string.explain_live_result), style = MaterialTheme.typography.caption)
        error?.let { Text(it, color = MaterialTheme.colors.error) }
        Button(onClick = {
            error = when {
                number.isBlank() -> emptyNumberMessage
                numberError != null -> numberError
                else -> {
                    rootNavController.navigate("explanation-detail/$number/$fromBase/$toBase")
                    null
                }
            }
        }, modifier = Modifier.fillMaxWidth()) { Text(stringResource(R.string.explain_action)) }
    }
}

@Composable
private fun BaseTextField(
    base: Int,
    value: String,
    placeholder: String,
    enabled: Boolean,
    isError: Boolean = false,
    errorMessage: String? = null,
    onBaseSelected: (Int) -> Unit,
    onValueChange: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val shape = RoundedCornerShape(12.dp)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(58.dp)
            .clip(shape)
            .border(2.dp, MaterialTheme.colors.primary, shape),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.fillMaxHeight().background(MaterialTheme.colors.primary).clickable { expanded = true }.padding(horizontal = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(stringResource(R.string.base_selector, base), color = Color.White, fontWeight = FontWeight.Bold)
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                (2..36).forEach { option ->
                    DropdownMenuItem(onClick = { onBaseSelected(option); expanded = false }) { Text(stringResource(R.string.text_base, option)) }
                }
            }
        }
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            enabled = enabled,
            isError = isError,
            modifier = Modifier.weight(1f),
            placeholder = { Text(placeholder) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Ascii),
            shape = RoundedCornerShape(0.dp),
        )
    }
    if (errorMessage != null) {
        Text(
            text = errorMessage,
            style = MaterialTheme.typography.caption,
            color = MaterialTheme.colors.error,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Destination
@Composable
fun ExplanationDetailScreen(number: String, fromBase: Int, toBase: Int) {
    val settingsViewModel: SettingsViewModel = hiltViewModel()
    val context = LocalContext.current
    val precision = settingsViewModel.currentPrecision.collectAsState().value.toIntOrNull() ?: 10
    val locale = ConfigurationCompat.getLocales(context.resources.configuration)[0]
    val steps = remember(number, fromBase, toBase, precision, locale) {
        buildExplanationSteps(context, number, fromBase, toBase, precision)
    }
    var showDigitInfo by remember { mutableStateOf(false) }
    val digitInfoBase = maxOf(fromBase, toBase)

    Box(Modifier.fillMaxSize()) {
        Column(
            Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(stringResource(R.string.step_by_step_title), style = MaterialTheme.typography.h5, fontWeight = FontWeight.Bold)
            steps.forEach { step -> ExplanationCard(step) }
        }
        if (digitInfoBase > 10) {
            FloatingActionButton(
                onClick = { showDigitInfo = true },
                modifier = Modifier.align(Alignment.TopEnd).padding(8.dp).size(40.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_info),
                    contentDescription = stringResource(R.string.base_digits_description),
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }

    if (showDigitInfo) {
        AlertDialog(
            onDismissRequest = { showDigitInfo = false },
            title = { Text(stringResource(R.string.base_digits_title)) },
            text = { Text(digitLegend(context, fromBase, toBase)) },
            confirmButton = {
                Button(onClick = { showDigitInfo = false }) { Text(stringResource(R.string.understood)) }
            }
        )
    }
}

private fun invalidNumberMessage(context: android.content.Context, number: String, base: Int): String {
    val invalidCharacter = number.firstOrNull { character ->
        character != '+' && character != '-' && character != '.' &&
            (character.digitToIntOrNull(36) ?: -1) !in 0 until base
    }
    return if (invalidCharacter != null) {
        context.getString(R.string.invalid_character, invalidCharacter, base)
    } else {
        context.getString(R.string.invalid_number_for_base, base)
    }
}

private fun digitLegend(context: android.content.Context, fromBase: Int, toBase: Int): String {
    val bases = listOf(fromBase, toBase).distinct().filter { it > 10 }
    return buildString {
        append(context.getString(R.string.digit_legend_intro))
        bases.forEach { base ->
            append(context.getString(R.string.digit_legend_base, base))
            append((10 until base).joinToString(", ") { value -> "${digitSymbol(value)} = $value" })
            append(".\n")
        }
        append(context.getString(R.string.digit_legend_outro))
    }
}

private fun digitSymbol(value: Int): Char =
    if (value < 10) ('0'.code + value).toChar() else ('A'.code + value - 10).toChar()

@Composable
private fun ExplanationCard(step: ExplanationStep) {
    Card(elevation = 4.dp, modifier = Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(step.title, color = MaterialTheme.colors.primary, fontWeight = FontWeight.Bold)
            FormulaText(step.formula, modifier = Modifier.fillMaxWidth())
            Text(step.description, style = MaterialTheme.typography.body2)
        }
    }
}
