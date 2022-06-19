package com.test.data.db

import androidx.room.*
import com.test.data.db.entity.Currency
import com.test.data.db.entity.Name
import com.test.data.db.entity.Rate

@Dao
interface CurrenciesDao {

    @Query("SELECT currency_base, name FROM Currency")
    fun getCurrencies(): List<Name>

    @Query("SELECT * FROM Currency WHERE currency_base = :base")
    fun getCurrencyRates(base: String): Currency

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(currencyItem: Currency)

    @Delete
    suspend fun delete(currencyItem: Currency)
}