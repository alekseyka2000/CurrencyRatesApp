package com.test.domain

interface FiltersGateway {
    fun getFilterTypeName(): String?
    fun saveFilterTypeName(filterTypeName: String)
    fun isOrderFilterByAscending(): Boolean
    fun isOrderFilterByAscending(isByAscending: Boolean)
}