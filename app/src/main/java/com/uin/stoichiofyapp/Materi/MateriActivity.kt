package com.uin.stoichiofyapp.Materi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.uin.stoichiofyapp.Materi.bab.BabActivity
import com.uin.stoichiofyapp.R
import com.uin.stoichiofyapp.Utils.Preferences
import kotlinx.android.synthetic.main.activity_materi.*
import java.nio.channels.spi.AbstractSelectionKey


class MateriActivity : AppCompatActivity() {

    private lateinit var preferences: Preferences
    private lateinit var mDatabase: DatabaseReference

    private var dataMateri = ArrayList<Materi>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_materi)

        preferences = Preferences(this)
        mDatabase = FirebaseDatabase.getInstance().getReference("Materi")

        iv_back.setOnClickListener {
            onBackPressed()
        }

        rv_materi.layoutManager = GridLayoutManager(this, 1, RecyclerView.VERTICAL, false)

        getData()
    }

    private fun getData() {
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                dataMateri.clear()
                for (dataSnapshot in snapshot.children){

                    var materi = dataSnapshot.getValue(Materi::class.java)
                    val judul = materi?.judul
                    val desc1 = materi?.desc1
                    val desc2 = materi?.desc2
                    val bab = materi?.bab
                    val url = materi?.url
                    val key = dataSnapshot.key.toString()

                    dataMateri.add(setData(judul!!,desc1!!,desc2!!,bab!!,url!!, key!!))
                }

                rv_materi.adapter = MateriAdapter(dataMateri){
                    val intent = Intent(applicationContext,
                    BabActivity::class.java).putExtra("data bab", it)
                    startActivity(intent)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@MateriActivity, "" + error.message, Toast.LENGTH_LONG).show()
            }

        })

    }


    private fun setData(judul: String, desc1: String, desc2: String, bab: String, url: String, key: String): Materi {
        val data = Materi(
            judul,
            desc1,
            desc2,
            bab,
            url,
            key
        )
        return data
    }
}