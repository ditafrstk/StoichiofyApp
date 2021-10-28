package com.uin.stoichiofyapp.Bookmark

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.uin.stoichiofyapp.R
import com.uin.stoichiofyapp.Utils.Preferences
import kotlinx.android.synthetic.main.activity_bookmark.*
import kotlinx.android.synthetic.main.activity_guide.*

class BookmarkActivity : AppCompatActivity() {
    private lateinit var preferences: Preferences
    private lateinit var mDatabase: DatabaseReference

    private var dataBookmark = ArrayList<Bookmark>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bookmark)

        preferences = Preferences(this)
        mDatabase = FirebaseDatabase.getInstance().getReference("User")
            .child(preferences.getValues("username")!!)
            .child("Book Mark")

        rvBookmark.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        getData()

    }

    private fun getData() {
        mDatabase.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                dataBookmark.clear()
                for (dataSnapshot in snapshot.children) {
                    val bookmark = dataSnapshot.getValue(Bookmark::class.java)
                    val key = dataSnapshot.key.toString()
                    val judul = bookmark?.judul
                    val url = bookmark?.url
                    val tujuan = bookmark?.tujuan
                    val tujuan2 = bookmark?.tujuan2
                    val tujuan3 = bookmark?.tujuan3



                        dataBookmark.add(setData(key, judul, url, tujuan, tujuan2, tujuan3))
                    Log.v("12311", dataBookmark.toString())
                }

                if (dataBookmark.isNullOrEmpty()){
                    rvBookmark.visibility = View.GONE
                } else {
                    noData.visibility = View.GONE
                    rvBookmark.adapter = BookmarkAdapter(dataBookmark){
                        val intent = Intent(applicationContext,
                            SubBookmarkActivity::class.java
                        ).putExtra("judul", it.judul)
                            .putExtra("url", it.url)
                            .putExtra("tujuan", it.tujuan)
                            .putExtra("tujuan2", it.tujuan2)
                            .putExtra("tujuan3", it.tujuan3)
                        startActivity(intent)

                    }
                }

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@BookmarkActivity, " " + error.message, Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun setData(key: String?, judul: String?, url: String?, tujuan: String?, tujuan2: String?, tujuan3: String?): Bookmark{
        val data = Bookmark(
            key,
            judul,
            url, tujuan, tujuan2, tujuan3
        )
        return data
    }

}