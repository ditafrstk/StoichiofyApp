package com.uin.stoichiofyapp.OnBoarding

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.uin.stoichiofyapp.R
import com.uin.stoichiofyapp.Sign.SignIn.SignInActivity
import com.uin.stoichiofyapp.Sign.SignUp.SignUpActivity
import com.uin.stoichiofyapp.Utils.Preferences
import kotlinx.android.synthetic.main.activity_on_boarding_one.*
import kotlinx.android.synthetic.main.activity_on_boarding_two.*
import kotlinx.android.synthetic.main.activity_sign_in.*

class OnBoardingTwoActivity : AppCompatActivity() {

    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding_two)

        preferences = Preferences(this)

        if (preferences.getValues("onboarding").equals("1")) {
            finishAffinity()

            val intent = Intent(this@OnBoardingTwoActivity,
                SignInActivity::class.java)
            startActivity(intent)
        }

        btn_start.setOnClickListener {
            val intent = Intent(this@OnBoardingTwoActivity,
                SignUpActivity::class.java)
            startActivity(intent)
        }

        sign_in.setOnClickListener {
            preferences.setValues("onboarding", "1")
            finishAffinity()

            val intent = Intent(this@OnBoardingTwoActivity,
                SignInActivity::class.java)
            startActivity(intent)
        }
    }
}