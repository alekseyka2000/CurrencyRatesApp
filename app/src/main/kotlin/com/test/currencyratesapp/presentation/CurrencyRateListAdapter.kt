package com.test.currencyratesapp.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.test.domain.entity.RateModel

class CurrencyRateListAdapter : RecyclerView.Adapter<CurrencyRateViewHolder>() {

    private var rateList: MutableList<RateModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyRateViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CurrencyRateViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: CurrencyRateViewHolder, position: Int) {
        holder.bind(rateList[position])
//            holder.itemView.setOnClickListener {
//                cellClickListener(listCryptocurrencys[position].cryptocurrencyName)
//            }
    }

    override fun getItemCount(): Int = rateList.size

    fun setList(
        newRateList: List<RateModel>
    ) {
        val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return rateList[oldItemPosition].base == newRateList[newItemPosition].base
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