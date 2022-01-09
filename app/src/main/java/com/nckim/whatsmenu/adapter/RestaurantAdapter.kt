package com.nckim.whatsmenu.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nckim.whatsmenu.R
import com.nckim.whatsmenu.data.RestaurantData

class RestaurantAdapter(private val context : Context) : RecyclerView.Adapter<RestaurantAdapter.ViewHolder>() {

    var datas = mutableListOf<RestaurantData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.restaurant_item_recycler, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        var mView = view
        val sectorName : TextView = mView.findViewById(R.id.sector_name)
        val restaurantName : TextView = mView.findViewById(R.id.restaurant_name)

        fun bind(item : RestaurantData){
            sectorName.text = item.sectortsName
            restaurantName.text = item.restaurantName
        }
    }


}