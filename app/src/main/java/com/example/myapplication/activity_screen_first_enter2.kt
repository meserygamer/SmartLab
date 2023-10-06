package com.example.myapplication

import ClassifiedSwipeClass
import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.core.view.GestureDetectorCompat
import com.example.myapplication.databinding.ActivityScreenFirstEnter1Binding
import com.example.myapplication.databinding.ActivityScreenFirstEnter2Binding

class activity_screen_first_enter2 : AppCompatActivity(), GestureDetector.OnGestureListener {

    private var gestureDetectorCompat: GestureDetectorCompat? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityScreenFirstEnter2Binding.inflate(layoutInflater)
        val View = binding.root
        setContentView(View)
        gestureDetectorCompat = GestureDetectorCompat(this, this)
        binding.textView.setOnClickListener{
            val intent = Intent(this@activity_screen_first_enter2, LoginWindow::class.java)
            startActivity(intent)
        }
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
                val intent = Intent(this@activity_screen_first_enter2, activity_screen_first_enter3::class.java)
                startActivity(intent)
            }
            SwipeVariant.SwipeRight ->{
                val intent = Intent(this@activity_screen_first_enter2, screen_first_enter::class.java)
                startActivity(intent)
            }
            else ->
            {

            }
        }
        return true
    }

    override fun onLongPress(p0: MotionEvent) {
    }

    override fun onFling(p0: MotionEvent, p1: MotionEvent, p2: Float, p3: Float): Boolean {
        return true
    }
}