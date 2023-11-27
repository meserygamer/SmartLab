package com.example.myapplication

import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityPaymentBinding

class payment : AppCompatActivity() {

    private var _binding : ActivityPaymentBinding? = null;

    public val binding : ActivityPaymentBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        (binding.animation.background as AnimationDrawable).start()
    }
}