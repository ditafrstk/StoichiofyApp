package com.uin.stoichiofyapp.Dashboard.Pembelajaran

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.uin.stoichiofyapp.Dashboard.Category.Pembelajaran
import com.uin.stoichiofyapp.Peta.PetaKonsepActivity
import com.uin.stoichiofyapp.PLO.PLOActivity
import com.uin.stoichiofyapp.R
import com.uin.stoichiofyapp.Silabus.SilabusActivity
import kotlinx.android.synthetic.main.item_home_two.view.*

class PembelajaranAdapter(private var data: List<Pembelajaran>,
                          private val listener: (Pembelajaran) -> Unit)
: RecyclerView.Adapter<PembelajaranAdapter.CategoryHolder>() {


    lateinit var contextAdapter : Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflatedView = layoutInflater.inflate(R.layout.item_home_two, parent, false)
        return CategoryHolder(inflatedView)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        holder.bindItem(data[position], listener, contextAdapter)
    }


    class CategoryHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imgAgenda = view.iv_home
        private val tvAgenda = view.tv_agenda

        @SuppressLint("CheckResult")
        fun bindItem(data: Pembelajaran, listener: (Pembelajaran) -> Unit, context : Context) {
            tvAgenda.text = data.agenda

            Glide.with(context)
                .load(data.image)
                .load(imgAgenda)

            when(data.agenda) {
                "Peta Konsep" -> {
                    itemView.setOnClickListener {
                        val intent = Intent(context,
                            PetaKonsepActivity::class.java)
                        listener(data)
                        context.startActivity(intent)
                    }
                }
                "Program Learning Outcome" -> {
                    itemView.setOnClickListener {
                        val intent = Intent(context,
                            PLOActivity::class.java)
                        listener(data)
                        context.startActivity(intent)
                    }
                }
                "Silabus Pembelajaran" -> {
                    itemView.setOnClickListener {
                        val intent = Intent(context,
                            SilabusActivity::class.java)
                        listener(data)
                        context.startActivity(intent)
                    }
                }
            }


        }
    }
}