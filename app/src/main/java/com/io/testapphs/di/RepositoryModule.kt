package com.io.testapphs.di

import com.io.data.country.repository.CountryListRepositoryImpl
import com.io.data.country.source.CountrySource
import com.io.domain.country.usecase.CountryDetailUseCase
import com.io.domain.country.usecase.CountryListUseCase
import com.io.domain.repository.CountryRepository
import com.io.domain.source.CountrySourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun sourceRepo(
        source: CountrySource
    ): CountryRepository = CountryListRepositoryImpl(source)


    @Provides
    fun provideUseCase(repository: CountryListRepositoryImpl): CountryListUseCase {
        return CountryListUseCase(repository)
    }

    @Provides
    fun provideDetailUseCase(repository: CountryListRepositoryImpl): CountryDetailUseCase {
        return CountryDetailUseCase(repository)
    }

}