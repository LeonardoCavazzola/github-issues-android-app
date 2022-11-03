package com.githubIssues.gateway

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.githubIssues.extension.enqueue
import okhttp3.OkHttpClient
import okhttp3.Request
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import reactor.core.publisher.Mono

class BitmapClient : KoinComponent {
    private val client: OkHttpClient by inject()

    fun get(url: String): Mono<Bitmap> {
        val request = Request.Builder().url(url).build()
        return client.newCall(request).enqueue()
            .map { BitmapFactory.decodeStream(it.body!!.byteStream()) }
    }
}