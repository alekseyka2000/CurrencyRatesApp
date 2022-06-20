package com.test.domain

import javax.inject.Singleton

@Singleton
interface FiltersGateway {
    fun getFilterTypeName(): String?
    fun saveFilterTypeName(filterTypeName: String)
    fun isOrderFilterByAscending(): Boolean
    fun isOrderFilterByAscending(isByAscending: Boolean)
}