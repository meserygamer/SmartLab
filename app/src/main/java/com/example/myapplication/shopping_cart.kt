package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityShoppingCartBinding

class shopping_cart : AppCompatActivity() {

    private var _binding : ActivityShoppingCartBinding? = null;

    public val binding : ActivityShoppingCartBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityShoppingCartBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_shopping_cart)

    }
}