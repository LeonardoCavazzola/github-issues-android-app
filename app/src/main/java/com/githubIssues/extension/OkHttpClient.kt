package com.githubIssues.extension

import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

fun Call.enqueue(
    onFailure: (call: Call, e: IOException) -> Unit,
    onResponse: (call: Call, response: Response) -> Unit
) {
    this.enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) = onFailure(call, e)
        override fun onResponse(call: Call, response: Response) = onResponse(call, response)
    })
}