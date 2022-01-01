package com.io.data.country.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CountryDetailEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val code: String,
    val name: String,
    val phone: String,
    val capital: String?,
    val currency: String?,
    val emoji: String
)
