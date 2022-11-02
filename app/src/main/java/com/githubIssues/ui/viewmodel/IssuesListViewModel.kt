package com.githubIssues.ui.viewmodel

import android.content.Context
import com.githubIssues.gateway.GithubClient
import com.githubIssues.model.Issue
import com.githubIssues.ui.activity.IssueActivity
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class IssuesListViewModel : KoinComponent {
    private val client: GithubClient by inject()

    fun getAllIssues(setContent: (List<Issue>) -> Unit) = client.getAllIssuesByRepository(
        owner = "JetBrains",
        repository = "kotlin",
        onFailure = { println("fail") },
        onSuccessful = { setContent(it) }
    )

    fun onIssueClick(ctx: Context, issue: Issue) = IssueActivity.start(ctx, issue)
}