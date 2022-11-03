package com.githubIssues.extension

import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import reactor.core.publisher.Mono
import java.io.IOException

fun Call.enqueue(): Mono<Response> = Mono.create { mono ->
    this.enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) = mono.error(e)
        override fun onResponse(call: Call, response: Response) = mono.success(response)
    })
}