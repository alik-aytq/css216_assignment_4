package com.example.tipcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import java.text.NumberFormat

class MainActivity: ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContent{
            MaterialTheme{
                TipLayout()
            }
        }
    }
}

@Composable
fun TipLayout(){
    var amountInput by remember {mutableStateOf("")}
    var tipPercentInput by remember { mutableStateOf("") }
    val amount = amountInput.toDoubleOrNull() ?: 0.0
    val tipPercent=tipPercentInput.toDoubleOrNull() ?: 15.0
    val tip = calculateTip(amount,tipPercent)

    Column(
        modifier=Modifier
            .statusBarsPadding()
            .padding(horizontal = 40.dp)
            .verticalScroll(rememberScrollState())
            .safeDrawingPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(
            text="Calculate Tip",
            fontSize=24.sp
        )
        Spacer(modifier=Modifier.height(32.dp))

        TextField(
            value=amountInput,
            onValueChange = {amountInput=it},
            label={Text("Bill Amount")},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )

        Spacer(modifier=Modifier.height(16.dp))

        TextField(
            value=tipPercentInput,
            onValueChange = {tipPercentInput=it},
            label={Text("Tip Percentage")},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )

        Spacer(modifier=Modifier.height(32.dp))

        Text(
            text="Tip Amount: $tip",
            fontSize=20.sp
        )
    }
}

private fun calculateTip(
    amount: Double,
    tipPercent: Double =15.0
): String{
    val tip = amount*tipPercent/100
    return NumberFormat.getCurrencyInstance().format(tip)
}