package com.test.domain

import com.test.domain.entity.RateModel
import com.test.domain.enum.Filters
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SortCurrencyRatesUseCaseImpl @Inject constructor(
    private val filtersGateway: FiltersGateway
) : SortCurrencyRatesUseCase {

    override fun sortCurrencyRates(rateList: List<RateModel>): List<RateModel> {
        return when (filtersGateway.getFilterTypeName()) {
            Filters.ALPHABETICALLY.name -> sortByAlphabet(rateList)
            Filters.BY_VALUE.name -> sortByValue(rateList)
            else -> rateList
        }
    }

    private fun sortByAlphabet(rateList: List<RateModel>): List<RateModel> {
        val sortedList = rateList.sortedBy { it.base }
        return applyOrderFilterToList(sortedList)
    }

    private fun sortByValue(rateList: List<RateModel>): List<RateModel> {
        val sortedList = rateList.sortedBy { it.rate }
        return applyOrderFilterToList(sortedList)
    }

    private fun applyOrderFilterToList(sortedList: List<RateModel>): List<RateModel> {
        return if (!filtersGateway.isOrderFilterByAscending()) {
            sortedList.reversed()
        } else {
            sortedList
        }
    }
}