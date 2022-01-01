package com.io.data.country.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.io.data.country.entity.CountryDetailEntity
import com.io.data.country.entity.CountryEntity

@Dao
interface CountryDao {

    @Query("SELECT * FROM countryEntity")
    suspend fun getAll(): List<CountryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAll(list: List<CountryEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDetail(detail: CountryDetailEntity)

    @Query("SELECT * FROM countrydetailentity WHERE code = :code")
    suspend fun getByName(code: String): CountryDetailEntity?


}