package com.githubIssues.ui.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.githubIssues.gateway.GithubClient
import com.githubIssues.model.Issue
import com.githubIssues.ui.activity.IssueActivity
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class IssuesListViewModel : KoinComponent {
    private val client: GithubClient by inject()

    var issues: List<Issue> by mutableStateOf(emptyList())

    fun getAllIssues() {
        client.getAllIssuesByRepository(owner = "JetBrains", repository = "kotlin")
            .subscribe { issues = issues + it }
    }

    fun onIssueClick(ctx: Context, issue: Issue) = IssueActivity.start(ctx, issue)
}