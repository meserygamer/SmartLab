package com.example.myapplication

import Common
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityLoginWindowBinding
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback
import java.io.IOException

class LoginWindow : AppCompatActivity(), TextWatcher {

    public var binding : ActivityLoginWindowBinding? = null;

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityLoginWindowBinding.inflate(layoutInflater);
        val View = binding!!.root
        setContentView(View)
        binding!!.editTextTextEmailAddress.addTextChangedListener(this)
        binding!!.appCompatButton.setOnClickListener{
            Common.retrofitService.SendCodeOnEmail(binding!!.editTextTextEmailAddress.text.toString())
                .enqueue(object : Callback<String> {

                override fun onFailure(call: Call<String>, t: Throwable) {

                    Toast.makeText(this@LoginWindow,  t.message, Toast.LENGTH_LONG).show()

                }

                override fun onResponse(call: Call<String>, response: Response<String>) {

                    if(response.code() == 200)
                    {
                        Common.User_mail = binding!!.editTextTextEmailAddress.text.toString()
                        var intent = Intent(this@LoginWindow, enter_confirm_email::class.java)
                        startActivity(intent)
                    }

                }
            })
        }
        OpenCodeBoard()

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

    public fun OpenCodeBoard(){
        binding!!.editTextTextEmailAddress.setFocusableInTouchMode(true)
        binding!!.editTextTextEmailAddress.requestFocus()
    }

}
