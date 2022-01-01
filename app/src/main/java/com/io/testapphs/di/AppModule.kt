package com.io.testapphs.di

import android.content.Context
import androidx.room.Room
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import com.io.data.country.db.AppDatabase
import com.io.data.country.network.okHttpClient
import com.io.testapphs.BuildConfig.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun apolloFit(): ApolloClient =
        ApolloClient.Builder()
            .serverUrl(BASE_URL)
            .okHttpClient(okHttpClient)
            .build()

    @Singleton
    @Provides
    fun database(@ApplicationContext app: Context):
            AppDatabase = Room
        .databaseBuilder(app, AppDatabase::class.java, "database")
        .fallbackToDestructiveMigration().build()

}