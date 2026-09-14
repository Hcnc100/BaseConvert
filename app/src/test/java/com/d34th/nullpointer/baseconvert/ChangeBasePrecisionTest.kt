package com.d34th.nullpointer.baseconvert

import com.d34th.nullpointer.baseconvert.core.utils.ChangeBase
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ChangeBasePrecisionTest {
    @Test
    fun periodicFractionStopsAtConfiguredPrecision() = runBlocking {
        val result = ChangeBase.baseToBase(
            numberString = "0.1",
            baseFrom = 10,
            baseTo = 3,
            decimalPrecision = 5
        )

        assertEquals(5, result.substringAfter('.').length)
    }

    @Test
    fun convertsZeroInsteadOfReturningAnEmptyIntegerPart() = runBlocking {
        assertEquals("0", ChangeBase.baseToBase("0", 10, 2, 10))
    }

    @Test
    fun convertsFractionFromBaseThreeWithoutArithmeticException() = runBlocking {
        val result = ChangeBase.baseToBase("0.1", 3, 10, 8)

        assertTrue(result.startsWith("0.333333"))
    }

    @Test
    fun validatesOnlyDigitsAllowedByTheSourceBase() {
        assertTrue(ChangeBase.validate("-1A.F", 16))
        assertTrue(ChangeBase.validate("101.01", 2))
        assertFalse(ChangeBase.validate("102", 2))
        assertFalse(ChangeBase.validate("1G", 16))
        assertFalse(ChangeBase.validate("10", 1))
    }
}
