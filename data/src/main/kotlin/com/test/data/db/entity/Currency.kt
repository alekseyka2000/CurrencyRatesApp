package com.test.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="Currency")
data class Currency(
    @PrimaryKey @ColumnInfo(name = "currency_base")val base: String,
    val date: String?,
    val name: String,
    val rates: List<Pair<String, Double>>?
)