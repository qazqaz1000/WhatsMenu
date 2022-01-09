package com.nckim.whatsmenu.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.nckim.whatsmenu.R
import com.nckim.whatsmenu.RaffleActivity
import com.nckim.whatsmenu.data.AreaData

class AreaAdapter(private val context : Context) : RecyclerView.Adapter<AreaAdapter.ViewHolder>() {

    var datas = mutableListOf<AreaData>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.area_item_recycler, parent , false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun getItemCount(): Int {
        return datas.size
    }


    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        var mView : View = view
        private val areaName : TextView = itemView.findViewById(R.id.area_name)


        fun bind(item : AreaData){
            areaName.text = item.name
            mView.setOnClickListener(View.OnClickListener {
//                Toast.makeText(context, "name : " + areaName.text,Toast.LENGTH_SHORT).show()
                val intent = Intent(context, RaffleActivity::class.java)
                context.startActivity(intent)
            })
        }
    }
}