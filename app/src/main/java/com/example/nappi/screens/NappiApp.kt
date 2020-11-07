package com.example.nappi.screens

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.ui.tooling.preview.Preview
import com.example.nappi.ui.NappiTheme
import androidx.compose.runtime.*
import com.example.nappi.NappiState
import com.example.nappi.common.Action

@Composable
fun NappiApp(
    state: NappiState,
    dispatch: (Action) -> Unit,
    darkTheme: Boolean = isSystemInDarkTheme()
) {
    NappiTheme(darkTheme = darkTheme) {
        Surface(color = MaterialTheme.colors.background) {
            NappiScreen(state, dispatch)
        }
    }
}

@Composable
private fun NappiScreen(state: NappiState, dispatch: (Action) -> Unit) = Scaffold(
    topBar = { TopBar() },
    bodyContent = { NappiContent(state = state, dispatch = dispatch) },
)

@Preview(name = "TimerScreen Light", showBackground = true)
@Composable
fun TimerScreenLightPreview() = NappiApp(
    state = NappiState(),
    dispatch = { /* No-Op */ },
    darkTheme = false
)


@Preview(name = "TimerScreen Dark", showBackground = true)
@Composable
fun TimerScreenDarkPreview() = NappiApp(
    state = NappiState(),
    dispatch = { /* No-Op */ },
    darkTheme = true
)

