package com.example.nappi

import android.os.CountDownTimer
import androidx.lifecycle.ViewModel
import com.example.nappi.MainViewModel.Companion.NAP_TIME_MINUTES
import com.example.nappi.common.Action
import com.example.nappi.common.Event
import com.example.nappi.timer.DefaultCountDownTimer
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.time.*

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModel : ViewModel() {

    /**
     * Single source of truth. This state and the changes can be observed
     * by activities/fragments to reflect any changes to the ui.
     */
    val state: StateFlow<NappiState> get() = _state

    /** Internal state that can only be changed within this view model. */
    private val _state = MutableStateFlow(NappiState())

    val event: StateFlow<Event> get() = _event
    private val _event = MutableStateFlow<Event>(Event.None)

    private var timer: CountDownTimer? = null

    private val baseTime get() = NAP_TIME_MINUTES.minutes

    /**
     * Dispatch an action to the view model. Depending on the provided [Action] class
     * view model proceeds with calling the corresponding function.
     *
     * Because we are using a sealed class for [Action] the 'when' check
     * is always exhaustive on all possible actions.
     */
    fun dispatch(action: Action) = when(action) {
        Action.StartTimer -> startTimer()
        is Action.SetCalmDownTime -> setCalmDownTime(action.calmDownTime)
        Action.StopAlarm -> stopAlarm()
    }

    fun resetEvent() {
        _event.value = Event.None
    }

    /**
     * Cancel any existing timer, create a new one
     * with the appropriate values and start it.
     */
    private fun startTimer() {
        val durationInFuture = baseTime + _state.value.calmDownTime
        timer?.cancel() // Cancel any previous timer
        timer = DefaultCountDownTimer(
            durationInFuture = durationInFuture,
            countDownInterval = 1.seconds,
            onEveryTick = { remainingTime -> setRemainingTime(remainingTime) },
            whenFinished = { playAlarm() },
        ).also { newTimer -> newTimer.start() }
    }

    private fun playAlarm() {
        _event.value = Event.PlayAlarm
    }

    private fun stopAlarm() {
        _event.value = Event.StopAlarm
    }

    private fun setRemainingTime(remainingTime: Duration) {
        _state.value = _state.value.copy(remainingTime = remainingTime)
    }

    private fun setCalmDownTime(calmDownTime: Duration) {
        _state.value = _state.value.copy(calmDownTime = calmDownTime)
    }

    companion object {
        const val NAP_TIME_MINUTES: Long = 20
    }
}

data class NappiState(
    val remainingTime: Duration = NAP_TIME_MINUTES.minutes,
    val calmDownTime: Duration = 0.minutes,
)