package com.uin.stoichiofyapp.Dashboard

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.uin.stoichiofyapp.R
import com.uin.stoichiofyapp.Utils.Preferences
import kotlinx.android.synthetic.main.fragment_dashboard.*

class DashboardFragment : Fragment() {

    private lateinit var preferences: Preferences

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

        tv_name.setText(preferences.getValues("nama"))
        if (!preferences.getValues("nama").equals("")){
        }

        Glide.with(this)
            .load(preferences.getValues("url"))
            .apply(RequestOptions.circleCropTransform())
            .into(iv_profile)

        Log.v("tamvan", "url "+preferences.getValues("url"))

        rv_home.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        rv_dashboard.layoutManager = GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
        setData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData()
    }

    private fun setData() {
        val listHome = listOf(
            Dashboard(image = R.drawable.txt, agenda = "Program Learing \n" +
                    "Outcome"),
            Dashboard(image = R.drawable.txt, agenda = "Peta \n" +
                    "Konsep"),
            Dashboard(image = R.drawable.txt, agenda = "Silabus \n" +
                    "Pembelajaran")
        )

        val homeAdapter = DashboardAdapter(listHome)

        rv_home.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = homeAdapter
        }

        val listDashboard = listOf(
            Dashboard(image = R.drawable.menu_stoires, agenda = "Materi"),
            Dashboard(image = R.drawable.menu_lesson, agenda = "Latihan"),
            Dashboard(image = R.drawable.menu_source, agenda = "Refrensi"),
            Dashboard(image = R.drawable.menu_quiz, agenda = "Petunjuk \n" +
                    "Penggunaan")
        )

        val dashboardAdapter = DashboardAdapter(listDashboard)

        rv_dashboard.apply {
            layoutManager = GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
            adapter = dashboardAdapter
        }
    }
}