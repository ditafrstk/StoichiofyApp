package com.uin.stoichiofyapp.OnBoarding

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.uin.stoichiofyapp.R
import kotlinx.android.synthetic.main.activity_on_boarding_one.*
import java.util.prefs.Preferences

class OnBoardingOneActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding_one)

        btn_next.setOnClickListener {
            val intent = Intent(this@OnBoardingOneActivity,
            OnBoardingTwoActivity::class.java)
            startActivity(intent)
        }
    }
}
