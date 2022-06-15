package com.test.data.db

import androidx.room.*
import com.test.data.db.entity.Currency
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrenciesDao {

    @Query("SELECT * FROM Currency")
    suspend fun getCurrencies(): Flow<MutableList<Currency>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(foodItem: Currency)

    @Delete
    suspend fun delete(foodItem: Currency)
}