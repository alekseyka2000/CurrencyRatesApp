package com.test.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "Currency")
@TypeConverters(RatesConverter::class)
data class Currency(
    @PrimaryKey @ColumnInfo(name = "currency_base") val base: String,
    val name: String,
    val date: String? = null,
    val rates: List<Rate> = listOf()
)

data class Rate(
    val base: String,
    val rate: Double
)

data class Name(
    @ColumnInfo(name = "currency_base") val base: String,
    @ColumnInfo(name = "name") val name: String
)
