package com.example.nappi.screens

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.ui.tooling.preview.Preview
import com.example.nappi.ui.NappiTheme

@Composable
fun TimerScreen(seconds: Long, onToggleTimer: () -> Unit) {
    Scaffold(topBar = { TopBar("Kai") }, bodyContent = { Timer(seconds = seconds, onToggleTimer = onToggleTimer) })
}

@Composable
fun Timer(seconds: Long, onToggleTimer: () -> Unit) {
    Column (Modifier.fillMaxSize().wrapContentSize(align = Alignment.TopCenter)){
        Text(text = "Time remaining: $seconds")
        Button(onClick = { onToggleTimer() }, content = { Text(text = "click mich") })
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


@Preview(name = "TimerScreen", showBackground = true)
@Composable
fun TimerScreenPreview() {
    NappiTheme {
        TimerScreen(seconds = 10, onToggleTimer = {})
    }
}