package com.uin.stoichiofyapp.Trivia

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.uin.stoichiofyapp.R
import kotlinx.android.synthetic.main.item_trivia.view.*

class TriviaAdapter(private var data: List<Trivia>,
                    private val listener : (Trivia) -> Unit)
    : RecyclerView.Adapter<TriviaAdapter.TriviaHolder>() {

    lateinit var contextAdapter : Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TriviaHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflatedView = layoutInflater.inflate(R.layout.item_trivia, parent, false)
        return TriviaHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: TriviaHolder, position: Int) {
        holder.bindItem(data[position], contextAdapter, listener)
    }

    override fun getItemCount(): Int = data.size

    class TriviaHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imgTrivia = view.iv_trivia
        private val tvTrivia = view.tvTrivia

        fun bindItem(data: Trivia, context: Context, listener: (Trivia) -> Unit) {
            tvTrivia.text = data.title

            Glide.with(context)
                .load(data.url)
                .into(imgTrivia)

            itemView.setOnClickListener {
                listener(data)
            }
        }
    }


}