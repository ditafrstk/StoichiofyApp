package com.uin.stoichiofyapp.Sign.SignUp

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.uin.stoichiofyapp.Home.HomeActivity
import com.uin.stoichiofyapp.R
import com.uin.stoichiofyapp.Sign.SignIn.User
import com.uin.stoichiofyapp.Utils.Preferences
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.activity_sign_up.btn_sign_up
import java.util.*

class SignUpActivity : AppCompatActivity() {

    lateinit var sUsername: String
    lateinit var sPassword: String
    lateinit var sNama: String
    lateinit var sEmail: String


    private lateinit var mFirebaseDatabase: DatabaseReference
    private lateinit var mFirebaseInstance: FirebaseDatabase


    private lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

//
        mFirebaseInstance = FirebaseDatabase.getInstance()

        mFirebaseDatabase = mFirebaseInstance.getReference("User")

        preferences = Preferences(this)

        iv_back.setOnClickListener {
            finish()
        }

        btn_sign_up.setOnClickListener{
            sUsername = edt_username.text.toString()
            sPassword = edt_password.text.toString()
            sNama = edt_name.text.toString()
            sEmail = edt_email.text.toString()

            if (sUsername.equals("")){
                edt_username.error = "Silahkan isi Username"
                edt_username.requestFocus()
            } else if (sPassword.equals("")){
                edt_password.error = "Silahkan isi Password"
                edt_password.requestFocus()
            } else if (sNama.equals("")){
                edt_name.error = "Silahkan isi Nama"
                edt_name.requestFocus()
            } else if (sEmail.equals("")){
                edt_email.error = "Silahkan isi Nomor"
                edt_email.requestFocus()
            } else {

                var statusUsername = sUsername.indexOf(".")
                if (statusUsername >= 0){
                    et_username.error = "Silahkan tulis Username anda tanpa ."
                    et_username.requestFocus()
                } else {
                    saveUser(sUsername, sPassword, sNama, sEmail)
                }
            }
        }
    }
    //
    private fun saveUser(sUsername: String, sPassword: String, sNama: String, sEmail: String){
        val user = User()
        user.username = sUsername
        user.password = sPassword
        user.nama = sNama
        user.email = sEmail

        if (sUsername != null){
            checkingUsername(sUsername, user)
        }
    }

    private fun checkingUsername(iUsername: String, data: User){
        mFirebaseDatabase.child(iUsername).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot){

                val user = dataSnapshot.getValue(User::class.java)
                if (user == null){
                    mFirebaseDatabase.child(iUsername).setValue(data)
                    preferences.setValues("nama", data.nama.toString())
                    preferences.setValues("user", data.username.toString())
                    preferences.setValues("email", data.email.toString())
                    preferences.setValues("password", data.password.toString())
                    preferences.setValues("status", "")


                    val intent = Intent(this@SignUpActivity,
                        SignUpConfirmationActivity::class.java).putExtra("data", data)
                    Log.v("hap", "Datanya yaitu $data")

                    startActivity(intent)
                } else {
                    Toast.makeText(this@SignUpActivity, "Username sudah terpakai", Toast.LENGTH_LONG).show()
                }
            }

            override fun onCancelled(error: DatabaseError){
                Toast.makeText(this@SignUpActivity, ""+error.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}