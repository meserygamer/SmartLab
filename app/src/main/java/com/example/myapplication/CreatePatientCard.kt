package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityCreatePatientCardBinding

class CreatePatientCard : AppCompatActivity() {

    var _binding : ActivityCreatePatientCardBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityCreatePatientCardBinding.inflate(layoutInflater)
        setContentView(_binding!!.root)
    }
}