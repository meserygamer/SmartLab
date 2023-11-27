package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityOrderRegistrationBinding

class Order_Registration : AppCompatActivity() {

    private var _binding : ActivityOrderRegistrationBinding? = null

    public val binding : ActivityOrderRegistrationBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityOrderRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}