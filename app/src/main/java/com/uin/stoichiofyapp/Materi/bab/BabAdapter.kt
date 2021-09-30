package com.uin.stoichiofyapp.Materi.bab

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.uin.stoichiofyapp.Materi.Bab
import com.uin.stoichiofyapp.R
import kotlinx.android.synthetic.main.item_list.view.*

class BabAdapter(private var data: List<Bab>,
                 private val listener: (Bab) -> Unit)
    : RecyclerView.Adapter<BabAdapter.BabHolder>(){

    lateinit var contextAdapter : Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BabHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflatedView = layoutInflater.inflate(R.layout.item_list, parent, false)
        return BabHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: BabHolder, position: Int) {
        holder.bindItem(data[position], listener, contextAdapter)
    }

    override fun getItemCount(): Int = data.size

    class BabHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvJudul = view.judul

        fun bindItem(data: Bab, listener: (Bab) -> Unit, context: Context){

            tvJudul.text = data.judul

            itemView.setOnClickListener {
                listener(data)
            }


        }
    }


}