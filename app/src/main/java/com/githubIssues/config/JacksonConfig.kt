package com.githubIssues.config

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper

fun buildObjectMapper(): ObjectMapper {
    val objectMapper = ObjectMapper()
    objectMapper.findAndRegisterModules()
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    return objectMapper
}