package com.example.nappi.timer

import android.os.CountDownTimer
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime
import kotlin.time.toDuration

@OptIn(ExperimentalTime::class)
class DefaultCountDownTimer(
    durationInFuture: Duration,
    countDownInterval: Duration,
    private val onEveryTick: (durationUntilFinished: Duration) -> Unit,
    private val whenFinished: () -> Unit
) : CountDownTimer(
    durationInFuture.toLongMilliseconds(),
    countDownInterval.toLongMilliseconds()
) {

    override fun onTick(millisUntilFinished: Long) =
        onEveryTick(millisUntilFinished.toDuration(DurationUnit.MILLISECONDS))

    override fun onFinish() = whenFinished()

}
