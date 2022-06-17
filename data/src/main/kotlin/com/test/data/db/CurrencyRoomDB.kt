package com.test.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.test.data.db.entity.Currency
import dagger.Provides

@Database(entities = [Currency::class], version = 1, exportSchema = false)
abstract class CurrencyRoomDB : RoomDatabase() {

    abstract fun currenciesDao(): CurrenciesDao

    companion object {

        @Volatile
        private var INSTANCE: CurrencyRoomDB? = null

        fun getDatabase(context: Context): CurrencyRoomDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CurrencyRoomDB::class.java,
                    "currencies_database"
                ).build()

                INSTANCE = instance

                // return instance
                instance
            }
        }
    }
}
