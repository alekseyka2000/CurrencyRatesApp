package com.test.data.api.deserializer

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.test.data.api.entity.Name
import com.test.data.api.entity.Rate
import com.test.data.api.entity.Rates
import java.lang.reflect.Type

class RatesDeserializer : JsonDeserializer<Rates> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Rates {

        val symbols = json?.toString()?.replace("[{}\"]".toRegex(), "")?.split(",")

        val rates = symbols?.map { name ->
            val rateList = name.split(":")
            Rate(rateList.first().toString(), rateList.last().toDouble())
        }

        return Rates(rates ?: listOf())
    }

}