package com.uin.stoichiofyapp.Dashboard.Category

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.uin.stoichiofyapp.Dashboard.Pembelajaran.Category
import com.uin.stoichiofyapp.Latihan.LatihanActivity
import com.uin.stoichiofyapp.Materi.MateriActivity
import com.uin.stoichiofyapp.R
import com.uin.stoichiofyapp.ReferensiActivity
import kotlinx.android.synthetic.main.item_home_one.view.*

class CategoryAdapter(private var data: List<Category>)
    : RecyclerView.Adapter<DashboardHolder>() {

    lateinit var contextAdapter : Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflatedView = layoutInflater.inflate(R.layout.item_home_one, parent, false)
        return DashboardHolder(inflatedView)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: DashboardHolder, position: Int) {
        holder.bindItem(data[position], contextAdapter)
    }
}

    class DashboardHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imgCategory = view.iv_poster_image
        private val tvCategory = view.tv_poster



        fun bindItem(data: Category, context: Context) {
            tvCategory.text = data.agenda

            Glide.with(context)
                .load(data.image)
                .into(imgCategory)

            when(data.agenda) {
                "Materi" -> {
                    itemView.setOnClickListener {
                        context.startActivity(Intent(context, MateriActivity::class.java))
                    }
                }
                "Latihan" -> {
                    itemView.setOnClickListener {
                        context.startActivity(Intent(context, LatihanActivity::class.java))
                    }
                }
                "Referensi" -> {
                    itemView.setOnClickListener {
                        context.startActivity(Intent(context, ReferensiActivity::class.java))
                    }
                }
                "Petunjuk Penggunaan" -> {

                }
            }


    }
}