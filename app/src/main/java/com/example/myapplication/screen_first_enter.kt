package com.example.myapplication

import ClassifiedSwipeClass
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.GestureDetector
import android.view.GestureDetector.OnGestureListener
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GestureDetectorCompat
import com.example.myapplication.databinding.ActivityLoginWindowBinding
import com.example.myapplication.databinding.ActivityScreenFirstEnter1Binding

class screen_first_enter : AppCompatActivity(), GestureDetector.OnGestureListener {
    private var gestureDetectorCompat: GestureDetectorCompat? = null

    private lateinit var prefs : SharedPreferences;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityScreenFirstEnter1Binding.inflate(layoutInflater)
        val View = binding.root
        setContentView(View)
        gestureDetectorCompat = GestureDetectorCompat(this, this)
        binding.textView.setOnClickListener{
            prefs = getSharedPreferences("settings", Context.MODE_PRIVATE);
            val editor : SharedPreferences.Editor = prefs.edit()
            editor.putBoolean("FirstLookWasPassed", true).apply();
            val intent = Intent(this@screen_first_enter, LoginWindow::class.java)
            startActivity(intent)
        }
    }

    fun onClick()
    {
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if(event != null)
        {
            gestureDetectorCompat!!.onTouchEvent(event)
        }
        return super.onTouchEvent(event)
    }


    override fun onDown(p0: MotionEvent): Boolean {
        return true
    }

    override fun onShowPress(p0: MotionEvent) {
    }

    override fun onSingleTapUp(p0: MotionEvent): Boolean {
        return true
    }

    override fun onScroll(p0: MotionEvent, p1: MotionEvent, p2: Float, p3: Float): Boolean {
        when (ClassifiedSwipeClass(p0, p1, p2, p3).defineSwipe(150))
        {
            SwipeVariant.SwipeLeft -> {
                val intent = Intent(this@screen_first_enter, activity_screen_first_enter2::class.java)
                startActivity(intent)
            }
            else ->
            {

            }
        }
        return true
    }

    override fun onLongPress(p0: MotionEvent)
    {
    }

    override fun onFling(p0: MotionEvent, p1: MotionEvent, p2: Float, p3: Float): Boolean {
        return true
    }
}