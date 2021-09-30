package com.uin.stoichiofyapp.Materi

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.uin.stoichiofyapp.Dashboard.Category.DashboardHolder
import com.uin.stoichiofyapp.R
import kotlinx.android.synthetic.main.list_materi.view.*

class MateriAdapter(private var data : List<Materi>,
                    private val listener: (Materi) -> Unit)
    : RecyclerView.Adapter<MateriAdapter.MateriHolder>() {

    lateinit var  contextAdapter : Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MateriHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflatedView = layoutInflater.inflate(R.layout.list_materi, parent, false)
        return MateriHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: MateriHolder, position: Int) {
        holder.bindItem(data[position], listener, contextAdapter)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class MateriHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvBab = view.perbab
        private val tvJudul = view.judul
        private val tvDesc1 = view.desc1
        private val tvDesc2 = view.desc2
        private val url = view.ic_materi


        fun bindItem(data: Materi, listener: (Materi) -> Unit, context: Context){

            tvBab.text = data.bab
            tvJudul.text = data.judul
            tvDesc1.text = data.desc1
            if (data.desc2!!.isEmpty()){
                tvDesc2.visibility = View.GONE
            } else {
                tvDesc2.text = data.desc2
            }


            Glide.with(context)
                .load(data.url)
                .into(url)

            itemView.setOnClickListener {
                listener(data)
            }

        }

    }

}
