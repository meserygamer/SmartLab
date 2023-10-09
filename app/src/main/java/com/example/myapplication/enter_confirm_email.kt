package com.example.myapplication

import Common
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityEnterConfirmEmailBinding
import com.example.myapplication.databinding.ActivityEnterConfirmEmailBinding.inflate
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Objects

class enter_confirm_email : AppCompatActivity(), TextWatcher {

    private var _bind : ActivityEnterConfirmEmailBinding? = null
    val _timer = object : CountDownTimer(60000, 1000) {

        override fun onTick(millisUntilFinished: Long) {
            _bind!!.Timer.text = "Отправить код можно\n через " + millisUntilFinished / 1000 + " секунд"
        }

        override fun onFinish() {
            _bind!!.Timer.text = "done!"
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _bind = inflate(layoutInflater)
        setContentView(_bind!!.root)
        _bind!!.MoveOnPreviousPageButton.setOnClickListener {
            var intent = Intent(this@enter_confirm_email, LoginWindow::class.java)
            startActivity(intent)
        }
        if(_bind != null)
        {
            _bind!!.firstPinView.addTextChangedListener(this)
        }
        _timer.start()
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        if(_bind != null)
        {
            if(_bind!!.firstPinView.text.toString().length == 4)
            {
                Common.retrofitService.SendEnterCodeOnServer(Common.User_mail, _bind!!.firstPinView.text.toString()).enqueue(object : Callback<String> {
                    override fun onResponse(call: Call<String>, response: Response<String>) {
                        if(response.code() == 200)
                        {
                            if(response.body() != null)
                            {
                                Common.Bearer = response.body()!!
                                Toast.makeText(this@enter_confirm_email, "Успешный вход", LENGTH_SHORT).show()
                            }
                        }
                        Toast.makeText(this@enter_confirm_email, "Код не верен", LENGTH_SHORT).show()
                        _bind!!.firstPinView.setText("")
                    }

                    override fun onFailure(call: Call<String>, t: Throwable) {
                        Toast.makeText(this@enter_confirm_email, "Что-то пошло не так", LENGTH_SHORT).show()
                    }

                })
            }

        }
    }

    override fun afterTextChanged(p0: Editable?) {

    }


}