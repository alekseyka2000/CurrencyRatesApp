package com.test.data.api

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.test.data.api.entity.Name
import com.test.data.api.entity.Symbols
import java.lang.reflect.Type

class SymbolsDeserializer : JsonDeserializer<Symbols> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Symbols {

        val symbols = json?.toString()?.replace("\"{} ", "")?.split(",")

        val names = symbols?.map { name ->
            val nameList = name.split(":")
            Name(nameList.first().toString(), nameList.last().toString())
        }

        return Symbols(names ?: listOf())
    }

}