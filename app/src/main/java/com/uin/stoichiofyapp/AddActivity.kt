package com.uin.stoichiofyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import com.uin.stoichiofyapp.AboutUs.addAbout
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity : AppCompatActivity() {

    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("About").child("Dosen Pembimbing")
    val arrayUser = arrayListOf<addAbout>()

    lateinit var addData : String
    lateinit var addData2: String
    lateinit var addData3 : String
    lateinit var addData4 : String
    lateinit var addData5 : String
    lateinit var addData6 : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)


//        val desc = et_data.text.toString()

        btn_add.setOnClickListener {

            addData = et_data.text.toString()
            addData2 = et_data2.text.toString()
            addData3 = et_data3.text.toString()
            addData4 = et_data4.text.toString()
            addData5 = et_data5.text.toString()
            addData6 = et_data.text.toString()

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
