package com.test.currencyratesapp.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

/**
 *  @author YarakhovichAA
 */

class CurrencyRateListAdapter(private val clickAction: (String) -> Unit) :
    RecyclerView.Adapter<CurrencyRateViewHolder>() {

    private var rateList: MutableList<PresentationRateModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyRateViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CurrencyRateViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: CurrencyRateViewHolder, position: Int) {
        holder.bind(rateList[position], clickAction)
    }

    override fun getItemCount(): Int = rateList.size

    fun setList(
        newRateList: List<PresentationRateModel>
    ) {
        val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return rateList[oldItemPosition].currencyBase == newRateList[newItemPosition].currencyBase
            }

            override fun areContentsTheSame(
                oldItemPosition: Int,
                newItemPosition: Int
            ): Boolean {
                return rateList[oldItemPosition] == newRateList[newItemPosition]
            }

            override fun getOldListSize() = rateList.size
            override fun getNewListSize() = newRateList.size
        })

        rateList.clear()
        rateList.addAll(newRateList)

        diff.dispatchUpdatesTo(this)
    }
}

data class PresentationRateModel(
    val currencyBase: String,
    val rate: String,
    val isFavorite: Boolean
)