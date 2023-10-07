package com.example.myapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import com.example.myapplication.databinding.ActivityLoginWindowBinding

class LoginWindow : AppCompatActivity(), TextWatcher {

    public var binding : ActivityLoginWindowBinding? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginWindowBinding.inflate(layoutInflater);
        val View = binding!!.root
        setContentView(View)
        binding!!.editTextTextEmailAddress.addTextChangedListener(this)
        binding!!.appCompatButton.setOnClickListener{
            val intent = Intent(this@LoginWindow, EnterCodeFromEmail::class.java)
            startActivity(intent)
        }
    }

    public fun validateEmail()
    {
        if(binding != null)
        {
            binding!!.appCompatButton.isEnabled = Regex(
                "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$")
                .containsMatchIn(binding!!.editTextTextEmailAddress.text)
        }
    }

    override fun beforeTextChanged(s: CharSequence?
                                   , start: Int
                                   , count: Int
                                   , after: Int) {
    }

    override fun onTextChanged(s: CharSequence?
                               , start: Int
                               , before: Int
                               , count: Int) {
        validateEmail();
    }

    override fun afterTextChanged(s: Editable?) {
    }
}