package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityCreatePassswordBinding

class CreatePassswordActivity : AppCompatActivity() {

    var binding : ActivityCreatePassswordBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreatePassswordBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
    }
}