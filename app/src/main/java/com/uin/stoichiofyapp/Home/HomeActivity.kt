package com.uin.stoichiofyapp.Home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.uin.stoichiofyapp.Dashboard.DashboardFragment
import com.uin.stoichiofyapp.Bookmark.BookmarkActivity
import com.uin.stoichiofyapp.Profile.ProfileFragment
import com.uin.stoichiofyapp.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val fragmentHome = DashboardFragment()
        val fragmentProfile = ProfileFragment()

        setFragment(fragmentHome)

        iv_menu1.setOnClickListener {
            setFragment(fragmentHome)

            changeIcon(iv_menu1, R.drawable.ic_menu_home)
            changeIcon(iv_menu2, R.drawable.ic_menu_profile)
        }

        iv_menu2.setOnClickListener {
            setFragment(fragmentProfile)

            changeIcon(iv_menu1, R.drawable.ic_menu_home)
            changeIcon(iv_menu2, R.drawable.ic_menu_profile)
        }


        fab.setOnClickListener{
            val intent = Intent(this@HomeActivity,
            BookmarkActivity::class.java)
            startActivity(intent)
        }
    }

    protected fun setFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.layout_frame, fragment)
        fragmentTransaction.commit()
    }

    private fun changeIcon(imageView: ImageView, int: Int){
        imageView.setImageResource(int)
    }
}