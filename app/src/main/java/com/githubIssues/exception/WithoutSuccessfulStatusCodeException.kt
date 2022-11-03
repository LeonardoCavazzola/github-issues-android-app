package com.githubIssues.exception

class WithoutSuccessfulStatusCodeException(statusCode: Int) : Exception(statusCode.toString())