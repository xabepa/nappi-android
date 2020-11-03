package com.example.nappi

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.setContent
import com.example.nappi.screens.TimerScreen
import com.example.nappi.ui.NappiTheme
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NappiTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val state = viewModel.state.collectAsState()
                    TimerScreen(state = state.value, onToggleTimer = { viewModel.onChange() }, onOvertimeSet = { minutes -> viewModel.onOvertimeSet(minutes) })
                }
            }
        }
    }
}