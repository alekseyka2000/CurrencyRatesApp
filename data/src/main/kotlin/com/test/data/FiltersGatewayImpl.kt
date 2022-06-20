package com.test.data

import com.test.data.preference.PreferencesProvider
import com.test.domain.FiltersGateway
import javax.inject.Inject

/**
 * Class provide currencies data
 *
 *  @author YarakhovichAA
 */

class FiltersGatewayImpl @Inject constructor(
    private val preferencesProvider: PreferencesProvider
) : FiltersGateway {

    override fun getFilterTypeName() = preferencesProvider.getFilterTypeName()

    override fun saveFilterTypeName(filterTypeName: String) {
        preferencesProvider.saveFilterTypeName(filterTypeName)
    }

    override fun isOrderFilterByAscending() = preferencesProvider.isOrderFilterByAscending()

    override fun isOrderFilterByAscending(isByAscending: Boolean) {
        preferencesProvider.isOrderFilterByAscending(isByAscending)
    }
}