package com.io.domain.models

data class CountryDetail(
    val code: String,
    val name: String,
    val phone: String,
    val capital: String?,
    val currency: String?,
    val emoji: String
)