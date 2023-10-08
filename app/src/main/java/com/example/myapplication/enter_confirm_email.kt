package com.example.myapplication

import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityEnterConfirmEmailBinding
import com.example.myapplication.databinding.ActivityEnterConfirmEmailBinding.inflate

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
        if(_bind != null)
        {
            _bind!!.firstPinView.addTextChangedListener(this)
        }
        _timer.start()
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        Toast.makeText(this, "ввели", LENGTH_SHORT).show()
    }

    override fun afterTextChanged(p0: Editable?) {

    }


}