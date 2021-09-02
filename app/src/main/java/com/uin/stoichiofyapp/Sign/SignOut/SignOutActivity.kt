package com.uin.stoichiofyapp.Sign.SignOut

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.uin.stoichiofyapp.Sign.SignIn.SignInActivity
import com.uin.stoichiofyapp.Utils.Preferences

class SignOutActivity : AppCompatActivity() {
    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        preferences = Preferences(applicationContext)

        val pref = preferences.sharedPref
        val edit = pref.edit()
        edit.remove("user")
        edit.remove("status")
        edit.commit()

        finishAffinity()
        Toast.makeText(this@SignOutActivity, "Berhasil Logout", Toast.LENGTH_SHORT).show()

        val intent = Intent(this@SignOutActivity, SignInActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)

    }
}