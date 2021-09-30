package com.uin.stoichiofyapp.PLO

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.uin.stoichiofyapp.AboutUs.getAbout
import com.uin.stoichiofyapp.R
import kotlinx.android.synthetic.main.item_about_us.view.*

class PLOAdapter(private var data: List<getAbout>,
                 private val listener: (getAbout) -> Unit)
    : RecyclerView.Adapter<PLOAdapter.ViewHolder>() {

    lateinit var  contextAdapter: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflatedView = layoutInflater.inflate(R.layout.item_about_us, parent, false)
        return ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(data[position], listener, contextAdapter)
    }

    override fun getItemCount(): Int = data.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvAbout = view.tv_data

        fun bindItem(data: getAbout, listener: (getAbout) -> Unit, context: Context){
            tvAbout.text = data.desc

        }
    }

}
