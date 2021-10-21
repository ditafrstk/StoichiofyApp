package com.uin.stoichiofyapp.AboutUs

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.uin.stoichiofyapp.R
import kotlinx.android.synthetic.main.item_card_about.view.*

class DosenAdapter(private var data: List<getDosen>)
    : RecyclerView.Adapter<DosenHolder>() {

    lateinit var contextAdapter : Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DosenHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflatedView = layoutInflater.inflate(R.layout.item_card_about, parent, false)
        return DosenHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: DosenHolder, position: Int) {
        holder.bindItem(data[position], contextAdapter)
    }

    override fun getItemCount(): Int = data.size

}

class DosenHolder(view: View) : RecyclerView.ViewHolder(view){
    private val tvNamaDosen = view.tvNamaDosen
    private val tvNomorDosen = view.tvNomorIndukDosen
    private val tvEmailDosen = view.tvEmailDosen
    private val tvJurusanDosen = view.tvJurusanDosen
    private val tvUniversitasDosen = view.tvUniversitasDosen
    private val ivDosen = view.ivDosen

    fun bindItem(data: getDosen, context: Context) {
        tvNamaDosen.text = data.nama
        tvEmailDosen.text = data.email
        tvNomorDosen.text = data.nomor_induk
        tvJurusanDosen.text = data.jurusan
        tvUniversitasDosen.text = data.universitas

        Glide.with(context)
            .load(data.url)
            .apply(RequestOptions.circleCropTransform())
            .into(ivDosen)
    }
}