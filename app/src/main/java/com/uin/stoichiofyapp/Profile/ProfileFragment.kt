package com.uin.stoichiofyapp.Profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.uin.stoichiofyapp.AboutUs.AboutUsActivity
import com.uin.stoichiofyapp.AddActivity
import com.uin.stoichiofyapp.R
import com.uin.stoichiofyapp.Sign.SignOut.SignOutActivity
import com.uin.stoichiofyapp.Utils.Preferences
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.iv_profile
import kotlinx.android.synthetic.main.fragment_profile.tv_name

class ProfileFragment : Fragment() {

    private lateinit var preferences: Preferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        preferences = Preferences(activity!!.applicationContext)

        tv_name.setText(preferences.getValues("nama"))
        tv_email.setText(preferences.getValues("email"))

        Glide.with(this)
            .load(preferences.getValues("url"))
            .apply(RequestOptions.circleCropTransform())
            .into(iv_profile)

        editProfile.setOnClickListener {
            startActivity(Intent(activity, EditProfileActivity::class.java))
        }

        aboutUs.setOnClickListener {
            startActivity(Intent(activity, AboutUsActivity::class.java))
        }

        logout.setOnClickListener {
            startActivity(Intent(activity, SignOutActivity::class.java))
        }

        add_data.setOnClickListener {
            startActivity(Intent(activity, AddActivity::class.java))
        }
    }

}