package com.example.mycart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button5 = findViewById(R.id.button5) as Button
        val button4 = findViewById(R.id.button4) as Button
        val button7 = findViewById(R.id.button7) as Button
        val button8 = findViewById(R.id.button8) as Button
        button5.setOnClickListener {
            startActivity(Intent(this@MainActivity, MapsActivity::class.java))
        }
        button7.setOnClickListener {
            startActivity(Intent(this@MainActivity, BuyActivity::class.java))
        }

        button8.setOnClickListener {
            startActivity(Intent(this@MainActivity, SellActivity::class.java))
        }

        button4.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this@MainActivity, SigninActivity::class.java))
            finish()
            }
        }


    }

