package com.example.nappi.common

import kotlin.time.Duration
import kotlin.time.ExperimentalTime

/** Every action that can be dispatched to the view model is defined here as an [Action]. */
sealed class Action {

    /** Action to start the countdown timer. */
    object StartTimer : Action()

    /** Action to stop the playing alarm. */
    object StopAlarm : Action()

    /** Action to set the calm down time to the provided [Duration]. */
    class SetCalmDownTime(val calmDownTime: Duration) : Action()

}


