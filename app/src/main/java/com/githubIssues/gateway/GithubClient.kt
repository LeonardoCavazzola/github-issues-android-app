package com.githubIssues.gateway

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.githubIssues.extension.enqueue
import com.githubIssues.model.Issue
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GithubClient : KoinComponent {
    private val httpClient: OkHttpClient by inject()
    private val objectMapper: ObjectMapper by inject()

    fun getAllIssuesByRepository(
        owner: String,
        repository: String,
        onFailure: Exception.() -> Unit,
        onSuccessful: (List<Issue>) -> Unit,
    ) {
        val request = Request.Builder()
            .url(ISSUES_BY_REPOSITORY_URI_TEMPLATE.format(owner, repository))
            .build()

        val onFailureBeforeResponse: (call: Call, e: Exception) -> Unit = { _, e ->
            onFailure(e)
        }

        val onResponse: (call: Call, response: Response) -> Unit = { _, response ->
            if (response.isSuccessful) {
                val value = objectMapper.readValue<List<Issue>>(response.body!!.string())
                onSuccessful(value)
            } else {
                onFailure(WithoutSuccessfulStatusCodeException(response.code))
            }
        }

        httpClient.newCall(request).enqueue(onFailureBeforeResponse, onResponse)
    }

    companion object {
        private const val GITHUB_API_URL = "https://api.github.com"
        private const val ISSUES_BY_REPOSITORY_URI_TEMPLATE = "$GITHUB_API_URL/repos/%s/%s/issues"
    }
}

class WithoutSuccessfulStatusCodeException(statusCode: Int) : Exception(statusCode.toString())