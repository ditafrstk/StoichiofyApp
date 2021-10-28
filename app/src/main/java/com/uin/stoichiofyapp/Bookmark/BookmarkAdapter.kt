package com.uin.stoichiofyapp.Bookmark

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.uin.stoichiofyapp.R
import com.uin.stoichiofyapp.Utils.Preferences
import kotlinx.android.synthetic.main.item_bookmark.view.*

class BookmarkAdapter(private var data: List<Bookmark>,
                        private val listener: (Bookmark) -> Unit) :
    RecyclerView.Adapter<BookmarkAdapter.BookmarkHolder>() {

    lateinit var contextAdapter : Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflatedView = layoutInflater.inflate(R.layout.item_bookmark, parent, false)
        return BookmarkHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: BookmarkHolder, position: Int) {
        holder.bindItem(data[position], listener, contextAdapter)
    }

    override fun getItemCount(): Int = data.size


    class BookmarkHolder(view: View) : RecyclerView.ViewHolder(view) {
        lateinit var preferences: Preferences
        lateinit var mDatabase: DatabaseReference
        private val tvBookmark = view.tvBookmark
        private val ivDelete = view.ivDelete


        fun bindItem(data: Bookmark, listener: (Bookmark) -> Unit, context: Context) {
            tvBookmark.text = data.judul

            itemView.setOnClickListener {
                listener(data)
            }

            preferences = Preferences(context.applicationContext)
            mDatabase = FirebaseDatabase.getInstance().getReference("User")
                .child(preferences.getValues("username")!!)
                .child("Book Mark")
                .child(data.key.toString())

            itemView.ivDelete.setOnClickListener {
                mDatabase.removeValue()
            }

        }
    }



    }


