package com.example.nappi

import android.os.CountDownTimer
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@ExperimentalCoroutinesApi
class MainViewModel : ViewModel() {
    //state state state state!!!!
    val state: StateFlow<Long> get() = _state
    private val _state = MutableStateFlow(NAP_TIME)

    fun onChange() {
        val timer = object : CountDownTimer(NAP_TIME * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                _state.value = millisUntilFinished / 1000
                Log.d("MainViewModel", state.value.toString())
            }

            override fun onFinish() {
                Log.d("MainViewModel", "Countdown finished! Yay!")
            }
        }
        timer.start()
    }

    companion object {
        const val NAP_TIME: Long = 1200 //==20minutes
    }

}