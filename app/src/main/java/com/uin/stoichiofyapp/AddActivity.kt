package com.uin.stoichiofyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import com.uin.stoichiofyapp.AboutUs.addAbout
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity : AppCompatActivity() {

    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("Materi").push()
        .child("Bab 4")
    val arrayUser = arrayListOf<addAbout>()

    lateinit var addData : String
    lateinit var addData2: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)


//        val desc = et_data.text.toString()

        btn_add.setOnClickListener {
            addData = et_data.text.toString()
//            addData2 = et_data2.text.toString()

            addingPudding(addData)
            Toast.makeText(applicationContext, "Berhasil", Toast.LENGTH_SHORT).show()

        }
    }



    private fun addingPudding(desc1: String){
        val pudding = addAbout(
            desc1
        )
        myRef.push().setValue(pudding)
    }



}
