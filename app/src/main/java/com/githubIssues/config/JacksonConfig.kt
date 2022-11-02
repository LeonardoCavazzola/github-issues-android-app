package com.githubIssues.config

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies

fun buildObjectMapper(): ObjectMapper {
    val objectMapper = ObjectMapper()
    objectMapper.findAndRegisterModules()
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    objectMapper.propertyNamingStrategy = PropertyNamingStrategies.SNAKE_CASE
    return objectMapper
}