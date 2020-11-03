package com.example.nappi

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.nappi.MainViewModel.Companion.NAP_TIME
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@ExperimentalCoroutinesApi
class MainViewModel : ViewModel() {
    //state state state state!!!!
    val state: StateFlow<NappiState> get() = _state
    private val _state = MutableStateFlow<NappiState>(NappiState())

    fun onChange() {
        val timer = object : CountDownTimer(NAP_TIME + _state.value.overTimeSeconds * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                _state.value = _state.value.copy(remainingSeconds = millisUntilFinished / 1000)
                Log.d("MainViewModel", state.value.toString())
            }

            override fun onFinish() {
                Log.d("MainViewModel", "Countdown finished! Yay!")
            }
        }
        timer.start()
    }

    fun onOvertimeSet(minutes: Long) {
        _state.value = _state.value.copy(overTimeSeconds = minutes * 60)
    }

    companion object {
        const val NAP_TIME: Long = 1200 // == 20minutes
    }
}

@ExperimentalCoroutinesApi
data class NappiState(
    val remainingSeconds: Long = NAP_TIME,
    val overTimeSeconds: Long = 0,
)