package com.example.nappi.screens

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun TopBar() = TopAppBar(title = { TitleText() })

@Composable
private fun TitleText() = Text(
    text = "Welcome to Nappi",
    textAlign = TextAlign.Center,
    modifier = Modifier.fillMaxSize().wrapContentSize(align = Alignment.Center)
)