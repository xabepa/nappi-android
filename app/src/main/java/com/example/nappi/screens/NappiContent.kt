package com.example.nappi.screens

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Slider
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.ui.tooling.preview.Preview
import com.example.nappi.NappiState
import com.example.nappi.common.Action
import java.util.*
import kotlin.math.ceil
import kotlin.math.roundToInt
import kotlin.time.minutes

@Composable
fun NappiContent(state: NappiState, dispatch: (Action) -> Unit) = Column(
    modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
) {
    val remainingTime = ceil(state.remainingTime.inMinutes).toLong().toString()
    DebugInfo(state = state, dispatch = dispatch)
    TimeDisplay(time = remainingTime)
    BigButton(dispatch = dispatch)
    CalmDownSlider(dispatch = dispatch)
}

@Composable
private fun DebugInfo(state: NappiState, dispatch: (Action) -> Unit) {
    Text(text = "Time remaining: ${state.remainingTime}", modifier = Modifier.padding(8.dp))
    Text(text = "Overtime: ${state.calmDownTime}", modifier = Modifier.padding(8.dp))
    TextButton(onClick = { dispatch(Action.StopAlarm) }, modifier = Modifier.padding((8.dp))) {
        Text("Stop alarm".toUpperCase(Locale.getDefault()), fontSize = 10.sp)
    }
}

@Composable
private fun BigButton(text: String = "Nap", dispatch: (Action) -> Unit) = Button(
    onClick = { dispatch(Action.StartTimer) },
    contentPadding = PaddingValues(16.dp),
    modifier = Modifier.padding(16.dp),
) {
    Text(text = text.toUpperCase(Locale.getDefault()))
}

@Composable
fun TimeDisplay(time: String) = Text(
    text = time,
    fontSize = 96.sp,
    fontFamily = FontFamily.Monospace,
    modifier = Modifier.padding(16.dp)
)

@Composable
private fun CalmDownSlider(dispatch: (Action) -> Unit) {
    var sliderPosition by remember { mutableStateOf(0f) }
    Column(
        modifier = Modifier.padding(horizontal = 64.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "+${sliderPosition.roundToInt()}",
            fontFamily = FontFamily.Monospace,
            fontSize = 20.sp
        )
        Slider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it },
            valueRange = 0f..10f,
            steps = 9,
            onValueChangeEnd = {
                dispatch(Action.SetCalmDownTime(calmDownTime = sliderPosition.toLong().minutes))
            }
        )
    }

}

@Preview(name = "NappiContent", showBackground = true)
@Composable
private fun NappiContentPreview() = NappiContent(state = NappiState(), dispatch = { })
