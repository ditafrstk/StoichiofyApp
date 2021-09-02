package com.uin.stoichiofyapp.Dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.database.*
import com.uin.stoichiofyapp.Dashboard.Category.MateriAdapter
import com.uin.stoichiofyapp.Dashboard.Category.Pembelajaran
import com.uin.stoichiofyapp.Dashboard.Pembelajaran.Category
import com.uin.stoichiofyapp.Dashboard.Pembelajaran.CategoryAdapter
import com.uin.stoichiofyapp.R
import com.uin.stoichiofyapp.Utils.Preferences
import kotlinx.android.synthetic.main.fragment_dashboard.*

class DashboardFragment : Fragment() {

    private lateinit var preferences: Preferences
    private lateinit var mDatabase: DatabaseReference
    private lateinit var mDatabaseMateri : DatabaseReference

    private var dataPembelajaran = ArrayList<Pembelajaran>()
    private var dataCategory = ArrayList<Category>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        preferences = Preferences(activity!!.applicationContext)
        mDatabase = FirebaseDatabase.getInstance().getReference("Pembelajaran")
        mDatabaseMateri = FirebaseDatabase.getInstance().getReference("Category")

        tv_name.setText(preferences.getValues("nama"))
        if (!preferences.getValues("nama").equals("")){
        }

        Glide.with(this)
            .load(preferences.getValues("url"))
            .apply(RequestOptions.circleCropTransform())
            .into(iv_profile)


        rv_home.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        rv_dashboard.layoutManager = GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
        getData()
    }

    private fun getData() {
        mDatabase.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                dataPembelajaran.clear()
                for (dataSnapshot in snapshot.children) {
                    var cat = dataSnapshot.getValue(Pembelajaran::class.java)
                    val agenda = cat?.agenda
                    val image = cat?.image
                    dataPembelajaran.add(setData(agenda!!,image!!))
                }

                rv_home.adapter = CategoryAdapter(dataPembelajaran){

                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "" + error.message, Toast.LENGTH_LONG).show()
            }

        })


        mDatabaseMateri.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                dataCategory.clear()
                for (dataSnapshot in snapshot.children) {
                    var materi = dataSnapshot.getValue(Category::class.java)
                    val agenda = materi?.agenda
                    val image = materi?.image
                    dataCategory.add(setDataCategory(agenda!!,image!!))
                }

                rv_dashboard.adapter = MateriAdapter(dataCategory){

                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "" + error.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun setData(agenda: String, image : String): Pembelajaran {
        val data = Pembelajaran(
            agenda,
            image
        )
        return data
    }

    private fun setDataCategory(agenda: String, image : String): Category {
        val data = Category(
            agenda,
            image
        )
        return data
    }

//    private fun setData() {
//        val listHome = listOf(
//            Dashboard(image = R.drawable.txt, agenda = "Program Learing \n" +
//                    "Outcome"),
//            Dashboard(image = R.drawable.txt, agenda = "Peta \n" +
//                    "Konsep"),
//            Dashboard(image = R.drawable.txt, agenda = "Silabus \n" +
//                    "Pembelajaran")
//        )
//
//        val homeAdapter = CategoryAdapter(listHome)
//
//        rv_home.apply {
//            adapter = homeAdapter
//            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
//        }
//
//        val listDashboard = listOf(
//            Dashboard(image = R.drawable.menu_stoires, agenda = "Materi"),
//            Dashboard(image = R.drawable.menu_lesson, agenda = "Latihan"),
//            Dashboard(image = R.drawable.menu_source, agenda = "Refrensi"),
//            Dashboard(image = R.drawable.menu_quiz, agenda = "Petunjuk \n" +
//                    "Penggunaan")
//        )
//
//        val dashboardAdapter = MateriAdapter(listDashboard)
//
//        rv_dashboard.apply {
//            layoutManager = GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
//            adapter = dashboardAdapter
//        }
//    }
}