package com.test.data.db.entity

import androidx.room.TypeConverter

class RatesConverter {

    @TypeConverter
    fun fromRates(rates: List<Rate>): String {
        return rates.joinToString(",") { "${it.base}-${it.rate}" }
    }

    @TypeConverter
    fun toRates(data: String): List<Rate> {
        return data.split(",").map {
            val rate = it.split("-")
            Rate(rate.first(), rate.last().toDouble())
        }
    }
}
