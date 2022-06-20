package com.test.data.api.deserializer

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.test.data.api.entity.Name
import com.test.data.api.entity.Symbols
import java.lang.reflect.Type

/**
 * Type adapter for retrofit factory which convert json response to Symbols class
 *
 * @author YarakhovichAA
 */

class SymbolsDeserializer : JsonDeserializer<Symbols> {

    companion object {
        private const val UNACCEPTABLE_SYMBOLS = "[{}\"]"
        private const val RATE_LIST_SEPARATOR = ","
        private const val RATE_FIELD_SEPARATOR = ":"
    }

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Symbols {
        val symbols = json?.toString()?.replace(UNACCEPTABLE_SYMBOLS.toRegex(), "")
            ?.split(RATE_LIST_SEPARATOR)

        val names = symbols?.map { name ->
            val nameList = name.split(RATE_FIELD_SEPARATOR)
            Name(nameList.first().toString(), nameList.last().toString())
        }

        return Symbols(names ?: listOf())
    }

}