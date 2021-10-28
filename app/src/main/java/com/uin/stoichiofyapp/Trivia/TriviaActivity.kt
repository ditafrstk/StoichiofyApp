package com.uin.stoichiofyapp.Trivia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.uin.stoichiofyapp.R
import com.uin.stoichiofyapp.Trivia.subTrivia.SubTriviaActivity
import com.uin.stoichiofyapp.Trivia.subTrivia.SubTriviaActivityJava
import kotlinx.android.synthetic.main.activity_trivia.*

class TriviaActivity : AppCompatActivity() {

    private lateinit var mDatabase: DatabaseReference
    private var dataTrivia = ArrayList<Trivia>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trivia)

        mDatabase = FirebaseDatabase.getInstance().getReference("Trivia")
        rvTrivia.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        iv_back.setOnClickListener {
            onBackPressed()
        }

        getData()
    }


    private fun getData() {
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                dataTrivia.clear()
                for (dataSnapshot in snapshot.children) {
                    val trivia = dataSnapshot.getValue(Trivia::class.java)
                    val key = dataSnapshot.key.toString()
                    val url = trivia?.url
                    val title = trivia?.title
//                    Log.v("124", key)
                    dataTrivia.add(setData(url!!, title!!, key))
                }

                rvTrivia.adapter = TriviaAdapter(dataTrivia) {
                    val intent = Intent(applicationContext,
                        SubTriviaActivityJava::class.java
                    ).putExtra("data trivia", it)
                        .putExtra("key", it.key)
                    startActivity(intent)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@TriviaActivity, "" + error.message, Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun setData(url: String, title: String, key: String): Trivia {
        val data = Trivia(
            key,
            url,
            title
        )
        return data

    }

}