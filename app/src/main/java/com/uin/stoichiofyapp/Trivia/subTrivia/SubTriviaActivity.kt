package com.uin.stoichiofyapp.Trivia.subTrivia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.*
import com.uin.stoichiofyapp.R
import com.uin.stoichiofyapp.Trivia.LearningPoint
import com.uin.stoichiofyapp.Trivia.Trivia
import kotlinx.android.synthetic.main.activity_sub_trivia.*

class SubTriviaActivity : AppCompatActivity() {

    private  lateinit var mDatabase : DatabaseReference
    private var dataSubTrivia = ArrayList<LearningPoint>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub_trivia)

        val data = intent.getParcelableExtra<Trivia>("data trivia")
        mDatabase = FirebaseDatabase.getInstance().getReference("Trivia")
            .child(data!!.key!!)

//        Log.v("191", data!!.key.toString())

        rv_subtrivia.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        getData()

    }

//    override fun onStart() {
//        super.onStart()
//        val firebaseRecyclerAdapter = FirebaseRecyclerAdapter<member, ViewHolder>(
//            member::class.java,
//            R.layout.item_subtrivia,
//            ViewHolder::class.java
//        ) {
//
//        }
//    }
//
//    private fun <T, U> FirebaseRecyclerAdapter(
//        java: Class<T>,
//        itemSubtrivia: Int,
//        java1: Class<U>,
//        function: () -> Unit
//    ): {
//        TODO("Not yet implemented")
//    }


    private fun getData() {
        mDatabase.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                dataSubTrivia.clear()
                for (dataSnapshot in snapshot.children) {
                    for(snapshot in dataSnapshot.children){
                        val subTrivia = snapshot.getValue(LearningPoint::class.java)
                        val url = subTrivia!!.url
                        val title = subTrivia.title
                        val subtitle = subTrivia.subtitle
                        Log.v("1523", url!!)
                        Log.v("1523", title!!)
                        Log.v("1523", subtitle!!)
                        dataSubTrivia.add(setData(url!!,title!!,subtitle!!))
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@SubTriviaActivity, "" + error.message, Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun setData(url: String, title: String, subtitle: String): LearningPoint {
        val data = LearningPoint(
            url,
            title,
            subtitle
        )
        return data

    }
}