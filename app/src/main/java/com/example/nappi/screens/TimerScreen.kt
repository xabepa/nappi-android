package com.example.nappi.screens

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.ui.tooling.preview.Preview
import com.example.nappi.ui.NappiTheme
import androidx.compose.material.Slider
import androidx.compose.runtime.*
import com.example.nappi.NappiState
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
@Composable
fun TimerScreen(state: NappiState, onToggleTimer: () -> Unit, onOvertimeSet: (Long) -> Unit) {
    Scaffold(
        topBar = { TopBar("Kai") },
        bodyContent = { Timer(state = state, onToggleTimer = onToggleTimer, onOvertimeSet = onOvertimeSet) })
}

@ExperimentalCoroutinesApi
@Composable
fun Timer(state: NappiState, onToggleTimer: () -> Unit, onOvertimeSet: (Long) -> Unit) {
    Column(
        Modifier
            .fillMaxSize()
            .wrapContentSize(align = Alignment.TopCenter)
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Time remaining: ${state.remainingSeconds}")
        Text(text = "Overtime: ${state.overTimeSeconds}")
        Button(onClick = { onToggleTimer() }, content = { Text(text = "click mich") })

        var sliderPosition by remember { mutableStateOf(0f) }
        Text(text = sliderPosition.toString())
        Slider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it },
            valueRange = 0f..10f,
            steps = 9,
            onValueChangeEnd = { onOvertimeSet(sliderPosition.toLong()) }
        )
    }
}

@Composable
fun TopBar(name: String) {
    TopAppBar(
        title = {
            Text(
                text = "Welcome $name",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxSize().wrapContentSize(align = Alignment.Center)
            )
        }
    )
}

@Composable
fun OvertimeSlider() {
    var sliderPosition by remember { mutableStateOf(0f) }
    Text(text = sliderPosition.toString())
    Slider(value = sliderPosition, onValueChange = { sliderPosition = it })
}


@ExperimentalCoroutinesApi
@Preview(name = "TimerScreen", showBackground = true)
@Composable
fun TimerScreenPreview() {
    NappiTheme {
        TimerScreen(state = NappiState(30, 120), onToggleTimer = {}, onOvertimeSet = {})
    }
}