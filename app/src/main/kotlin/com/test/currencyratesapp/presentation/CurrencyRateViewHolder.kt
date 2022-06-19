package com.test.currencyratesapp.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.test.currencyratesapp.R

class CurrencyRateViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.currency_rate_item, parent, false)) {
    private var currencyNameText: TextView = itemView.findViewById(R.id.currencyName)
    private var currencyRateText: TextView = itemView.findViewById(R.id.currencyRate)
    private var favoriteCheckBox: CheckBox = itemView.findViewById(R.id.favoriteIndicator)

    fun bind(model: PresentationRateModel, clickAction: (String) -> Unit) {
        currencyNameText.text = model.currencyBase
        currencyRateText.text = model.rate
        favoriteCheckBox.isChecked = model.isFavorite
        favoriteCheckBox.setOnCheckedChangeListener{ _, _ ->
            clickAction.invoke(model.currencyBase)
        }
    }
}
