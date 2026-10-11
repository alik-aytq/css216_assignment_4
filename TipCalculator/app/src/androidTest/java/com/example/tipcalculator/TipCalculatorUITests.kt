package com.example.tipcalculator

import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import java.text.NumberFormat
import org.junit.Test

class TipCalculatorUITests {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun calculate_20_percent_tip() {
        composeTestRule.setContent {
            TipLayout()
        }

        composeTestRule
            .onNodeWithText("Bill Amount")
            .performTextInput("10")

        composeTestRule
            .onNodeWithText("Tip Percentage")
            .performTextInput("20")

        val expectedTip = NumberFormat
            .getCurrencyInstance()
            .format(2)

        composeTestRule
            .onNodeWithText("Tip Amount: $expectedTip")
            .assertIsDisplayed()
    }
}
