package com.githubIssues.gateway

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.githubIssues.exception.WithoutSuccessfulStatusCodeException
import com.githubIssues.extension.enqueue
import com.githubIssues.model.Issue
import okhttp3.OkHttpClient
import okhttp3.Request
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import reactor.core.publisher.Flux

class GithubClient : KoinComponent {
    private val httpClient: OkHttpClient by inject()
    private val objectMapper: ObjectMapper by inject()

    fun getAllIssuesByRepository(owner: String, repository: String): Flux<Issue> {
        val request = Request.Builder()
            .url(ISSUES_BY_REPOSITORY_URI_TEMPLATE.format(owner, repository))
            .build()

        return httpClient.newCall(request).enqueue().flatMapMany {
            if (it.isSuccessful) {
                Flux.fromIterable(objectMapper.readValue<List<Issue>>(it.body!!.string()))
            } else {
                Flux.error(WithoutSuccessfulStatusCodeException(it.code))
            }
        }
    }

    companion object {
        private const val GITHUB_API_URL = "https://api.github.com"
        private const val ISSUES_BY_REPOSITORY_URI_TEMPLATE = "$GITHUB_API_URL/repos/%s/%s/issues"
    }
}
