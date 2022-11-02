package com.githubIssues.gateway

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.githubIssues.extension.enqueue
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class BitmapClient : KoinComponent {
    private val client: OkHttpClient by inject()

    fun get(url: String, onSuccess: (Bitmap) -> Unit) {
        val request = Request.Builder().url(url).build()

        val onResponse: (Call, Response) -> Unit = { _, response ->
            BitmapFactory.decodeStream(response.body!!.byteStream()).let(onSuccess)
        }

        client.newCall(request).enqueue({ _, _ -> println("fdp") }, onResponse)
    }
}