package com.uin.stoichiofyapp.Materi.bab

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.uin.stoichiofyapp.Materi.Bab
import com.uin.stoichiofyapp.Materi.Materi
import com.uin.stoichiofyapp.R
import com.uin.stoichiofyapp.Utils.Preferences
import kotlinx.android.synthetic.main.activity_bab.*
import kotlinx.android.synthetic.main.activity_show_bab.*
import kotlinx.android.synthetic.main.fragment_sub_materi.*
import java.io.InputStream

class BabActivity : AppCompatActivity() {

    private lateinit var preferences: Preferences
    private lateinit var mDatabase: DatabaseReference

    private var databab = ArrayList<Bab>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bab)

        val data = intent.getParcelableExtra<Materi>("data bab")
        Log.v("DATA PERBAB", "$data")

        tvBab.text = data!!.bab
        tvTitle.text = data.judul

        iv_back.setOnClickListener {
            onBackPressed()
        }


        preferences = Preferences(this)
        mDatabase = FirebaseDatabase.getInstance().getReference("Materi")
            .child(data!!.key!!)

        rv_bab.layoutManager = GridLayoutManager(this, 1, RecyclerView.VERTICAL, false)

        getData(data.bab!!, data.judul!!)
    }

    private fun getData(bab: String, judul: String) {
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                databab.clear()
                for (dataSnapshot in snapshot.children){
                    for(getchild in dataSnapshot.children){
                        val bab = getchild.getValue(Bab::class.java)
                        val title = bab?.judul
                        val tujuan = bab?.tujuan
                        val tujuan2 = bab?.tujuan2
                        val tujuan3 = bab?.tujuan3
                        val url = bab?.url

                        databab.add(setData(title!!, url!!, tujuan!!, tujuan2!!, tujuan3!!))
                    }

                }
                rv_bab.adapter = BabAdapter(databab) {
                    val intent = Intent(applicationContext,
                    showBabActivity::class.java)
                        .putExtra("pdf", it.url)
                        .putExtra("title", it.judul)
                        .putExtra("bab", bab)
                        .putExtra("tujuan", it.tujuan)
                        .putExtra("tujuan2", it.tujuan2)
                        .putExtra("tujuan3", it.tujuan3)
                        .putExtra("judul", judul)
                    startActivity(intent)
                }

                }



            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@BabActivity, "" + error.message, Toast.LENGTH_LONG).show()
            }

        })
    }


    private fun setData(judul: String, url: String, tujuan: String, tujuan2: String, tujuan3: String) : Bab {
        val data = Bab(
            judul,
            url,
            tujuan,
            tujuan2,
            tujuan3
        )
        return data
    }

}