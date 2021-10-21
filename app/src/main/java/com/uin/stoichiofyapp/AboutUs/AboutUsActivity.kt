package com.uin.stoichiofyapp.AboutUs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.database.*
import com.uin.stoichiofyapp.R
import kotlinx.android.synthetic.main.activity_about_us.*

class AboutUsActivity : AppCompatActivity() {

    private lateinit var mDatabasePengarang: DatabaseReference
    private lateinit var mDatabasePengembang: DatabaseReference
    private lateinit var mDatabaseDosen: DatabaseReference
    private var dataDosen = ArrayList<getDosen>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us)


        mDatabasePengarang = FirebaseDatabase.getInstance().getReference("About")
            .child("Identitas Pengarang Buku")
        mDatabasePengembang = FirebaseDatabase.getInstance().getReference("About")
            .child("Identitas Pengembang")
        mDatabaseDosen = FirebaseDatabase.getInstance().getReference("About")
            .child("Dosen Pembimbing")

        btn_back.setOnClickListener {
            onBackPressed()
        }

        rvDosenPembimbing.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        getDataPengarang()
        getDataPengembang()
        getDataDosen()
    }

    private fun getDataPengarang() {
        mDatabasePengarang.get().addOnSuccessListener {
            if (it.exists()){
                val nama = it.child("nama").value
                val nomor_induk = it.child("nomor_induk").value
                val email = it.child("email").value
                val universitas = it.child("universitas").value
                val jurusan = it.child("jurusan").value
                val url = it.child("url").value

                tvNamaPengarang.text = nama.toString()
                tvNomorIndukPengarang.text = nomor_induk.toString()
                tvEmailPengarang.text = email.toString()
                tvJurusanPengarang.text = jurusan.toString()
                tvUniversitasPengarang.text = universitas.toString()
                Glide.with(this)
                    .load(url)
                    .apply(RequestOptions.circleCropTransform())
                    .into(ivPengarang)

            } else {
                Toast.makeText(this, "Data Pengarang Tidak Ada", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
    private fun getDataPengembang() {
        mDatabasePengembang.get().addOnSuccessListener {
            if (it.exists()){
                val nama = it.child("nama").value
                val nomor_induk = it.child("nomor_induk").value
                val email = it.child("email").value
                val universitas = it.child("universitas").value
                val jurusan = it.child("jurusan").value
                val url = it.child("url").value

                tvNamaPengembang.text = nama.toString()
                tvNomorIndukPengembang.text = nomor_induk.toString()
                tvEmailPengembang.text = email.toString()
                tvJurusanPengembang.text = jurusan.toString()
                tvUniversitasPengembang.text = universitas.toString()
                Glide.with(this)
                    .load(url)
                    .apply(RequestOptions.circleCropTransform())
                    .into(ivPengembang)

            } else {
                Toast.makeText(this, "Data Pengembang Tidak Ada", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
    private fun getDataDosen(){
        mDatabaseDosen.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                dataDosen.clear()
                for(dataSnapshot in snapshot.children){
                        val data = dataSnapshot.getValue(getDosen::class.java)
                        val nama = data?.nama
                        val nomor = data?.nomor_induk
                        val email = data?.email
                        val jurusan = data?.jurusan
                        val universitas = data?.universitas
                        val url = data?.url
                        dataDosen.add(setData(nama, nomor, email, jurusan, universitas, url))

                }

                rvDosenPembimbing.adapter = DosenAdapter(dataDosen)
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@AboutUsActivity, "" + error.message, Toast.LENGTH_SHORT).show()
            }

        })

    }

    private fun setData(nama: String?, nomor: String?, email: String?, jurusan: String?,universitas: String?, url: String?): getDosen {
        val data = getDosen(
            nama, nomor, email, jurusan, universitas, url
        )
        return data
    }


}