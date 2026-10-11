package com.example.tipcalculator

import org.junit.Assert.assertEquals
import org.junit.Test
import java.text.NumberFormat

class TipCalculatorTests {

    @Test
    fun calculateTip_20PercentNoRoundup() {
        val amount = 10.00
        val tipPercent = 20.00

        val expectedTip = NumberFormat
            .getCurrencyInstance()
            .format(2)

        val actualTip = calculateTip(
            amount = amount,
            tipPercent = tipPercent,
            roundUp = false
        )

        assertEquals(expectedTip, actualTip)
    }

    @Test
    fun calculateTip_20PercentRoundup() {
        val amount = 10.10
        val tipPercent = 20.00

        val expectedTip = NumberFormat
            .getCurrencyInstance()
            .format(3)

        val actualTip = calculateTip(
            amount = amount,
            tipPercent = tipPercent,
            roundUp = true
        )

        assertEquals(expectedTip, actualTip)
    }

}
