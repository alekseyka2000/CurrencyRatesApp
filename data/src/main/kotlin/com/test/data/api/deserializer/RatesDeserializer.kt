package com.test.data.api.deserializer

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.test.data.api.entity.Rate
import com.test.data.api.entity.Rates
import java.lang.reflect.Type

/**
 * Type adapter for retrofit factory which convert json response to Rates class
 *
 * @author YarakhovichAA
 */

class RatesDeserializer : JsonDeserializer<Rates> {

    companion object {
        private const val UNACCEPTABLE_SYMBOLS = "[{}\"]"
        private const val RATE_LIST_SEPARATOR = ","
        private const val RATE_FIELD_SEPARATOR = ":"
    }

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Rates {
        val symbols = json?.toString()?.replace(UNACCEPTABLE_SYMBOLS.toRegex(), "")
            ?.split(RATE_LIST_SEPARATOR)

        val rates = symbols?.map { name ->
            val rateList = name.split(RATE_FIELD_SEPARATOR)
            Rate(rateList.first().toString(), rateList.last().toDouble())
        }

        return Rates(rates ?: listOf())
    }

}