package com.githubIssues.ui.viewmodel

import android.graphics.Bitmap
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.githubIssues.gateway.BitmapClient
import com.githubIssues.model.Issue
import com.githubIssues.model.User
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class IssueViewModel : KoinComponent {
    private val bitmapClient: BitmapClient by inject()

    var title: String by mutableStateOf("")
    var state: String by mutableStateOf("")
    var body: String? by mutableStateOf(null)
    var createdAt: String by mutableStateOf("")
    var userLogin: String by mutableStateOf("")
    var userAvatarUrl: String by mutableStateOf("")
    var userAvatar: Bitmap? by mutableStateOf(null)

    var issue: Issue
        get() = toModel()
        set(value) {
            title = value.title
            state = value.state
            body = value.body
            createdAt = value.createdAt.format(formatter)
            userLogin = value.user.login
            userAvatarUrl = value.user.avatarUrl
            bitmapClient.get(userAvatarUrl) { userAvatar = it }
        }

    companion object {
        private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern(Issue.DATE_PATTERN_BR)
    }
}

private fun IssueViewModel.toModel() = Issue(
    title = title,
    state = state,
    user = User(
        login = userLogin,
        avatarUrl = userAvatarUrl,
    ),
    body = body,
    createdAt = ZonedDateTime.now()
)
