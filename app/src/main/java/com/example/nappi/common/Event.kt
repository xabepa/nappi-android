package com.example.nappi.common

/**
 * Events represent one-time effects.
 * They are observed by the Activity and the activity reacts accordingly.
 *
 * TODO: Currently the events don't have a "one time" behaviour, we need to adjust it,
 *      so that the consumer does not have to call MainViewModel.resetEvent() after
 *      handling on of the events
 */
sealed class Event {
    object None : Event()
    object PlayAlarm : Event()
    object StopAlarm : Event()
}