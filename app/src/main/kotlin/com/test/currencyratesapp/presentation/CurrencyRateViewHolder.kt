package com.test.currencyratesapp.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.test.currencyratesapp.R
import com.test.domain.entity.RateModel

class CurrencyRateViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.currency_rate_item, parent, false)) {
    private var currencyNameText: TextView = itemView.findViewById(R.id.currencyName)
    private var currencyRateText: TextView = itemView.findViewById(R.id.currencyRate)
    private var cryptocurrencyPriceUSDText: CheckBox = itemView.findViewById(R.id.favoriteIndicator)

    fun bind(model: RateModel) {
        currencyNameText.text = model.base
        currencyRateText.text = model.rate.toString()
    }
}
