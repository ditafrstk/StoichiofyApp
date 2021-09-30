package com.uin.stoichiofyapp.PLO

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.barteksc.pdfviewer.PDFView
import com.google.firebase.database.*
import com.uin.stoichiofyapp.AboutUs.getAbout
import com.uin.stoichiofyapp.R
import kotlinx.android.synthetic.main.activity_about_us.*

class PLOActivity : AppCompatActivity() {

    private lateinit var mDatabase: DatabaseReference
    private var dataAboutUs = ArrayList<getAbout>()

    lateinit var pdfView: PDFView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_polactivity)

        pdfView = findViewById(R.id.pdfView)

        pdfView.fromAsset("Program Learning Outcome.pdf")
            .enableDoubletap(true)
            .load()
//        mDatabase = FirebaseDatabase.getInstance().getReference("About")

        iv_back.setOnClickListener {
            onBackPressed()
        }
//
//
//        rv_aboutUs.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
//        getData()

    }

//    private fun getData() {
//        mDatabase.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                dataAboutUs.clear()
//                for(dataSnapshot in snapshot.children){
//                    val about = dataSnapshot.getValue(getAbout::class.java)
//                    Log.v("15112", about.toString())
//                    val key = dataSnapshot.key.toString()
//                    val data = about?.desc
//
//                    dataAboutUs.add(setData(key, data!!))
//                }
//
//                if (dataAboutUs.isNotEmpty()){
//                    rv_aboutUs.adapter = PLOAdapter(dataAboutUs){
//
//                    }
//                }
//
//
//
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                Toast.makeText(this@PLOActivity, "" + error.message, Toast.LENGTH_LONG).show()
//            }
//
//        })
//    }
//
//    private fun setData(key: String, desc: String) : getAbout {
//        val data = getAbout(
//            key,
//            desc
//        )
//        return data
//    }
}