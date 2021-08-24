package com.uin.stoichiofyapp.Dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.uin.stoichiofyapp.R
import kotlinx.android.synthetic.main.item_home_two.view.*

class DashboardAdapter(private val dashboard: List<Dashboard>) : RecyclerView.Adapter<DashboardHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): DashboardHolder {
        return DashboardHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.item_home_two, viewGroup, false))
    }

    override fun getItemCount(): Int = dashboard.size

    override fun onBindViewHolder(holder: DashboardHolder, position: Int) {
        holder.bindHero(dashboard[position])
    }
}

class DashboardHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val imgAgenda = view.iv_home
    private val tvAgenda  = view.tv_agenda

    fun bindHero(dashboard: Dashboard) {
        imgAgenda.setImageResource(dashboard.image)
        tvAgenda.text = dashboard.agenda
    }
}