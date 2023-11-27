package com.example.myapplication

import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.myapplication.databinding.ActivityPaymentBinding
import java.util.Timer
import java.util.TimerTask

class payment : AppCompatActivity() {

    private var _binding : ActivityPaymentBinding? = null;

    public val binding : ActivityPaymentBinding
        get() = _binding!!

    var timer : Timer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var animation : Animation = AnimationUtils.loadAnimation(this, R.anim.payment_animation)
        animation.repeatCount = Animation.INFINITE
        binding.animation.startAnimation(animation)
        timer = Timer();
        timer!!.schedule(object : TimerTask() {

            override fun run() {

                runOnUiThread(object : Runnable{

                    override fun run() {

                        binding.PaymentStatusTextView.text = "Производим оплату...";

                    }

                })
            }

        }, 3000)
        timer!!.schedule(object : TimerTask() {

            override fun run() {

                runOnUiThread(object : Runnable{

                    override fun run() {

                        finishPayment()

                    }

                })
            }

        }, 6000)
    }

    private fun finishPayment()
    {
        binding.PaymentAnimationLinearLayout.visibility = View.GONE;
        binding.SuccessfullPaymentLinearLayout.visibility = View.VISIBLE;
    }

}