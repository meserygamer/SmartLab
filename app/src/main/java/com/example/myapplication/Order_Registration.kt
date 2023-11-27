package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.myapplication.databinding.ActivityOrderRegistrationBinding

class Order_Registration : AppCompatActivity() {

    private var _binding : ActivityOrderRegistrationBinding? = null

    public val binding : ActivityOrderRegistrationBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityOrderRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.BackShoppingCartPageButton.setOnClickListener(object : View.OnClickListener{

            override fun onClick(p0: View?) {
                startActivity(Intent(this@Order_Registration, shopping_cart::class.java))
            }

        })
        binding.AdressTextView.setOnClickListener(object : View.OnClickListener{

            override fun onClick(p0: View?) {
                var showingFragment = AddressBottomSheetDialog()
                showingFragment.show(supportFragmentManager, "tag");
            }

        })

        binding.DateTimeTextView.setOnClickListener(object : View.OnClickListener{

            override fun onClick(p0: View?) {
                var showingFragment = ChoosingTheDeliveryDate()
                showingFragment.show(supportFragmentManager, "tag");
            }

        })

        binding.AddPatientButton.setOnClickListener(object : View.OnClickListener{

            override fun onClick(p0: View?) {
                var showingFragment = PatientSelection()
                showingFragment.show(supportFragmentManager, "tag");
            }

        })

        binding.RegisterButton.setOnClickListener(object : View.OnClickListener{

            override fun onClick(p0: View?) {
                startActivity(Intent(this@Order_Registration, payment::class.java))
            }

        })
    }
}