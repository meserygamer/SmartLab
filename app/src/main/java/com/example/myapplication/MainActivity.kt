package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class MainActivity : AppCompatActivity() {

    private lateinit var prefs : SharedPreferences;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        prefs = getSharedPreferences("settings", Context.MODE_PRIVATE);
        if(prefs.contains("FirstLookWasPassed"))
        {
            Handler().postDelayed({
                val intent = Intent(this@MainActivity, LoginWindow::class.java)
                startActivity(intent)
            }, 3000)
        }
        else
        {
            Handler().postDelayed({
                val intent = Intent(this@MainActivity, screen_first_enter::class.java)
                startActivity(intent)
            }, 3000)
        }
    }
}