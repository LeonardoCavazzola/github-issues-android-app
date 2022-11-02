package com.githubIssues.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.githubIssues.gateway.BitmapClient
import com.githubIssues.gateway.GithubClient
import okhttp3.OkHttpClient
import org.koin.dsl.module

val appModule = module {
    single<OkHttpClient> { OkHttpClient() }
    single<GithubClient> { GithubClient() }
    single<BitmapClient> { BitmapClient() }
    single<ObjectMapper> { buildObjectMapper() }
}