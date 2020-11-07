package com.example.nappi

import android.media.Ringtone
import android.media.RingtoneManager
import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.setContent
import androidx.lifecycle.lifecycleScope
import com.example.nappi.common.Event
import com.example.nappi.screens.NappiApp
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    /** Currently we are getting the default  */
    private val ringtone: Ringtone by lazy {
        val notification: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        RingtoneManager.getRingtone(this, notification)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NappiApp(
                state = viewModel.state.collectAsState().value,
                dispatch = { action -> viewModel.dispatch(action) }
            )
        }
        lifecycleScope.launch {
            viewModel.event.collect { handleEvent(it) }
        }
    }

    private fun handleEvent(event: Event) = when (event) {
        Event.None -> noEvent()
        Event.PlayAlarm -> playAlarm()
        Event.StopAlarm -> stopAlarm()
    }

    private fun noEvent() {
        /* No-Op */
    }

    private fun playAlarm() {
        ringtone.stop()
        ringtone.play()
        viewModel.resetEvent()
    }

    private fun stopAlarm() {
        ringtone.stop()
        viewModel.resetEvent()
    }
}
