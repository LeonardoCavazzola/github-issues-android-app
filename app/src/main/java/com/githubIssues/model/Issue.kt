package com.githubIssues.model

import java.io.Serializable
import java.time.ZonedDateTime

class Issue(
    val title: String,
    val state: String,
    val user: User,
    val body: String?,
    val createdAt: ZonedDateTime
) : Serializable {
    companion object {
        const val DATE_PATTERN_US = "yyyy-MM-dd'T'HH:mm:ssz"
        const val DATE_PATTERN_BR = "dd-MM-yyyy HH:mm:ss z"
    }
}