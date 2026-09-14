package com.d34th.nullpointer.baseconvert.ui.screens.explanation

import android.content.Context
import com.d34th.nullpointer.baseconvert.R

import java.math.BigDecimal
import java.math.BigInteger
import java.math.RoundingMode

data class ExplanationStep(val title: String, val formula: String, val description: String)

fun buildExplanationSteps(context: Context, number: String, fromBase: Int, toBase: Int, precision: Int = 10): List<ExplanationStep> {
    val sign = if (number.startsWith("-")) "-" else ""
    val unsigned = number.removePrefix("+").removePrefix("-")
    val integerPart = unsigned.substringBefore('.')
    val fractionalPart = unsigned.substringAfter('.', "")
    val steps = mutableListOf<ExplanationStep>()
    val safePrecision = precision.coerceAtLeast(0)

    steps += ExplanationStep(context.getString(R.string.step_goal_title), "$number₍$fromBase₎ → ?₍$toBase₎", context.getString(R.string.step_goal_description))

    val decimalInteger = integerToDecimal(integerPart, fromBase)
    val decimalFraction = fractionToDecimal(fractionalPart, fromBase)
    if (fromBase == 10) {
        steps += ExplanationStep(context.getString(R.string.step_integer_already_decimal_title), "$sign$integerPart₍10₎", context.getString(R.string.step_integer_already_decimal_description))
    } else {
        val integerTerms = integerPart.mapIndexed { index, digit ->
            "${digitValue(digit)} × $fromBase⁽${integerPart.length - 1 - index}⁾"
        }
        val fractionTerms = fractionalPart.mapIndexed { index, digit ->
            "${digitValue(digit)} × $fromBase⁽-${index + 1}⁾"
        }
        steps += ExplanationStep(context.getString(R.string.step_integer_to_decimal_title), integerTerms.joinToString(" + ") + " = $sign$decimalInteger₍10₎", context.getString(R.string.step_integer_to_decimal_description))
        if (fractionalPart.isNotEmpty()) {
            steps += ExplanationStep(context.getString(R.string.step_fraction_to_decimal_title), fractionTerms.joinToString(" + ") + " = ${formatDecimal(decimalFraction)}₍10₎", context.getString(R.string.step_fraction_to_decimal_description))
        }
    }

    val integerDivisions = mutableListOf<String>()
    val integerDigits = decimalIntegerToBase(context, decimalInteger, toBase, integerDivisions)
    if (toBase != 10) {
        steps += ExplanationStep(context.getString(R.string.step_integer_divide_title), integerDivisions.joinToString("\n"), context.getString(R.string.step_integer_divide_description))
        steps += ExplanationStep(context.getString(R.string.step_integer_build_title), "$sign$decimalInteger₍10₎ → $sign$integerDigits₍$toBase₎", context.getString(R.string.step_integer_build_description, "$sign$integerDigits", toBase))
    }

    val fractionalDigits = mutableListOf<Char>()
    val fractionOperations = mutableListOf<String>()
    var currentFraction = decimalFraction
    repeat(safePrecision) {
        if (currentFraction.compareTo(BigDecimal.ZERO) == 0) return@repeat
        val multiplied = currentFraction.multiply(BigDecimal.valueOf(toBase.toLong()))
        val digit = multiplied.setScale(0, RoundingMode.FLOOR).toInt()
        fractionalDigits += valueToDigit(digit)
        val remaining = multiplied.subtract(BigDecimal.valueOf(digit.toLong()))
        fractionOperations += context.getString(R.string.fraction_operation, formatDecimal(currentFraction), toBase, formatDecimal(multiplied), displayDigit(context, digit, toBase), formatDecimal(remaining))
        currentFraction = remaining
    }
    if (fractionalPart.isNotEmpty()) {
        val stopReason = when {
            currentFraction.compareTo(BigDecimal.ZERO) == 0 -> context.getString(R.string.fraction_stop_zero)
            safePrecision == 0 -> context.getString(R.string.fraction_stop_precision_zero)
            else -> context.getString(R.string.fraction_stop_precision, safePrecision)
        }
        steps += ExplanationStep(context.getString(R.string.step_fraction_multiply_title), fractionOperations.joinToString("\n"), context.getString(R.string.step_fraction_multiply_description, stopReason))
    }

    val fractionalResult = fractionalDigits.joinToString("")
    val result = if (fractionalResult.isEmpty()) integerDigits else "$integerDigits.$fractionalResult"
    steps += ExplanationStep(context.getString(R.string.step_result_title), "$number₍$fromBase₎ = $sign$result₍$toBase₎", if (fractionalPart.isEmpty()) context.getString(R.string.step_result_integer_description) else context.getString(R.string.step_result_fraction_description, safePrecision))
    return steps
}

private fun integerToDecimal(value: String, base: Int): BigInteger {
    var result = BigInteger.ZERO
    value.forEach { digit -> result = result * BigInteger.valueOf(base.toLong()) + BigInteger.valueOf(digitValue(digit).toLong()) }
    return result
}

private fun fractionToDecimal(value: String, base: Int): BigDecimal {
    var result = BigDecimal.ZERO
    val baseDecimal = BigDecimal.valueOf(base.toLong())
    value.forEachIndexed { index, digit ->
        result += BigDecimal.valueOf(digitValue(digit).toLong()).divide(baseDecimal.pow(index + 1), 50, RoundingMode.HALF_UP)
    }
    return result
}

private fun decimalIntegerToBase(context: Context, value: BigInteger, base: Int, operations: MutableList<String>): String {
    if (value == BigInteger.ZERO) return "0"
    var current = value
    val target = BigInteger.valueOf(base.toLong())
    val digits = mutableListOf<Char>()
    do {
        val quotient = current.divide(target)
        val remainder = current.mod(target).toInt()
        digits += valueToDigit(remainder)
        operations += context.getString(R.string.division_operation, current, base, quotient, displayDigit(context, remainder, base))
        current = quotient
    } while (current > BigInteger.ZERO)
    return digits.asReversed().joinToString("")
}

private fun formatDecimal(value: BigDecimal): String = value.stripTrailingZeros().toPlainString()

private fun digitValue(digit: Char): Int = when (digit.uppercaseChar()) {
    in '0'..'9' -> digit - '0'
    in 'A'..'Z' -> digit.uppercaseChar() - 'A' + 10
    else -> 0
}

private fun valueToDigit(value: Int): Char = if (value < 10) ('0'.code + value).toChar() else ('A'.code + value - 10).toChar()

private fun displayDigit(context: Context, value: Int, base: Int): String =
    if (base > 10 && value >= 10) context.getString(R.string.digit_with_value, valueToDigit(value), value) else valueToDigit(value).toString()
