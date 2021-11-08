package com.uin.stoichiofyapp.Latihan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import com.uin.stoichiofyapp.Home.HomeActivity
import com.uin.stoichiofyapp.R
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        var mCorrectQuestions = intent.getIntExtra(Constants.CORRECT_ANWERS, 0)
        val result = mCorrectQuestions*10
        val calculate : Int = 100 * mCorrectQuestions/15
        Log.v("1522", mCorrectQuestions.toString())

        score.text = "Nilai $calculate"

        btn_back.setOnClickListener {
            startActivity(Intent(this,HomeActivity::class.java))
        }

    }
}