package com.io.data.country.mapper

import com.io.data.CountryDetailQuery
import com.io.data.country.entity.CountryDetailEntity
import com.io.domain.models.CountryDetail

fun mapDetailModelToDBEntity(itemDetail: CountryDetailQuery.Country): CountryDetailEntity =
    itemDetail.let {
        CountryDetailEntity(
            0,
            it.code,
            it.name,
            it.phone,
            it.capital,
            it.currency,
            it.emoji
        )
    }

fun mapDBEntityToModels(itemDetail: CountryDetailEntity): CountryDetail =
    itemDetail.let {
        CountryDetail(
            it.code,
            it.name,
            it.phone,
            it.capital,
            it.currency,
            it.emoji
        )
    }