package com.io.data.country.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.io.data.country.dao.CountryDao
import com.io.data.country.entity.CountryDetailEntity
import com.io.data.country.entity.CountryEntity

@Database(entities = [CountryEntity::class, CountryDetailEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun countryDao(): CountryDao
}