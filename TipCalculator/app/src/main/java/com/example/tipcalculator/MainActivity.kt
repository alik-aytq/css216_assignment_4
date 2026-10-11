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
import kotlin.math.ceil
import androidx.compose.ui.res.stringResource
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.focus.FocusDirection
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
    var amountInput by rememberSaveable() {mutableStateOf("")}
    var tipPercentInput by rememberSaveable() { mutableStateOf("") }
    var roundUp by rememberSaveable() {mutableStateOf(false)}
    val amount = amountInput.toDoubleOrNull() ?: 0.0
    val tipPercent=tipPercentInput.toDoubleOrNull() ?: 15.0
    val tip = calculateTip(amount,tipPercent,roundUp)

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
            text=stringResource(R.string.calculate_tip),
            fontSize=24.sp
        )
        Spacer(modifier=Modifier.height(32.dp))

        EditNumberField(
            value=amountInput,
            onValueChange = {amountInput=it},
            label=stringResource(R.string.bill_amount),
            imeAction=ImeAction.Next
        )

        Spacer(modifier=Modifier.height(16.dp))

        EditNumberField(
            value=tipPercentInput,
            onValueChange = {tipPercentInput=it},
            label = stringResource(R.string.tip_percentage),
            imeAction=ImeAction.Done
        )

        Spacer(modifier=Modifier.height(16.dp))

        Row(
            modifier=Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ){
            RoundTheTipRow(
                roundUp = roundUp,
                onRoundUpChanged = { roundUp = it }
            )
        }

        Spacer(modifier=Modifier.height(32.dp))

        Text(
            text=stringResource(R.string.tip_amount, tip),
            fontSize=20.sp
        )
    }
}

private fun calculateTip(
    amount: Double,
    tipPercent: Double =15.0,
    roundUp:Boolean =false
): String{
    var tip = amount*tipPercent/100

    if(roundUp){
        tip=ceil(tip)
    }
    return NumberFormat.getCurrencyInstance().format(tip)
}

@Composable
fun EditNumberField(
    value: String,
    onValueChange: (String)-> Unit,
    label: String,
    imeAction: ImeAction
){
    val focusManager=LocalFocusManager.current

    TextField(
        value=value,
        onValueChange=onValueChange,
        label={Text(label)},
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = imeAction
        ),
        keyboardActions = KeyboardActions(
            onNext = {
               focusManager.moveFocus(FocusDirection.Down)
            },
            onDone = {
                focusManager.clearFocus()
            }
        )
    )
}

@Composable
fun RoundTheTipRow(
    roundUp: Boolean,
    onRoundUpChanged: (Boolean)->Unit,
){
    Row(
        modifier=Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(
            text=stringResource(R.string.round_up_tip),
            modifier=Modifier.weight(1f)
        )

        Switch(
            checked = roundUp,
            onCheckedChange = onRoundUpChanged
        )
    }
}