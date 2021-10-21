package com.uin.stoichiofyapp.Profile

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.dhaval2404.imagepicker.ImagePicker
import com.github.dhaval2404.imagepicker.constant.ImageProvider
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import com.uin.stoichiofyapp.Home.HomeActivity
import com.uin.stoichiofyapp.R
import com.uin.stoichiofyapp.Sign.SignIn.User
import com.uin.stoichiofyapp.Utils.Preferences
import kotlinx.android.synthetic.main.activity_confirm.*
import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlinx.android.synthetic.main.activity_edit_profile.iv_profile
import kotlinx.android.synthetic.main.fragment_profile.*
import java.util.*
import kotlin.collections.HashMap


class EditProfileActivity : AppCompatActivity(), PermissionListener {

    var statusAdd: Boolean = false
    lateinit var filePath: Uri
    lateinit var user : User

    private lateinit var preferences: Preferences
    private lateinit var mDatabase : DatabaseReference

    lateinit var updateNama : String
    lateinit var updateEmail : String
    lateinit var updatePassword: String


    lateinit var storage: FirebaseStorage
    lateinit var storageReference: StorageReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        preferences = Preferences(applicationContext)

        val showNama = preferences.getValues("nama").toString()
        val showEmail = preferences.getValues("email").toString()
        val showPassword = preferences.getValues("password").toString()


        storage = FirebaseStorage.getInstance()
        storageReference = storage.getReference("User")

        et_fullname.setText(showNama)
        et_email.setText(showEmail)
        et_password.setText(showPassword)

        Glide.with(this)
            .load(preferences.getValues("url"))
            .apply(RequestOptions.circleCropTransform())
            .into(iv_profile)


        btn_update.setOnClickListener {
            updateNama = et_fullname.text.toString()
            updateEmail = et_email.text.toString()
            updatePassword = et_confirm.text.toString()



            // Data cannot Empty
            if (updateNama.equals("")){
                et_fullname.error = "Masukkan Nama"
                et_fullname.requestFocus()
            } else if (updateEmail.equals("")){
                et_email.error = "Masukkan Nomor"
                et_email.requestFocus()
            } else if (updatePassword.equals("")){
                et_confirm.error = "Masukkan Password"
                et_confirm.requestFocus()
            } else {

                val progressDialog = ProgressDialog(this)
                progressDialog.setTitle("Menambahkan Data...")
                progressDialog.show()

                mDatabase = FirebaseDatabase.getInstance().getReference("User")
                    .child(preferences.getValues("username")!!)

                val hashMap: HashMap<String, String> = HashMap()
                hashMap["nama"] = updateNama
                hashMap["email"] = updateEmail
                hashMap["password"] = updatePassword

                preferences.setValues("nama", updateNama)
                preferences.setValues("email", updateEmail)
                preferences.setValues("password", updatePassword)

                mDatabase.updateChildren(hashMap as Map<String, Any>)
                finishAffinity()
                val intent = Intent (this@EditProfileActivity,
                    HomeActivity::class.java).putExtra("user", intent.getStringExtra("user"))
                startActivity(intent)


            }
        }

        btn_add.setOnClickListener {
            if (statusAdd) {
                statusAdd = false

                iv_add.setImageResource(R.drawable.btn_add_photo)
            } else {
                Dexter.withActivity(this)
                    .withPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    .withListener(this)
                    .check()
            }
        }
    }


    override fun onPermissionGranted(response: PermissionGrantedResponse?) {
        // Use Gallery and Camera
        ImagePicker.with(this)
            .provider(ImageProvider.BOTH)
            .start()
    }

    override fun onPermissionDenied(response: PermissionDeniedResponse?) {
        Toast.makeText(this, "Anda tidak bisa menambahkan photo profile", Toast.LENGTH_LONG ).show()
    }

    override fun onPermissionRationaleShouldBeShown(
        permission: PermissionRequest?,
        token: PermissionToken?
    ) {
        //To change body of created functions use File | Settings | File Templates.
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            Activity.RESULT_OK -> {
                //Image Uri will not be null for RESULT_OK
                statusAdd = false
                filePath = data?.data!!

                Glide.with(this)
                    .load(filePath)
                    .apply(RequestOptions.circleCropTransform())
                    .into(iv_profile)

                val progressDialog = ProgressDialog(this)
                progressDialog.setTitle("Mengganti Profile...")
                progressDialog.show()

                val ref = storageReference.child("images/"+ UUID.randomUUID().toString())
                ref.putFile(filePath)
                    .addOnSuccessListener {
                        progressDialog.dismiss()
                        Toast.makeText(this@EditProfileActivity,"Uploaded",
                            Toast.LENGTH_SHORT).show()

                        mDatabase = FirebaseDatabase.getInstance().getReference("User")
                            .child(preferences.getValues("username")!!)

                        ref.downloadUrl.addOnSuccessListener {
                            val hashMap: HashMap<String, String> = HashMap()
                            hashMap["url"] = it.toString()
                            preferences.setValues("url", it.toString())
                            mDatabase.updateChildren(hashMap as Map<String, Any>)
                        }
                    }
                    .addOnFailureListener { e ->
                        progressDialog.dismiss()
                        Toast.makeText(this@EditProfileActivity,
                            "Failed"+ e.message, Toast.LENGTH_SHORT).show()
                    }
                    .addOnProgressListener { taskSnapshot ->
                        val progress = 100.0 * taskSnapshot.bytesTransferred / taskSnapshot
                            .totalByteCount
                        progressDialog.setMessage("Uploaded " + progress.toInt() + "%")
                    }
            }
            ImagePicker.RESULT_ERROR -> {
                Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
            }
            else -> {
                Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
            }
        }
    }
}