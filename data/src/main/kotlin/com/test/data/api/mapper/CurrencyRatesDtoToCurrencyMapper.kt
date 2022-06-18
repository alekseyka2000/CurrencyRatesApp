package com.test.data.api.mapper

import com.test.data.api.entity.CurrencyRatesDto
import com.test.data.db.entity.Currency
import com.test.data.db.entity.Rate
import com.test.domain.entity.CurrencyNameModel

class CurrencyRatesDtoToCurrencyMapper(): (CurrencyRatesDto, CurrencyNameModel) -> Currency {
    override fun invoke(dto: CurrencyRatesDto, nameModel: CurrencyNameModel) : Currency {
        return Currency(
            base = nameModel.base,
            name = nameModel.name,
            date = dto.date,
            rates = dto.rates.rates.map { Rate(it.base, it.rate) }
        )
    }
}