package com.example.mvvmtestproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmtestproject.R
import com.example.mvvmtestproject.data.model.CityData
import com.example.mvvmtestproject.databinding.ItemCityBinding
import javax.inject.Inject

class CityAdapter @Inject constructor() : RecyclerView.Adapter<CityAdapter.ItemViewHolder>() {

    private val labListData: ArrayList<CityData> = ArrayList()
    private var listener : OnCityClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(parent)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(labListData[position])
    }

    override fun getItemCount() = labListData.size

    fun setClickListener(listener1 :OnCityClickListener){
        listener  = listener1
    }

    fun update(list: List<CityData>) {
        this.labListData.clear()
        this.labListData.addAll(list)
        notifyDataSetChanged()
    }

    inner class ItemViewHolder(
        private val parent: ViewGroup,
        private val binding: ItemCityBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_city,
            parent,
            false
        )
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            item: CityData) {
            binding.cityData = item
            binding.root.setOnClickListener { listener!!.onCityClicked(item) }
        }
    }

    interface OnCityClickListener{
        fun onCityClicked( data : CityData)
    }
}