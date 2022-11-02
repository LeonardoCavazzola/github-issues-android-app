package com.githubIssues.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier


@Composable
fun Loading() = Box(
    modifier = Modifier.fillMaxSize(),
    contentAlignment = Alignment.Center,
    content = { Text(text = "Loading...") }
)

@Composable
fun LazyLoadingComponent(isLoading: Boolean, whenLoaded: @Composable () -> Unit) =
    if (isLoading) Loading() else whenLoaded()